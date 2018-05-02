package br.com.cpsoftware.budget.model;

public class Projeto {

	private Long id;
	private Long gerenteId;
	private String nome;
	private Double valor;
	private Double valorParcial;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String GERENTE_ID = "gerente_id";
	public static final String NOME = "nome";
	public static final String VALOR_TOTAL = "valor_total";
	public static final String VALOR_PARCIAL = "valor_parcial";
	
	public Projeto(Long id, Long gerenteId, String nome, Double valor, Double valorParcial) {
		this.id = id;
		this.gerenteId = gerenteId;
		this.nome = nome;
		this.valor = valor;
		this.valorParcial = valorParcial;
	}

	public Projeto(Long gerenteId, String nome, Double valor, Double valorParcial) {
		this.gerenteId = gerenteId;
		this.nome = nome;
		this.valor = valor;
		this.valorParcial = valorParcial;
	}

	public Projeto(Long gerenteId, String nome, Double valor) {
		this.gerenteId = gerenteId;
		this.nome = nome;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(Long gerenteId) {
		this.gerenteId = gerenteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(Double valorParcial) {
		this.valorParcial = valorParcial;
	}
	
}
