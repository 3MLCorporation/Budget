<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div>
	<div class="container" style="width: 60%;">
		<h3><strong>Nota fiscal</strong></h3>
		<br/>
		<h4>Fornecedor: <strong>${nota.fornecedor}</strong>&emsp; Valor: <strong>${nota.valor}</strong>&emsp; Data: <strong>${nota.data}</h4>
			<form action="abrirNotaFiscal" method="GET">
				<input type="hidden" name="notaId" value="${nota.id}">
				<button type="submit" class="btn btn-dark">Ver arquivo</button>
			</form>
			<br/>
			<h3><strong>Pagamentos cadastrados</strong></h3>
			<br/>
			<input class="form-control" id="myInput" type="text" placeholder="Pesqusiar...">
			<table class="table table-sm">
				<thead class="thead-dark">
					<tr>
						<th>Valor</th>
						<th>Data</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="myTable">
					<c:forEach items="${pagamentos}" var="pagamento" varStatus="id">
					<tr>
						<td> ${pagamento.valor}</td>
						<td> ${pagamento.data}</td>
						<td style="width: 8%"> 
							<form action="visualizarPagamento" method="GET">
								<input type="hidden" name="pagamentoId" value="${pagamento.id }">
								<button type="submit" class="btn btn-link"><img src="../img/visualizar.png" style="width: 80%;"></button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form action="cadastrarPagamento" method="GET">
			<input type="hidden" name="nota_fiscal_id" value="${nota.id }">
			<button type="submit" class="btn btn-dark">Cadastrar pagamento</button>
		</form>
	</div>
</div>

<script>
	$(document).ready(function(){
		$("#myInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#myTable tr").filter(function() {
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
	});
</script>