package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Rubrica;

public class CadastrarRubrica extends HttpServlet {

	private RubricaDAO dao = new RubricaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setAttribute("page", "criarRubrica");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Rubrica rubrica = new Rubrica(req.getParameter("nome"), Double.parseDouble(req.getParameter("valor")));
		
		dao.create(rubrica);
		
		req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
}
