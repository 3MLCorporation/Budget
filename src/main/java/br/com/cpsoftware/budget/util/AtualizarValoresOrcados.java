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

public class AtualizarValoresOrcados {

	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	
	public static final int CADASTRAR = 0;
	public static final int EXCLUIR = 1;
	public static final int EDITAR = 2;
	
	public static void atualizarPrecoProjeto(int tipoDeAtualizacao, Long orcamentoId, Double valor) {
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(orcamentoId);
		Projeto projeto = (Projeto) projetoDAO.read(orcamento.getProjetoId());
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
	
	public static void atualizarPrecoOrcamento(int tipoDeAtualizacao, Long categoriaId, Double valor) {
		Categoria categoria = (Categoria) categoriaDAO.read(categoriaId);
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(categoria.getOrcamentoId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				orcamento.setValorOrcado(orcamento.getValorOrcado() + valor);
				atualizarPrecoProjeto(CADASTRAR, orcamento.getId(), valor);
				break;
			case EXCLUIR:
				orcamento.setValorOrcado(orcamento.getValorOrcado() - valor);
				atualizarPrecoProjeto(EXCLUIR, orcamento.getId(), valor);
				break;
		}	
		orcamentoDAO.update(orcamento);
	}
	
	public static void atualizarPrecoCategoria(int tipoDeAtualizacao, Long rubricaId, Double valor) {
		Rubrica rubrica = (Rubrica) rubricaDAO.read(rubricaId);
		Categoria categoria = (Categoria) categoriaDAO.read(rubrica.getCategoriaId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				//Antes 
				//categoria.setValorOrcado(categoria.getValorOrcado() + (valor - rubrica.getValorEstimado()));
				//categoria.setValorOrcado(categoria.getValorOrcado() + (valor - rubrica.getValorOrcado()));
				categoria.setValorOrcado(categoria.getValorOrcado() + valor);
				atualizarPrecoOrcamento(CADASTRAR, categoria.getId(), valor);
				break;
			case EXCLUIR:
				categoria.setValorOrcado(categoria.getValorOrcado() - valor);
				atualizarPrecoOrcamento(EXCLUIR, categoria.getId(), valor);
				break;
		}	
		categoriaDAO.update(categoria);
	}
	
	public static void atualizarPrecoRubrica(int tipoDeAtualizacao, Long itemId, Double valor) {
		Item item = itemDAO.read(itemId);
		Rubrica rubrica = (Rubrica) rubricaDAO.read(item.getRubricaId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
				rubrica.setValorOrcado(rubrica.getValorOrcado() + valor);
				atualizarPrecoCategoria(CADASTRAR, rubrica.getId(), valor);
				break;
			case EDITAR:
				rubrica.setValorOrcado(rubrica.getValorOrcado() + valor);
				atualizarPrecoCategoria(EDITAR, rubrica.getId(), valor);
				break;
			case EXCLUIR:
				rubrica.setValorOrcado(rubrica.getValorOrcado() - valor);
				atualizarPrecoCategoria(EXCLUIR, rubrica.getId(), valor);
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
