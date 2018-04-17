package br.com.cpsoftware.budget.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import br.com.cpsoftware.budget.model.Categoria;
import br.com.cpsoftware.budget.model.Entidade;
import br.com.cpsoftware.budget.model.Orcamento;

public class CategoriaDAO implements EntidadeDao{

	/*
	 * TODO Validação dos dados(entrada/banco)
	 * TODO Criação de construtor sem id
	 */
	
	private DatastoreService datastore;
	private static final String CATEGORIA_KIND = "Categoria";
	
	public CategoriaDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	@Override
	public Long create(Entidade categoria) {
		Entity categoriaEntity = new Entity(CATEGORIA_KIND);
		categoriaEntity.setProperty(Orcamento.NOME, categoria.getNome());
		categoriaEntity.setProperty(Orcamento.VALOR_TOTAL, categoria.getValorTotal());
		
		Key categoriaKey = datastore.put(categoriaEntity);
		return categoriaKey.getId();
	}

	@Override
	public Entidade read(Long categoriaId) {
		try {
		    Entity categoriaEntity = datastore.get(KeyFactory.createKey(CATEGORIA_KIND, categoriaId));
		    return categoriaToOrcamento(categoriaEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	@Override
	public void update(Entidade categoria) {
		Key key = KeyFactory.createKey(CATEGORIA_KIND, categoria.getId());  // From a book, create a Key
		Entity categoriaEntity = new Entity(key);         // Convert Book to an Entity
		categoriaEntity.setProperty(Orcamento.NOME, categoria.getNome());
		categoriaEntity.setProperty(Orcamento.VALOR_TOTAL, categoria.getValorTotal());
		categoriaEntity.setProperty(Orcamento.VALOR_PARCIAL, categoria.getValorParcial());

		datastore.put(categoriaEntity);                   // Update the Entity
		
	}

	@Override
	public void delete(Long categoriaId) {
		Key key = KeyFactory.createKey(CATEGORIA_KIND, categoriaId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}
	
	private Entidade categoriaToOrcamento(Entity categoriaEntity) {
		return new Categoria((Long)categoriaEntity.getProperty(Categoria.ID),
				 (String)categoriaEntity.getProperty(Categoria.NOME),
				 (Float)categoriaEntity.getProperty(Categoria.VALOR_TOTAL),
				 (Float)categoriaEntity.getProperty(Categoria.VALOR_PARCIAL));
	}

}
