package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Rubrica;

@SuppressWarnings("serial")
public class CadastrarRubrica extends HttpServlet {

	private RubricaDAO dao = new RubricaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrcamentoDAO orcamentos = new OrcamentoDAO();
		CategoriaDAO categorias = new CategoriaDAO();
		
	    req.setAttribute("page", "criarRubrica");
	    
	    req.setAttribute("orcamentos", orcamentos.getOrcamentos());
	    req.setAttribute("categorias", categorias.getCategorias());
	    
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
