package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Rubrica;

public class CadastrarRubrica extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setAttribute("page", "criarRubrica");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Rubrica rubrica = new Rubrica(req.getParameter("nome"), Float.parseFloat(req.getParameter("valor")));
		
		RubricaDAO dao = (RubricaDAO) this.getServletContext().getAttribute("dao");
		dao.create(rubrica);
	}
}
