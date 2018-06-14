package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Rubrica;

@SuppressWarnings("serial")
public class AtualizarRubrica extends HttpServlet {
	
	private RubricaDAO rubricaDao = new RubricaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long rubricaId = Long.parseLong(req.getParameter("rubricaId"));
		
		req.setAttribute("rubrica", this.rubricaDao.read(rubricaId));
		req.setAttribute("page", "atualizarRubrica");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long rubricaId = Long.parseLong(req.getParameter("rubricaId"));
		String nome = req.getParameter("nome");
		Double valor = Double.parseDouble(req.getParameter("valor"));
		
		Rubrica rubrica = (Rubrica) this.rubricaDao.read(rubricaId);
		rubrica.setNome(nome);
		rubrica.setValorTotal(valor);
		
		this.rubricaDao.update(rubrica);
		
		resp.sendRedirect("/listarRubricas");
		
	}
	
}
