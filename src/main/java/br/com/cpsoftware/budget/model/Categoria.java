package br.com.cpsoftware.budget.model;


public class Categoria extends Entidade{

	public Categoria(String nome, Double valor_total) {
		super(nome, valor_total);
	}
	
	public Categoria(Long id, String nome, Double valor_total) {
		super(id, nome, valor_total);
	}
	
	public Categoria(Long id, String nome, Double valor_total, Double valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
	}
	
	
}
