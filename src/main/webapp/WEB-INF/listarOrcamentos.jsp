<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script src="../js/script.js"></script>
	
<div>
	<div class="container" style="width: 60%;">
   		<h2>Orçamentos cadastrados</h2>
		<table class="table">
    		<thead>
      			<tr>
        			<th>Orçamento</th>
        			<th>Valor</th>
        			<th>Editar</th>
        			<th>Usuários</th>
       			</tr>
    		</thead>
    		<tbody>
   				<c:if test="${empty orcamentos}"> <!-- Alerta caso o orçamento esteja vazio -->
					<script>mostraraAlerta()</script>
				</c:if>
        		<c:forEach items="${orcamentos}" var="orcamento">
					<tr>
						<td> ${orcamento.nome}</td>
						<td> ${orcamento.getValorTotal()}</td>
						<td> 
							<form action="selecionarOrcamento" method="POST">
								<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
								<button type="submit" class="btn btn-dark"> <img src="../img/editar.png" alt="Logo" style="width:100%;"> </button>
				           	</form> 
   						</td>
   						<td>
   							<form action="adicionarEditor" method="GET">
								<%-- <input type="hidden" class="form-control" value="${orcamento.id}" name="orcamento"> --%>
								<button class="btn btn-dark"> <img src="../img/adicionar.png" alt="Logo" style="width:100%;"> </button>
				           	</form>
   						</td>
					</tr>
				</c:forEach>
	   	 	</tbody>
	  	</table>
	</div>
</div>