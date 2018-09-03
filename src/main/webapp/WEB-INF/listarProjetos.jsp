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
        <li class="breadcrumb-item active">
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
							           				
										<form action="excluirProjeto" method="POST">
											<input type="hidden" class="form-control" value="${projeto.id}" name="projeto_id">
											<button type="submit" class="btn btn-link" title="Apagar projeto"><!--<img src="../img/excluir.png" alt="Logo">--><i class="material-icons" style="color:black" >delete_forever</i> </button> <!--DELETAR-->
							           	</form>

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
	  	</div>
  	</div>
</div>