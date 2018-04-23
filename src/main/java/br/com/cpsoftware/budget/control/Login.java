package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.UsuarioDAO;
import br.com.cpsoftware.budget.model.Usuario;

public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/loginUsuario.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario  usuario = usuarioDAO.entrar(login, senha);
		/*System.out.println(usuario.getLogin());
		System.out.println(usuario.getSenha());*/
		
		if(usuario != null) {
			req.getSession().setAttribute("usuario", usuario);
			req.setAttribute("page", "visualizarResumo");           
			req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
		}else {
			//TODO Tratamento de erros
		}
		
		
		
	}
	
}
