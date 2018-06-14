package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.model.Categoria;

@SuppressWarnings("serial")
public class AtualizarCategoria extends HttpServlet {
	
	private CategoriaDAO categoriaDao = new CategoriaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		
		req.setAttribute("categoria", this.categoriaDao.read(categoriaId));
		req.setAttribute("page", "atualizarCategoria");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		String nome = req.getParameter("nome");
		Double valor = Double.parseDouble(req.getParameter("valor"));
		
		Categoria categoria = (Categoria) this.categoriaDao.read(categoriaId);
		categoria.setNome(nome);
		categoria.setValorTotal(valor);
		
		this.categoriaDao.update(categoria);
		
		resp.sendRedirect("/listarCategorias");
		
	}
	
}
