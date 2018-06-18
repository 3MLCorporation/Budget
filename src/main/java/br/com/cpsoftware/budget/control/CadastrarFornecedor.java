package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.model.Fornecedor;

@SuppressWarnings("serial")
public class CadastrarFornecedor extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("page", "cadastrarFornecedor");
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nome = req.getParameter("nome");
		String cnpj = req.getParameter("cnpj");
		String uf = req.getParameter("uf");
		
		Fornecedor fornecedor = new Fornecedor(nome, cnpj, uf);
		
		new FornecedorDAO().create(fornecedor);
		
		resp.sendRedirect("/principal");
		
	}
	
}
