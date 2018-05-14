<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<div class="container">
   		<h2>Orçamentos cadastrados</h2>
   		<input class="form-control" id="myInput" type="text" placeholder="Pesqusiar...">
		<table class="table table-sm">
    		<thead class="thead-dark">
      			<tr>
        			<th>Orçamento</th>
        			<th>Valor</th>
        			<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
        				<th>Usuários</th>
        			</c:if>
        			<th>Editar orçamento</th>
       			</tr>
    		</thead>
    		<tbody id="myTable">
   				<c:if test="${empty orcamentos}"> <!-- Alerta caso o orçamento esteja vazio -->
					<script>mostraraAlerta()</script>
				</c:if>
        		<c:forEach items="${orcamentos}" var="orcamento">
					<tr>
						<td> ${orcamento.nome}</td>
						<td> ${orcamento.getValorTotal()}</td>
						<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
	   						<td style="width: 12%">
	   							<form action="adicionarEditor" method="GET">
									<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamento">
									<button class="btn btn-link"> <img src="../img/adicionar.png" alt="Logo" style="width:100%;"> </button>
					           	</form>
	   						</td>
   						</c:if>
						<td style="width: 16%">
							<div class="btn-group"> 
								<form action="selecionarOrcamento" method="POST">
									<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
									<button type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo" style="width:100%;"> </button>
					           	</form> 

								<form action="excluirOrcamento" method="POST">
									<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamento_id">
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