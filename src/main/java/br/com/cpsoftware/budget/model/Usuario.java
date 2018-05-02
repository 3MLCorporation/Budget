package br.com.cpsoftware.budget.model;

import java.io.Serializable;

public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private int perfil;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String PERFIL = "perfil";
	public static final String NOME = "nome";
	public static final String EMAIL = "email";
	public static final String LOGIN = "login";
	public static final String SENHA = "senha";
	
	//Definição dos tipos de perfil
	public static final int PERFIL_ADMIN = 0;
	public static final int PERFIL_GERENTE = 1;
	public static final int PERFIL_PADRAO = 2;
	
	public Usuario(Long id, int perfil, String nome, String email, String login, String senha) {
		this.id = id;
		this.perfil = perfil;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(int perfil, String nome, String email, String login, String senha) {
		this.nome = nome;
		this.perfil = perfil;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario(Long id, int perfil, String nome, String email, String login) {
		this.id = id;
		this.perfil = perfil;
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

	public int getPerfil() {
		return perfil;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
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
