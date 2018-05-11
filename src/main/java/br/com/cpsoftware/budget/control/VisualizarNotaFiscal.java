package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.model.NotaFiscal;

@SuppressWarnings("serial")
public class VisualizarNotaFiscal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long notaId = (Long) req.getSession().getAttribute("notaId");
		NotaFiscal nota = null;
		
		if(notaId == null) {
			Long itemId = Long.parseLong(req.getParameter("item_id"));
			nota = new NotaFiscalDAO().getNotaFiscal(itemId);
		}else {
			nota = new NotaFiscalDAO().read(notaId);
			req.getSession().setAttribute("notaId", null);
		}
		
		req.setAttribute("nota", nota);
		req.setAttribute("pagamentos", new PagamentoDAO().getPagamentos(nota.getId()));
		req.setAttribute("page", "visualizarNotaFiscal");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
