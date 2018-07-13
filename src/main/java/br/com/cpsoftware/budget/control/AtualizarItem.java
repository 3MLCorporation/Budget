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
		String nome = null;
		String descricao = null;
		Double valorUniforme = null;
		int quantidade = 0;
		int unidadeMedida = 0;
		Blob arquivoDetalhes = null;
		Blob arquivoAuxiliar = null;
		
		if(isMultipart) {
			
			ServletFileUpload upload = new ServletFileUpload();
			
			try {
				FileItemIterator iterator = upload.getItemIterator(req);
				while (iterator.hasNext()) {
					FileItemStream item = iterator.next();
					InputStream stream = item.openStream();

					if (item.isFormField()) {
						if (item.getFieldName().equals(Item.RUBRICA_ID))
							rubricaId = new Long(Streams.asString(stream));
						if (item.getFieldName().equals(Item.ID))
							itemId = new Long(Streams.asString(stream));
						if (item.getFieldName().equals(Item.NOME))
							nome = new String(Streams.asString(stream));
						if (item.getFieldName().equals(Item.DESCRICAO))
							descricao = new String(Streams.asString(stream));
						if (item.getFieldName().equals(Item.PRECO_UNITARIO))
							valorUniforme = new Double(Streams.asString(stream));
						if (item.getFieldName().equals(Item.QUANTIDADE))
							quantidade = new Integer(Streams.asString(stream));
						if (item.getFieldName().equals(Item.UNIDADE_MEDIDA))
							unidadeMedida = new Integer(Streams.asString(stream));
					} else {
						if (item.getFieldName().startsWith(Item.ARQUIVO_DETALHES)){
							arquivoDetalhes = new Blob(IOUtils.toByteArray(stream));
						}
						if (item.getFieldName().startsWith(Item.ARQUIVO_AUXILIAR)){
							arquivoAuxiliar = new Blob(IOUtils.toByteArray(stream));
						}
					}
				}
			}catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		
		
		Item item = this.itemDao.read(itemId);
		item.setRubricaId(rubricaId);
		item.setNome(nome);
		item.setDescricao(descricao);
		item.setPrecoUnitario(valorUniforme);
		item.setQuantidade(quantidade);
		item.setUnidadeMedida(unidadeMedida);
		
		if(arquivoDetalhes.getBytes().length > 0) {
			item.setArquivoDetalhes(arquivoDetalhes);
		}
		if(arquivoAuxiliar.getBytes().length > 0) {
			item.setArquivoAuxiliar(arquivoAuxiliar);
		}
		
		AtualizarValoresOrcados.atualizarPrecoRubrica(
			AtualizarValoresOrcados.EXCLUIR,
			itemId,
			item.getValor()
		);
		
		//Atualizo a rubrica anterior, excluindo o valor parcial do item
		AtualizarValoresRealizados.atualizarPrecoRubrica(
			AtualizarValoresRealizados.EXCLUIR,
			itemId,
			item.getValorRealizado()
		);
		
		this.itemDao.update(item);
		
		AtualizarValoresOrcados.atualizarPrecoRubrica(
			AtualizarValoresOrcados.CADASTRAR,
			itemId,
			item.getValor()
		);
		
		//Atualizo a nova rubrica, adicionando o valor parcial do item
		AtualizarValoresRealizados.atualizarPrecoRubrica(
			AtualizarValoresRealizados.EDITAR,
			itemId,
			item.getValorRealizado()
		);
		
		resp.sendRedirect("/listarItens");
		
	}
	
}
