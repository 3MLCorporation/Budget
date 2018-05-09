package br.com.cpsoftware.budget.model;

import java.util.Date;

public class Pagamento {
	private Long id;
	private Long notaFiscalId;
	private Long arquivo;
	private Double valor;
	private Date data;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String NOTA_FISCAL_ID = "nota_fiscal_id";
	public static final String ARQUIVO = "arquivo";
	public static final String VALOR = "valor";
	public static final String DATA = "data";
	
	public Pagamento(Long notaFiscalId, Long id, Long arquivo, Double valor, Date data) {
		this.notaFiscalId = notaFiscalId;
		this.id = id;
		this.arquivo = arquivo;
		this.valor = valor;
		this.data = data;
	}

	public Pagamento(Long notaFiscalId, Long arquivo, Double valor, Date data) {
		this.notaFiscalId = notaFiscalId;
		this.arquivo = arquivo;
		this.valor = valor;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNotaFiscalId() {
		return notaFiscalId;
	}

	public void setNotaFiscalId(Long notaFiscalId) {
		this.notaFiscalId = notaFiscalId;
	}

	public Long getArquivo() {
		return arquivo;
	}

	public void setArquivo(Long arquivo) {
		this.arquivo = arquivo;
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
	
}
