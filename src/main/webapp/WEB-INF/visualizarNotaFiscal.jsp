<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div>
	<c:if test="${empty nota }"> 
		<div class="container">
			<h2><strong>Nota fiscal não cadastrada!</strong></h2>
			<br>
			<form action="cadastrarNotaFiscal" method="GET">
				<input type="hidden" name="itemId" value="${itemId }">
				<button class="btn btn-link"> Cadastrar nota </button>
	       	</form> 
       	</div>
	</c:if>
	<c:if test="${not empty nota }"> 
		<div class="container" style="width: 60%;">
			<h3><strong>Nota fiscal</strong></h3>
			<br/>
			<h4>Fornecedor: <strong>${nota.fornecedor}</strong>&emsp;
				Valor: <strong>${nota.valor}</strong>&emsp;
				Data: <strong>${nota.data}</strong>&emsp;
				Valor parcial: <strong>${nota.valorParcial}</strong>&emsp;
				Status: 
				<strong>
		            <c:choose>
		               <c:when test = "${nota.status == 1}">Parcial</c:when>
		               <c:when test = "${nota.status == 2}">Quitado</c:when>
		            </c:choose>
	            </strong>
	
			</h4>
				<form action="abrirNotaFiscal" target="_blank" method="GET">
					<input type="hidden" name="notaId" value="${nota.id}">
					<div class="botaoVisualizarNotaFiscal">
						<button type="submit" class="btn btn-dark">Ver arquivo</button>
					</div>
				</form>
				<br/>
				<h3><strong>Pagamento cadastrados</strong></h3>
				<br/>
				<input class="form-control" id="myInput" type="text" placeholder="Pesquisar...">
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
							<td style="width: 11%">
								<div class="btn-group"> 
		                            <form action="abrirPagamento" target="_blank" method="GET">
		                                <input type="hidden" name="pagamentoId" value="${pagamento.id }">
		                                <button type="submit" class="btn btn-link"><img src="../img/visualizar.png" style="width: 80%;"></button>
		                            </form>
									<form action="excluirPagamento" method="POST">
										<input type="hidden" class="form-control" value="${pagamento.id}" name="pagamento_id">
										<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
									</form>
								</div>	
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
	</c:if>
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