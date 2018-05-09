package br.com.cpsoftware.budget.model;

import java.util.Date;

public class NotaFiscal {
	private Long id;
	private Long itemId;
	private Long arquivo;
	private String fornecedor;
	private Double valor;
	private Date data;
	private int status;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String ITEM_ID = "item_id";
	public static final String ARQUIVO = "arquivo";
	public static final String FORNECEDOR = "fornecedor";
	public static final String VALOR = "valor";
	public static final String DATA = "data";
	public static final String STATUS = "status";
	
	public NotaFiscal(Long itemId, Long id, Long arquivo, String fornecedor, Double valor, Date data, int status) {
		this.itemId = itemId;
		this.id = id;
		this.arquivo = arquivo;
		this.fornecedor = fornecedor;
		this.valor = valor;
		this.data = data;
		this.status = status;
	}

	public NotaFiscal(Long itemId, Long arquivo, String fornecedor, Double valor, Date data, int status) {
		this.itemId = itemId;
		this.arquivo = arquivo;
		this.fornecedor = fornecedor;
		this.valor = valor;
		this.data = data;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getArquivo() {
		return arquivo;
	}

	public void setArquivo(Long arquivo) {
		this.arquivo = arquivo;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
