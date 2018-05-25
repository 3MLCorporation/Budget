package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Pagamento;

@SuppressWarnings("serial")
public class VisualizarNotaFiscal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long notaId = (Long) req.getSession().getAttribute("notaId");
		NotaFiscal nota = null;
		
		if(notaId == null) {
			Long itemId = Long.parseLong(req.getParameter("itemId"));
			nota = new NotaFiscalDAO().getNotaFiscal(itemId);
		}else {	//Depois que cadastra
			nota = new NotaFiscalDAO().read(notaId);
			req.getSession().setAttribute("notaId", null);
		}
		
		if(nota == null) {
			Long itemId = Long.parseLong(req.getParameter("itemId"));
			req.setAttribute("itemId", itemId);
		}else {
			List<Pagamento> pagamentos = new PagamentoDAO().getPagamentos(nota.getId());
			
			nota.calcularValorParcial(pagamentos);
			
			req.setAttribute("nota", nota);
			req.setAttribute("pagamentos", pagamentos);
		}
		
		req.setAttribute("page", "visualizarNotaFiscal");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
