package br.com.cpsoftware.budget.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import br.com.cpsoftware.budget.model.NotaFiscal;

public class NotaFiscalDAO {

	private DatastoreService datastore;
	private static final String NOTA_FISCAL_KIND = "NotaFiscal";
	
	public NotaFiscalDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public Long create(NotaFiscal nota) {
		Entity notaFiscalEntity = new Entity(NOTA_FISCAL_KIND);
		notaFiscalEntity.setProperty(NotaFiscal.ITEM_ID, nota.getItemId());
		notaFiscalEntity.setProperty(NotaFiscal.FORNECEDOR_ID, nota.getFornecedorId());
		notaFiscalEntity.setProperty(NotaFiscal.ARQUIVO, nota.getArquivo());
		notaFiscalEntity.setProperty(NotaFiscal.VALOR, nota.getValor());
		notaFiscalEntity.setProperty(NotaFiscal.VALOR_PARCIAL, nota.getValorParcial());
		notaFiscalEntity.setProperty(NotaFiscal.DATA, nota.getData());
		notaFiscalEntity.setProperty(NotaFiscal.STATUS, nota.getStatus());
		
		Key notaFiscalKey = datastore.put(notaFiscalEntity);
		
		return notaFiscalKey.getId();
	}
	
	public NotaFiscal read(Long notaId) {
		try {
		    Entity notaFiscalEntity = datastore.get(KeyFactory.createKey(NOTA_FISCAL_KIND, notaId));
		    return entityToNotaFiscal(notaFiscalEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	public void update(NotaFiscal nota) {
		Key key = KeyFactory.createKey(NOTA_FISCAL_KIND, nota.getId());
		Entity notaFiscalEntity = new Entity(key);
		
		notaFiscalEntity.setProperty(NotaFiscal.ITEM_ID, nota.getItemId());
		notaFiscalEntity.setProperty(NotaFiscal.FORNECEDOR_ID, nota.getFornecedorId());
		notaFiscalEntity.setProperty(NotaFiscal.ARQUIVO, nota.getArquivo());
		notaFiscalEntity.setProperty(NotaFiscal.VALOR, nota.getValor());
		notaFiscalEntity.setProperty(NotaFiscal.VALOR_PARCIAL, nota.getValorParcial());
		notaFiscalEntity.setProperty(NotaFiscal.DATA, nota.getData());
		notaFiscalEntity.setProperty(NotaFiscal.STATUS, nota.getStatus());

		datastore.put(notaFiscalEntity);
	}

	public void delete(Long notaId) {
		Key key = KeyFactory.createKey(NOTA_FISCAL_KIND, notaId);
		datastore.delete(key);
	}
	
	private NotaFiscal entityToNotaFiscal(Entity notaFiscalEntity) {
		return new NotaFiscal((Long)notaFiscalEntity.getProperty(NotaFiscal.ITEM_ID),
							 notaFiscalEntity.getKey().getId(),
							 (Long)notaFiscalEntity.getProperty(NotaFiscal.FORNECEDOR_ID),
							 (Blob)notaFiscalEntity.getProperty(NotaFiscal.ARQUIVO),
							 (Double)notaFiscalEntity.getProperty(NotaFiscal.VALOR),
							 (Double)notaFiscalEntity.getProperty(NotaFiscal.VALOR_PARCIAL),
							 (Date)notaFiscalEntity.getProperty(NotaFiscal.DATA),
							 ((Long) notaFiscalEntity.getProperty(NotaFiscal.STATUS)).intValue());
	}
	
	private List<NotaFiscal> entitiesToNotaFiscal(List<Entity> entities) {
		List<NotaFiscal> resultNotasFiscais = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultNotasFiscais.add(entityToNotaFiscal(entidade));
		}
		
		return resultNotasFiscais;
	}
	
	public List<NotaFiscal> getNotasFiscais(Long itemId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(NOTA_FISCAL_KIND).addSort(NotaFiscal.DATA, SortDirection.ASCENDING);
		
		Filter itemFilter = new FilterPredicate(NotaFiscal.ITEM_ID, FilterOperator.EQUAL, itemId);
		query.setFilter(itemFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> notaFiscalEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		
		return entitiesToNotaFiscal(notaFiscalEntities);
	}
}
