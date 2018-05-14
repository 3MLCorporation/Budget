package br.com.cpsoftware.budget.model;

public class Entidade {
	
	private Long id;
	private String nome;
	private Double valorTotal;
	private Double valorParcial;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String VALOR_TOTAL = "valor_total";
	public static final String VALOR_PARCIAL = "valor_parcial";
	
	public Entidade(String nome, Double valor_total, Double valorParcial) {
		this.nome = nome;
		this.valorTotal = valor_total;
		this.valorParcial = valorParcial;
	}
	
	public Entidade(String nome, Double valor_total) {
		this.nome = nome;
		this.valorTotal = valor_total;
	}
	
	public Entidade(Long id, String nome, Double valor_total) {
		this.id = id;
		this.nome = nome;
		this.valorTotal = valor_total;
	}
	
	public Entidade(Long id, String nome, Double valor_total, Double valor_parcial) {
		this.id = id;
		this.nome = nome;
		this.valorTotal = valor_total;
		this.valorParcial = valor_parcial;
	}
	/*public static class Builder {
		private Long id;
		private String nome;
		private float valor_total;
		private float valor_parcial;
		
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder nome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder valorTotal(float valorTotal) {
			this.valor_total = valorTotal;
			return this;
		}
		
		public Builder valorParcial (float valorParcial) {
			this.valor_parcial = valorParcial;
			return this;
		}
		
		public Entidade build() {
			return new Entidade(this);
		}
		
	}*/
	
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valor_total) {
		this.valorTotal = valor_total;
	}

	public Double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(Double valor_parcial) {
		this.valorParcial = valor_parcial;
	}

}
