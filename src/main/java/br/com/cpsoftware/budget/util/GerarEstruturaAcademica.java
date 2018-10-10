package br.com.cpsoftware.budget.util;

import java.util.Arrays;
import java.util.List;

import com.google.appengine.api.datastore.Blob;
import com.google.gson.Gson;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.ItemDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.dao.RubricaDAO;
import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Rubrica;

public class GerarEstruturaAcademica {
	
	private static OrcamentoDAO orcamentoDao = new OrcamentoDAO();
	private static CategoriaDAO categoriaDao = new CategoriaDAO();
	private static RubricaDAO rubricaDao = new RubricaDAO();
	private static ItemDAO itemDao = new ItemDAO();
	
	private static final List<String> categoriasJson =  Arrays.asList(
			"{ \"nome\":\"Despesas com pessoal\", \"codigo\":\"2\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Saídas operacionais\", \"codigo\":\"3\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Saídas não operacionais\", \"codigo\":\"4\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Patrimônio e Investimentos\", \"codigo\":\"5\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Movimentação financeira\", \"codigo\":\"6\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"
	);
	
	private static final List<List<String>> rubricasJson = Arrays.asList(
			Arrays.asList("{ \"nome\":\"Folha de Pagamento\", \"codigo\":\"1\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
			Arrays.asList("{ \"nome\":\"Serviços Operacionais\", \"codigo\":\"1\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
						"{ \"nome\":\"Despesas Operacionais\", \"codigo\":\"2\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
						"{ \"nome\":\"Vestibular\", \"codigo\":\"3\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
						"{ \"nome\":\"ENADE\", \"codigo\":\"4\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
			Arrays.asList("{ \"nome\":\"Administrativas\", \"codigo\":\"1\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
			Arrays.asList("{ \"nome\":\" Patrimônio e Investimentos\", \"codigo\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
			Arrays.asList("{ \"nome\":\"Mov.Finan.- Entradas\", \"codigo\":\"1\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Mov.Finan.-Saídas\", \"codigo\":\"2\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }")
	);

	/*private static final List<List<String>> itensDespesasComPessoal = Arrays.asList(
		Arrays.asList("{ \"nome\":\"Salários e Ordenados\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Gratificações extra salariais\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Diárias em Viagens\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Hora Extra\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Vale de Transporte\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Plano de Saúde\", \"codigo\":\"6\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Bolsas e Estágio\", \"codigo\":\"7\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Monitoria\", \"codigo\":\"8\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"INSS s/ Folha\", \"codigo\":\"9\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"FGTS\", \"codigo\":\"10\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Outros Encargos s/ Folha\", \"codigo\":\"11\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"\bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"EPI/EPC, Uniformes e Fardamentos\", \"codigo\":\"12\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"\bytes\":[0]} }",
					"{ \"nome\":\"Auxilio Transporte e Alimentação\", \"codigo\":\"13\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Rescisão Trabalhista e Aviso Prévio\", \"codigo\":\"14\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Acordos Trabalhistas\", \"codigo\":\"15\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Vale Alimentação\", \"codigo\":\"16\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Contribuição Sindical\", \"codigo\":\"17\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }",
					"{ \"nome\":\"Consignado e Descontos em Folha\", \"codigo\":\"18\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\", \"arquivoDetalhes\":{\"bytes\":[0]}, \"arquivoAuxiliar\":{\"bytes\":[0]} }")
	);*/
	private static final List<List<String>> itensDespesasComPessoal = Arrays.asList(
		Arrays.asList("{ \"nome\":\"Salários e Ordenados\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Gratificações extra salariais\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Diárias em Viagens\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Hora Extra\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Vale de Transporte\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Plano de Saúde\", \"codigo\":\"6\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Bolsas e Estágio\", \"codigo\":\"7\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Monitoria\", \"codigo\":\"8\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"INSS s/ Folha\", \"codigo\":\"9\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"FGTS\", \"codigo\":\"10\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Outros Encargos s/ Folha\", \"codigo\":\"11\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"EPI/EPC, Uniformes e Fardamentos\", \"codigo\":\"12\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Auxilio Transporte e Alimentação\", \"codigo\":\"13\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Rescisão Trabalhista e Aviso Prévio\", \"codigo\":\"14\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Acordos Trabalhistas\", \"codigo\":\"15\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Vale Alimentação\", \"codigo\":\"16\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Contribuição Sindical\", \"codigo\":\"17\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}",
					"{ \"nome\":\"Consignado e Descontos em Folha\", \"codigo\":\"18\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\"}")
	);
	private static final List<List<String>> itensSaidasOperacionais = Arrays.asList(
		Arrays.asList("{ \"nome\":\"Professores PJ\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }", //TODO Apagar propriedades repetidas e desnecessarias 
					"{ \"nome\":\"Professores PF\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Transporte e Hospedagens - Professores\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
		Arrays.asList("{ \"nome\":\"Energia\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Água e Esgoto\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Material de Apoio Aulas - Insumos\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Livros, Material Didático e Periódicos\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Alimentação - Corpo Acadêmico\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Alugueis de imóveis Acadêmicos\", \"codigo\":\"6\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Feiras, Seminários,  Eventos e Congressos\", \"codigo\":\"7\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Cursos e Treinamentos - Corpo Acadêmico\", \"codigo\":\"8\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Mat. Consumo de Laboratórios e Clínicas\", \"codigo\":\"9\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Estagios/Convenios/Amb/Hosp/Laboratórios\", \"codigo\":\"10\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Serviços Prestados Laboratório\", \"codigo\":\"11\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
		Arrays.asList("{ \"nome\":\"Vestib.Marketing e Propaganda\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Vestib.Pessoal, Alimentação e Transporte\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"Vestib.Prova,Segurança e Outros Materiais\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
		Arrays.asList("{ \"nome\":\"ENADE - Lanches\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"ENADE - Serv.Prest.PF\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"ENADE - Serv.Prest.PJ\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"ENADE - Locação\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
					"{ \"nome\":\"ENADE - Mat.Expediente\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }")
	);
	
	private static final List<List<String>> itensSaidasNaoOperacionais = Arrays.asList(
		Arrays.asList("{ \"nome\":\"Aluguéis Administrativos\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Serviços Jurídicos\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Consultorias e Auditorias\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Papelaria e Material de Expediente\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Lanches e Refeições\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Taxas Cartoriais\", \"codigo\":\"6\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Treinamentos - Administrativo\", \"codigo\":\"7\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Transporte e Combustível - ADM\", \"codigo\":\"8\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Telefone\", \"codigo\":\"9\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Internet\", \"codigo\":\"10\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Viagens e Hospedagens - ADM\", \"codigo\":\"11\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Marketing\", \"codigo\":\"12\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Propaganda e Publicidade (Outros)\", \"codigo\":\"13\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Taxas\", \"codigo\":\"14\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Locação de Máquinas e Equipamentos\", \"codigo\":\"15\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Assinatura de Jornais e Revistas\", \"codigo\":\"16\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Licença de Uso e Mensalidades - Sistemas\", \"codigo\":\"17\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Material e Serviços de Limpeza\", \"codigo\":\"18\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Manutenção Predial e Reformas\", \"codigo\":\"19\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Manutenção de Veículos\", \"codigo\":\"20\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Manutenção de Ar Condicionados\", \"codigo\":\"21\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Manutenção Móveis e Utensílios\", \"codigo\":\"22\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Segurança Patrimonial\", \"codigo\":\"23\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Seguros\", \"codigo\":\"24\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Auxílio Donativos e Doações\", \"codigo\":\"25\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Correios e Telégrafos\", \"codigo\":\"26\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Propaganda e Publicidade de Televisão\", \"codigo\":\"27\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Propaganda e Publicidade de Jornais\", \"codigo\":\"28\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Propaganda e Publicidade Rádios\", \"codigo\":\"29\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Impostos - I.R.\", \"codigo\":\"30\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Impostos - INSS\", \"codigo\":\"31\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Impostos - PIS/COFINS/CSLL\", \"codigo\":\"32\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Impostos - I.S.S.\", \"codigo\":\"33\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Associações e Entidades\", \"codigo\":\"34\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Manutenção de Equipamentos\", \"codigo\":\"35\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Custas Processuais e de Cobrança\", \"codigo\":\"36\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Eventos Institucionais\", \"codigo\":\"37\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }")
		
	);
	
	private static final List<List<String>> itensPatrimoniosInvestimentos = Arrays.asList(
		Arrays.asList("{ \"nome\":\"Informática e Tecnologia\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Aquisição de Veículos\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Aquisição de Imóveis e Terrenos\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Aquisição de Móveis e Utensílios\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Equipamentos de Laboratório e Clínicas\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Investimentos em Imóveis e Terrenos\", \"codigo\":\"6\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Adiantamento a Fornecedores\", \"codigo\":\"7\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Aquisição de Maquinas e Equipamentos\", \"codigo\":\"9\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }")
		
	);
	
	private static final List<List<String>> itensMovimentacaoFinanceira = Arrays.asList(
		Arrays.asList("{ \"nome\":\"Resgate de Aplicação - Principal\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Juros sobre Aplicações\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Emprestimos e Financiamentos\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"),
		Arrays.asList("{ \"nome\":\"Aplicação Financeira\", \"codigo\":\"1\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Amortização de Emprestimos\", \"codigo\":\"2\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Juros sobre Emprestimos\", \"codigo\":\"3\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Juros e Multa sobre Pagamentos\", \"codigo\":\"4\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
				"{ \"nome\":\"Taxas e Serviços Bancários\", \"codigo\":\"5\", \"descricao\":\"\", \"precoUnitario\":\"0\", \"quantidade\":\"0\", \"unidadeMedida\":\"0\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }")
		
	);
	
	private static final List<List<List<String>>> itensJson = Arrays.asList(
			itensDespesasComPessoal,
			itensSaidasOperacionais,
			itensSaidasNaoOperacionais,
			itensPatrimoniosInvestimentos,
			itensMovimentacaoFinanceira
	);
	
	
	public static void gerarCategorias(Long orcamentoId) {
		 int i = 0;
		 for(String categoriaJson : categoriasJson) {
			 Categoria categoria = new Gson().fromJson(categoriaJson, Categoria.class);
			 categoria.setOrcamentoId(orcamentoId);
			 
			 Long categoriaId = categoriaDao.create(categoria);
			 gerarRubricas(categoriaId, rubricasJson.get(i), itensJson.get(i));
			 
			 i++;
		 }
	}

	private static void gerarRubricas(Long categoriaId, List<String> rubricaJson, List<List<String>> itemJson) {
		int i = 0;
		for(String json : rubricaJson) {
			Rubrica rubrica = new Gson().fromJson(json, Rubrica.class); 
			rubrica.setCategoriaId(categoriaId);
			
			Long rubricaId = rubricaDao.create(rubrica);
			gerarItens(rubricaId, itemJson.get(i));
			
			i++;
		}
	}

	private static void gerarItens(Long rubricaId, List<String> itemJson) {
		for(String json : itemJson) {
			Item item = new Gson().fromJson(json, Item.class);
			item.setRubricaId(rubricaId);
			
			Blob emptyBlob = new Blob(new byte[0]);
			item.setArquivoDetalhes(emptyBlob);
			item.setArquivoAuxiliar(emptyBlob);
			
			itemDao.create(item);
		}
		
	}
}
