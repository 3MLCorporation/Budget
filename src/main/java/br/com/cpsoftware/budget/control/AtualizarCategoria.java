package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;

@SuppressWarnings("serial")
public class AtualizarCategoria extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		
		req.setAttribute("categoria", new CategoriaDAO().read(categoriaId));
		req.setAttribute("page", "atualizarCategoria");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);	
	}
	
}
