<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../js/script.js"></script>

<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="/listarUsuarios" title="Listar usuários">Usuários</a>
        </li>
      </ol>
    </div>

   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Usuários cadastrados

        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			      			<th>Nome</th>
			      			<th>Login</th>
			        		<th>Email</th>
			        		<th>Perfil</th>
			        		<th></th>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
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
									<div class="btn-group">
										<form action="atualizarPerfil" method="POST">
											<input type="hidden" class="form-control" value="${usuario.id}" name="usuarioParaAtulizar">
											<input type="hidden" class="form-control" id="valorPerfil${id.count }" name="perfilAtualizado">
											<button type="submit"  onclick="getValorPerfil('${id.count}')" class="btn btn-link" title="Salvar status de perfil do usuário">
												<!--<img src="../img/salvar.png" alt="Logo">--><i class="material-icons" style="color:black" >done_all</i></button> <!--SALVAR STATUS-->
									    	</form>  
							           	</form>

										<form action="visualizarUsuario" method="POST">
											<input type="hidden" class="form-control" name="">
											<button type="submit" class="btn btn-link" title="Visualizar perfil">
												<!--<img src="../img/visualizar.png" alt="Logo" style="width:100%;">--> <i class="material-icons" style="color:black">visibility</i></button> <!--Visualizar-->
							           	</form>
						           	</div>
								</td>
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>
	  	</div>
  	</div>
</div>