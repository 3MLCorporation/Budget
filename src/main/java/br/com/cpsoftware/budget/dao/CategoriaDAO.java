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
import com.google.appengine.api.datastore.Query.SortDirection;

import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Entidade;
import br.com.cpsoftware.budget.model.Orcamento;

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
		categoriaEntity.setProperty(Orcamento.NOME, categoria.getNome());
		categoriaEntity.setProperty(Orcamento.VALOR_TOTAL, categoria.getValorTotal());
		
		Key categoriaKey = datastore.put(categoriaEntity);
		return categoriaKey.getId();
	}

	@Override
	public Entidade read(Long categoriaId) {
		try {
		    Entity categoriaEntity = datastore.get(KeyFactory.createKey(CATEGORIA_KIND, categoriaId));
		    return entityToCategoria(categoriaEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	@Override
	public void update(Entidade categoria) {
		Key key = KeyFactory.createKey(CATEGORIA_KIND, categoria.getId());  // From a book, create a Key
		Entity categoriaEntity = new Entity(key);         // Convert Book to an Entity
		categoriaEntity.setProperty(Orcamento.NOME, categoria.getNome());
		categoriaEntity.setProperty(Orcamento.VALOR_TOTAL, categoria.getValorTotal());
		categoriaEntity.setProperty(Orcamento.VALOR_PARCIAL, categoria.getValorParcial());

		datastore.put(categoriaEntity);                   // Update the Entity
		
	}

	@Override
	public void delete(Long categoriaId) {
		Key key = KeyFactory.createKey(CATEGORIA_KIND, categoriaId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}
	
	private Entidade entityToCategoria(Entity categoriaEntity) {
		return new Categoria((Long)categoriaEntity.getProperty(Categoria.ID),
				 (String)categoriaEntity.getProperty(Categoria.NOME),
				 (Double)categoriaEntity.getProperty(Categoria.VALOR_TOTAL),
				 (Double)categoriaEntity.getProperty(Categoria.VALOR_PARCIAL));
	}
	
	private List<Entidade> entitiesToCategoria(List<Entity> entities) {
		List<Entidade> resultCategorias = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultCategorias.add(entityToCategoria(entidade));
		}
		
		return resultCategorias;
	}
	
	
	
	public List<Entidade> getCategorias(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(CATEGORIA_KIND).addSort(Categoria.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 List<Entity> categoriaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		 return entitiesToCategoria(categoriaEntities);
		
	}

}
