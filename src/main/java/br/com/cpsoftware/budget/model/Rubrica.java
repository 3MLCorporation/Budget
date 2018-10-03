package br.com.cpsoftware.budget.model;

public class Rubrica extends Entidade{
	
	private Long categoriaId;
	private int codigo;

	public static final String CODIGO = "codigo";
	public static final String CATEGORIA_ID = "categoria_id";
	
	public Rubrica(Long categoriaId, int codigo, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		super(nome, valorEstimado, valorOrcado, valorRealizado, valorComprovado);
		this.categoriaId = categoriaId;
		this.codigo = codigo;
	}
	
	public Rubrica(Long categoriaId, Long id, int codigo, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		super(id, nome, valorEstimado, valorOrcado, valorRealizado, valorComprovado);
		this.categoriaId = categoriaId;
		this.codigo = codigo;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
