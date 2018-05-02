package br.com.cpsoftware.budget.model;

public class Orcamento extends Entidade{

	private Long projetoId;
	
	public static final String PROJETO_ID = "projeto_id";
	
	public Orcamento(Long projetoId, String nome, Double valor_total) {
		super(nome, valor_total);
		this.projetoId = projetoId;
	}
	
	public Orcamento(Long projetoId, Long id, String nome, Double valor_total) {
		super(id, nome, valor_total);
		this.projetoId = projetoId;
	}
	
	public Orcamento(Long projetoId, Long id, String nome, Double valor_total, Double valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
		this.projetoId = projetoId;
	}
	
	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}
}
