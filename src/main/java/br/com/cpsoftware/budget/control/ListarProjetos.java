package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class ListarProjetos extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		ProjetoDAO projetoDao = new  ProjetoDAO();
		switch(usuario.getPerfil()) {
			case 0:
				req.setAttribute("projetos", projetoDao.getProjetos());
				break;
			case 1:
				req.setAttribute("projetos", projetoDao.getProjetos(usuario.getId()));
				break;
		}
		
		req.setAttribute("page", "listarProjetos");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
