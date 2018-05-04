 package br.com.cpsoftware.budget.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.UsuarioDAO;
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
		//req.getSession().setAttribute("orcamentoEditavel", null);
		
		/*Long orcamentoEditavelId;
		if((String) req.getSession().getAttribute("orcamentoEditavel") == null) {
			orcamentoEditavelId = null;
		}else {
			orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		}*/
		 
		
		if(usuario == null) {
			System.out.println("usuario logado null");
		}else {
			System.out.println(usuario.getPerfil());
			System.out.println(usuario.getId());
			System.out.println(usuario.getLogin());
			System.out.println(usuario.getSenha());
		}
		
		/*if(orcamentoEditavelId == null) {
			System.out.println("orcamentoEditavel null");
		}else {
			System.out.println("orcamentoEditavel : " + orcamentoEditavelId);
		}*/
		
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
				/*List<OrcamentoUsuario> relacoes = new OrcamentoUsuario.getRelacoes(usuario.getId);
				List<Orcamento> orcamentos = new ArrayList();
				for(OrcamentoUsuario relacao: relacoes){
					orcamentos.add(new OrcamentoDAO.read(relacao.getOrcamentoId()));
				}
				req.setAttribute("orcamentos", orcamentos);
				
				if(req.getSession().getAttribute("orcamentoEditavel") == null) {
				
				}else {
					req.setAttribute("orcamentoSelecionado", new OrcamentoDAO().read(Long.parseLong(
															(String) req.getSession().getAttribute("orcamentoEditavel"))).getNome());
				}
				
				req.setAttribute("page", "listarOrcamentos");           
				req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
				*/
				break;
		}
	}
}
