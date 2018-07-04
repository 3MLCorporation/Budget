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
import br.com.cpsoftware.budget.util.AtualizarPrecos;


@SuppressWarnings("serial")
public class AtualizarRubrica extends HttpServlet {
	
	private RubricaDAO rubricaDao = new RubricaDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long orcamentoEditavelId = Long.parseLong((String) req.getSession().getAttribute("orcamentoEditavel"));
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(orcamentoEditavelId);
		List<Categoria> categorias = new ArrayList<>();
		
		for(Categoria categoria : new CategoriaDAO().getCategorias(orcamento.getId())) {
			categorias.add(categoria);
		}

		Long rubricaId = Long.parseLong(req.getParameter("rubricaId"));
		Rubrica rubrica = (Rubrica) this.rubricaDao.read(rubricaId);
		
		req.setAttribute("orcamentoSelecionado", orcamento.getNome());
		req.setAttribute("categoriaAtualId", rubrica.getCategoriaId());
		req.setAttribute("categorias", categorias);
		req.setAttribute("rubrica", rubrica);
		req.setAttribute("page", "atualizarRubrica");           
		req.getRequestDispatcher("/WEB-INF/base.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long categoriaId = Long.parseLong(req.getParameter("categoriaId"));
		Long rubricaId = Long.parseLong(req.getParameter("rubricaId"));
		String nome = req.getParameter("nome");
		Double valor = Double.parseDouble(req.getParameter("valor"));
		
		Rubrica rubrica = (Rubrica) this.rubricaDao.read(rubricaId);
		rubrica.setCategoriaId(categoriaId);
		rubrica.setNome(nome);
		rubrica.setValorEstimado(valor);
		
		//Atualizo a categoria anterior, excluindo o valor parcial da rubrica
		AtualizarPrecos.atualizarPrecoCategoria(AtualizarPrecos.EXCLUIR, rubricaId, rubrica.getValorRealizado());
		
		this.rubricaDao.update(rubrica);
		
		//Atualizo a nova categoria, adicionando o valor parcial da rubrica
		AtualizarPrecos.atualizarPrecoCategoria(AtualizarPrecos.EDITAR, rubricaId, rubrica.getValorRealizado());
		
		resp.sendRedirect("/listarRubricas");
		
	}
	
}
