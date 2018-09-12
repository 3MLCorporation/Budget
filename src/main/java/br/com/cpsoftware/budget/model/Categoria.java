package br.com.cpsoftware.budget.model;


public class Categoria extends Entidade{

	private Long orcamentoId;
	
	public static final String ORCAMENTO_ID = "orcamento_id";
	
	public Categoria(Long orcamentoId, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		super(nome, valorEstimado, valorOrcado, valorRealizado, valorComprovado);
		this.orcamentoId = orcamentoId;
	}
	
	public Categoria(Long orcamentoId, Long id, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		super(id, nome, valorEstimado, valorOrcado, valorRealizado, valorComprovado);
		this.orcamentoId = orcamentoId;
	}

	public Long getOrcamentoId() {
		return orcamentoId;
	}

	public void setOrcamentoId(Long orcamentoId) {
		this.orcamentoId = orcamentoId;
	}
	
	
}
