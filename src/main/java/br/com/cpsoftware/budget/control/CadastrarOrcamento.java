package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class CadastrarOrcamento extends HttpServlet {
	
	private OrcamentoDAO dao = new OrcamentoDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
		Usuario gerente = (Usuario) req.getSession().getAttribute("usuario");
		
		req.setAttribute("projetos", new ProjetoDAO().getProjetos(gerente.getId()));

		req.setAttribute("page", "criarOrcamento");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long projetoId = Long.parseLong((String) req.getSession().getAttribute("projetoEditavel"));
		String nome = req.getParameter("nome");
		String valor = req.getParameter("valor");
				 
		 /*System.out.println("Nome - " + nome);
		 System.out.println("Valor - " + valor);
		 System.out.println("Valor float - " + Float.parseFloat(valor));*/
		 
		Orcamento orcamento = new Orcamento(projetoId, nome, Double.parseDouble(valor), Orcamento.STATUS_ELABORACAO);
		
		System.out.println("Projeto_Id - " + projetoId);
		System.out.println("Nome - " + orcamento.getNome());
		System.out.println("Valor - " + orcamento.getValorTotal());
		
		
		Long orcamentoId = this.dao.create(orcamento);
		
		Orcamento aux = (Orcamento) this.dao.read(orcamentoId);
		System.err.println("Nome do orcamento no banco - " + aux.getNome());
		System.err.println("Valor do orcamento no banco - " + aux.getValorTotal());
		
		
		resp.sendRedirect("/listarOrcamentos");
		
		/*req.setAttribute("page", "visualizarResumo");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
		*/
		
	    /*try {
	        Long id = dao.create(orcamento);
	        resp.sendRedirect("/read?id=" + id.toString());   // read what we just wrote
	      } catch (Exception e) {
	        throw new ServletException("Erro criando orcamento", e);
	      }*/
	}
}
