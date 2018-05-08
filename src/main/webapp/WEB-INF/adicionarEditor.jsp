<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script src="../js/script.js"></script>
	
<div>
	<div class="container" style="width: 60%;">
   		<h2><strong>Editores do orçamento ${orcamento}</strong></h2>
   		<c:if test="${not empty editoresCadastrados}">
			<table class="table">
	    		<thead>
	      			<tr>
	        			<th>Nome</th>
	        			<th>Email</th>
	     			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach items="${editoresCadastrados}" var="editor">
						<tr>
							<td>${editor.nome}</td>
							<td>${editor.email}</td>
						</tr>
						<tr></tr>
					</c:forEach>
				</tbody>
		  	</table>
	  	</c:if>

	  	<c:if test="${empty editoresCadastrados}">
		  	<h2>
		  		<strong>Este orçamento não possui editores, adicione-os abaixo!</strong>
		  	</h2>
	  	</c:if>
	  	
	  	<br/>
	  	<form action="adicionarEditor" method="POST">
	  		<label for="text"><strong>Email</strong></label><input type="text"
					class="form-control" placeholder="Fornecer o email do usuário a ser adicionado"
					name="email" required="required">
			<button type="submit" class="btn btn-dark">Adicionar</button>
	  	</form>
	  	
		<c:if test="${not empty confirmacao }">
			<c:if test="${not empty usuarioAdicionado}">
				<h2><strong>Usuário ${usuarioAdicionado.nome} adicionado com sucesso!</strong></h2>
			</c:if>
			<c:if test="${empty usuarioAdicionado}">
				<h3><strong>Email do usuário não encontrado, experimente tentar outro!</strong></h3>
			</c:if>
		</c:if>
	</div>
</div>