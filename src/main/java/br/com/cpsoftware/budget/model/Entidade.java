package br.com.cpsoftware.budget.model;

import br.com.cpsoftware.budget.util.Formatacao;

public class Entidade {
	
	private Long id;
	private String nome;
	private Double valorEstimado;
	private Double valorOrcado;
	private Double valorRealizado;
	private Double valorComprovado;

	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String VALOR_ESTIMADO = "valor_estimado";
	public static final String VALOR_ORCADO = "valor_orcado";
	public static final String VALOR_REALIZADO = "valor_realizado";
	public static final String VALOR_COMPROVADO = "valor_comprovado";
	
	public Entidade(String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		this.nome = nome;
		this.valorEstimado = valorEstimado;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.valorComprovado = valorComprovado;
	}
	
	public Entidade(String nome, Double valorEstimado) {
		this.nome = nome;
		this.valorEstimado = valorEstimado;
	}
	
	public Entidade(Long id, String nome, Double valorEstimado) {
		this.id = id;
		this.nome = nome;
		this.valorEstimado = valorEstimado;
	}
	
	public Entidade(Long id, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		this.id = id;
		this.nome = nome;
		this.valorEstimado = valorEstimado;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.valorComprovado = valorComprovado;
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

	public Double getValorEstimado() {
		return valorEstimado;
	}

	public void setValorEstimado(Double valorEstimado) {
		this.valorEstimado = valorEstimado;
	}
	
	public String getValorEstimadoFormatado() {
		return Formatacao.formatarDinheiro(valorEstimado);
	}
	
	public Double getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(Double valorOrcado) {
		this.valorOrcado = valorOrcado;
	}
	
	public String getValorOrcadoFormatado() {
		return Formatacao.formatarDinheiro(valorOrcado);
	}

	public Double getValorRealizado() {
		return valorRealizado;
	}

	public void setValorRealizado(Double valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

	public String getValorRealizadoFormatado() {
		return Formatacao.formatarDinheiro(valorRealizado);
	}

	public Double getValorComprovado() {
		return valorComprovado;
	}

	public void setValorComprovado(Double valorComprovado) {
		this.valorComprovado = valorComprovado;
	}
	
	public String getValorComprovadoFormatado() {
		return Formatacao.formatarDinheiro(valorComprovado);
	}
}
