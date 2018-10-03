package br.com.cpsoftware.budget.model;


public class Categoria extends Entidade{

	private Long orcamentoId;
	private int codigo;
	
	public static final String CODIGO = "codigo";
	public static final String ORCAMENTO_ID = "orcamento_id";
	
	public Categoria(Long orcamentoId, int codigo, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		super(nome, valorEstimado, valorOrcado, valorRealizado, valorComprovado);
		this.orcamentoId = orcamentoId;
		this.codigo = codigo;
	}
	
	public Categoria(Long orcamentoId, Long id, int codigo, String nome, Double valorEstimado, Double valorOrcado, Double valorRealizado, Double valorComprovado) {
		super(id, nome, valorEstimado, valorOrcado, valorRealizado, valorComprovado);
		this.orcamentoId = orcamentoId;
		this.codigo = codigo;
	}

	public Long getOrcamentoId() {
		return orcamentoId;
	}

	public void setOrcamentoId(Long orcamentoId) {
		this.orcamentoId = orcamentoId;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}
