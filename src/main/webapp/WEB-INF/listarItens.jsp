<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container">
		<h2>Itens cadastrados</h2>
		<input class="form-control" id="myInput" type="text" placeholder="Pesquisar...">
		<table class="table table-sm">
	    	<thead class="thead-dark">
	      		<tr>
	        		<th>Categoria</th>
	        		<th>Rubrica</th>
	        		<th>Item</th>
	        		<th>Valor</th>
	        		<th>Valor Parcial</th>
	        		<th>Detalhes</th>
	        		<th>&emsp;Item</th>
	        		<th>&emsp;Nota fiscal</th>
	      		</tr>
	    	</thead>
    		<tbody id="myTable">
        		<c:forEach items="${itensMaps}" var="itemMap">
					<tr>
						<td> ${itemMap.nomeCategoria}</td>
						<td> ${itemMap.nomeRubrica}</td>
						<td> ${itemMap.item.nome}</td>
						<td> ${itemMap.item.valor}</td>
						<td> ${itemMap.item.valorParcial}</td>
						<td>
							<form action="visualizarItem" method="GET">
								<input type="hidden" class="form-control" value="${itemMap.item.id}" name="itemId">
								<button type="submit" class="btn btn-link"> <img src="../img/detalhes.png" alt="Logo"> </button>
					         </form>
						</td>
						<td>
							<form action="excluirItem" method="POST">
								<input type="hidden" class="form-control" value="${itemMap.item.id}" name="item_id">
								<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo"> </button>
					         </form>
						</td>
						<td style="width: 15%;">
							<div class="btn-group">
								<form action="cadastrarNotaFiscal" method="GET">
									<input type="hidden" name="itemId" value="${itemMap.item.id }">
									<button class="btn btn-link"> <img src="../img/adicionar.png" alt="Logo"> </button>
					           	</form> 
								<form action="visualizarNotaFiscal" method="GET">
									<input type="hidden" name="itemId" value="${itemMap.item.id }">
									<button class="btn btn-link"><img src="../img/visualizar.png" alt="Logo"> </button>
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