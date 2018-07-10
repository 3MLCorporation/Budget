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
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;

@SuppressWarnings("serial")
public class CadastrarItem extends HttpServlet {

	private ItemDAO dao = new ItemDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoEditavelId);
		
		List<Categoria> categorias = new ArrayList<>();
		
		for(Categoria categoria : new CategoriaDAO().getCategorias(orcamento.getId())) {
			categorias.add(categoria);
		}
		
		req.setAttribute("page", "criarItens");
	    req.setAttribute("categorias", categorias);
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Long rubricaId = null;
		String nome = null;
		String descricao = null;
		Double precoUnitario = null;
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
						if (item.getFieldName().equals(Item.NOME))
							nome = new String(Streams.asString(stream));
						if (item.getFieldName().equals(Item.DESCRICAO))
							descricao = new String(Streams.asString(stream));
						if (item.getFieldName().equals(Item.PRECO_UNITARIO))
							precoUnitario = new Double(Streams.asString(stream));
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
			
			Item item = new Item(rubricaId, nome, descricao, precoUnitario, quantidade, 0d, 0d, unidadeMedida, arquivoDetalhes, arquivoAuxiliar);
			this.dao.create(item);
			
			resp.sendRedirect("/listarItens");
		}
	}
}
