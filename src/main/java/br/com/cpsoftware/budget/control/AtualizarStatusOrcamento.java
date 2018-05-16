package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Orcamento;

@SuppressWarnings("serial")
public class AtualizarStatusOrcamento extends HttpServlet {

	/**
	 * TODO CORRIGIR!!!
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		OrcamentoDAO orcamentoDao = new OrcamentoDAO();
		
		Orcamento orcamento = (Orcamento) orcamentoDao.read(Long.parseLong(req.getParameter("orcamentoId")));
		
		orcamento.setStatus(Integer.parseInt(req.getParameter("statusSelecionado")));
		
		orcamentoDao.update(orcamento);
		
		resp.sendRedirect("/listarOrcamentos");
	}
	
}
