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
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;

public class ItemDAO{
	
	/*
	 * TODO Validação dos dados(entrada/banco)
	 */
	
	private DatastoreService datastore;
	private static final String ITEM_KIND = "Item";
	
	public ItemDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public Long create(Item item) {
		Entity itemEntity = new Entity(ITEM_KIND);
		itemEntity.setProperty(Item.NOME, item.getNome());
		itemEntity.setProperty(Item.DESCRICAO, item.getDescricao());
		itemEntity.setProperty(Item.VALOR_UNIFORME, item.getValor_uniforme());
		itemEntity.setProperty(Item.QUANTIDADE, item.getQuantidade());
		
		Key itemKey = datastore.put(itemEntity);
		return itemKey.getId();
	}

	public Item read(Long itemId) {
		try {
		    Entity itemEntity = datastore.get(KeyFactory.createKey(ITEM_KIND, itemId));
		    return entityToItem(itemEntity);
		  } catch (EntityNotFoundException e) {
		    return null;
		  }
	}

	public void update(Item item) {
		Key key = KeyFactory.createKey(ITEM_KIND, item.getId());  // From a book, create a Key
		Entity itemEntity = new Entity(key);         // Convert Book to an Entity
		itemEntity.setProperty(Orcamento.NOME, item.getNome());
		itemEntity.setProperty(Item.DESCRICAO, item.getDescricao());
		itemEntity.setProperty(Item.VALOR_UNIFORME, item.getValor_uniforme());
		itemEntity.setProperty(Item.QUANTIDADE, item.getQuantidade());

		datastore.put(itemEntity);                   // Update the Entity
	}

	public void delete(Long itemId) {
		Key key = KeyFactory.createKey(ITEM_KIND, itemId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}
	
	private Item entityToItem(Entity itemEntity) {
		return new Item((Long)itemEntity.getProperty(Item.ID),
				 (String)itemEntity.getProperty(Item.NOME),
				 (String)itemEntity.getProperty(Item.DESCRICAO),
				 (Double)itemEntity.getProperty(Item.VALOR_UNIFORME),
				 (Long)itemEntity.getProperty(Item.QUANTIDADE));
	}
	
	private List<Item> entitiesToItem(List<Entity> entities) {
		List<Item> resultItens = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultItens.add(entityToItem(entidade));
		}
		
		return resultItens;
	}
	
	
	
	public List<Item> getItens(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(ITEM_KIND).addSort(Item.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 List<Entity> itemEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		 return entitiesToItem(itemEntities);
		
	}

}