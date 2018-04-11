package br.com.cpsoftware.budget.model;

public class Item {
	private String nome;
	private String descricao;
	private int valor_uniforme;
	private int quantidade;
	
	public Item(String nome, String descricao, int valor_uniforme, int quantidade) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getValor_uniforme() {
		return valor_uniforme;
	}
	public void setValor_uniforme(int valor_uniforme) {
		this.valor_uniforme = valor_uniforme;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
