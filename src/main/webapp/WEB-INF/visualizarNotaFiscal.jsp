<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div>
	<div class="container" style="width: 60%;">
		<h2>Visualizar nota fiscal</h2> <!--TEM QUE MUDAR O CÓDIGO-->
		<br/>
		<h4>Fornecedor: <strong>${nota.fornecedor}</strong></h4>
		<h4>Valor: <strong>${nota.valor}</strong></h4>
		<h4>Data: <strong>${nota.data}</strong></h4>
		<h4>Arquivo: </h4> 

		<form action="abrirNotaFiscal" method="GET">
			<input type="hidden" name="notaId" value="${nota.id}">
			<button type="submit" class="btn btn-dark">Ver arquivo</button>
		</form>
		<div class="botaoCadastroPagamento">
			<form action="adicionarPagamento" method="GET">
				<button type="submit" class="btn btn-dark">Cadastrar pagamento</button>
			</form>
		</div>
	</div>
</div>