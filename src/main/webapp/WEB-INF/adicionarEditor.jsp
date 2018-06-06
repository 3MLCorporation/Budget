<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
	      <ol class="breadcrumb">
	        <li class="breadcrumb-item">
	          <c:if test="${not empty orcamentoSelecionado}">
		          <a href="">
					${orcamentoSelecionado}
		          </a>
	          </c:if>
	        </li>
	        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
		        <li class="breadcrumb-item active">
		        	<a href="/listarUsuarios">Usuários</a>
		        </li>
	        </c:if>
	      </ol>
	 </div>

   	<div class="card mb-3">
   	    <div class="card-header">
        	Editores do orçamento <strong>${orcamento}</strong>
        </div>
	   	<div class="card-body">
	   		<div class="form-group col-lg-12">
		   		<form class="" action="adicionarEditor" method="POST">
		   			<label for="text"><strong>Adicionar usuário ao orçamento</strong></label>
		   			<div class="inputPosicao">	   					
			  			<input type="email" class="form-control" placeholder="Fornecer o email do usuário a ser adicionado" name="email" required="required">
			  		</div>
			  		<div class="botaoPosicao">
						<button type="submit" class="btn btn-dark botao">Adicionar</button>
					</div>
				</form>
			</div>
		   	<div class="table-responsive">
		   		<c:if test="${not empty editoresCadastrados}">
		            <table class="table table-bordered" id="dataTable">
				   		<thead>
				   			<tr>
				       			<th>Nome</th>
				       			<th>Email</th>
				   			</tr>
				   		</thead>
				   		<tbody id="myTable">
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
		  	</div>
	  	</div>
  	</div>



	<c:if test="${empty editoresCadastrados}">
	   	<div class="card mb-3">
			<div class="card-header">
	       		Este orçamento não possui editores, adicione-os!
	    	</div>
	    </div>
	</c:if>
	 	
	<br/> 	
	<c:if test="${not empty confirmacao }">
		<c:if test="${not empty usuarioAdicionado}">
			<div class="card mb-3">
				<div class="card-header">
		       		Usuário ${usuarioAdicionado.nome} adicionado com sucesso!
		    	</div>
	    	</div>
		</c:if>
		<c:if test="${empty usuarioAdicionado}">
			<div class="card mb-3">
				<div class="card-header">
		       		Email do usuário não encontrado, experimente tentar outro!
		    	</div>
	    	</div>		
		</c:if>
	</c:if>
</div>

