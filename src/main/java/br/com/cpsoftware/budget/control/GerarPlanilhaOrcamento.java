package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.util.Planilha;

@SuppressWarnings("serial")
public class GerarPlanilhaOrcamento extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoId = Long.parseLong(req.getParameter("orcamentoId"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoId);
		
		String nomeOrcamento = orcamento.getNome().trim().replace('ç', 'c').replace('ã', 'a').replace('õ', 'o');
		resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		resp.setHeader("Content-Disposition", "attachment; filename=" + nomeOrcamento + ".xlsx");
	 
		Planilha.gerarPlanilhaOrcamento(orcamentoId, resp.getOutputStream());
	}
	
}
