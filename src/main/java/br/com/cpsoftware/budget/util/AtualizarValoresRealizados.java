package br.com.cpsoftware.budget.util;


import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Pagamento;
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Rubrica;

public class AtualizarValoresRealizados {

	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	private static NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
	private static PagamentoDAO pagamentoDAO = new PagamentoDAO();
	
	public static final int CADASTRAR = 0;
	public static final int EXCLUIR = 1;
	public static final int EDITAR = 2;
	
	
	public static void atualizarPrecoProjeto(int tipoDeAtualizacao, Long orcamentoId, Double valor) {
		Projeto projeto = (Projeto) projetoDAO.read(((Orcamento) orcamentoDAO.read(orcamentoId)).getProjetoId());
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
	
	public static void atualizarPrecoOrcamento(int tipoDeAtualizacao, Long categoriaId, Double valor) {
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(((Categoria) categoriaDAO.read(categoriaId)).getOrcamentoId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				orcamento.setValorRealizado(orcamento.getValorRealizado() + valor);
				atualizarPrecoProjeto(CADASTRAR, orcamento.getId(), valor);
				break;
			case EXCLUIR:
				orcamento.setValorRealizado(orcamento.getValorRealizado() - valor);
				atualizarPrecoProjeto(EXCLUIR, orcamento.getId(), valor);
				break;
		}	
		orcamentoDAO.update(orcamento);
	}
	
	public static void atualizarPrecoCategoria(int tipoDeAtualizacao, Long rubricaId, Double valor) {
		Categoria categoria = (Categoria) categoriaDAO.read(((Rubrica) rubricaDAO.read(rubricaId)).getCategoriaId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				categoria.setValorRealizado(categoria.getValorRealizado() + valor);
				atualizarPrecoOrcamento(CADASTRAR, categoria.getId(), valor);
				break;
			case EXCLUIR:
				categoria.setValorRealizado(categoria.getValorRealizado() - valor);
				atualizarPrecoOrcamento(EXCLUIR, categoria.getId(), valor);
				break;
		}	
		categoriaDAO.update(categoria);
	}
	
	public static void atualizarPrecoRubrica(int tipoDeAtualizacao, Long itemId, Double valor) {
		Rubrica rubrica = (Rubrica) rubricaDAO.read((itemDAO.read(itemId)).getRubricaId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				rubrica.setValorRealizado(rubrica.getValorRealizado() + valor);
				atualizarPrecoCategoria(CADASTRAR, rubrica.getId(), valor);
				break;
			case EXCLUIR:
				rubrica.setValorRealizado(rubrica.getValorRealizado() - valor);
				atualizarPrecoCategoria(EXCLUIR, rubrica.getId(), valor);
				break;
		}	
		rubricaDAO.update(rubrica);
	}
	
	//TODO DISCUTIR SOBRE VALOR PARCIAL NO ITEM
	public static void atualizarPrecoItem(int tipoDeAtualizacao, Long notaId, Double valor) {
		Item item = itemDAO.read((notaFiscalDAO.read(notaId)).getItemId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				item.setValorRealizado(item.getValorRealizado() + valor);
				atualizarPrecoRubrica(CADASTRAR, item.getId(), valor);
				break;
			case EXCLUIR:
				item.setValorRealizado(item.getValorRealizado() - valor);
				atualizarPrecoRubrica(EXCLUIR, item.getId(), valor);
				break;
		}	
		itemDAO.update(item);
	}
	
	public static void atualizarPrecoNotaFiscal(int tipoDeAtualizacao, Long pagamentoId, Double valor) {
		NotaFiscal nota = notaFiscalDAO.read((pagamentoDAO.read(pagamentoId)).getNotaFiscalId());
		switch(tipoDeAtualizacao) {
			case CADASTRAR:
			case EDITAR:
				
				//Aqui o valor é tratado igual, pois ele já vem ajustado, 
				//tanto para cadastro(todo o valor), como para edição(diferença calculada no metodo anterior);
				nota.setValorRealizado(nota.getValorRealizado() + valor);
				atualizarPrecoItem(CADASTRAR, nota.getId(), valor);
				break;
			case EXCLUIR:
				nota.setValorRealizado(nota.getValorRealizado() - valor);
				atualizarPrecoItem(EXCLUIR, nota.getId(), valor);
				break;
		}	
		notaFiscalDAO.update(nota);
	}
	
	//TODO PAGAMENTO VAI TER VALOR PARCIAL??
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
	}
}
