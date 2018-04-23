package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.UsuarioDAO;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarUsuario extends HttpServlet {

	private UsuarioDAO dao = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/cadastroUsuario.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		String confirmacao = req.getParameter("confirmacao");
		
		//TODO Validações
		
		Usuario usuario = new Usuario(nome, email, login, senha);
		
		this.dao.create(usuario);
		
		req.getSession().setAttribute("usuario", usuario);
		/*req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);*/
		resp.sendRedirect("/principal");
		
	}
	
}
