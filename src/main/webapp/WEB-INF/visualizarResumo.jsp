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
      			<th>Orcamento</th>
        		<th>Valor</th>
        		<th>Valor utilizado</th>
        		<th>Valor Restante</th>
      		</tr>
    	</thead>
    	<tbody>
        	<c:forEach items="${requestScope.rubrica}" var="rubricaDB">
				<tr>
					<td ${requestScope.rubrica == rubricaDB.codigo ? 'selected' : ''}>${rubricaDB.codigo}</td>
				</tr>
			</c:forEach>
	   	</tbody>
	  	</table>
	  </div>
</div>