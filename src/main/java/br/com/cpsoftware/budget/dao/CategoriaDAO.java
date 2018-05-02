package br.com.cpsoftware.budget.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Entidade;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Usuario;

public class CategoriaDAO implements EntidadeDao{

	/*
	 * TODO Validação dos dados(entrada/banco)
	 * TODO Criação de construtor sem id
	 */
	
	private DatastoreService datastore;
	private static final String CATEGORIA_KIND = "Categoria";
	
	public CategoriaDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public Long create(Entidade categoria) {
		Entity categoriaEntity = new Entity(CATEGORIA_KIND);
		categoriaEntity.setProperty(Categoria.ORCAMENTO_ID, ((Categoria) categoria).getOrcamentoId());
		categoriaEntity.setProperty(Categoria.NOME, categoria.getNome());
		categoriaEntity.setProperty(Categoria.VALOR_TOTAL, categoria.getValorTotal());
		
		Key categoriaKey = datastore.put(categoriaEntity);
		
		
		/*Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(((Categoria) categoria).getOrcamentoId());
		Usuario usuario =  new UsuarioDAO().read(((Orcamento) orcamento).getUsuarioId());
		System.out.println("Categoria " + categoria.getNome() + " do orcamento " + orcamento.getNome()
							+ " do usuario " + usuario.getNome() + " criada com id = " + categoriaKey.getId());*/
		
		return categoriaKey.getId();
	}

	@Override
	public Entidade read(Long categoriaId) {
		try {
			System.out.println("categoriaId = " + categoriaId);
		    Entity categoriaEntity = datastore.get(KeyFactory.createKey(CATEGORIA_KIND, categoriaId));
		    return entityToCategoria(categoriaEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	@Override
	public void update(Entidade categoria) {
		Key key = KeyFactory.createKey(CATEGORIA_KIND, categoria.getId()); 
		Entity categoriaEntity = new Entity(key);         
		categoriaEntity.setProperty(Orcamento.NOME, categoria.getNome());
		categoriaEntity.setProperty(Orcamento.VALOR_TOTAL, categoria.getValorTotal());
		categoriaEntity.setProperty(Orcamento.VALOR_PARCIAL, categoria.getValorParcial());

		datastore.put(categoriaEntity);                   
		
	}

	@Override
	public void delete(Long categoriaId) {
		Key key = KeyFactory.createKey(CATEGORIA_KIND, categoriaId); 
		datastore.delete(key); 
	}
	
	private Categoria entityToCategoria(Entity categoriaEntity) {
		return new Categoria((Long) categoriaEntity.getProperty(Categoria.ORCAMENTO_ID),
							categoriaEntity.getKey().getId(),
							 (String)categoriaEntity.getProperty(Categoria.NOME),
							 (Double)categoriaEntity.getProperty(Categoria.VALOR_TOTAL),
							 (Double)categoriaEntity.getProperty(Categoria.VALOR_PARCIAL));
	}
	
	private List<Categoria> entitiesToCategoria(List<Entity> entities) {
		List<Categoria> resultCategorias = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultCategorias.add(entityToCategoria(entidade));
		}
		
		return resultCategorias;
	}
	
	
	
	public List<Categoria> getCategorias(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(CATEGORIA_KIND).addSort(Categoria.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> categoriaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToCategoria(categoriaEntities);
		
	}
	public List<Categoria> getCategorias(Long orcamentoId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(CATEGORIA_KIND).addSort(Categoria.NOME, SortDirection.ASCENDING);
		
		Filter orcamentoFilter = new FilterPredicate(Categoria.ORCAMENTO_ID, FilterOperator.EQUAL, orcamentoId);
		query.setFilter(orcamentoFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> categoriaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToCategoria(categoriaEntities);
		
	}

}
