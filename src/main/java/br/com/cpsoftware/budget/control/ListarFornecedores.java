package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.FornecedorDAO;

@SuppressWarnings("serial")
public class ListarFornecedores extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("fornecedores", new FornecedorDAO().getFornecedores());
		req.setAttribute("page", "listarFornecedores");
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
