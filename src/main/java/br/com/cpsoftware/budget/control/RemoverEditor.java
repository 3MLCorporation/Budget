package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoUsuarioDAO;
import br.com.cpsoftware.budget.model.OrcamentoUsuario;

@SuppressWarnings("serial")
public class RemoverEditor extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long usuarioId = Long.parseLong(req.getParameter("editorId"));
		Long orcamentoId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));;
		OrcamentoUsuarioDAO orcamentoUsuarioDao = new OrcamentoUsuarioDAO();
		
		for(OrcamentoUsuario orcamentoUsuario : orcamentoUsuarioDao.getOrcamentoUsuarioByUsuarioId(usuarioId)) {
			if(orcamentoUsuario.getOrcamentoId().equals(orcamentoId)){
				orcamentoUsuarioDao.delete(orcamentoUsuario.getId());
			}
		}
		
		resp.sendRedirect("/adicionarEditor");
	}
	
}
