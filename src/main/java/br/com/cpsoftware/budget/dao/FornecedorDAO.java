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

import br.com.cpsoftware.budget.model.Fornecedor;

public class FornecedorDAO {
	
	private DatastoreService datastore;
	private static final String FORNECEDOR_KIND = "Fornecedor";
	
	public FornecedorDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public Long create(Fornecedor fornecedor) {
		Entity fornecedorEntity = new Entity(FORNECEDOR_KIND);
		fornecedorEntity.setProperty(Fornecedor.NOME, fornecedor.getNome());
		fornecedorEntity.setProperty(Fornecedor.CNPJ, fornecedor.getCnpj());
		fornecedorEntity.setProperty(Fornecedor.UF, fornecedor.getUf());
		
		Key fornecedorKey = datastore.put(fornecedorEntity);
		
		return fornecedorKey.getId();
	}
	
	public Fornecedor read(Long fornecedorId) {
		try {
		    Entity fornecedorEntity = datastore.get(KeyFactory.createKey(FORNECEDOR_KIND, fornecedorId));
		    return entityToFornecedor(fornecedorEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}
	
	public void update(Fornecedor fornecedor) {
		Key key = KeyFactory.createKey(FORNECEDOR_KIND, fornecedor.getId());
		Entity fornecedorEntity = new Entity(key);
		
		fornecedorEntity.setProperty(Fornecedor.NOME, fornecedor.getNome());
		fornecedorEntity.setProperty(Fornecedor.CNPJ, fornecedor.getCnpj());
		fornecedorEntity.setProperty(Fornecedor.UF, fornecedor.getUf());

		datastore.put(fornecedorEntity);
	}
	
	public void delete(Long fornecedorId) {
		Key key = KeyFactory.createKey(FORNECEDOR_KIND, fornecedorId);
		datastore.delete(key);
	}
	
	private Fornecedor entityToFornecedor(Entity fornecedorEntity) {
		return new Fornecedor(fornecedorEntity.getKey().getId(),
							 (String)fornecedorEntity.getProperty(Fornecedor.NOME),
							 (String)fornecedorEntity.getProperty(Fornecedor.CNPJ),
							 (String)fornecedorEntity.getProperty(Fornecedor.UF));
	}
	
	private List<Fornecedor> entitiesToFornecedor(List<Entity> entities) {
		List<Fornecedor> resultFornecedores = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultFornecedores.add(entityToFornecedor(entidade));
		}
		
		return resultFornecedores;
	}
	
	public List<Fornecedor> getFornecedores(){
		
		Query query = new Query(FORNECEDOR_KIND).addSort(Fornecedor.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> fornecedorEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToFornecedor(fornecedorEntities);
		
	}

}
