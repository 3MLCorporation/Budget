package br.com.cpsoftware.budget.util;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Rubrica;

public class AtualizarValoresOrcados {

	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	
	public static final int CADASTRAR = 0;
	public static final int EXCLUIR = 1;
	public static final int EDITAR = 2;
	
	public static void atualizarPrecoProjeto(int tipoDeAtualizacao, Long projetoId, Double valor) {
		Projeto projeto = (Projeto) projetoDAO.read(projetoId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				projeto.setValorOrcado(projeto.getValorOrcado() + valor);
				break;
			case EXCLUIR:
				projeto.setValorOrcado(projeto.getValorOrcado() - valor);
				break;
		}	
		projetoDAO.update(projeto);
	}
	
	public static void atualizarPrecoOrcamento(int tipoDeAtualizacao, Long orcamentoId, Double valor) {
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(orcamentoId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				orcamento.setValorOrcado(orcamento.getValorOrcado() + valor);
				atualizarPrecoProjeto(CADASTRAR, orcamento.getProjetoId(), valor);
				break;
			case EXCLUIR:
				orcamento.setValorOrcado(orcamento.getValorOrcado() - valor);
				atualizarPrecoProjeto(EXCLUIR, orcamento.getProjetoId(), valor);
				break;
		}	
		orcamentoDAO.update(orcamento);
	}
	
	public static void atualizarPrecoCategoria(int tipoDeAtualizacao, Long categoriaId, Double valor) {
		Categoria categoria = (Categoria) categoriaDAO.read(categoriaId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				//Antes 
				//categoria.setValorOrcado(categoria.getValorOrcado() + (valor - rubrica.getValorEstimado()));
				//categoria.setValorOrcado(categoria.getValorOrcado() + (valor - rubrica.getValorOrcado()));
				categoria.setValorOrcado(categoria.getValorOrcado() + valor);
				atualizarPrecoOrcamento(CADASTRAR, categoria.getOrcamentoId(), valor);
				break;
			case EXCLUIR:
				categoria.setValorOrcado(categoria.getValorOrcado() - valor);
				atualizarPrecoOrcamento(EXCLUIR, categoria.getOrcamentoId(), valor);
				break;
		}	
		categoriaDAO.update(categoria);
	}
	
	public static void atualizarPrecoRubrica(int tipoDeAtualizacao, Long rubricaId, Double valor) {
		Rubrica rubrica = (Rubrica) rubricaDAO.read(rubricaId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
				rubrica.setValorOrcado(rubrica.getValorOrcado() + valor);
				atualizarPrecoCategoria(CADASTRAR, rubrica.getCategoriaId(), valor);
				break;
			case EDITAR:
				rubrica.setValorOrcado(rubrica.getValorOrcado() + valor);
				atualizarPrecoCategoria(EDITAR, rubrica.getCategoriaId(), valor);
				break;
			case EXCLUIR:
				rubrica.setValorOrcado(rubrica.getValorOrcado() - valor);
				atualizarPrecoCategoria(EXCLUIR, rubrica.getCategoriaId(), valor);
				break;
		}	
		rubricaDAO.update(rubrica);
	}
	
	/*public static void atualizarPrecoItem(int tipoDeAtualizacao, Long notaId, Double valor) {
		NotaFiscal notaFiscal = notaFiscalDAO.read(notaId);
		Item item = itemDAO.read(notaFiscal.getItemId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
				item.setValorOrcado(item.getValorOrcado() + valor);
				break;
			case EDITAR:
				item.setValorOrcado(item.getValorOrcado() + (valor - notaFiscal.getValor()));
				break;
			case EXCLUIR:
				item.setValorOrcado(item.getValorOrcado() - valor);
				break;
		}	
		itemDAO.update(item);
	}*/
	
}
