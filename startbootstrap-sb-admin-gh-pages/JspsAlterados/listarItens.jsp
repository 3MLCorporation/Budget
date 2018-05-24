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
			        		<th>Rubrica</th>
			        		<th>Item</th>
			        		<th>Valor</th>
			        		<th>Valor Parcial</th>
			        		<th>Detalhes</th>
			        		<th>Item</th>
			        		<th>Nota fiscal</th>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
						<c:forEach items="${itens}" var="item">
							<tr>
								<td> --- </td>
								<td> --- </td>
								<td> ${item.nome}</td>
								<td> ${item.valor}</td>
								<td> ${item.valorParcial}</td>
								<td>
									<form action="visualizarItem" method="GET">
										<input type="hidden" class="form-control" value="${item.id}" name="itemId">
										<button type="submit" class="btn btn-link"> <img src="../img/detalhes.png" alt="Logo"> </button>
							         </form>
								</td>
								<td>
									<form action="excluirItem" method="POST">
										<input type="hidden" class="form-control" value="${item.id}" name="itemId">
										<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo"> </button>
							         </form>
								</td>
								<td style="width: 15%;">
									<div class="btn-group">
										<form action="cadastrarNotaFiscal" method="GET">
											<input type="hidden" name="itemId" value="${item.id }">
											<button class="btn btn-link"> <img src="../img/adicionar.png" alt="Logo"> </button>
							           	</form> 
										<form action="visualizarNotaFiscal" method="GET">
											<input type="hidden" name="itemId" value="${item.id }">
											<button class="btn btn-link"><img src="../img/visualizar.png" alt="Logo"> </button>
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