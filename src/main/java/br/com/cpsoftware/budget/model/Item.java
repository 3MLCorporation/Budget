package br.com.cpsoftware.budget.model;

import com.google.appengine.api.datastore.Blob;

import br.com.cpsoftware.budget.util.Formatacao;

/*
 * TODO UNIDADE DE MEDIDA
 */
public class Item {
	
	private Long rubricaId;
	private Long id;
	private String nome;
	private String descricao;
	private Double precoUnitario;
	private Double valorOrcado;
	private Double valorRealizado;
	private int quantidade;
	private int unidadeMedida;
	private Blob arquivoDetalhes;
	private Blob arquivoAuxiliar; // TODO PENSAR NOME MELHOR
	
	//Definição das keys do banco
	public static final String RUBRICA_ID = "rubrica_id";
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";
	public static final String VALOR_UNIFORME = "preco_unitario";
	public static final String QUANTIDADE = "quantidade";
	public static final String VALOR_ORCADO = "valor_orcado";
	public static final String VALOR_REALIZADO = "valor_realizado";
	public static final String UNIDADE_MEDIDA = "unidade_medida";
	public static final String ARQUIVO_DETALHES = "arquivo_detalhes";
	public static final String ARQUIVO_AUXILIAR = "arquivo_auxiliar";

	//Tipos de unidade de medida
	public static final int UNIDADE_MEDIDA_VERBA = 0;
	public static final int UNIDADE_MEDIDA_UNIDADE = 1;
	public static final int UNIDADE_MEDIDA_MES = 2;
	public static final int UNIDADE_MEDIDA_KG = 3;
	public static final int UNIDADE_MEDIDA_METRO = 4;
	public static final int UNIDADE_MEDIDA_LITRO = 5;
	
	public Item(Long rubricaId, String nome, String descricao, Double precoUnitario, int quantidade, Double valorOrcado, Double valorRealizado, int unidadeMedida) {
		this.rubricaId = rubricaId;
		this.nome = nome;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Item(Long rubricaId, Long id,String nome, String descricao, Double precoUnitario, int quantidade, Double valorOrcado, Double valorRealizado, int unidadeMedida) {
		this.rubricaId = rubricaId;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Item(Long rubricaId, String nome, String descricao, Double precoUnitario, int quantidade, Double valorOrcado, Double valorRealizado, int unidadeMedida, Blob arquivoDetalhes) {
		this.rubricaId = rubricaId;
		this.nome = nome;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
	}
	public Item(Long rubricaId, Long id, String nome, String descricao, Double precoUnitario, int quantidade, Double valorOrcado, Double valorRealizado, int unidadeMedida, Blob arquivoDetalhes) {
		this.rubricaId = rubricaId;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
	}

	public Item(Long rubricaId, String nome, String descricao, Double precoUnitario, int quantidade, Double valorOrcado, Double valorRealizado, int unidadeMedida, Blob arquivoDetalhes, Blob arquivoAuxiliar) {
		this.rubricaId = rubricaId;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
		this.arquivoAuxiliar = arquivoAuxiliar;
	}
	
	public Item(Long rubricaId, Long id, String nome, String descricao, Double precoUnitario, int quantidade, Double valorOrcado, Double valorRealizado, int unidadeMedida, Blob arquivoDetalhes, Blob arquivoAuxiliar) {
		this.rubricaId = rubricaId;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.valorOrcado = valorOrcado;
		this.valorRealizado = valorRealizado;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
		this.arquivoAuxiliar = arquivoAuxiliar;
	}
	
	public Long getRubricaId() {
		return rubricaId;
	}

	public void setRubricaId(Long rubricaId) {
		this.rubricaId = rubricaId;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	
	public String getPrecoUnitarioFormatado() {
		return Formatacao.formatarDinheiro(precoUnitario);
	}
	
	public Double getValorOrcado() {
		return valorOrcado;
	}

	public void setValorOrcado(Double valorOrcado) {
		this.valorOrcado = valorOrcado;
	}
	
	public String getValorOrcadoFormatado() {
		return Formatacao.formatarDinheiro(valorOrcado);
	}

	public Double getValorRealizado() {
		return valorRealizado;
	}

	public void setValorRealizado(Double valorRealizado) {
		this.valorRealizado = valorRealizado;
	}

	public String getValorRealizadoFormatado() {
		return Formatacao.formatarDinheiro(valorRealizado);
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return precoUnitario * quantidade;
	}
	
	public String getValorFormatado() {
		return Formatacao.formatarDinheiro(precoUnitario * quantidade);
	}
	
	public int getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(int unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Blob getArquivoDetalhes() {
		return arquivoDetalhes;
	}
	
	public void setArquivoDetalhes(Blob arquivoDetalhes) {
		this.arquivoDetalhes = arquivoDetalhes;
	}

	public boolean isArquivoDetalhesVazio() {
		if(this.arquivoDetalhes.getBytes().length > 0) {
			return false;
		}else {
			return true;
		}
	}

	public Blob getArquivoAuxiliar() {
		return arquivoAuxiliar;
	}

	public void setArquivoAuxiliar(Blob arquivoAuxiliar) {
		this.arquivoAuxiliar = arquivoAuxiliar;
	}

	public boolean isArquivoAuxiliarVazio() {
		if(this.arquivoAuxiliar.getBytes().length > 0) {
			return false;
		}else {
			return true;
		}
	}

	
}
