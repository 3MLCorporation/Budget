package br.com.cpsoftware.budget.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.cpsoftware.budget.dao.CategoriaDAO;
import br.com.cpsoftware.budget.dao.OrcamentoDAO;
import br.com.cpsoftware.budget.model.Categoria;

public class GerarEstrutura {

	private static OrcamentoDAO orcamentoDao = new OrcamentoDAO();
	private static CategoriaDAO categoriaDao = new CategoriaDAO();
	
	private static final List<String> categoriasJson =  Arrays.asList(
			"{ \"nome\":\"Despesas com pessoal\", \"codigo\":\"2\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Saídas operacionais\", \"codigo\":\"3\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Saídas não operacionais\", \"codigo\":\"4\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }",
			"{ \"nome\":\"Patrimônio e Investimentos\", \"codigo\":\"5\", \"valorEstimado\":\"0\", \"valorOrcado\":\"0\", \"valorRealizado\":\"0\", \"valorComprovado\":\"0\" }"
	);
	
	public static void gerarCategorias(Long orcamentoId) {
		 
		 for(String categoriaJson: categoriasJson) {
			 Categoria categoria = new Gson().fromJson(categoriaJson, Categoria.class);
			 categoria.setOrcamentoId(orcamentoId);
			 Long categoriaId = categoriaDao.create(categoria);
			 gerarRubricas(categoriaId);
		 }
	}

	private static void gerarRubricas(Long categoriaId) {
		// TODO Auto-generated method stub
		
	}
	
}
