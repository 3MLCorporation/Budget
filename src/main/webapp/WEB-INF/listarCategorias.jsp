<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container">
   		<h2>Categorias cadastradas</h2>
   		<input class="form-control" id="myInput" type="text" placeholder="Pesqusiar...">
		<table class="table table-sm">
    		<thead class="thead-dark">
      			<tr>
        			<th>Categoria</th>
        			<th>Valor</th>
      			</tr>
    		</thead>
    		<tbody id="myTable">
        		<c:forEach items="${categorias}" var="categoria">
					<tr>
						<td> ${categoria.nome}</td>
						<td> ${categoria.getValorTotal()}</td>
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