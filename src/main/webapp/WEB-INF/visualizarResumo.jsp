<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container" style="width: 60%;">
	<h2>Resumo</h2>
		<table class="table">
	    	<thead>
	      		<tr>
	      			<th>Orçamento</th>
	        		<th>Valor</th>
	        		<th>Valor utilizado</th>
	        		<th>Valor Restante</th>
	        		<th></th>
	      		</tr>
	    	</thead>
	    	<tbody>
	    	
	        	<c:forEach items="${orcamentos}" var="orcamento">
						
							<tr>
								<td> ${orcamento.nome}</td>
								<td> ${orcamento.getValorTotal()}</td>
								<td> --- </td>
								<td> --- </td>
								<td> <form action="selecionarOrcamento" method="POST">
									<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
									<button type="submit" class="btn btn-dark"> > </button>
						           	</form> 
           						</td>
							</tr>
				</c:forEach>
				
		   	</tbody>
	  	</table>
	  </div>
</div>