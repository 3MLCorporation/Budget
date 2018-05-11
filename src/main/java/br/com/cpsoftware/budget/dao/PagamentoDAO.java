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
import br.com.cpsoftware.budget.model.Pagamento;

public class PagamentoDAO {

	private DatastoreService datastore;
	private static final String PAGAMENTO_KIND = "Pagamento";
	
	public PagamentoDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	public Long create(Pagamento pagamento) {
		Entity pagamentoEntity = new Entity(PAGAMENTO_KIND);
		pagamentoEntity.setProperty(Pagamento.NOTA_FISCAL_ID, pagamento.getNotaFiscalId());
		pagamentoEntity.setProperty(Pagamento.ARQUIVO, pagamento.getArquivo());//???
		pagamentoEntity.setProperty(Pagamento.VALOR, pagamento.getValor());
		pagamentoEntity.setProperty(Pagamento.DATA, pagamento.getData());
		
		Key pagamentoKey = datastore.put(pagamentoEntity);
		
		return pagamentoKey.getId();
	}
	
	public Pagamento read(Long pagamentoId) {
		try {
		    Entity pagamentoEntity = datastore.get(KeyFactory.createKey(PAGAMENTO_KIND, pagamentoId));
		    return entityToPagamento(pagamentoEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	public void update(Pagamento pagamento) {
		Key key = KeyFactory.createKey(PAGAMENTO_KIND, pagamento.getId());
		Entity pagamentoEntity = new Entity(key);
		
		pagamentoEntity.setProperty(Pagamento.NOTA_FISCAL_ID, pagamento.getNotaFiscalId());
		pagamentoEntity.setProperty(Pagamento.ARQUIVO, pagamento.getArquivo());//???
		pagamentoEntity.setProperty(Pagamento.VALOR, pagamento.getValor());
		pagamentoEntity.setProperty(Pagamento.DATA, pagamento.getData());

		datastore.put(pagamentoEntity);
	}

	public void delete(Long notaId) {
		Key key = KeyFactory.createKey(PAGAMENTO_KIND, notaId);
		datastore.delete(key);
	}
	
	private Pagamento entityToPagamento(Entity pagamentoEntity) {
		return new Pagamento((Long)pagamentoEntity.getProperty(Pagamento.NOTA_FISCAL_ID),
				 pagamentoEntity.getKey().getId(),
				 (Blob)pagamentoEntity.getProperty(NotaFiscal.ARQUIVO),
				 (Double)pagamentoEntity.getProperty(NotaFiscal.VALOR),
				 (Date)pagamentoEntity.getProperty(NotaFiscal.DATA));
	}
	
	private List<Pagamento> entitiesToPagamento(List<Entity> entities) {
		List<Pagamento> resultNotasFiscais = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultNotasFiscais.add(entityToPagamento(entidade));
		}
		
		return resultNotasFiscais;
	}
	
	public List<Pagamento> getPagamentos(Long notaId){
		
		Query query = new Query(PAGAMENTO_KIND).addSort(Pagamento.DATA, SortDirection.DESCENDING);//TODO procurar a respeito de Object Date no datastore
		
		Filter notafiscalFilter = new FilterPredicate(Pagamento.NOTA_FISCAL_ID, FilterOperator.EQUAL, notaId);
		query.setFilter(notafiscalFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> pagamentoEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToPagamento(pagamentoEntities);
		
	}
}
