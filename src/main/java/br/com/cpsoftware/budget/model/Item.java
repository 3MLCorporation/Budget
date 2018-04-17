package br.com.cpsoftware.budget.model;


public class Item {
	private Long id;
	private String nome;
	private String descricao;
	private Float valor_uniforme;
	private int quantidade;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";
	public static final String VALOR_UNIFORME = "valor_uniforme";
	public static final String QUANTIDADE = "quantidade";

	public Item(Long id,String nome, String descricao, Float valor_uniforme, int quantidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Float getValor_uniforme() {
		return valor_uniforme;
	}
	public void setValor_uniforme(Float valor_uniforme) {
		this.valor_uniforme = valor_uniforme;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
