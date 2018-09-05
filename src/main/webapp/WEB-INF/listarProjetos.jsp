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
        <li class="breadcrumb-item active" title="Listar projetos">
        	<a href="/listarProjetos">Projetos</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Projetos cadastrados
        	
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			     			<tr>
				       			<th>Projeto</th>
				       			<th>Valor estimado</th>
				       			<th>Valor orçado</th>
				       			<th>Valor realizado</th>
				       			<th>Valor comprovado</th>
				       			<th>Editar projeto</th>
				       			<!--<th>Gerar relatório</th>-->
			     			</tr>
			   		</thead>
			   		<tbody id="myTable">
			       		<c:forEach items="${projetos}" var="projeto">
							<tr>
								<td> ${projeto.nome}</td>
								<td> ${projeto.getValorEstimadoFormatado()}</td>
								<td> ${projeto.getValorOrcadoFormatado()}</td>
								<td> ${projeto.getValorRealizadoFormatado()}</td>
								<td></td>
								<td>
									<div class="btn-group"> 
										<form action="atualizarProjeto" method="GET">
											<input type="hidden" class="form-control" value="${projeto.id}" name="projetoId">
											<button type="submit" class="btn btn-link" title="Editar projeto"><!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
										</form>

							           	<form action="selecionarProjeto" method="POST">
											<input type="hidden" class="form-control" value="${projeto.id}" name="projetoEditavel">
											<button type="submit" class="btn btn-link" data-toggle="tooltip" title="Listar orçamentos"><!--<img src="../img/editar.png" alt="Logo">--><i class="material-icons" style="color:black" >format_align_justify</i> </button> <!--LISTAR-->
							           	</form>

										<button type="submit" class="btn btn-link open-delete-modal" title="Apagar projeto" data-toggle="modal" data-target="#deletarEntidade" data-id="${projeto.id}">
											<!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i></button> <!--DELETAR PROJETO-->								           								           	
							           	<form action="gerarRelatorio" target="_blank" method="GET">
											<input type="hidden" class="form-control" value="${projeto.id}" name="projetoId">
											<button type="submit" class="btn btn-link" title="Gerar relatório"><!--<img src="../img/relatorio.png" alt="Logo">--><i class="material-icons" style="color:black">insert_drive_file</i></button> <!--PDF-->
							           	</form>
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
		            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo apagar o projeto?</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">Pressione o botão "Deletar" se é o que deseja.</div>
		          <div class="modal-footer">
		            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
						<form action="excluirProjeto" method="POST">
							<input type="hidden" class="form-control" id="entityId" value="" name="projeto_id">
							<button type="submit" class="btn btn-primary" title="Apagar projeto">Deletar</button> <!--DELETAR-->
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