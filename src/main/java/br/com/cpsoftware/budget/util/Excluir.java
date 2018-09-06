package br.com.cpsoftware.budget.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.FornecedorDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.NotaFiscalDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.OrcamentoUsuarioDAO;
import br.com.cpsoftware.budget.dao.PagamentoDAO;
import br.com.cpsoftware.budget.dao.ProjetoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.NotaFiscal;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.OrcamentoUsuario;
import br.com.cpsoftware.budget.model.Pagamento;
import br.com.cpsoftware.budget.model.Rubrica;
import br.com.cpsoftware.budget.util.AtualizarValoresRealizados;

public class Excluir {
	
	private static ProjetoDAO projetoDAO = new ProjetoDAO();
	private static OrcamentoDAO orcamentoDAO = new OrcamentoDAO();
	private static CategoriaDAO categoriaDAO = new CategoriaDAO();
	private static RubricaDAO rubricaDAO = new RubricaDAO();
	private static ItemDAO itemDAO = new ItemDAO();
	private static FornecedorDAO fornecedorDAO = new FornecedorDAO();
	private static NotaFiscalDAO notaFiscalDAO = new NotaFiscalDAO();
	private static PagamentoDAO pagamentoDAO = new PagamentoDAO();
	private static OrcamentoUsuarioDAO orcamentoUsuarioDAO = new OrcamentoUsuarioDAO();
	
	/*
	 * Esta classe possui 2 tipos de métodos: os de paramentro req, resp e os de parametro Long id
	 * 
	 * Os metodos de parametro req, resp são acionados pelas classes control e chamam os metodos de
	 * 		 parametro Long id dos seus filhos, com exceção do Pagamento. Eles também chamam os metodos util.Atualizar
	 * 		 de seus pais;
	 * 
	 * Os metodos de parametro Long id sempre são acionados  pelos metodos de exclusao do seus pais
	 * 		 e sempre acionam os metodos de parametro Long id dos seus filhos, gerando uma exclusão em cascata;
	 * 
	 */
	public static boolean excluirProjeto(HttpServletRequest req, HttpServletResponse resp) {
		
		Long projetoId = Long.parseLong(req.getParameter("projeto_id"));
		
		projetoDAO.delete(projetoId);
		
		excluirOrcamento(projetoId);
		
		return true;
	}
	
	private static void excluirOrcamento(Long projetoId) {
		for(Orcamento orcamento : orcamentoDAO.getOrcamentos(projetoId)) {
			excluirOrcamentoUsuario(orcamento.getId());
			orcamentoDAO.delete(orcamento.getId());
			excluirCategoria(orcamento.getId());
		}
		
	}

	public static boolean excluirOrcamento(HttpServletRequest req, HttpServletResponse resp) {
		
		Long orcamentoId = Long.parseLong(req.getParameter("orcamento_id"));
		Orcamento orcamento = (Orcamento) orcamentoDAO.read(orcamentoId);
		
		AtualizarValoresOrcados.atualizarPrecoProjeto(
			AtualizarValoresOrcados.EXCLUIR,
			orcamentoId,
			orcamento.getValorEstimado()
		);
		
		AtualizarValoresRealizados.atualizarPrecoProjeto(
			AtualizarValoresRealizados.EXCLUIR,
			orcamentoId,
			orcamento.getValorRealizado()
		);
		
		orcamentoDAO.delete(orcamentoId);

		AtualizarValoresEstimados.atualizarValorEstimadoProjeto(orcamento.getProjetoId());
		
		excluirCategoria(orcamentoId);
		excluirOrcamentoUsuario(orcamentoId);
		
		return true;
	}

	private static void excluirOrcamentoUsuario(Long orcamentoId) {
		for(OrcamentoUsuario orcamentoUsuario : orcamentoUsuarioDAO.getOrcamentoUsuarioByOrcamentoId(orcamentoId)) {
			orcamentoUsuarioDAO.delete(orcamentoUsuario.getId());
		}
		
	}
	
	private static void excluirCategoria(Long orcamentoId) {
		for(Categoria categoria : categoriaDAO.getCategorias(orcamentoId)) {
			categoriaDAO.delete(categoria.getId());
			excluirRubrica(categoria.getId());
		}
		
	}
	
	public static boolean excluirCategoria(HttpServletRequest req, HttpServletResponse resp) {
		
		Long categoriaId = Long.parseLong(req.getParameter("categoria_id"));
		Categoria categoria = (Categoria) categoriaDAO.read(categoriaId);
		
		AtualizarValoresOrcados.atualizarPrecoOrcamento(
			AtualizarValoresOrcados.EXCLUIR,
			categoriaId,
			categoria.getValorEstimado()
		);
			
		AtualizarValoresRealizados.atualizarPrecoOrcamento(
			AtualizarValoresRealizados.EXCLUIR,
			categoriaId,
			categoria.getValorRealizado()
		);
		
		categoriaDAO.delete(categoriaId);
		
		AtualizarValoresEstimados.atualizarValorEstimadoOrcamento(categoria.getOrcamentoId());
		
		excluirRubrica(categoriaId);
		
		return true;
	}

