<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../js/script.js"></script>

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
		        		<th>Salvar</th>
		        		<th>Visualizar</th>
		      		</tr>
		    	</thead>
		    	<tbody>
		        	<c:forEach items="${usuarios}" var="usuario" varStatus="id">
								<tr>
									<td> ${usuario.nome}</td>
									<td> ${usuario.login}</td>
									<td> ${usuario.email}</td>
									<td>
										<select id="perfilSelect${id.count }"><!-- TODO Mudar o evento ou desabilitar o botao de atualizarPerfil, enquanto não houver mudado no select, ou mudar a posição da chamada do método(colocar form de atualizarPerfil -->
		           							<option ${1 == usuario.perfil ? 'selected' : ''} value = '1'>Gerente</option> <!-- 1 = model.Usuario.PERFIL_GERENTE -->
											<option ${2 == usuario.perfil ? 'selected' : ''} value = '2'>Padrão</option> <!-- 2 = model.Usuario.PERFIL_PADRAO -->
		           						</select>
									</td>
									<td>
										<form action="atualizarPerfil" method="POST">
											<input type="hidden" class="form-control" value="${usuario.id}" name="usuarioParaAtulizar">
											<input type="hidden" class="form-control" id="valorPerfil${id.count }" name="perfilAtualizado">
											<button type="submit"  onclick="getValorPerfil('${id.count}')" class="btn btn-dark"><img src="../img/salvarPerfil.png" alt="Logo" style="width:100%;"></button>
							           	</form> 
							        </td>
									<td>
									<form action="visualizarUsuario" method="POST">
											<input type="hidden" class="form-control" name="">
											<button type="submit" class="btn btn-dark"><img src="../img/visualizar.png" alt="Logo" style="width:100%;"></button>
							           	</form>
									</td>
								</tr>
					</c:forEach>
			   	</tbody>
		  	</table>
	</div>
</div>