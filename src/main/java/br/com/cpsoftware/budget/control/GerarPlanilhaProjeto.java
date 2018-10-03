package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.util.Planilha;

@SuppressWarnings("serial")
public class GerarPlanilhaProjeto extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long projetoId = Long.parseLong(req.getParameter("projetoId"));
		Projeto projeto = (Projeto) new ProjetoDAO().read(projetoId);
		
		String nomeProjeto = projeto.getNome().trim().replace('ç', 'c');
		resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		resp.setHeader("Content-Disposition", "attachment; filename=" + nomeProjeto + ".xlsx");
	 
		Planilha.gerarPlanilhaProjeto(projetoId, resp.getOutputStream());
	}
	
}
