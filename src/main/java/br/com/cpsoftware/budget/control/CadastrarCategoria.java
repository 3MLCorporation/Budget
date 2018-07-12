package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.util.AtualizarPrecos;

@SuppressWarnings("serial")
public class CadastrarCategoria extends HttpServlet {
	
	private CategoriaDAO dao = new CategoriaDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("page", "criarCategoria");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	    
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		
		Categoria categoria = new Categoria(
			orcamentoId,
			req.getParameter("nome"),
			Double.parseDouble(req.getParameter("valor")), //valorEstimado
			0d, // valorOrcado
			0d // valorParcial
		);
		
		Long categoriaId = dao.create(categoria);
		
        if(categoriaId != null) {
            AtualizarPrecos.atualizarPrecoOrcamento(
                AtualizarPrecos.CADASTRAR,
                AtualizarPrecos.ORCADO,
                categoriaId,
                categoria.getValorEstimado()
            );
        }
        

		
		resp.sendRedirect("/listarCategorias");
	}
}
