package br.com.cpsoftware.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastrarCategoria extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setAttribute("page", "criarCategoria");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
}
