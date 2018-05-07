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

import br.com.cpsoftware.budget.model.OrcamentoUsuario;

public class OrcamentoUsuarioDAO {

	private DatastoreService datastore;
	private static final String ORCAMENTO_USUARIO_KIND = "OrcamentoUsuario";
	
	public OrcamentoUsuarioDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public Long create(OrcamentoUsuario orcamentoUsuario) {
		Entity orcamentoUsuarioEntity = new Entity(ORCAMENTO_USUARIO_KIND);
		orcamentoUsuarioEntity.setProperty(OrcamentoUsuario.EDITOR_ID, orcamentoUsuario.getEditorId());
		orcamentoUsuarioEntity.setProperty(OrcamentoUsuario.ORCAMENTO_ID, orcamentoUsuario.getOrcamentoId());
		
		Key orcamentoUsuarioKey = datastore.put(orcamentoUsuarioEntity);
		
		return orcamentoUsuarioKey.getId();
	}

	public OrcamentoUsuario read(Long orcamentoUsuarioId) {
		try {
		    Entity orcamentoUsuarioEntity = datastore.get(KeyFactory.createKey(ORCAMENTO_USUARIO_KIND, orcamentoUsuarioId));
		    return entityToOrcamentoUsuario(orcamentoUsuarioEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	public void update(OrcamentoUsuario orcamentoUsuario) {
		Key key = KeyFactory.createKey(ORCAMENTO_USUARIO_KIND, orcamentoUsuario.getId());
		Entity orcamentoUsuarioEntity = new Entity(key);
		
		orcamentoUsuarioEntity.setProperty(OrcamentoUsuario.EDITOR_ID, orcamentoUsuario.getEditorId());
		orcamentoUsuarioEntity.setProperty(OrcamentoUsuario.ORCAMENTO_ID, orcamentoUsuario.getOrcamentoId());
		
		datastore.put(orcamentoUsuarioEntity);
		
	}

	public void delete(Long orcamentoUsuarioId) {
		Key key = KeyFactory.createKey(ORCAMENTO_USUARIO_KIND, orcamentoUsuarioId);
		datastore.delete(key);
	}

	private OrcamentoUsuario entityToOrcamentoUsuario(Entity orcamentoUsuarioEntity) {
		return new OrcamentoUsuario(orcamentoUsuarioEntity.getKey().getId(),
								 (Long) orcamentoUsuarioEntity.getProperty(OrcamentoUsuario.EDITOR_ID),
								 (Long)orcamentoUsuarioEntity.getProperty(OrcamentoUsuario.ORCAMENTO_ID));
	}
	
	private List<OrcamentoUsuario> entitiesToOrcamentoUsuario(List<Entity> entities) {
		List<OrcamentoUsuario> resultOrcamentosUsuarios = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultOrcamentosUsuarios.add(entityToOrcamentoUsuario(entidade));
		}
		
		return resultOrcamentosUsuarios;
	}
	
	public List<OrcamentoUsuario> getOrcamentoUsuarioByUsuarioId(Long usuarioId) {
		
		Query query = new Query(ORCAMENTO_USUARIO_KIND).addSort(OrcamentoUsuario.EDITOR_ID, SortDirection.ASCENDING);
		
		Filter usuarioFilter = new FilterPredicate(OrcamentoUsuario.EDITOR_ID, FilterOperator.EQUAL, usuarioId);
		query.setFilter(usuarioFilter);
		
		PreparedQuery preparedQuery = this.datastore.prepare(query);
		
		List<Entity> orcamentoUsuarioEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToOrcamentoUsuario(orcamentoUsuarioEntities);
		
	}
	
	public List<OrcamentoUsuario> getOrcamentoUsuarioByOrcamentoId(Long orcamentoId) {
		
		Query query = new Query(ORCAMENTO_USUARIO_KIND).addSort(OrcamentoUsuario.ORCAMENTO_ID, SortDirection.ASCENDING);
		
		Filter usuarioFilter = new FilterPredicate(OrcamentoUsuario.ORCAMENTO_ID, FilterOperator.EQUAL, orcamentoId);
		query.setFilter(usuarioFilter);
		
		PreparedQuery preparedQuery = this.datastore.prepare(query);
		
		List<Entity> orcamentoUsuarioEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToOrcamentoUsuario(orcamentoUsuarioEntities);
		
	}
}
