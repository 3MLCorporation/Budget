<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container" style="width: 60%;">
   		<h2>Categorias cadastradas</h2>
		<table class="table">
    		<thead>
      			<tr>
        			<th>Orçamento</th>
        			<th>Categoria</th>
        			<th>Valor</th>
      			</tr>
    		</thead>
    		<tbody>
        		<c:forEach items="${requestScope.categoria}" var="categoriaDB">
					<tr>
						<td ${requestScope.categoria == categoriaDB.codigo ? 'selected' : ''}>${categoriaDB.codigo}</td>
					</tr>
				</c:forEach>
	   	 	</tbody>
	  	</table>
	</div>
</div>