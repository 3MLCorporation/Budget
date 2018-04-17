package br.com.cpsoftware.budget.model;

public class Orcamento extends Entidade{

	public Orcamento(Long id, String nome, float valor_total) {
		super(id, nome, valor_total);
	}
	
	public Orcamento(Long id, String nome, float valor_total, float valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
	}
	
	
}
