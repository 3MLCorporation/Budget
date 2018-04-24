package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarItem extends HttpServlet {

	private ItemDAO dao = new ItemDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		OrcamentoDAO orcamentos = new OrcamentoDAO();
		CategoriaDAO categorias = new CategoriaDAO();
		RubricaDAO rubricas = new RubricaDAO();
		
		req.setAttribute("page", "criarItens");
		
		req.setAttribute("orcamentos", orcamentos.getOrcamentos(usuario.getId()));
	    req.setAttribute("categorias", categorias.getCategorias());
	    req.setAttribute("rubricas", rubricas.getRubricas());
		
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Item item = new Item(req.getParameter("nome"), req.getParameter("descricao"),
							Double.parseDouble(req.getParameter("valor_uniforme")),
							Long.parseLong(req.getParameter("quantidade")));
		
		dao.create(item);
		
		resp.sendRedirect("/listarItens");
		
		/*req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);*/
	}
}
