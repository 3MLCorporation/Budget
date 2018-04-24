package br.com.cpsoftware.budget.model;


public class Categoria extends Entidade{

	private Long orcamentoId;
	
	public static final String ORCAMENTO_ID = "orcamento_id";
	
	public Categoria(Long orcamentoId, String nome, Double valor_total) {
		super(nome, valor_total);
		this.orcamentoId = orcamentoId;
	}
	
	public Categoria(Long orcamentoId, Long id, String nome, Double valor_total) {
		super(id, nome, valor_total);
		this.orcamentoId = orcamentoId;
	}
	
	public Categoria(Long orcamentoId, Long id, String nome, Double valor_total, Double valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
		this.orcamentoId = orcamentoId;
	}

	public Long getOrcamentoId() {
		return orcamentoId;
	}

	public void setOrcamentoId(Long orcamentoId) {
		this.orcamentoId = orcamentoId;
	}
	
	
}
