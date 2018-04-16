package br.com.cpsoftware.budget.dao;


import br.com.cpsoftware.budget.model.Tipo;

public interface TipoDao {
	
	Long create(Tipo tipo);
	
	Tipo read(Long tipoId);
	
	void update(Tipo tipo);
	
	void delete(Long tipoId);
}
