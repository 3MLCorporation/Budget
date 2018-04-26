<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container" style="width: 60%;">
		<h2>Rubricas cadastradas</h2>
		<table class="table">
	    	<thead>
	      		<tr>
	        		<th>Categoria</th>
	        		<th>Rubrica</th>
	        		<th>Valor</th>
	      		</tr>
	    	</thead>
	    	<tbody>
	        	<c:forEach items="${rubricas}" var="rubrica">
					<tr>
						<td> --- </td>
						<td> ${rubrica.nome}</td>
						<td> ${rubrica.getValorTotal()}</td>
					</tr>
				</c:forEach>
		   	</tbody>
	  	</table>
	  </div>
</div>