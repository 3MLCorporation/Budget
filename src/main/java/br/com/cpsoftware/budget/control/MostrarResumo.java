package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class MostrarResumo extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		Long orcamentoEditavelId;
		if((String) req.getSession().getAttribute("orcamentoEditavel") == null) {
			orcamentoEditavelId = null;
		}else {
			orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		}
		 
		
		if(usuario == null) {
			System.out.println("usuario logado null");
		}else {
			System.out.println(usuario.getId());
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getSenha());
		}
		
		if(orcamentoEditavelId == null) {
			System.out.println("orcamentoEditavel null");
		}else {
			System.out.println("orcamentoEditavel : " + orcamentoEditavelId);
		}
		
		OrcamentoDAO dao = new OrcamentoDAO();
		
		req.setAttribute("orcamentos", dao.getOrcamentos(usuario.getId()));
		//req.setAttribute("orcamentoEditavel", orcamentoEditavelId);
		
		req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
