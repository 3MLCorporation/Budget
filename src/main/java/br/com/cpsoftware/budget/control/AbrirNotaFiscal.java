package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.model.NotaFiscal;

@SuppressWarnings("serial")
public class AbrirNotaFiscal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long notaId = Long.parseLong(req.getParameter("notaId"));
		
		NotaFiscal notaFiscal = new NotaFiscalDAO().read(notaId);
		resp.setContentType("application/pdf");
		resp.getOutputStream().write(notaFiscal.getArquivo().getBytes());;
		
		/*
		 * TODO Validações
		 */
	}
	
}
