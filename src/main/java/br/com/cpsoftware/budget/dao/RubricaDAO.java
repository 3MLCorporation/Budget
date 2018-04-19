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

import br.com.cpsoftware.budget.model.Entidade;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;

public class RubricaDAO implements EntidadeDao{

	/*
	 * TODO Validação dos dados(entrada/banco)
	 */
	
	private DatastoreService datastore;
	private static final String RUBRICA_KIND = "Rubrica";
	
	public RubricaDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public Long create(Entidade rubrica) {
		Entity rubricaEntity = new Entity(RUBRICA_KIND);
		rubricaEntity.setProperty(Orcamento.NOME, rubrica.getNome());
		rubricaEntity.setProperty(Orcamento.VALOR_TOTAL, rubrica.getValorTotal());
		
		Key rubricaKey = datastore.put(rubricaEntity);
		return rubricaKey.getId();
	}

	@Override
	public Entidade read(Long rubricaId) {
		try {
		    Entity rubricaEntity = datastore.get(KeyFactory.createKey(RUBRICA_KIND, rubricaId));
		    return entityToRubrica(rubricaEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	@Override
	public void update(Entidade rubrica) {
		Key key = KeyFactory.createKey(RUBRICA_KIND, rubrica.getId());  // From a book, create a Key
		Entity rubricaEntity = new Entity(key);         // Convert Book to an Entity
		rubricaEntity.setProperty(Rubrica.NOME, rubrica.getNome());
		rubricaEntity.setProperty(Rubrica.VALOR_TOTAL, rubrica.getValorTotal());
		rubricaEntity.setProperty(Rubrica.VALOR_PARCIAL, rubrica.getValorParcial());

		datastore.put(rubricaEntity);                   // Update the Entity
		
	}

	@Override
	public void delete(Long rubricaId) {
		Key key = KeyFactory.createKey(RUBRICA_KIND, rubricaId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
		
	}
	
	private Entidade entityToRubrica(Entity rubricaEntity) {
		return new Rubrica((Long)rubricaEntity.getProperty(Rubrica.ID),
				 (String)rubricaEntity.getProperty(Rubrica.NOME),
				 (Double)rubricaEntity.getProperty(Rubrica.VALOR_TOTAL),
				 (Double)rubricaEntity.getProperty(Rubrica.VALOR_PARCIAL));
	}
	
	private List<Entidade> entitiesToRubrica(List<Entity> entities) {
		List<Entidade> resultRubricas = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultRubricas.add(entityToRubrica(entidade));
		}
		
		return resultRubricas;
	}
	
	
	
	public List<Entidade> getRubricas(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(RUBRICA_KIND).addSort(Rubrica.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 List<Entity> rubricaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		 return entitiesToRubrica(rubricaEntities);
		
	}
	

}
