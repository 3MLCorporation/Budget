package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Orcamento;

public class CadastrarOrcamento extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setAttribute("page", "criarOrcamento");           // Tells base.jsp to include form.jsp
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		 String valor = req.getParameter("valor");
				 //Float.parseFloat(
		 /*System.out.println("Nome - " + nome);
		 System.out.println("Valor - " + valor);
		 System.out.println("Valor float - " + Float.parseFloat(valor));*/
		 
		
		Orcamento orcamento = new Orcamento(nome, Float.parseFloat(valor));
		
		OrcamentoDAO dao = (OrcamentoDAO) this.getServletContext().getAttribute("dao");
		dao.create(orcamento);
	    /*try {
	        Long id = dao.create(orcamento);
	        resp.sendRedirect("/read?id=" + id.toString());   // read what we just wrote
	      } catch (Exception e) {
	        throw new ServletException("Erro criando orcamento", e);
	      }*/
	}
}
