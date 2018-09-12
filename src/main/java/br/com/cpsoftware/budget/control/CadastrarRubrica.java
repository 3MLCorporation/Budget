package br.com.cpsoftware.budget.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;


@SuppressWarnings("serial")
public class CadastrarRubrica extends HttpServlet {

	private RubricaDAO dao = new RubricaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoEditavelId);
		List<Categoria> categorias = new ArrayList<>();
		
		for(Categoria categoria : new CategoriaDAO().getCategorias(orcamento.getId())) {
			categorias.add(categoria);
		}
		
		req.setAttribute("categorias", categorias);
		
	    req.setAttribute("page", "criarRubrica");
	    req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		
		Rubrica rubrica = new Rubrica(
			categoriaId,
			req.getParameter("nome"),
			0d, //valorEstimado
			0d, // valorOrcado
			0d, // valorParcial
			0d // valorComprovado
		);
		
		Long rubricaId = dao.create(rubrica);
		
		/*Antes o cálculo do valor orçado era valorOrcadoCategoria = sum(valoresEstimadosRubricas);
		 * if(rubricaId != null) {
			AtualizarValoresOrcados.atualizarPrecoCategoria(
				AtualizarValoresOrcados.CADASTRAR,
	            rubricaId,
	            rubrica.getValorOrcado()
            );
		}*/
	
		resp.sendRedirect("/listarRubricas");
	}
}
