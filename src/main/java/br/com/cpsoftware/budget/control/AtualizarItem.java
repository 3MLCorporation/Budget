package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;
import br.com.cpsoftware.budget.util.AtualizarValoresComprovados;
import br.com.cpsoftware.budget.util.AtualizarValoresEstimados;
import br.com.cpsoftware.budget.util.AtualizarValoresOrcados;
import br.com.cpsoftware.budget.util.AtualizarValoresRealizados;

@SuppressWarnings("serial")
public class AtualizarItem extends HttpServlet {
	
	private ItemDAO itemDao = new ItemDAO();
	private CategoriaDAO categoriaDao = new CategoriaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoEditavelId);
		
		List<Categoria> categorias = new ArrayList<>();
		List<Rubrica> rubricas = new ArrayList<>();
		
		for(Categoria categoria : this.categoriaDao.getCategorias(orcamento.getId())) {
			categorias.add(categoria);
		}
		
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		Item item = new ItemDAO().read(itemId);
		
		Rubrica rubricaAtual = (Rubrica)new RubricaDAO().read(item.getRubricaId());
		Categoria categoriaAtual = (Categoria) this.categoriaDao.read((rubricaAtual.getCategoriaId()));
		rubricas.addAll(new RubricaDAO().getRubricas(categoriaAtual.getId()));
		
		req.setAttribute("orcamentoSelecionado", orcamento.getNome());
	    req.setAttribute("categorias", categorias);
	    req.setAttribute("categoriaAtualId", rubricaAtual.getCategoriaId());
	    req.setAttribute("rubricas", rubricas);
	    req.setAttribute("rubricaAtualId", rubricaAtual.getId());
		req.setAttribute("item", item);
		req.setAttribute("page", "atualizarItem");
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Long itemId = null;
		Long rubricaId = null;
		int codigo = 0;
		String nome = null;
		String descricao = null;
		Double precoUnitario = null;
		int quantidade = 0;
		Double valorEstimado = 0d;
		int unidadeMedida = 0;
		Blob arquivoDetalhes = null;
		Blob arquivoAuxiliar = null;
		
		
		if(isMultipart) {
			
			ServletFileUpload upload = new ServletFileUpload();
			
			try {
				FileItemIterator iterator = upload.getItemIterator(req);
				while (iterator.hasNext()) {
					FileItemStream itemFile = iterator.next();
					InputStream stream = itemFile.openStream();

					if (itemFile.isFormField()) {
						if (itemFile.getFieldName().equals(Item.RUBRICA_ID))
							rubricaId = new Long(Streams.asString(stream));
						if (itemFile.getFieldName().equals(Item.CODIGO)) {
							String codigoStr = Streams.asString(stream);
							codigo = codigoStr.isEmpty() ?  0 : new Integer(codigoStr);
						}
						if (itemFile.getFieldName().equals(Item.ID))
							itemId = new Long(Streams.asString(stream));
						if (itemFile.getFieldName().equals(Item.NOME))
							nome = new String(Streams.asString(stream));
						if (itemFile.getFieldName().equals(Item.DESCRICAO))
							descricao = new String(Streams.asString(stream));
						if (itemFile.getFieldName().equals(Item.VALOR_ESTIMADO)) {
							String valorEstimadoStr = Streams.asString(stream);
							valorEstimado = valorEstimadoStr.isEmpty() ? 0d : new Double(valorEstimadoStr);
						}
						if (itemFile.getFieldName().equals(Item.PRECO_UNITARIO)) {
							String precoUnitarioStr = Streams.asString(stream);
							precoUnitario = precoUnitarioStr.isEmpty() ? 0d : new Double(precoUnitarioStr);
						}
						if (itemFile.getFieldName().equals(Item.QUANTIDADE)) {
							String quantidadeStr = Streams.asString(stream);
							quantidade = quantidadeStr.isEmpty() ? 0 : new Integer(quantidadeStr);
						}
						if (itemFile.getFieldName().equals(Item.UNIDADE_MEDIDA))
							unidadeMedida = new Integer(Streams.asString(stream));
					} else {
						if (itemFile.getFieldName().startsWith(Item.ARQUIVO_DETALHES)){
							arquivoDetalhes = new Blob(IOUtils.toByteArray(stream));
						}
						if (itemFile.getFieldName().startsWith(Item.ARQUIVO_AUXILIAR)){
							arquivoAuxiliar = new Blob(IOUtils.toByteArray(stream));
						}
					}
				}
			}catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		
		Item item = this.itemDao.read(itemId);
		
		/*
		 * Exclui da rubrica anterior o valor do item, antes que seja alterado logo abaixo
		 * Aqui é diferente dos valores realizados ou comprovado, 
		 * pois há a possibilidade de mover um item para outra rubrica,
		 *  coisa que não existe na NotaFiscal e Pagamento
		 * */
		AtualizarValoresOrcados.atualizarPrecoRubrica(
			AtualizarValoresOrcados.EXCLUIR,
			item.getRubricaId(),
			item.getValor()
		);
			
		Long rubricaAnterior = item.getRubricaId();
		
		item.setRubricaId(rubricaId);
		item.setCodigo(codigo);
		item.setNome(nome);
		item.setDescricao(descricao);
		item.setValorEstimado(valorEstimado);
		item.setPrecoUnitario(precoUnitario);
		item.setQuantidade(quantidade);
		item.setUnidadeMedida(unidadeMedida);
		
		if(arquivoDetalhes.getBytes().length > 0) {
			item.setArquivoDetalhes(arquivoDetalhes);
		}
		
		if(arquivoAuxiliar.getBytes().length > 0) {
			item.setArquivoAuxiliar(arquivoAuxiliar);
		}
		
        if(!rubricaAnterior.equals(item.getRubricaId())) {
            //Atualizo a rubrica anterior, excluindo o valor parcial do item
            AtualizarValoresRealizados.atualizarPrecoRubrica(
                AtualizarValoresRealizados.EXCLUIR,
                rubricaAnterior,
                item.getValorRealizado()
            );
            
            AtualizarValoresComprovados.atualizarPrecoRubrica(
        		AtualizarValoresComprovados.EXCLUIR,
        		rubricaAnterior,
        		item.getValorComprovado()
    		);
        }
		
		this.itemDao.update(item);
		
		if(!rubricaAnterior.equals(item.getRubricaId())) {
			//Atualizar os valores estimados, se o Item for movido para outra Rubrica
			AtualizarValoresEstimados.atualizarValorEstimadoRubrica(rubricaAnterior);
			AtualizarValoresEstimados.atualizarValorEstimadoRubrica(item.getRubricaId());
			
			//Atualizo a nova rubrica, adicionando o valor parcial do item
			AtualizarValoresRealizados.atualizarPrecoRubrica(
				AtualizarValoresRealizados.EDITAR,
				item.getRubricaId(),
				item.getValorRealizado()
			);
			
			AtualizarValoresComprovados.atualizarPrecoRubrica(
        		AtualizarValoresComprovados.EDITAR,
        		item.getRubricaId(),
        		item.getValorComprovado()
    		);
			
		} else {
			AtualizarValoresEstimados.atualizarValorEstimadoRubrica(item.getRubricaId());
		}
		
		AtualizarValoresOrcados.atualizarPrecoRubrica(
			AtualizarValoresOrcados.EDITAR,
			item.getRubricaId(),
			item.getValor()
		);
		
		
		
		resp.sendRedirect("/listarItens");
		
	}
	
}
