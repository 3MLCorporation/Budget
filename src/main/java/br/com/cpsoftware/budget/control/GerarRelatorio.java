package br.com.cpsoftware.budget.control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.util.Relatorio;

import java.io.IOException;

@SuppressWarnings("serial")
public class GerarRelatorio extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/pdf");
		Relatorio.generatePDF(Long.parseLong(req.getParameter("projetoId")), resp.getOutputStream());
	}
	
}