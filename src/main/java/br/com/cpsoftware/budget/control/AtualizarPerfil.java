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

	/*
	 * TODO Verificação de permissão (??)
	 */
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		Usuario usuario = usuarioDAO.read(Long.parseLong(req.getParameter("usuarioParaAtulizar")));
		System.out.println("Usuario : " + usuario.getNome()+ " \nperfilAtualizado -  " + req.getParameter("perfilAtualizado"));
		
		usuario.setPerfil(Integer.parseInt(req.getParameter("perfilAtualizado")));
		
		usuarioDAO.update(usuario);
		
		/*
		 * TODO redirect, alerta javascript, etc ??
		 */
		
		resp.sendRedirect("/principal");
	}
	
}
