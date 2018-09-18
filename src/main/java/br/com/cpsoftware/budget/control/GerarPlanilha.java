package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.util.Planilha;

@SuppressWarnings("serial")
public class GerarPlanilha extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		resp.setHeader("Content-Disposition", "attachment; filename=planilha.xlsx");
	 
		Planilha.gerarPlanilha(Long.parseLong(req.getParameter("projetoId")), resp.getOutputStream());
	}
	
}
