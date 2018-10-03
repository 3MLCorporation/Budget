<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
	        <li class="breadcrumb-item active">
	        	<a href="/listarProjetos" title="Listar projetos">Projetos</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active">
        	<a href="/listarOrcamentos" title="Listar orçamentos">Orçamentos</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarCategorias" title="Listar categorias">Categorias</a>
        </li>
        <li class="breadcrumb-item active" title="Listar rubricas">
        	<a href="/listarRubricas">Rubricas</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Rubricas cadastradas <!--(${orcamentoSelecionado})-->
        	
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			        		<th>Categoria</th>
			        		<th>Rubrica</th>
		        			<th>Valor estimado</th>
		        			<th>Valor orçado</th>
		        			<th>Valor realizado</th>
		        			<th>Valor comprovado</th>
			        		<th style="width: 5%;">Editar rubrica</th>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
						<c:forEach items="${rubricasMaps}" var="rubricaMap">
							<tr>
								<td> ${rubricaMap.categoria.codigo} - ${rubricaMap.categoria.nome}</td>
								<td> ${rubricaMap.rubrica.codigo} - ${rubricaMap.rubrica.nome}</td>
								<td> ${rubricaMap.rubrica.getValorEstimadoFormatado()}</td>
								<td> ${rubricaMap.rubrica.getValorOrcadoFormatado()}</td>
								<td> ${rubricaMap.rubrica.getValorRealizadoFormatado()}</td>
								<td>${rubricaMap.rubrica.getValorComprovadoFormatado()}</td>
								<td>
									<div class="btn-group">
										<form action="listarItens" method="GET">
								         	<input type="hidden" class="form-control" value="${rubricaMap.rubrica.id}" name="rubricaId">
											<button type="submit" class="btn btn-link" title="Listar itens">
												<!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
								        </form>
								        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
											<form action="atualizarRubrica" method="GET">
												<input type="hidden" class="form-control" value="${rubricaMap.rubrica.id}" name="rubricaId">												
												<button type="submit" class="btn btn-link" title="Editar rubrica">
													<!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
											</form>
								           												
											<button type="submit" class="btn btn-link open-delete-modal" title="Apagar rubrica" data-toggle="modal" data-target="#deletarEntidade" data-id="${rubricaMap.rubrica.id}">
											<!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i> </button> <!--DELETAR RUBRICA-->
										</c:if>	
									</div>
		   						</td>
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>
	  		<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
			  	<form action="cadastrarRubrica" method="GET">
					&emsp;<button type="submit" class="btn btn-dark">Cadastrar rubrica</button>
				</form>
			</c:if>

			<!--Caixa de dialogo do botão delete-->
		    <div class="modal fade" id="deletarEntidade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo apagar a rubrica?</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">Pressione o botão "Deletar" se é o que deseja.</div>
		          <div class="modal-footer">
		            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
						<form action="excluirRubrica" method="POST">
							<input type="hidden" class="form-control" id="entityId" value="" name="rubrica_id">
							<button type="submit" class="btn btn-primary" title="Apagar rubrica">Deletar</button> <!--DELETAR-->
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