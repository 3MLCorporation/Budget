package br.com.cpsoftware.budget.model;

public class Orcamento extends Entidade{

	private Long projetoId;
	private int status;
	
	public static final String PROJETO_ID = "projeto_id";
	public static final String STATUS = "status";
	
	//Definição dos tipos de perfil
	public static final int STATUS_ELABORACAO = 0;
	public static final int STATUS_CONTROLE = 1;
	public static final int STATUS_FINALIZADO = 2;
	
	public Orcamento(Long projetoId, String nome, Double valor_total, int status) {
		super(nome, valor_total);
		this.projetoId = projetoId;
		this.status = status;
	}
	
	public Orcamento(Long projetoId, Long id, String nome, Double valor_total, int status) {
		super(id, nome, valor_total);
		this.projetoId = projetoId;
		this.status = status;
	}
	
	public Orcamento(Long projetoId, Long id, String nome, Double valor_total, Double valor_parcial, int status) {
		super(id, nome, valor_total, valor_parcial);
		this.projetoId = projetoId;
		this.status = status;
	}
	
	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
