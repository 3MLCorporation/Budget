package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.UsuarioDAO;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class AtualizarPerfil extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		usuario.setPerfil(Integer.parseInt(req.getParameter("perfilAtualizado")));
		
		usuarioDAO.update(usuario);
		
		//TODO redirect
	}
	
}
