package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().setAttribute("usuario", null);
		req.getSession().setAttribute("orcamentoEditavel", null);
		req.getSession().setAttribute("projetoEditavel", null);
		
		//req.getRequestDispatcher("/WEB-INF/loginUsuario.jsp").forward(req, resp);
		resp.sendRedirect("/login");
	}
	
}
