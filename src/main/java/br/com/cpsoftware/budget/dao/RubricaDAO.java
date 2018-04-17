package br.com.cpsoftware.budget.dao;

import com.google.appengine.api.datastore.DatastoreService;

import br.com.cpsoftware.budget.model.Entidade;

public class RubricaDAO implements EntidadeDao{

	private DatastoreService datastore;
	private static final String RUBRICA_KIND = "Rubrica";
	
	@Override
	public Long create(Entidade tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entidade read(Long tipoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Entidade tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long tipoId) {
		// TODO Auto-generated method stub
		
	}

}
