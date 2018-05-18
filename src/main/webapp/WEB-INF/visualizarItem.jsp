<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
 <div>
	<div class="container">
		<h3><strong>Detalhes do item: ${item.nome}</strong></h3>
		<h4>Nome: <strong>${item.nome}</strong>&emsp; Descrição: <strong>${item.descricao}</strong>&emsp; Valor: <strong>${item.valor}</strong></h4>
		<h4>Quantidade: <strong>${item.quantidade}</strong>&emsp; Valor uniforme: <strong>${item.valor_uniforme}</strong>&emsp; Unidade de medida: <strong>${item.unidade_medida}</strong></h4>

		<form action="visualizarNotaFiscal" method="GET">
			<div class="inputPosicao">	   					
	  			<input type="hidden" name="item_id" value="${item.id }">
	  		</div>
	  		<div class="botaoPosicao">
				<button class="btn btn-link">Ver arquivo</button>
			</div>
       	</form>
	</div>
</div>