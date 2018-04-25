package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarItem extends HttpServlet {

	private ItemDAO dao = new ItemDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		List<Orcamento> orcamentos = new OrcamentoDAO().getOrcamentos(usuario.getId());
		List<Categoria> categorias = new ArrayList<>();
		List<Rubrica> rubricas = new ArrayList<>();
		
		for(Orcamento orcamento : orcamentos) {
			for(Categoria categoria : new CategoriaDAO().getCategorias(orcamento.getId())) {
				categorias.add(categoria);
				rubricas.addAll(new RubricaDAO().getRubricas(categoria.getId()));
			}
		}
		
		req.setAttribute("page", "criarItens");
		
		req.setAttribute("orcamentos", orcamentos);
	    req.setAttribute("categorias", categorias);
	    req.setAttribute("rubricas", rubricas);
		
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long rubricaId = Long.parseLong(req.getParameter("rubricaId"));
		
		Item item = new Item(rubricaId,
							 req.getParameter("nome"),
							 req.getParameter("descricao"),
							 Double.parseDouble(req.getParameter("valor_uniforme")),
							 Long.parseLong(req.getParameter("quantidade")));
		
		dao.create(item);
		
		resp.sendRedirect("/listarItens");
		
		/*req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);*/
	}
}
