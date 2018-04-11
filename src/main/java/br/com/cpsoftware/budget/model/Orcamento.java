package br.com.cpsoftware.budget.model;

public class Orcamento {
	private String nome;
	private float valor;
	private float valor_parcial;
	
	
	public Orcamento(String nome, float valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public float getValor_parcial() {
		return valor_parcial;
	}
	public void setValor_parcial(float valor_parcial) {
		this.valor_parcial = valor_parcial;
	}
	
	
}
