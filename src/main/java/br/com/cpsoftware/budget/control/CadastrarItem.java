package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.model.Item;

public class CadastrarItem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setAttribute("page", "criarItens");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Item item = new Item(req.getParameter("nome"), req.getParameter("descricao"),
							Double.parseDouble(req.getParameter("valor")), Long.parseLong("quantidade"));
		
		ItemDAO dao = (ItemDAO) this.getServletContext().getAttribute("dao");
		dao.create(item);
	}
}
