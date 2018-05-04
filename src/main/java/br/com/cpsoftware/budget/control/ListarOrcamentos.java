package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;

@SuppressWarnings("serial")
public class ListarOrcamentos extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long projetoId = Long.parseLong((String) req.getSession().getAttribute("projetoEditavel"));
		
		req.setAttribute("orcamentos", new OrcamentoDAO().getOrcamentos(projetoId));
		
		req.setAttribute("page", "listarOrcamentos");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
}
