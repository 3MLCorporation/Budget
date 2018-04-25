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
import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;
import br.com.cpsoftware.budget.model.Usuario;

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
		itemEntity.setProperty(Item.RUBRICA_ID, item.getRubricaId());
		itemEntity.setProperty(Item.NOME, item.getNome());
		itemEntity.setProperty(Item.DESCRICAO, item.getDescricao());
		itemEntity.setProperty(Item.VALOR_UNIFORME, item.getValor_uniforme());
		itemEntity.setProperty(Item.QUANTIDADE, item.getQuantidade());
		
		Key itemKey = datastore.put(itemEntity);
		
		Rubrica rubrica = (Rubrica) new RubricaDAO().read(item.getRubricaId());
		Categoria categoria = (Categoria) new CategoriaDAO().read(((Rubrica) rubrica).getCategoriaId());
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(((Categoria) categoria).getOrcamentoId());
		Usuario usuario =  new UsuarioDAO().read(((Orcamento) orcamento).getUsuarioId());
		System.out.println("Item " + item.getNome()+ "da rubrica " + rubrica.getNome() + " da categoria " + categoria.getNome() + "do orcamento " + orcamento.getNome()
							+ " do usuario " + usuario.getNome() + " criado com id = " + itemKey.getId());
		
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
		return new Item((Long)itemEntity.getProperty(Item.RUBRICA_ID),
				 itemEntity.getKey().getId(),
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
	
	public List<Item> getItens(Long rubricaId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(ITEM_KIND).addSort(Item.NOME, SortDirection.ASCENDING);
		
		Filter rubricaFilter = new FilterPredicate(Item.RUBRICA_ID, FilterOperator.EQUAL, rubricaId);
		query.setFilter(rubricaFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 List<Entity> itemEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		 return entitiesToItem(itemEntities);
		
	}

}