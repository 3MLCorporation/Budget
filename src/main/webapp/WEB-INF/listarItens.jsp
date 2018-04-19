<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container" style="width: 60%;">
	<h2>Itens cadastrados</h2>
	<table class="table">
    	<thead>
      		<tr>
      			<th>Orcamento</th>
        		<th>Categoria</th>
        		<th>Rubrica</th>
        		<th>Item</th>
        		<th>Descrição</th>
        		<th>Valor</th>
        		<th>Quantidade</th>
      		</tr>
    	</thead>
    		<tbody>
        		<c:forEach items="${itens}" var="item">
					<tr>
						<td> --- </td>
						<td> --- </td>
						<td> --- </td>
						<td> ${item.nome}</td>
						<td> ${item.descricao}</td>
						<td> ${item.getValor_uniforme()}</td>
						<td> ${item.quantidade}</td>
					</tr>
				</c:forEach>
	   	 	</tbody>
	  	</table>
	  </div>
</div>