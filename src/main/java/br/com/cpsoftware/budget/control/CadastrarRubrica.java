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
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarRubrica extends HttpServlet {

	private RubricaDAO dao = new RubricaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		OrcamentoDAO orcamentos = new OrcamentoDAO();
		CategoriaDAO categorias = new CategoriaDAO();
		
	    req.setAttribute("page", "criarRubrica");
	    
	    req.setAttribute("orcamentos", orcamentos.getOrcamentos(usuario.getId()));
	    req.setAttribute("categorias", categorias.getCategorias(usuario.getId()));
	    
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		
		Rubrica rubrica = new Rubrica(categoriaId, req.getParameter("nome"), Double.parseDouble(req.getParameter("valor")));
		
		dao.create(rubrica);
	
		resp.sendRedirect("/listarRubricas");
		
		/*req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);*/
	}
}
