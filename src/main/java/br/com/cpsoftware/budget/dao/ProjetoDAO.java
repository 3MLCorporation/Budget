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
import br.com.cpsoftware.budget.model.Projeto;
import br.com.cpsoftware.budget.model.Usuario;

public class ProjetoDAO implements EntidadeDao{

	private DatastoreService datastore;
	private static final String PROJETO_KIND = "Projeto";
	
	public ProjetoDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public Long create(Entidade projeto) {
		
		Entity projetoEntity = new Entity(PROJETO_KIND);
		projetoEntity.setProperty(Projeto.GERENTE_ID, (( Projeto) projeto).getGerenteId());
		projetoEntity.setProperty(Projeto.NOME, projeto.getNome());
		projetoEntity.setProperty(Projeto.VALOR_ESTIMADO, projeto.getValorEstimado());
		projetoEntity.setProperty(Projeto.VALOR_REALIZADO, projeto.getValorRealizado());
		
		Key projetoKey = datastore.put(projetoEntity);
		
		UsuarioDAO userDao = new UsuarioDAO();
		Usuario gerente = userDao.read(((Projeto) projeto).getGerenteId());
		System.out.println("Projeto " + projeto.getNome() + " do gerente " + gerente.getNome() + " criado com id = " + projetoKey.getId());
		
		return projetoKey.getId();
		
	}

	@Override
	public Entidade read(Long projetoId) {
		try {
		    Entity projetoEntity = datastore.get(KeyFactory.createKey(PROJETO_KIND, projetoId));
		    return entityToProjeto(projetoEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	@Override
	public void update(Entidade projeto) {
		/*
		 * CORRIGIR
		 */
		Key key = KeyFactory.createKey(PROJETO_KIND, projeto.getId());
		Entity projetoEntity = new Entity(key);
		
		projetoEntity.setProperty(Projeto.GERENTE_ID, (( Projeto) projeto).getGerenteId());
		projetoEntity.setProperty(Projeto.NOME, projeto.getNome());
		projetoEntity.setProperty(Projeto.VALOR_ESTIMADO, projeto.getValorEstimado());
		projetoEntity.setProperty(Projeto.VALOR_REALIZADO, projeto.getValorRealizado());

		datastore.put(projetoEntity);
	}

	@Override
	public void delete(Long projetoId) {
		Key key = KeyFactory.createKey(PROJETO_KIND, projetoId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}

	private Projeto entityToProjeto(Entity projetoEntity) {
		
		/*System.out.println(orcamentoEntity.getProperty(Orcamento.ID));
		System.out.println(orcamentoEntity.getProperty(Orcamento.NOME));
		System.out.println(orcamentoEntity.getProperty(Orcamento.VALOR_TOTAL));*/
		
		return new Projeto((Long) projetoEntity.getProperty(Projeto.GERENTE_ID),
							projetoEntity.getKey().getId(),
							(String)projetoEntity.getProperty(Projeto.NOME),
							((Double)projetoEntity.getProperty(Projeto.VALOR_ESTIMADO)),
							(Double)projetoEntity.getProperty(Projeto.VALOR_REALIZADO));
	}
	
	
	private List<Projeto> entitiesToProjeto(List<Entity> entities) {
		List<Projeto> resultProjetos = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultProjetos.add(entityToProjeto(entidade));
		}
		
		return resultProjetos;
	}
	
	public List<Projeto> getProjetos(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(PROJETO_KIND).addSort(Projeto.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> projetoEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToProjeto(projetoEntities);
		
	}
	
	public List<Projeto> getProjetos(Long gerenteId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(PROJETO_KIND).addSort(Projeto.NOME, SortDirection.ASCENDING);
		
		Filter gerenteFilter = new FilterPredicate(Projeto.GERENTE_ID, FilterOperator.EQUAL, gerenteId);
		query.setFilter(gerenteFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> projetoEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToProjeto(projetoEntities);
		
	}

	
}
