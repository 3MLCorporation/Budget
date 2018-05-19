package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.model.Item;

@SuppressWarnings("serial")
public class AbrirArquivoAuxiliarItem extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long itemId = Long.parseLong(req.getParameter("itemId"));
		
		Item item = new ItemDAO().read(itemId);
		resp.setContentType("application/pdf");
		resp.getOutputStream().write(item.getArquivoAuxiliar().getBytes());;
		
		/*
		 * TODO Validações
		 */
	}
	
}
