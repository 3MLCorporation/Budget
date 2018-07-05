package br.com.cpsoftware.budget.model;

public class Projeto extends Entidade{

	private Long gerenteId;
	
	//Definição das keys do banco
	public static final String GERENTE_ID = "gerente_id";
	
	public Projeto(Long gerenteId, Long id, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado) {
		super(id, nome, valorEstimado, valorOrcado, valorRealizado);
		this.gerenteId = gerenteId;
	}

	public Projeto(Long gerenteId, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado) {
		super(nome, valorEstimado, valorOrcado, valorRealizado);
		this.gerenteId = gerenteId;
	}

	public Long getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(Long gerenteId) {
		this.gerenteId = gerenteId;
	}
}
