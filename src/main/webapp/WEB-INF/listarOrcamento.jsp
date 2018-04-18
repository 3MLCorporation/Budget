<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container" style="width: 60%;">
   		<h2>Orçamentos cadastrados</h2>
		<table class="table">
    		<thead>
      			<tr>
        			<th>Orçamento</th>
        			<th>Valor</th>
      			</tr>
    		</thead>
    		<tbody>
        		<c:forEach items="${requestScope.orcamento}" var="orcamentoDB">
					<tr>
						<td ${requestScope.orcamento == orcamentoDB.codigo ? 'selected' : ''}>${orcamentoDB.codigo}</td>
					</tr>
				</c:forEach>
	   	 	</tbody>
	  	</table>
	</div>
</div>