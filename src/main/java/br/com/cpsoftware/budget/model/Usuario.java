package br.com.cpsoftware.budget.model;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id; //TODO colocar depois que criar?
	private String nome;
	private String email;
	private String login;
	private String senha;
	//private List<Long> listaOrcamentos; //TODO id's ou objetos?
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String EMAIL = "email";
	public static final String LOGIN = "login";
	public static final String SENHA = "senha";
	public static final String ORCAMENTOS = "orcamentos";
	
	public Usuario(Long id, String nome, String email, String login, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(String nome, String email, String login, String senha) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(Long id, String nome, String email, String login) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.login = login;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
