package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Orcamento;

@SuppressWarnings("serial")
public class AtualizarOrcamento extends HttpServlet {
	
	private OrcamentoDAO orcamentoDao = new OrcamentoDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoEditavelId = Long.parseLong(req.getParameter("orcamentoId"));
		req.getSession().setAttribute("orcamentoEditavel", orcamentoEditavelId);
		
		req.setAttribute("orcamento", this.orcamentoDao.read(orcamentoEditavelId));
		req.setAttribute("page", "atualizarOrcamento");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long orcamentoId = Long.parseLong(req.getParameter("orcamentoId"));
		String nome = req.getParameter("nome");
		
		Orcamento orcamento = (Orcamento) this.orcamentoDao.read(orcamentoId);
		orcamento.setNome(nome);
		
		/*Antes teria que atualizar o valor orcado do projeto, depois que editasse
		Double valor = Double.parseDouble(req.getParameter("valor"));
		orcamento.setValorEstimado(valor);
		
		AtualizarValoresOrcados.atualizarPrecoProjeto(
				AtualizarValoresOrcados.EDITAR,
				orcamentoId,
				valor
		);*/
		
		this.orcamentoDao.update(orcamento);
		
		resp.sendRedirect("/listarOrcamentos");
		
	}
	
}
