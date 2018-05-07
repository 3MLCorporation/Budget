<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
	<div class="container" style="width: 60%;">
   		<h2>Projetos cadastrados</h2>
		<table class="table">
    		<thead>
      			<tr>
        			<th>Projeto</th>
        			<th>Valor</th>
        			<th>Visualizar</th>
      			</tr>
    		</thead>
    		<tbody>
        		<c:forEach items="${projetos}" var="projeto">
					<tr>
						<td> ${projeto.nome}</td>
						<td> ${projeto.getValorTotal()}</td>
						<td> <form action="selecionarProjeto" method="POST">
							<input type="hidden" class="form-control" value="${projeto.id}" name="projetoEditavel">
							<button type="submit" class="btn btn-dark"> <img src="../img/visualizar.png" alt="Logo" style="width:100%;"> </button>
				           	</form> 
   						</td>
					</tr>
				</c:forEach>
	   	 	</tbody>
	  	</table>
	</div>
</div>