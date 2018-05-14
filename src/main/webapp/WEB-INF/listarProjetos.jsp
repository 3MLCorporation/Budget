<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container">
   		<h2>Projetos cadastrados</h2>
   		<input class="form-control" id="myInput" type="text" placeholder="Pesquisar...">
		<table class="table table-sm">
    		<thead class="thead-dark">
      			<tr>
        			<th>Projeto</th>
        			<th>Valor</th>
        			<th>Editar projeto</th>
      			</tr>
    		</thead>
    		<tbody id="myTable">
        		<c:forEach items="${projetos}" var="projeto">
					<tr>
						<td> ${projeto.nome}</td>
						<td> ${projeto.getValorTotal()}</td>
						<td style="width: 12%">
							<div class="btn-group"> 
								<form action="selecionarProjeto" method="POST">
									<input type="hidden" class="form-control" value="${projeto.id}" name="projetoEditavel">
									<button type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo" style="width:100%;"> </button>
					           	</form> 

								<form action="excluirProjeto" method="POST">
									<input type="hidden" class="form-control" value="${projeto.id}" name="projeto_id">
									<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
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