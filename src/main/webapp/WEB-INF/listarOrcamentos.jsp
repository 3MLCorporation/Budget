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
	        <li class="breadcrumb-item active" title="Listar projetos">
	        	<a href="/listarProjetos">Projetos</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active" title="Listar orçamentos">
        	<a href="/listarOrcamentos">Orçamentos</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Orçamentos cadastrados
        	
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			     			<th>Orçamento</th>
		        			<th>Valor estimado</th>
		        			<th>Valor orçado</th>
		        			<th>Valor realizado</th>
		        			<th>Valor comprovado</th>		        			
		        			<th>Status</th>
		        			<th>&emsp;Editar<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"> orçamento</c:if></th>
		        		</tr>
			   		</thead>
			   		<tbody id="myTable">
			       		<c:forEach items="${orcamentos}" var="orcamento" varStatus="id">
							<tr>
								<td> ${orcamento.nome}</td>
								<td> ${orcamento.getValorEstimadoFormatado()}</td>
								<td> ${orcamento.getValorOrcadoFormatado()}</td>
								<td> ${orcamento.getValorRealizadoFormatado()}</td>
								<td></td>
								<td>
								<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
									<select id="orcamentoSelect${id.count}">
		         						<option ${0 == orcamento.status ? 'selected' : ''} value = '0'>Elaboração</option>
										<option ${1 == orcamento.status ? 'selected' : ''} value = '1'>Controle</option>
										<option ${2 == orcamento.status ? 'selected' : ''} value = '2'>Finalizado</option>
									</select>
								</c:if>
								<c:if test="${sessionScope.usuario.perfil == 2}">
									<c:if test="${orcamento.status == 0}">Elaboração</c:if>
									<c:if test="${orcamento.status == 1}">Controle</c:if>
									<c:if test="${orcamento.status == 2}">Finalizado</c:if>
								</c:if>
								</td>
								<td>
									<div class="btn-group">
										<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
											<form action="atualizarStatusOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoId">
												<input type="hidden" class="form-control" id="statusOrcamento${id.count }" name="statusSelecionado">
												<button type="submit"  onclick="getStatusOrcamento('${id.count}')" class="btn btn-link" title="Salvar status do orçamento">
													<!--<img src="../img/salvar.png" alt="Logo">--><i class="material-icons" style="color: black;">save</i></button> <!--SALVAR STATUS-->
									    	</form>  
								    	 </c:if>
								    	 <!--Orcamento em elaboracao e usuarios admin/gerente  -->
								    	 <c:if test="${orcamento.status == 0 && (sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1) }">
							    	 		<form action="selecionarOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
												<button type="submit" class="btn btn-link" title="Listar categorias">
													<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								           	</form> 
								    	 </c:if>
								    	 <!--Orcamento em elaboracao e usuario padrao  -->
								    	 <c:if test="${orcamento.status == 0 && sessionScope.usuario.perfil == 2}">
								    	  	<!--TODO Mostrar alerta na tela!!!  -->
											<button  disabled="disabled" type="submit" class="btn btn-link" title="O orçamento está em elaboração"> 
												<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								    	 </c:if>
								    	 <!--Orcamento em controle e usuarios admin/gerente  -->
								    	 <c:if test="${orcamento.status == 1 }">
								    	 	<form action="selecionarOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
												<button type="submit" class="btn btn-link" title="Listar categorias"> 
													<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								           	</form> 
								    	 </c:if>
										<!--Orcamento finalizado e usuarios admin/gerente  -->	
										<c:if test="${orcamento.status == 2 && (sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1) }">
								    	 	<form action="selecionarOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
												<button type="submit" class="btn btn-link" title="Listar categorias"> 
													<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								           	</form> 
								    	 </c:if>
								    	 <!--Orcamento finalizado e usuario padrao -->	
										<c:if test="${orcamento.status == 2 && sessionScope.usuario.perfil == 2 }">
											<!--TODO Mostrar alerta na tela!!!  -->
								    	 	<button  disabled="disabled" type="submit" class="btn btn-link" title="O orçamento já foi finalizado"> 
								    	 		<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								    	 </c:if>
								        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
											<form action="atualizarOrcamento" method="GET">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoId">												
												<button type="submit" class="btn btn-link" title="Editar orçamento">
													<!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
											</form>

											<button type="submit" class="btn btn-link open-delete-modal" title="Apagar orçamento" data-toggle="modal" data-target="#deletarEntidade" data-id="${orcamento.id}">
												<!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i></button>	<!--DELETAR ORÇAMENTO-->								           									           	
							           	</c:if>
				   						<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
				   							<form action="adicionarEditor" method="GET">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamento">
												<button class="btn btn-link" title="Adicionar um usuário"> <!--<img src="../img/adicionarUsuario.png" alt="Logo">--> <i class="material-icons" style="color:black" >person_add</i></button> <!--ADICIONAR USUÁRIO-->
								           	</form>
				   						</c:if>
			   						</div>
		   						</td>
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>


			<!--Caixa de dialogo do botão delete-->
		    <div class="modal fade" id="deletarEntidade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo apagar o orçamento?</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">Pressione o botão "Deletar" se é o que deseja.</div>
		          <div class="modal-footer">
		            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
						<form action="excluirOrcamento" method="POST">
							<input type="hidden" class="form-control" id="entityId" value="" name="orcamento_id">
							<button type="submit" class="btn btn-primary" title="Apagar orçamento">Deletar</button> <!--DELETAR-->
				        </form>
		          </div>
		        </div>
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