<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
    <%-- <div class="container-fluid">
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
	 </div> --%>

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
						<button type="submit" class="btn btn-dark botao" title="Adicionar editor" data-toggle="modal" data-target="#usuarioModal">Adicionar</button>
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
				       			<th style="width: 5%;"></th>
				   			</tr>
				   		</thead>
				   		<tbody id="myTable">
				   			<c:forEach items="${editoresCadastrados}" var="editor">
								<tr>
									<td>${editor.nome}</td>
									<td>${editor.email}</td>
									<td>
										<button type="submit" class="btn btn-link open-delete-modal" title="Remover usuário" data-toggle="modal" data-target="#deletarEntidade" data-id="${}">
											<!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i></button>	<!--REMOVER EDITOR-->			
									</td>
								</tr>
								<tr></tr>
							</c:forEach>
				   	 	</tbody>
				  	</table>
				  </c:if>
		  	</div>
	  	</div>
  	</div>



	<!--<c:if test="${empty editoresCadastrados}">
	   	<div class="card mb-3">
			<div class="card-header">
	       		Este orçamento não possui editores, adicione-os!
	    	</div>
	    </div>
	</c:if>-->
	 	
	
		
	<c:if test="${not empty confirmacao }">
		<c:if test="${not empty usuarioAdicionado}">
		    <!-- Usuario encontrado | Modal-->
		    <div class="modal fade" id="usuarioModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Adicionado com sucesso!</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">O usuário foi adicionado ao orçamento.</div>
		          <div class="modal-footer">
		            <button class="btn btn-primary" type="button" data-dismiss="modal">Confirmar</button>
		          </div>
		        </div>
		      </div>
		    </div>
		</c:if>
		<c:if test="${empty usuarioAdicionado}">
		    <!-- Usuario não encontrado | Modal-->
		    <div class="modal fade" id="usuarioModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Falha ao adicionar usuário!</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">Email de usuário não encontrado, experimente outro.</div>
		          <div class="modal-footer">
		            <button class="btn btn-primary" type="button" data-dismiss="modal">Confirmar</button>
		          </div>
		        </div>
		      </div>
		    </div>

		</c:if>
	</c:if>


	<!--Caixa de dialogo do botão delete-->
    <div class="modal fade" id="deletarEntidade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo remover o usuário do orçamento?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Pressione o botão "Remover"</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
				<form action="excluirOrcamento" method="POST">
					<input type="hidden" class="form-control" id="entityId" value="" name="orcamento_id">
					<button type="submit" class="btn btn-primary" title="Remover usuário">Remover</button> <!--Remover-->
		        </form>
          </div>
        </div>
      </div>
    </div>	

</div>

<script>
	$(document).on("click",".open-delete-modal",function(){
		var selectedId = $(this).data('id');
		$(".modal-footer #entityId").val(selectedId);
	});
</script>