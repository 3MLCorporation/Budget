package br.com.cpsoftware.budget.model;

import com.google.appengine.api.datastore.Blob;

/*
 * TODO UNIDADE DE MEDIDA
 */
public class Item {
	
	private Long rubricaId;
	private Long id;
	private String nome;
	private String descricao;
	private Double valor_uniforme;
	private Double valorParcial;
	private int quantidade;
	private int unidadeMedida;
	private Blob arquivoDetalhes;
	private Blob arquivoAuxiliar; // TODO PENSAR NOME MELHOR
	
	//Definição das keys do banco
	public static final String RUBRICA_ID = "rubrica_id";
	public static final String ID = "id";
	public static final String NOME = "nome";
	public static final String DESCRICAO = "descricao";
	public static final String VALOR_UNIFORME = "valor_uniforme";
	public static final String QUANTIDADE = "quantidade";
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
	
	public Item(Long rubricaId, String nome, String descricao, Double valor_uniforme, int quantidade, int unidadeMedida) {
		this.rubricaId = rubricaId;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Item(Long rubricaId, Long id,String nome, String descricao, Double valor_uniforme, int quantidade, int unidadeMedida) {
		this.rubricaId = rubricaId;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
	}
	
	public Item(Long rubricaId, String nome, String descricao, Double valor_uniforme, int quantidade, int unidadeMedida, Blob arquivoDetalhes) {
		this.rubricaId = rubricaId;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
	}
	public Item(Long rubricaId, Long id, String nome, String descricao, Double valor_uniforme, int quantidade, int unidadeMedida, Blob arquivoDetalhes) {
		this.rubricaId = rubricaId;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
	}

	public Item(Long rubricaId, String nome, String descricao, Double valor_uniforme, int quantidade, int unidadeMedida, Blob arquivoDetalhes, Blob arquivoAuxiliar) {
		this.rubricaId = rubricaId;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
		this.arquivoDetalhes = arquivoDetalhes;
		this.arquivoAuxiliar = arquivoAuxiliar;
	}
	public Item(Long rubricaId, Long id, String nome, String descricao, Double valor_uniforme, int quantidade, int unidadeMedida, Blob arquivoDetalhes, Blob arquivoAuxiliar) {
		this.rubricaId = rubricaId;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.valor_uniforme = valor_uniforme;
		this.quantidade = quantidade;
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
	public Double getValor_uniforme() {
		return valor_uniforme;
	}
	public void setValor_uniforme(Double valor_uniforme) {
		this.valor_uniforme = valor_uniforme;
	}
	public Double getValorParcial() {
		return valorParcial;
	}

	public void setValorParcial(Double valorParcial) {
		this.valorParcial = valorParcial;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor_uniforme * quantidade;
	}

	/**
	 * @return the unidadeMedida
	 */
	public int getUnidadeMedida() {
		return unidadeMedida;
	}

	/**
	 * @param unidadeMedida the unidadeMedida to set
	 */
	public void setUnidadeMedida(int unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Blob getArquivoDetalhes() {
		return arquivoDetalhes;
	}

	public void setArquivoDetalhes(Blob arquivoDetalhes) {
		this.arquivoDetalhes = arquivoDetalhes;
	}

	public Blob getArquivoAuxiliar() {
		return arquivoAuxiliar;
	}

	public void setArquivoAuxiliar(Blob arquivoAuxiliar) {
		this.arquivoAuxiliar = arquivoAuxiliar;
	}

	
}
