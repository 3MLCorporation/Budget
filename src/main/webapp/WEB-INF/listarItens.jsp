<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container">
		<h2>Itens cadastrados</h2>
		<input class="form-control" id="myInput" type="text" placeholder="Pesqusiar...">
		<table class="table table-sm">
	    	<thead class="thead-dark">
	      		<tr>
	        		<th>Categoria</th>
	        		<th>Rubrica</th>
	        		<th>Item</th>
	        		<th>Descrição</th>
	        		<th>Valor</th>
	        		<th>Quantidade</th>
	        		<th>&emsp;Item</th>
	        		<th>&emsp;Nota fiscal</th>
	      		</tr>
	    	</thead>
    		<tbody id="myTable">
        		<c:forEach items="${itens}" var="item">
					<tr>
						<td> --- </td>
						<td> --- </td>
						<td> ${item.nome}</td>
						<td> ${item.descricao}</td>
						<td> ${item.getValor_uniforme()}</td>
						<td> ${item.quantidade}</td>
						<td>
							<form action="excluirItem" method="POST">
								<input type="hidden" class="form-control" value="${item.id}" name="item_id">
								<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
					         </form>
						</td>
						<td style="width: 15%;">
							<div class="btn-group">
								<form action="cadastrarNotaFiscal" method="GET">
									<input type="hidden" name="item_id" value="${item.id }">
									<button class="btn btn-link"> <img src="../img/adicionar.png" alt="Logo" style="width:100%;"> </button>
					           	</form> 
								<form action="visualizarNotaFiscal" method="GET">
									<input type="hidden" name="item_id" value="${item.id }">
									<button class="btn btn-link"> <img src="../img/visualizar.png" alt="Logo" style="width:100%;"> </button>
					           	</form>
				           	</div> 
						</td>
					</tr>
				</c:forEach>
	   	 	</tbody>
		 </table>
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