package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarProjeto extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setAttribute("page", "criarProjeto");           // Tells base.jsp to include form.jsp
		 req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjetoDAO dao = new ProjetoDAO();
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		String nome = req.getParameter("nome");
		String valor = req.getParameter("valor");
				 
		 /*System.out.println("Nome - " + nome);
		 System.out.println("Valor - " + valor);
		 System.out.println("Valor float - " + Float.parseFloat(valor));*/
		 
		Projeto projeto = new Projeto(usuario.getId(), nome, Double.parseDouble(valor));
		
		System.out.println("Gerente_Id - " + usuario.getId());
		System.out.println("Nome - " + projeto.getNome());
		System.out.println("Valor - " + projeto.getValorTotal());
		
		
		Long projetoId = dao.create(projeto);
		
		Projeto aux = (Projeto) dao.read(projetoId);
		System.err.println("Nome do projeto no banco - " + aux.getNome());
		System.err.println("Valor do projeto no banco - " + aux.getValorTotal());
		
		
		resp.sendRedirect("/listarProjetos");
	}
	
}
