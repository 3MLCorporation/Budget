package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.UsuarioDAO;

@SuppressWarnings("serial")
public class ListarUsuarios extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("usuarios", new UsuarioDAO().getUsuarios());
		req.setAttribute("page", "listarUsuario");
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
