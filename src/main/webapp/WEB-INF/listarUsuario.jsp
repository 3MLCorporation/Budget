<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<div class="container" style="width: 60%;">
		<h2>Usuários</h2>
			<table class="table">
		    	<thead>
		      		<tr>
		      			<th>Nome</th>
		      			<th>Login</th>
		        		<th>Email</th>
		        		<th>Perfil</th>
		      		</tr>
		    	</thead>
		    	<tbody>
		        	<c:forEach items="${usuarios}" var="usuario">
								<tr>
									<td> ${usuario.nome}</td>
									<td> ${usuario.login}</td>
									<td> ${usuario.email}</td>
									<td>
										<select>
		           							<option ${1 == usuario.perfil ? 'selected' : ''}>Gerente</option> <!-- 1 = model.Usuario.PERFIL_GERENTE -->
											<option ${2 == usuario.perfil ? 'selected' : ''}>Padrão</option> <!-- 2 = model.Usuario.PERFIL_PADRAO -->
		           						</select>
									</td>
								</tr>
					</c:forEach>
			   	</tbody>
		  	</table>
	</div>
</div>