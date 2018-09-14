package br.com.cpsoftware.budget.util;


import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Rubrica;

public class AtualizarValoresRealizados {

	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	private static NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
	
	public static final int CADASTRAR = 0;
	public static final int EXCLUIR = 1;
	public static final int EDITAR = 2;
	
	
	public static void atualizarPrecoProjeto(int tipoDeAtualizacao, Long projetoId, Double valor) {
		Projeto projeto = (Projeto) projetoDAO.read(projetoId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				projeto.setValorRealizado(projeto.getValorRealizado() + valor);
				break;
			case EXCLUIR:
				projeto.setValorRealizado(projeto.getValorRealizado() - valor);
				break;
		}	
		projetoDAO.update(projeto);
	}
	
	public static void atualizarPrecoOrcamento(int tipoDeAtualizacao, Long orcamentoId, Double valor) {
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(orcamentoId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				orcamento.setValorRealizado(orcamento.getValorRealizado() + valor);
				atualizarPrecoProjeto(CADASTRAR, orcamento.getProjetoId(), valor);
				break;
			case EXCLUIR:
				orcamento.setValorRealizado(orcamento.getValorRealizado() - valor);
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
				categoria.setValorRealizado(categoria.getValorRealizado() + valor);
				atualizarPrecoOrcamento(CADASTRAR, categoria.getOrcamentoId(), valor);
				break;
			case EXCLUIR:
				categoria.setValorRealizado(categoria.getValorRealizado() - valor);
				atualizarPrecoOrcamento(EXCLUIR, categoria.getOrcamentoId(), valor);
				break;
		}	
		categoriaDAO.update(categoria);
	}
	
	public static void atualizarPrecoRubrica(int tipoDeAtualizacao, Long rubricaId, Double valor) {
		Rubrica rubrica = (Rubrica) rubricaDAO.read(rubricaId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				rubrica.setValorRealizado(rubrica.getValorRealizado() + valor);
				atualizarPrecoCategoria(CADASTRAR, rubrica.getCategoriaId(), valor);
				break;
			case EXCLUIR:
				rubrica.setValorRealizado(rubrica.getValorRealizado() - valor);
				atualizarPrecoCategoria(EXCLUIR, rubrica.getCategoriaId(), valor);
				break;
		}	
		rubricaDAO.update(rubrica);
	}
	
	//TODO DISCUTIR SOBRE VALOR PARCIAL NO ITEM
	public static void atualizarPrecoItem(int tipoDeAtualizacao, Long itemId, Double valor) {
		Item item = itemDAO.read(itemId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				item.setValorRealizado(item.getValorRealizado() + valor);
				atualizarPrecoRubrica(CADASTRAR, item.getRubricaId(), valor);
				break;
			case EXCLUIR:
				item.setValorRealizado(item.getValorRealizado() - valor);
				atualizarPrecoRubrica(EXCLUIR, item.getRubricaId(), valor);
				break;
		}	
		itemDAO.update(item);
	}
	
	public static void atualizarPrecoNotaFiscal(int tipoDeAtualizacao, Long notaId, Double valor) {
		NotaFiscal nota = notaFiscalDAO.read(notaId);
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
				atualizarPrecoItem(CADASTRAR, nota.getItemId(), valor);
				break;
			case EDITAR:
				//O parametro valor aqui é o novo valor do pagamento.
				//O terceiro argumento é a diferença entre o novo valor e o antigo valor;
				atualizarPrecoItem(EDITAR, nota.getItemId(), (valor - nota.getValor()));
				break;
			case EXCLUIR:
				atualizarPrecoItem(EXCLUIR, nota.getItemId(), valor);
				break;
		}
	}
	
	/*//TODO PAGAMENTO VAI TER VALOR PARCIAL??
	public static void atualizarPrecoPagamento(int tipoDeAtualizacao, Long pagamentoId, Double valor) {
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
				atualizarPrecoNotaFiscal(CADASTRAR, pagamentoId, valor);
				break;
			case EDITAR://O parametro valor aqui é o novo valor do pagamento.
				Pagamento pagamento = pagamentoDAO.read(pagamentoId);
				
				//O terceiro argumento é a diferença entre o novo valor e o antigo valor;
				atualizarPrecoNotaFiscal(EDITAR, pagamentoId, (valor - pagamento.getValor()));
				pagamento.setValor(valor);
				pagamentoDAO.update(pagamento);
				break;
			case EXCLUIR:
				atualizarPrecoNotaFiscal(EXCLUIR, pagamentoId, valor);
				break;
		}
	}*/
}
