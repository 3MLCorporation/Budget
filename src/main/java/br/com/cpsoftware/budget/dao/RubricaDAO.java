package br.com.cpsoftware.budget.dao;

import com.google.appengine.api.datastore.DatastoreService;

import br.com.cpsoftware.budget.model.Tipo;

public class RubricaDAO implements TipoDao{

	private DatastoreService datastore;
	private static final String RUBRICA_KIND = "Rubrica";
	
	@Override
	public Long create(Tipo tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tipo read(Long tipoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Tipo tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Long tipoId) {
		// TODO Auto-generated method stub
		
	}

}
