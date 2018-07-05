package br.com.cpsoftware.budget.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.cpsoftware.budget.model.Rubrica;

public class RubricaDAO implements EntidadeDao{

	/*
	 * TODO Validação dos dados(entrada/banco)
	 */
	
	private DatastoreService datastore;
	private static final String RUBRICA_KIND = "Rubrica";
	
	public RubricaDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public Long create(Entidade rubrica) {
		Entity rubricaEntity = new Entity(RUBRICA_KIND);
		rubricaEntity.setProperty(Rubrica.CATEGORIA_ID, ((Rubrica) rubrica).getCategoriaId());
		rubricaEntity.setProperty(Rubrica.NOME, rubrica.getNome());
		rubricaEntity.setProperty(Rubrica.VALOR_ESTIMADO, rubrica.getValorEstimado());
		rubricaEntity.setProperty(Rubrica.VALOR_ORCADO, rubrica.getValorOrcado());
		rubricaEntity.setProperty(Rubrica.VALOR_REALIZADO, rubrica.getValorRealizado());
		
		Key rubricaKey = datastore.put(rubricaEntity);

		System.out.println("categoriaId = " + ((Rubrica) rubrica).getCategoriaId());
		
		/*Categoria categoria = (Categoria) new CategoriaDAO().read(((Rubrica) rubrica).getCategoriaId());
		Orcamento orcamento = (Orcamento) new OrcamentoDAO().read(((Categoria) categoria).getOrcamentoId());
		Usuario usuario =  new UsuarioDAO().read(((Orcamento) orcamento).getUsuarioId());
		System.out.println("Rubrica " + rubrica.getNome() + " da categoria " + categoria.getNome() + " do orcamento " + orcamento.getNome()
							+ " do usuario " + usuario.getNome() + " criada com id = " + rubricaKey.getId());*/
		
		return rubricaKey.getId();
	}

	@Override
	public Entidade read(Long rubricaId) {
		try {
		    Entity rubricaEntity = datastore.get(KeyFactory.createKey(RUBRICA_KIND, rubricaId));
		    return entityToRubrica(rubricaEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	@Override
	public void update(Entidade rubrica) {
		Key key = KeyFactory.createKey(RUBRICA_KIND, rubrica.getId()); 
		Entity rubricaEntity = new Entity(key);
		rubricaEntity.setProperty(Rubrica.CATEGORIA_ID, ((Rubrica) rubrica).getCategoriaId());
		rubricaEntity.setProperty(Rubrica.NOME, rubrica.getNome());
		rubricaEntity.setProperty(Rubrica.VALOR_ESTIMADO, rubrica.getValorEstimado());
		rubricaEntity.setProperty(Rubrica.VALOR_ORCADO, rubrica.getValorOrcado());
		rubricaEntity.setProperty(Rubrica.VALOR_REALIZADO, rubrica.getValorRealizado());

		datastore.put(rubricaEntity);
		
	}

	@Override
	public void delete(Long rubricaId) {
		Key key = KeyFactory.createKey(RUBRICA_KIND, rubricaId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
		
	}
	
	private Rubrica entityToRubrica(Entity rubricaEntity) {
		return new Rubrica((Long) rubricaEntity.getProperty(Rubrica.CATEGORIA_ID) ,
						 rubricaEntity.getKey().getId(),
						 (String)rubricaEntity.getProperty(Rubrica.NOME),
						 (Double)rubricaEntity.getProperty(Rubrica.VALOR_ESTIMADO),
						 (Double)rubricaEntity.getProperty(Rubrica.VALOR_ORCADO),
						 (Double)rubricaEntity.getProperty(Rubrica.VALOR_REALIZADO));
	}
	
	private List<Rubrica> entitiesToRubrica(List<Entity> entities) {
		
		List<Rubrica> resultRubricas = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultRubricas.add(entityToRubrica(entidade));
		}
		
		return resultRubricas;
	}
	
	
	
	public List<Rubrica> getRubricas(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(RUBRICA_KIND).addSort(Rubrica.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> rubricaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToRubrica(rubricaEntities);
		
	}
	
	public List<Rubrica> getRubricas(Long categoriaId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(RUBRICA_KIND).addSort(Rubrica.NOME, SortDirection.ASCENDING);
		
		Filter categoriaFilter = new FilterPredicate(Rubrica.CATEGORIA_ID, FilterOperator.EQUAL, categoriaId);
		query.setFilter(categoriaFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> rubricaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		return entitiesToRubrica(rubricaEntities);
		
	}
	
	public List<Map<Object, Object>> getRubricasMaps(Long categoriaId){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(RUBRICA_KIND).addSort(Rubrica.NOME, SortDirection.ASCENDING);
		
		Filter categoriaFilter = new FilterPredicate(Rubrica.CATEGORIA_ID, FilterOperator.EQUAL, categoriaId);
		query.setFilter(categoriaFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		List<Entity> rubricaEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		
		List<Rubrica> entitiesToRubrica = entitiesToRubrica(rubricaEntities);
		List <Map<Object, Object>> rubricasMapsList = new ArrayList<>();
		
		for(Rubrica rubrica : entitiesToRubrica) {
			Map<Object, Object> rubricaMap = new HashMap<>();
			rubricaMap.put("nomeCategoria", new CategoriaDAO().read(categoriaId).getNome());
			rubricaMap.put("rubrica", rubrica);
			rubricasMapsList.add(rubricaMap);
		}
		
		return rubricasMapsList;
		
	}
	

}
