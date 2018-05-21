<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container">
		<h3><strong>Detalhes do item: ${item.nome}</strong></h3>
		<h4>Nome: <strong>${item.nome}</strong>&emsp; Descrição: <strong>${item.descricao}</strong>&emsp; Valor: <strong>${item.valor}</strong></h4>
		<h4>Quantidade: <strong>${item.quantidade}</strong>&emsp; Valor uniforme: <strong>${item.valor_uniforme}
		</strong>&emsp; Unidade de medida: 
		<strong>
			<c:choose>

			<c:when test = "${item.unidadeMedida == 0}">Verba</c:when>
			<c:when test = "${item.unidadeMedida == 1}">Unidade</c:when>
			<c:when test = "${item.unidadeMedida == 2}">Mês</c:when>
			<c:when test = "${item.unidadeMedida == 3}">Quilograma</c:when>
			<c:when test = "${item.unidadeMedida == 4}">Metro</c:when>
			<c:when test = "${item.unidadeMedida == 5}">Litro</c:when>
			<c:otherwise>---</c:otherwise>

		</c:choose>

		</strong></h4>
		<br>
		<div class="btn-group">
			<form action="abrirArquivoDetalhesItem" target="_blank" method="GET">
				<input type="hidden" name="itemId" value="${item.id}">
				<button type="submit" class="btn btn-dark">Ver detalhes</button>
			</form>
			
			<form action="abrirArquivoAuxiliarItem" target="_blank" method="GET">
				<input type="hidden" name="itemId" value="${item.id}">
				&emsp;<button type="submit" class="btn btn-dark">Ver arquivo auxiliar</button>
			</form>
			
			<form action="visualizarNotaFiscal" method="GET">
				<input type="hidden" name="itemId" value="${item.id }">
				&emsp;<button type="submit" class="btn btn-dark">Ver nota fiscal</button>
			</form>
		</div>
	</div>
</div>