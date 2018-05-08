package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.OrcamentoUsuarioDAO;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.OrcamentoUsuario;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class ListarOrcamentos extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		switch(usuario.getPerfil()) {
			case 0:
			case 1:
				Long projetoId = Long.parseLong((String) req.getSession().getAttribute("projetoEditavel"));
				
				req.setAttribute("orcamentos", new OrcamentoDAO().getOrcamentos(projetoId));
				break;
				
			case 2:
				List<OrcamentoUsuario> orcamentoUsuarioLista = new OrcamentoUsuarioDAO().getOrcamentoUsuarioByUsuarioId(usuario.getId());
				List<Orcamento> orcamentos = new ArrayList<Orcamento>();
				for(OrcamentoUsuario relacao: orcamentoUsuarioLista){
					orcamentos.add((Orcamento) new OrcamentoDAO().read(relacao.getOrcamentoId()));
				}
				req.setAttribute("orcamentos", orcamentos);
				break;
		
		}
		
		
		req.setAttribute("page", "listarOrcamentos");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
}
