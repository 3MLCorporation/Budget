package br.com.cpsoftware.budget.model;

public class OrcamentoUsuario {
	
	private Long id;
	private Long editorId;
	private Long orcamentoId;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public final String EDITOR_ID = "editor_id";
	public final String ORCAMENTO_ID = "orcamento_id";
	
	
	public OrcamentoUsuario(Long id, Long editorId, Long orcamentoId) {
		this.id = id;
		this.editorId = editorId;
		this.orcamentoId = orcamentoId;
	}

	public OrcamentoUsuario(Long editorId, Long orcamentoId) {
		this.editorId = editorId;
		this.orcamentoId = orcamentoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEditorId() {
		return editorId;
	}

	public void setEditorId(Long editorId) {
		this.editorId = editorId;
	}

	public Long getOrcamentoId() {
		return orcamentoId;
	}

	public void setOrcamentoId(Long orcamentoId) {
		this.orcamentoId = orcamentoId;
	}
	
	
	
	
	
}