	private static void excluirRubrica(Long categoriaId) {
		
		for(Rubrica rubrica : rubricaDAO.getRubricas(categoriaId)) {
			rubricaDAO.delete(rubrica.getId());
			excluirItem(rubrica.getId());
		}
		
	}
	
	public static boolean excluirRubrica(HttpServletRequest req, HttpServletResponse resp) {
		
		Long rubricaId = Long.parseLong(req.getParameter("rubrica_id"));
		Rubrica rubrica = (Rubrica) rubricaDAO.read(rubricaId);
		
		AtualizarValoresOrcados.atualizarPrecoCategoria(
			AtualizarValoresOrcados.EXCLUIR,
			rubricaId,
			rubrica.getValorEstimado()
		);
			
		AtualizarValoresRealizados.atualizarPrecoCategoria(
			AtualizarValoresRealizados.EXCLUIR,
			rubricaId,
			rubrica.getValorRealizado()
		);
			
		rubricaDAO.delete(rubricaId);

		AtualizarValoresEstimados.atualizarValorEstimadoCategoria(rubrica.getCategoriaId());
		
		excluirItem(rubricaId);
		
		return true;
	}

	private static void excluirItem(Long rubricaId) {
		
		for(Item item : itemDAO.getItens(rubricaId)) {
			itemDAO.delete(item.getId());
			excluirNotaFiscal(item.getId());
		}
		
	}
	
	public static boolean excluirItem(HttpServletRequest req, HttpServletResponse resp) {
		
		Long itemId = Long.parseLong(req.getParameter("item_id"));
		
		//Se o Item tivesse valor parcial, ficaria assim:
		Item item = itemDAO.read(itemId);
		
		//Mas, como ele não tem, tenho que chamar a nota fiscal e passar o valor parcial dela, assim:
		
		
		/*NotaFiscal nota = notaFiscalDAO.getNotasFiscais(itemId);
		if(nota != null) {
			AtualizarPrecos.atualizarPrecoRubrica(AtualizarPrecos.EXCLUIR, itemId, nota.getValorParcial());
			excluirNotaFiscal(itemId);
		}*/
		
		AtualizarValoresOrcados.atualizarPrecoRubrica(
			AtualizarValoresOrcados.EXCLUIR,
			itemId,
			item.getValor()
		);
			
		AtualizarValoresRealizados.atualizarPrecoRubrica(
			AtualizarValoresRealizados.EXCLUIR,
			itemId,
			item.getValorRealizado()
		);
		
		itemDAO.delete(itemId);
		AtualizarValoresEstimados.atualizarValorEstimadoRubrica(item.getRubricaId());
		excluirNotaFiscal(item.getId());
		
		return true;
	}

	private static void excluirNotaFiscal(Long itemId) {
		
		/*NotaFiscal nota = notaFiscalDAO.getNotasFiscais(itemId);
		if(nota != null) {
			notaFiscalDAO.delete(nota.getId());
			excluirPagamento(nota.getId());
		}*/
		
		for(NotaFiscal nota : notaFiscalDAO.getNotasFiscais(itemId)) {
			notaFiscalDAO.delete(nota.getId());
			excluirPagamento(nota.getId());
		}
	}
	
	public static boolean excluirNotaFiscal(HttpServletRequest req, HttpServletResponse resp) {
		
		Long notaId = Long.parseLong(req.getParameter("nota_id"));
		
		NotaFiscal nota = notaFiscalDAO.read(notaId);
		
		AtualizarValoresOrcados.atualizarPrecoItem(
			AtualizarValoresOrcados.EXCLUIR,
			notaId,
			nota.getValor()
		);
			
		AtualizarValoresRealizados.atualizarPrecoItem(
			AtualizarValoresRealizados.EXCLUIR,
			notaId,
			nota.getValorRealizado()
		);
			
		notaFiscalDAO.delete(notaId);
		
		excluirPagamento(notaId);
		
		return true;
	}

	private static void excluirPagamento(Long notaId) {

		for(Pagamento pagamento : pagamentoDAO.getPagamentos(notaId)) {
			pagamentoDAO.delete(pagamento.getId());
		}
		
	}
	
	//TODO DISCUTIR SOBRE A EXCLUSAO DE UM PAGAMENTO!!!
	public static boolean excluirPagamento(HttpServletRequest req, HttpServletResponse resp) {
		
		Long pagamentoId = Long.parseLong(req.getParameter("pagamento_id"));
		
		Pagamento pagamento = pagamentoDAO.read(pagamentoId);

		//TODO DISCUTIR: atualizarPrecoPagamento ou atualizarPrecoNotaFiscal??
		AtualizarValoresRealizados.atualizarPrecoPagamento(
			AtualizarValoresRealizados.EXCLUIR,
			pagamentoId,
			pagamento.getValor()
		);
		
		System.out.println("SAIU DO ATUALIZAR PREÇOS!!");
		pagamentoDAO.delete(pagamentoId);
		
		return true;
	}

	public static void excluirFornecedor(HttpServletRequest req, HttpServletResponse resp) {

		Long fornecedorId = Long.parseLong(req.getParameter("fornecedor_id"));
		
		fornecedorDAO.delete(fornecedorId);
		
	}

}
