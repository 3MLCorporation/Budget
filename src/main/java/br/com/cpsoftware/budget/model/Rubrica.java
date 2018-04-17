package br.com.cpsoftware.budget.model;

public class Rubrica extends Entidade{

	public Rubrica(Long id, String nome, float valor_total) {
		super(id, nome, valor_total);
	}
	
	public Rubrica(Long id, String nome, float valor_total, float valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
	}
	
}
