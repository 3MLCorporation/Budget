package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SelecionarOrcamento extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("orcamentoEditavel", req.getParameter("orcamentoEditavel"));
		System.out.println("SelecionarOrcamento/doPost -- orcamentoEditavel : " + req.getParameter("orcamentoEditavel"));
		resp.sendRedirect("/listarCategorias");
		
	}
	
}
