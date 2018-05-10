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
import br.com.cpsoftware.budget.model.NotaFiscal;

@SuppressWarnings("serial")
public class CadastrarNotaFiscal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("itemId", req.getParameter("itemId"));
		req.setAttribute("page", "adicionarNotaFiscal");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
			
			if (arquivo != null && arquivo.getBytes().length > 0){
				NotaFiscal notaFiscal = new NotaFiscal(itemId, arquivo, fornecedor, valor, data, NotaFiscal.STATUS_PARCIAL);
				NotaFiscalDAO  dao = new NotaFiscalDAO();
				dao.create(notaFiscal);
			}
			 
			resp.sendRedirect("/visualizarNotaFiscal");
		}
		
	}
	
}
