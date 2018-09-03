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
	        	<a href="/listarProjetos">Projetos</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active">
        	<a href="/listarOrcamentos">Orçamentos</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarCategorias">Categorias</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Categorias cadastradas
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
		        			<th>Categoria</th>
		        			<th>Valor estimado</th>
		        			<th>Valor orçado</th>
		        			<th>Valor realizado</th>
		        			<!--<th>Rubricas</th>-->
		        			<th>Editar categoria</th>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
		        		<c:forEach items="${categorias}" var="categoria">
							<tr>
								<td> ${categoria.nome}</td>
								<td> ${categoria.getValorEstimadoFormatado()}</td>
								<td> ${categoria.getValorOrcadoFormatado()}</td>
								<td> ${categoria.getValorRealizadoFormatado()}</td>
								<td>
									<div class="btn-group">
										<form action="listarRubricas" method="GET">
								         	<input type="hidden" class="form-control" value="${categoria.id}" name="categoriaId">
											<button type="submit" class="btn btn-link" title="Listar rubricas">
												<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								        </form>
									
										<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
											<form action="atualizarCategoria" method="GET">
												<input type="hidden" class="form-control" value="${categoria.id}" name="categoriaId">												
												<button type="submit" class="btn btn-link" title="Editar categoria">
													<!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
											</form>

											<button type="submit" class="btn btn-link open-delete-modal" title="Apagar categoria" data-toggle="modal" data-target="#deletarEntidade" data-id="${categoria.id}">
												<!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i></button>	<!--DELETAR CATEGORIA-->								           	
				   						</c:if>
			   						</div>
		   						</td>	
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>
		  	<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
			  	<form action="cadastrarCategoria" method="GET">
					&emsp;<button type="submit" class="btn btn-dark">Cadastrar categoria</button>
				</form>
			</c:if>

			<!--Caixa de dialogo do botão delete-->
		    <div class="modal fade" id="deletarEntidade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo apagar a catagoria?</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">Pressione o botão "Deletar" se é o que deseja.</div>
		          <div class="modal-footer">
		            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
						<form action="excluirCategoria" method="POST">
							<input type="hidden" class="form-control" id="entityId" value="" name="categoria_id">
							<button type="submit" class="btn btn-primary" title="Apagar categoria">Deletar</button> <!--DELETAR-->
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