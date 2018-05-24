<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="">Tabelas</a>
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
				       			<th>Valor</th>
				       			<th>Valor Parcial</th>
				       			<th>Editar projeto</th>
			     			</tr>
			   		</thead>
			   		<tbody id="myTable">
			       		<c:forEach items="${projetos}" var="projeto">
							<tr>
								<td> ${projeto.nome}</td>
								<td> ${projeto.valorTotal}</td>
								<td> ${projeto.valorParcial}</td>
								<td style="width: 12%">
									<div class="btn-group"> 
										<form action="selecionarProjeto" method="POST">
											<input type="hidden" class="form-control" value="${projeto.id}" name="projetoEditavel">
											<button type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo" style="width:100%;"> </button>
							           	</form> 
			
										<form action="excluirProjeto" method="POST">
											<input type="hidden" class="form-control" value="${projeto.id}" name="projeto_id">
											<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
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