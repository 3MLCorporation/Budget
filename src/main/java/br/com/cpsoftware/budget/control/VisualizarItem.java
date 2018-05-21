package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.model.Item;

@SuppressWarnings("serial")
public class VisualizarItem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		
		Item item = new ItemDAO().read(itemId);
		
		req.setAttribute("item", item);
		req.setAttribute("page", "visualizarItem");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
