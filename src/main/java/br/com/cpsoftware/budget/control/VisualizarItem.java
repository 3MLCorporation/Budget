package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Orcamento;

@SuppressWarnings("serial")
public class VisualizarItem extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoEditavelId);		
		
		Long itemId = Long.parseLong(req.getParameter("itemId"));
		
		Item item = new ItemDAO().read(itemId);
		
		List<NotaFiscal> notas = new NotaFiscalDAO().getNotasFiscais(itemId);
		
		List<String> fornecedores = new ArrayList<>();
		
		for(NotaFiscal nota : notas) {
			fornecedores.add(new FornecedorDAO().read(nota.getFornecedorId()).getNomeFantasia());
		}
		
		req.setAttribute("item", item);
		req.setAttribute("orcamentoSelecionado", new OrcamentoDAO().read(Long.parseLong(
				(String) req.getSession().getAttribute("orcamentoEditavel"))).getNome());		
		req.setAttribute("notas", notas);
		req.setAttribute("fornecedores", fornecedores);
		req.setAttribute("page", "visualizarItem");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
}
