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
		        			<th>Valor</th>
		        			<th>Valor Parcial</th>
		        			<th>Excluir</th>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
		        		<c:forEach items="${categorias}" var="categoria">
							<tr>
								<td> ${categoria.nome}</td>
								<td> ${categoria.valorTotal}</td>
								<td> ${categoria.valorParcial}</td>
								<td style="width: 16%">
									<form action="excluirProjeto" method="POST">
										<input type="hidden" class="form-control" value="${projeto.id}" name="projeto_id">
										<button type="submit" class="btn btn-link"><img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
						           	</form>	
		   						</td>
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>
	  	</div>
  	</div>
</div>