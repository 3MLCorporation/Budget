package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarCategoria extends HttpServlet {
	
	private CategoriaDAO dao = new CategoriaDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		OrcamentoDAO orcamentos = new OrcamentoDAO();
		
		req.setAttribute("page", "criarCategoria");
		
	    req.setAttribute("orcamentos", orcamentos.getOrcamentos(usuario.getId()));
	    
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Categoria categoria = new Categoria(req.getParameter("nome"), Double.parseDouble(req.getParameter("valor")));
		
		dao.create(categoria);
		
		resp.sendRedirect("/listarCategorias");
		
		/*req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);*/
	}
}
