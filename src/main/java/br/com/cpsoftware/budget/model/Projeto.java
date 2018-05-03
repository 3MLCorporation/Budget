package br.com.cpsoftware.budget.model;

public class Projeto extends Entidade{

	private Long gerenteId;
	
	//Definição das keys do banco
	public static final String GERENTE_ID = "gerente_id";
	
	public Projeto(Long gerenteId, Long id, String nome, Double valor, Double valorParcial) {
		super(id, nome, valor, valorParcial);
		this.gerenteId = gerenteId;
	}

	public Projeto(Long gerenteId, String nome, Double valor, Double valorParcial) {
		super(gerenteId, nome, valor, valorParcial);
		this.gerenteId = gerenteId;
	}

	public Projeto(Long gerenteId, String nome, Double valor) {
		super(nome, valor);
		this.gerenteId = gerenteId;
	}

	public Long getGerenteId() {
		return gerenteId;
	}

	public void setGerenteId(Long gerenteId) {
		this.gerenteId = gerenteId;
	}
}
