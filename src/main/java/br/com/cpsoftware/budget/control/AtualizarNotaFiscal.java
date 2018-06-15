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

import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Rubrica;

public class AtualizarNotaFiscal extends HttpServlet {
	
	private NotaFiscalDAO notaFiscalDao = new NotaFiscalDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long notaFiscalId = Long.parseLong(req.getParameter("notaFiscalId"));
		
		req.setAttribute("notaFiscal", new NotaFiscalDAO().read(notaFiscalId));
		req.setAttribute("page", "atualizarNotaFiscal");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long notaFiscalId = Long.parseLong(req.getParameter("notaFiscalId"));
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		Long itemId = null;
		String fornecedor = null;
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
						if (item.getFieldName().equals(NotaFiscal.ITEM_ID))
							itemId = new Long(Streams.asString(stream));
						if (item.getFieldName().equals(NotaFiscal.FORNECEDOR))
							fornecedor = new String(Streams.asString(stream));
						if (item.getFieldName().equals(NotaFiscal.VALOR))
							valor = new Double(Streams.asString(stream));
						if (item.getFieldName().equals(NotaFiscal.DATA))
							data = new SimpleDateFormat("yyyy-MM-dd").parse(Streams.asString(stream));
					} else {
						if (item.getFieldName().startsWith(NotaFiscal.ARQUIVO)){
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
		
		NotaFiscal notaFiscal = (NotaFiscal) this.notaFiscalDao.read(notaFiscalId);
		notaFiscal.setItemId(itemId);
		notaFiscal.setFornecedor(fornecedor);
		notaFiscal.setValor(valor);
		notaFiscal.setData(data);
		
		if(arquivo.getBytes().length > 0) {
			notaFiscal.setArquivo(arquivo);
		}
		
		this.notaFiscalDao.update(notaFiscal);
		
		// resp.sendRedirect("/visualizarNotaFiscal?itemId=" + notaFiscal.getItemId());
		
	}
}
