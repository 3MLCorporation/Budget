package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Orcamento;

@SuppressWarnings("serial")
public class ListarCategorias extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OrcamentoDAO orcamentoDao = new OrcamentoDAO();
		CategoriaDAO categoriaDao = new CategoriaDAO();
		
		List<Categoria> categorias = new ArrayList<>();
		
		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) orcamentoDao.read(orcamentoEditavelId);
		
		for(Categoria categoria : categoriaDao.getCategorias(orcamento.getId())) {
			categorias.add(categoria);
		}
		
		
		req.setAttribute("categorias", categorias);
		
		req.setAttribute("page", "listarCategorias");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
