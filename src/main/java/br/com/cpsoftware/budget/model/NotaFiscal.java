package br.com.cpsoftware.budget.model;

import java.util.Date;

import com.google.appengine.api.datastore.Blob;

import br.com.cpsoftware.budget.util.Formatacao;

public class NotaFiscal {
	private Long id;
	private Long itemId;
	private Long fornecedorId;
	private Blob arquivo;
	private Double valor;
	private Double valorParcial;
	private Date data;
	private int status;
	
	//Definição das keys do banco
	public static final String ID = "id";
	public static final String ITEM_ID = "item_id";
	public static final String FORNECEDOR_ID = "fornecedor_id";
	public static final String ARQUIVO = "arquivo";
	public static final String VALOR = "valor";
	public static final String VALOR_PARCIAL = "valor_parcial";
	public static final String DATA = "data";
	public static final String STATUS = "status";
	
	//Status da nota fiscal
	public static final int STATUS_PARCIAL = 1;
	public static final int STATUS_QUITADO = 2;
	
	public NotaFiscal(Long itemId, Long id, Long fornecedorId, Blob arquivo, Double valor, Double valorParcial, Date data, int status) {
		this.itemId = itemId;
		this.id = id;
		this.fornecedorId = fornecedorId;
		this.arquivo = arquivo;
		this.valor = valor;
		this.valorParcial = valorParcial;
		this.data = data;
		this.status = status;
	}

	public NotaFiscal(Long itemId, Long fornecedorId, Blob arquivo, Double valor, Double valorParcial, Date data, int status) {
		this.itemId = itemId;
		this.fornecedorId = fornecedorId;
		this.arquivo = arquivo;
		this.valor = valor;
		this.valorParcial = valorParcial;
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

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedor(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public Blob getArquivo() {
		return arquivo;
	}

	public void setArquivo(Blob arquivo) {
		this.arquivo = arquivo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getValorFormatado() {
		return Formatacao.formatarDinheiro(valor);
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
		verificarStatus();
	}
	
	public String getValorParcialFormatado() {
		return Formatacao.formatarDinheiro(valorParcial);
	}
	
	/*public Double calcularValorParcial(List<Pagamento> pagamentos) {
		this.valorParcial = 0d;
		for(Pagamento pagamento : pagamentos) {
			this.valorParcial += pagamento.getValor();
		}
		return this.valorParcial;
	}*/

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDataFormatada() {
		return Formatacao.formatarData(data);
	}
	
	public String getDataFormatadaUS() {
		return Formatacao.formatarDataFormatoUS(data);
	}
	
	public int getStatus() {
		return status;
	}

	private void verificarStatus() {
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
