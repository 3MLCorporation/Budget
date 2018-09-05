package br.com.cpsoftware.budget.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.Blob;
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

import br.com.cpsoftware.budget.model.Item;
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
		itemEntity.setProperty(Item.RUBRICA_ID, item.getRubricaId());
		itemEntity.setProperty(Item.NOME, item.getNome());
		itemEntity.setProperty(Item.DESCRICAO, item.getDescricao());
		itemEntity.setProperty(Item.PRECO_UNITARIO, item.getPrecoUnitario());
		itemEntity.setProperty(Item.QUANTIDADE, item.getQuantidade());
		itemEntity.setProperty(Item.VALOR_ESTIMADO, item.getValorEstimado());
		itemEntity.setProperty(Item.VALOR_ORCADO, item.getValorOrcado());
		itemEntity.setProperty(Item.VALOR_REALIZADO, item.getValorRealizado());
		itemEntity.setProperty(Item.UNIDADE_MEDIDA, item.getUnidadeMedida());
		itemEntity.setProperty(Item.ARQUIVO_DETALHES, item.getArquivoDetalhes());
		itemEntity.setProperty(Item.ARQUIVO_AUXILIAR, item.getArquivoAuxiliar());
		
		
		Key itemKey = datastore.put(itemEntity);
		
		/*Rubrica rubrica = (Rubrica) new RubricaDAO().read(item.getRubricaId());
		Categoria categoria = (Categoria) new CategoriaDAO().read(((Rubrica) rubrica).getCategoriaId());
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(((Categoria) categoria).getOrcamentoId());
		Usuario usuario =  new UsuarioDAO().read(((Orcamento) orcamento).getUsuarioId());
		System.out.println("Item " + item.getNome()+ " da rubrica " + rubrica.getNome() + " da categoria " + categoria.getNome() + " do orcamento " + orcamento.getNome()
							+ " do usuario " + usuario.getNome() + " criado com id = " + itemKey.getId());*/
		
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
		Key key = KeyFactory.createKey(ITEM_KIND, item.getId());
		Entity itemEntity = new Entity(key);
		itemEntity.setProperty(Item.RUBRICA_ID, item.getRubricaId());
		itemEntity.setProperty(Item.NOME, item.getNome());
		itemEntity.setProperty(Item.DESCRICAO, item.getDescricao());
		itemEntity.setProperty(Item.PRECO_UNITARIO, item.getPrecoUnitario());
		itemEntity.setProperty(Item.QUANTIDADE, item.getQuantidade());
		itemEntity.setProperty(Item.VALOR_ESTIMADO, item.getValorEstimado());
		itemEntity.setProperty(Item.VALOR_ORCADO, item.getValorOrcado());
		itemEntity.setProperty(Item.VALOR_REALIZADO, item.getValorRealizado());
		itemEntity.setProperty(Item.UNIDADE_MEDIDA, item.getUnidadeMedida());
		itemEntity.setProperty(Item.ARQUIVO_DETALHES, item.getArquivoDetalhes());
		itemEntity.setProperty(Item.ARQUIVO_AUXILIAR, item.getArquivoAuxiliar());

		datastore.put(itemEntity);
	}

	public void delete(Long itemId) {
		Key key = KeyFactory.createKey(ITEM_KIND, itemId);
		datastore.delete(key);
	}
	
	private Item entityToItem(Entity itemEntity) {
		return new Item((Long)itemEntity.getProperty(Item.RUBRICA_ID),
				 itemEntity.getKey().getId(),
				 (String)itemEntity.getProperty(Item.NOME),
				 (String)itemEntity.getProperty(Item.DESCRICAO),
				 (Double)itemEntity.getProperty(Item.PRECO_UNITARIO),
				 ((Long)itemEntity.getProperty(Item.QUANTIDADE)).intValue(),
				 (Double)itemEntity.getProperty(Item.VALOR_ESTIMADO),
				 (Double)itemEntity.getProperty(Item.VALOR_ORCADO),
				 (Double)itemEntity.getProperty(Item.VALOR_REALIZADO),
				 ((Long)itemEntity.getProperty(Item.UNIDADE_MEDIDA)).intValue(),
				 (Blob)itemEntity.getProperty(Item.ARQUIVO_DETALHES),
				 (Blob)itemEntity.getProperty(Item.ARQUIVO_AUXILIAR));
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
	
	public List<Map<Object, Object>> getItensMaps(Long rubricaId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(ITEM_KIND).addSort(Item.NOME, SortDirection.ASCENDING);
		
		Filter rubricaFilter = new FilterPredicate(Item.RUBRICA_ID, FilterOperator.EQUAL, rubricaId);
		query.setFilter(rubricaFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> itemEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		
		List<Item> entitiesToItem = entitiesToItem(itemEntities);
		List <Map<Object, Object>> itensMapsList = new ArrayList<>();
		 
		for (Item item : entitiesToItem) {
			Map<Object, Object> itemMap = new HashMap<>();
			Rubrica rubrica = (Rubrica) new RubricaDAO().read(rubricaId);
			itemMap.put("nomeRubrica", rubrica.getNome());
			itemMap.put("nomeCategoria", new CategoriaDAO().read(rubrica.getCategoriaId()).getNome());
			itemMap.put("item", item);
			itensMapsList.add(itemMap);
		}
		return itensMapsList;
		
	}

}