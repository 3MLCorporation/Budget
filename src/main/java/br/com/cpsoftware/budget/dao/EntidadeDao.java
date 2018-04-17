package br.com.cpsoftware.budget.dao;


import br.com.cpsoftware.budget.model.Entidade;

public interface EntidadeDao {
	
	Long create(Entidade tipo);
	
	Entidade read(Long tipoId);
	
	void update(Entidade tipo);
	
	void delete(Long tipoId);
}
