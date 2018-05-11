package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.model.Pagamento;

@SuppressWarnings("serial")
public class AbrirPagamento extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long pagamentoId = Long.parseLong(req.getParameter("pagamentoId"));
		
		Pagamento pagamento = new PagamentoDAO().read(pagamentoId);
		resp.setContentType("application/pdf");
		resp.getOutputStream().write(pagamento.getArquivo().getBytes());;
		
		/*
		 * TODO Validações
		 */
	}
	
}
