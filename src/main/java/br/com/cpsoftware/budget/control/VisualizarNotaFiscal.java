package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Pagamento;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Orcamento;

@SuppressWarnings("serial")
public class VisualizarNotaFiscal extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoEditavelId);		

		Long notaIdSessao = (Long) req.getSession().getAttribute("notaId");
		NotaFiscal nota = null;
		
		if(notaIdSessao != null) {//Depois que cadastra
			nota = new NotaFiscalDAO().read(notaIdSessao);
			req.getSession().setAttribute("notaId", null);
		}else {
			Long notaId = Long.parseLong(req.getParameter("notaId"));
			nota = new NotaFiscalDAO().read(notaId);
		}
		
		List<Pagamento> pagamentos = new PagamentoDAO().getPagamentos(nota.getId());
		
		req.setAttribute("nota", nota);
		req.setAttribute("orcamentoSelecionado", new OrcamentoDAO().read(Long.parseLong(
				(String) req.getSession().getAttribute("orcamentoEditavel"))).getNome());		
		req.setAttribute("fornecedor", new FornecedorDAO().read(nota.getFornecedorId()).getNomeFantasia());
		req.setAttribute("pagamentos", pagamentos);
		req.setAttribute("page", "visualizarNotaFiscal");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
