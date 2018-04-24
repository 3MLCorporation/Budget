package br.com.cpsoftware.budget.model;

public class Orcamento extends Entidade{

	private Long usuarioId;
	
	public static final String USUARIO_ID = "usuario_id";
	
	public Orcamento(Long usuarioId, String nome, Double valor_total) {
		super(nome, valor_total);
		this.usuarioId = usuarioId;
	}
	
	public Orcamento(Long usuarioId, Long id, String nome, Double valor_total) {
		super(id, nome, valor_total);
		this.usuarioId = usuarioId;
	}
	
	public Orcamento(Long usuarioId, Long id, String nome, Double valor_total, Double valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
		this.usuarioId = usuarioId;
	}
	
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
