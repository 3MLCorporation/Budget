package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Projeto;

@SuppressWarnings("serial")
public class AtualizarProjeto extends HttpServlet {
	
	private ProjetoDAO projetoDAO = new ProjetoDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long projetoId = Long.parseLong(req.getParameter("projetoId"));
		
		req.setAttribute("projeto", this.projetoDAO.read(projetoId));
		req.setAttribute("page", "atualizarProjeto");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long projetoId = Long.parseLong(req.getParameter("projetoId"));
		String nome = req.getParameter("nome");
		Double valor = Double.parseDouble(req.getParameter("valor"));
		
		Projeto projeto = (Projeto) this.projetoDAO.read(projetoId);
		projeto.setNome(nome);
		projeto.setValorTotal(valor);
		
		this.projetoDAO.update(projeto);
		
		resp.sendRedirect("/principal");
		
	}
	
}
