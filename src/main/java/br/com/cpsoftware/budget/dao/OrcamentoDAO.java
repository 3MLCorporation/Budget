package br.com.cpsoftware.budget.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import br.com.cpsoftware.budget.model.Entidade;
import br.com.cpsoftware.budget.model.Orcamento;

public class OrcamentoDAO implements EntidadeDao{
	
	/*
	 * TODO Validação dos dados(entrada/banco)
	 */
	
	private DatastoreService datastore;
	private static final String ORCAMENTO_KIND = "Orcamento";
	
	public OrcamentoDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public Long create(Entidade orcamento) {
		Entity orcamentoEntity = new Entity(ORCAMENTO_KIND);
		orcamentoEntity.setProperty(Orcamento.NOME, orcamento.getNome());
		orcamentoEntity.setProperty(Orcamento.VALOR_TOTAL, orcamento.getValorTotal());
		
		Key orcamentoKey = datastore.put(orcamentoEntity);
		return orcamentoKey.getId();
		
	}

	@Override
	public Entidade read(Long orcamentoId) {
		try {
		    Entity orcamentoEntity = datastore.get(KeyFactory.createKey(ORCAMENTO_KIND, orcamentoId));
		    return entityToOrcamento(orcamentoEntity);
		  } catch (EntityNotFoundException e) {
		    return null;
		  }
	}

	@Override
	public void update(Entidade orcamento) {
		Key key = KeyFactory.createKey(ORCAMENTO_KIND, orcamento.getId());  // From a book, create a Key
		Entity orcamentoEntity = new Entity(key);         // Convert Book to an Entity
		orcamentoEntity.setProperty(Orcamento.NOME, orcamento.getNome());
		orcamentoEntity.setProperty(Orcamento.VALOR_TOTAL, orcamento.getValorTotal());
		orcamentoEntity.setProperty(Orcamento.VALOR_PARCIAL, orcamento.getValorParcial());

		datastore.put(orcamentoEntity);                   // Update the Entity
	}

	@Override
	public void delete(Long orcamentoId) {
		Key key = KeyFactory.createKey(ORCAMENTO_KIND, orcamentoId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}

	private Entidade entityToOrcamento(Entity orcamentoEntity) {
		return new Orcamento((Long)orcamentoEntity.getProperty(Orcamento.ID),
							 (String)orcamentoEntity.getProperty(Orcamento.NOME),
							 (Float)orcamentoEntity.getProperty(Orcamento.VALOR_TOTAL),
							 (Float)orcamentoEntity.getProperty(Orcamento.VALOR_PARCIAL));
	}

}
