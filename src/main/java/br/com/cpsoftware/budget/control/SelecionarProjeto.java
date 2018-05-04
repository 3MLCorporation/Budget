package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SelecionarProjeto extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute("projetoEditavel", req.getParameter("projetoEditavel"));
		req.getSession().setAttribute("orcamentoEditavel", null);
		System.out.println("SelecionarProjeto/doPost -- projetoEditavel : " + req.getParameter("projetoEditavel"));
		resp.sendRedirect("/listarOrcamentos");
	}
	
}
