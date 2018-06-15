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

import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Pagamento;

public class AtualizarPagamento extends HttpServlet {
	
	private PagamentoDAO pagamentoDao = new PagamentoDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long pagamentoId = Long.parseLong(req.getParameter("pagamentoId"));
		
		req.setAttribute("pagamento", new NotaFiscalDAO().read(pagamentoId));
		req.setAttribute("page", "atualizarPagamento");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long pagamentoId = Long.parseLong(req.getParameter("pagamentoId"));
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Long notaFiscalId = null;
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
							pagamentoId = new Long(Streams.asString(stream));
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
		}
		
		Pagamento pagamento = (Pagamento) this.pagamentoDao.read(pagamentoId);
		pagamento.setNotaFiscalId(notaFiscalId);
		pagamento.setValor(valor);
		pagamento.setData(data);
		
		if(arquivo.getBytes().length > 0) {
			pagamento.setArquivo(arquivo);
		}
		
		this.pagamentoDao.update(pagamento);
		
		// resp.sendRedirect("/visualizarNotaFiscal?itemId=" + pagamento.getNotaFiscalId());
		
	}
}
