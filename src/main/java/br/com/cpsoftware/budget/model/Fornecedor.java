package br.com.cpsoftware.budget.model;

public class Fornecedor {

	private Long id;
	private String fornecedor;
	private String cnpj;
	private String uf;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String CNPJ = "cnpj";
	public static final String UF = "uf";
	
	public Fornecedor(Long id, String fornecedor, String cnpj, String uf) {
		super();
		this.id = id;
		this.fornecedor = fornecedor;
		this.cnpj = cnpj;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
