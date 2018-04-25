package br.com.cpsoftware.budget.model;

public class Rubrica extends Entidade{
	
	private Long categoriaId;
	
	public static final String CATEGORIA_ID = "categoria_id";
	
	public Rubrica(Long categoriaId, String nome, Double valor_total) {
		super(nome, valor_total);
		this.categoriaId = categoriaId;
	}
	
	public Rubrica(Long categoriaId, Long id, String nome, Double valor_total) {
		super(id, nome, valor_total);
		this.categoriaId = categoriaId;
	}
	
	public Rubrica(Long categoriaId, Long id, String nome, Double valor_total, Double valor_parcial) {
		super(id, nome, valor_total, valor_parcial);
		this.categoriaId = categoriaId;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}
	
}
