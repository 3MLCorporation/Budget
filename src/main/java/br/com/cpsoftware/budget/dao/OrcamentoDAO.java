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

import br.com.cpsoftware.budget.model.Entidade;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Usuario;

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
		orcamentoEntity.setProperty(Orcamento.USUARIO_ID, ((Orcamento) orcamento).getUsuarioId());
		orcamentoEntity.setProperty(Orcamento.NOME, orcamento.getNome());
		orcamentoEntity.setProperty(Orcamento.VALOR_TOTAL, orcamento.getValorTotal());
		
		Key orcamentoKey = datastore.put(orcamentoEntity);
		
		UsuarioDAO userDao = new UsuarioDAO();
		Usuario read = userDao.read(((Orcamento) orcamento).getUsuarioId());
		System.out.println("Orcamento " + orcamento.getNome() + " do usuário " + read.getNome() + " criado com id = " + orcamentoKey.getId());
		
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
		Key key = KeyFactory.createKey(ORCAMENTO_KIND, orcamento.getId());
		Entity orcamentoEntity = new Entity(key);
		orcamentoEntity.setProperty(Orcamento.NOME, orcamento.getNome());
		orcamentoEntity.setProperty(Orcamento.VALOR_TOTAL, orcamento.getValorTotal());
		orcamentoEntity.setProperty(Orcamento.VALOR_PARCIAL, orcamento.getValorParcial());

		datastore.put(orcamentoEntity);
	}

	@Override
	public void delete(Long orcamentoId) {
		Key key = KeyFactory.createKey(ORCAMENTO_KIND, orcamentoId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}

	private Orcamento entityToOrcamento(Entity orcamentoEntity) {
		
		/*System.out.println(orcamentoEntity.getProperty(Orcamento.ID));
		System.out.println(orcamentoEntity.getProperty(Orcamento.NOME));
		System.out.println(orcamentoEntity.getProperty(Orcamento.VALOR_TOTAL));*/
		
		return new Orcamento((Long) orcamentoEntity.getProperty(Orcamento.USUARIO_ID),
							orcamentoEntity.getKey().getId(),
							(String)orcamentoEntity.getProperty(Orcamento.NOME),
							((Double)orcamentoEntity.getProperty(Orcamento.VALOR_TOTAL)),
							(Double)orcamentoEntity.getProperty(Orcamento.VALOR_PARCIAL));
	}
	
	
	private List<Orcamento> entitiesToOrcamento(List<Entity> entities) {
		List<Orcamento> resultOrcamentos = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultOrcamentos.add(entityToOrcamento(entidade));
		}
		
		return resultOrcamentos;
	}
	
	
	
	public List<Orcamento> getOrcamentos(Long usuarioId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(ORCAMENTO_KIND).addSort(Orcamento.NOME, SortDirection.ASCENDING);
		
		Filter usuarioFilter = new FilterPredicate(Orcamento.USUARIO_ID, FilterOperator.EQUAL, usuarioId);
		query.setFilter(usuarioFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 List<Entity> orcamentoEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		 return entitiesToOrcamento(orcamentoEntities);
		
	}

}
