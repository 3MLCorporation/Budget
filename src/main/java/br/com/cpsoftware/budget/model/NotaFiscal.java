package br.com.cpsoftware.budget.model;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Blob;

public class NotaFiscal {
	private Long id;
	private Long itemId;
	private Blob arquivo;
	private String fornecedor;
	private Double valor;
	private Double valorParcial;
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
	
	//Status da nota fiscal
	public static final int STATUS_PARCIAL = 1;
	public static final int STATUS_QUITADO = 2;
	
	public NotaFiscal(Long itemId, Long id, Blob arquivo, String fornecedor, Double valor, Date data, int status) {
		this.itemId = itemId;
		this.id = id;
		this.arquivo = arquivo;
		this.fornecedor = fornecedor;
		this.valor = valor;
		this.data = data;
		this.status = status;
	}

	public NotaFiscal(Long itemId, Blob arquivo, String fornecedor, Double valor, Date data, int status) {
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

	public Blob getArquivo() {
		return arquivo;
	}

	public void setArquivo(Blob arquivo) {
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

	/**
	 * @return the valorParcial
	 */
	public Double getValorParcial() {
		return valorParcial;
	}

	/**
	 * @param valorParcial the valorParcial to set
	 */
	public void setValorParcial(Double valorParcial) {
		this.valorParcial = valorParcial;
	}
	
	public Double calcularValorParcial(List<Pagamento> pagamentos) {
		this.valorParcial = 0d;
		for(Pagamento pagamento : pagamentos) {
			this.valorParcial += pagamento.getValor();
		}
		return this.valorParcial;
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

	public void verificarStatus() {
		if(this.valor.equals(this.valorParcial)) {
			this.status = NotaFiscal.STATUS_QUITADO;
		}else {
			this.status = NotaFiscal.STATUS_PARCIAL;
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
