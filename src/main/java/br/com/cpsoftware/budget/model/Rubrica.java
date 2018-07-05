package br.com.cpsoftware.budget.model;

public class Rubrica extends Entidade{
	
	private Long categoriaId;
	
	public static final String CATEGORIA_ID = "categoria_id";
	
	public Rubrica(Long categoriaId, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado) {
		super(nome, valorEstimado, valorOrcado, valorRealizado);
		this.categoriaId = categoriaId;
	}
	
	public Rubrica(Long categoriaId, Long id, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado) {
		super(id, nome, valorEstimado, valorOrcado, valorRealizado);
		this.categoriaId = categoriaId;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
}
