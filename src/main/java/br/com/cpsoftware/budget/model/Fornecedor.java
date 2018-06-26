package br.com.cpsoftware.budget.model;

public class Fornecedor {

	private Long id;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private String uf;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOME_FANTASIA = "nome_fantasia";
	public static final String RAZAO_SOCIAL = "razao_social";
	public static final String CNPJ = "cnpj";
	public static final String UF = "uf";
	
	public Fornecedor(Long id, String nome, String razao, String cnpj, String uf) {
		this.id = id;
		this.nomeFantasia = nome;
		this.razaoSocial = razao;
		this.cnpj = cnpj;
		this.uf = uf;
	}
	
	public Fornecedor(String nome, String razao, String cnpj, String uf) {
		this.nomeFantasia = nome;
		this.razaoSocial = razao;
		this.cnpj = cnpj;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
