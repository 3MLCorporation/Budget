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
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.UsuarioDAO;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.OrcamentoUsuario;
import br.com.cpsoftware.budget.model.Usuario;

@SuppressWarnings("serial")
public class MostrarResumo extends HttpServlet {

	/*
	 * 
	 * TODO Ajustar a tela visualizarResumo.jsp
	 */
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		if(usuario == null) {
			System.out.println("usuario logado null");
		}else {
			System.out.println(usuario.getPerfil());
			System.out.println(usuario.getId());
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getSenha());
		}
		
		switch(usuario.getPerfil()){
			
			case Usuario.PERFIL_ADMIN:
				req.setAttribute("usuarios", new UsuarioDAO().getUsuarios());
				req.setAttribute("page", "listarUsuario");
				req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
				break;
			case Usuario.PERFIL_GERENTE:
				ProjetoDAO projetoDao = new  ProjetoDAO();
				req.setAttribute("projetos", projetoDao.getProjetos(usuario.getId()));
				req.setAttribute("page", "listarProjetos");           
				req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
				break;
			case Usuario.PERFIL_PADRAO:
				List<OrcamentoUsuario> orcamentoUsuarioLista = new OrcamentoUsuarioDAO().getOrcamentoUsuarioByUsuarioId(usuario.getId());
				List<Orcamento> orcamentos = new ArrayList<Orcamento>();
				for(OrcamentoUsuario relacao: orcamentoUsuarioLista){
					orcamentos.add((Orcamento) new OrcamentoDAO().read(relacao.getOrcamentoId()));
				}
				
				req.getSession().setAttribute("orcamentoEditavel", null);
				req.setAttribute("orcamentos", orcamentos);
				
				req.setAttribute("page", "listarOrcamentos");           
				req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
				
				break;
		}
	}
}
