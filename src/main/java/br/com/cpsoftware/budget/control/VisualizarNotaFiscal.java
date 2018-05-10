package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.NotaFiscalDAO;

@SuppressWarnings("serial")
public class VisualizarNotaFiscal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long notaId = (Long) req.getSession().getAttribute("notaId");
		
		if(notaId == null) {
			Long itemId = Long.parseLong(req.getParameter("item_id"));
			req.setAttribute("nota", new NotaFiscalDAO().getNotaFiscal(itemId));
		}else {
			req.setAttribute("nota", new NotaFiscalDAO().read(notaId));
			req.getSession().setAttribute("notaId", null);
		}
		
		req.setAttribute("page", "visualizarNotaFiscal");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
