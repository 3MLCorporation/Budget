package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.model.Pagamento;
import br.com.cpsoftware.budget.util.AtualizarValoresComprovados;

@SuppressWarnings("serial")
public class CadastrarPagamento extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("nota_fiscal_id", req.getParameter("nota_fiscal_id"));
		req.setAttribute("page", "cadastrarPagamento");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Long notaFiscalId = null;
		int tipo = 0;
		Double valor = null;
		Date data = null;
		Blob arquivo = null;
		
		
		if(isMultipart) {
			
			ServletFileUpload upload = new ServletFileUpload();
			
			try {
				FileItemIterator iterator = upload.getItemIterator(req);
				while (iterator.hasNext()) {
					FileItemStream item = iterator.next();
					InputStream stream = item.openStream();

					if (item.isFormField()) {
						if (item.getFieldName().equals(Pagamento.NOTA_FISCAL_ID))
							notaFiscalId = new Long(Streams.asString(stream));
						if (item.getFieldName().equals(Pagamento.TIPO))
							tipo = new Integer(Streams.asString(stream));
						if (item.getFieldName().equals(Pagamento.VALOR))
							valor = new Double(Streams.asString(stream));
						if (item.getFieldName().equals(Pagamento.DATA))
							data = new SimpleDateFormat("yyyy-MM-dd").parse(Streams.asString(stream));
					} else {
						if (item.getFieldName().startsWith(Pagamento.ARQUIVO)){
							arquivo = new Blob(IOUtils.toByteArray(stream));
						}
					}
				}
			}catch (FileUploadException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if (arquivo != null && arquivo.getBytes().length > 0){
				Pagamento pagamento = new Pagamento(notaFiscalId, arquivo, tipo, valor, data);
				PagamentoDAO  pagamentoDao = new PagamentoDAO();
				Long pagamentoId = pagamentoDao.create(pagamento);
				
				//TODO DISCUTIR: atualizarPrecoPagamento ou atualizarPrecoNotaFiscal??
				AtualizarValoresComprovados.atualizarPrecoPagamento(
					AtualizarValoresComprovados.CADASTRAR,
					pagamentoId,
					pagamento.getValor()
				);
				
				/*NotaFiscal nota = new NotaFiscalDAO().read(notaFiscalId);
				nota.setValorParcial(nota.getValorParcial() + valor);
				Double valorParcial = nota.calcularValorParcial(pagamentoDao.getPagamentos(notaFiscalId));
				nota.verificarStatus();
				new NotaFiscalDAO().update(nota);
				
				//TODO DIVIDIR EM FUNÇÔES ISSO AQUI
				Item item = new ItemDAO().read(nota.getItemId());
				
				item.setValorParcial(nota.getValorParcial());
				RubricaDAO rubricaDAO = new RubricaDAO();
				Rubrica rubrica = (Rubrica) rubricaDAO.read(item.getRubricaId());
				rubrica.setValorParcial(rubrica.getValorParcial() + pagamento.getValor());
				rubricaDAO.update(rubrica);
				
				
				CategoriaDAO categoriaDAO = new CategoriaDAO();
				Categoria categoria = (Categoria) categoriaDAO.read(( (Rubrica)rubrica ).getCategoriaId());
				categoria.setValorParcial(categoria.getValorParcial() + pagamento.getValor());
				categoriaDAO.update(categoria);
				
				OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
				Orcamento orcamento = (Orcamento) orcamentoDAO.read(( (Categoria) categoria ).getOrcamentoId());
				orcamento.setValorParcial(orcamento.getValorParcial() + pagamento.getValor());
				orcamentoDAO.update(orcamento);
				
				ProjetoDAO projetoDAO = new ProjetoDAO();
				Projeto projeto = (Projeto) projetoDAO.read(( (Orcamento) orcamento).getProjetoId());
				projeto.setValorParcial(projeto.getValorParcial() + pagamento.getValor());
				projetoDAO.update(projeto);*/
			}
			
			req.getSession().setAttribute("notaId", notaFiscalId);
			resp.sendRedirect("/visualizarNotaFiscal");
		}
	}
	
}
