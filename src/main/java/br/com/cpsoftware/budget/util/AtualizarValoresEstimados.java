package br.com.cpsoftware.budget.util;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Rubrica;

public class AtualizarValoresEstimados {

	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	
	public static void atualizarValorEstimadoRubrica(Long rubricaId) {
		Rubrica rubrica = (Rubrica) rubricaDAO.read(rubricaId);
		
		double somaEstimados = 0;
		for(Item item: itemDAO.getItens(rubricaId)) {
			somaEstimados += item.getValor();
		}
		rubrica.setValorEstimado(somaEstimados);
		
		rubricaDAO.update(rubrica);
		
		atualizarValorEstimadoCategoria(rubrica.getCategoriaId());
	}

	private static void atualizarValorEstimadoCategoria(Long categoriaId) {
		Categoria categoria = (Categoria) categoriaDAO.read(categoriaId);
		
		double somaEstimados = 0;
		for(Rubrica rubrica: rubricaDAO.getRubricas(categoriaId)) {
			somaEstimados += rubrica.getValorEstimado();
		}
		categoria.setValorEstimado(somaEstimados);
		
		categoriaDAO.update(categoria);
		
		atualizarValorEstimadoOrcamento(categoria.getOrcamentoId());
		
	}

	private static void atualizarValorEstimadoOrcamento(Long orcamentoId) {
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(orcamentoId);
		
		double somaEstimados = 0;
		for(Categoria categoria: categoriaDAO.getCategorias(orcamentoId)) {
			somaEstimados += categoria.getValorEstimado();
		}
		orcamento.setValorEstimado(somaEstimados);
		
		orcamentoDAO.update(orcamento);
		
		atualizarValorEstimadoProjeto(orcamento.getProjetoId());
		
	}

	private static void atualizarValorEstimadoProjeto(Long projetoId) {
		Projeto projeto = (Projeto) projetoDAO.read(projetoId);
		
		double somaEstimados = 0;
		for(Orcamento orcamento: orcamentoDAO.getOrcamentos(projetoId)) {
			somaEstimados += orcamento.getValorEstimado();
		}
		projeto.setValorEstimado(somaEstimados);
		
		projetoDAO.update(projeto);
	}
}
