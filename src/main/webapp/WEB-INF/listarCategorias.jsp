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
		        			<th>Valor</th>
		        			<th>Valor Parcial</th>
		        			<th>Rubricas</th>
		        			<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
		        				<th>Editar categoria</th>
		        			</c:if>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
		        		<c:forEach items="${categorias}" var="categoria">
							<tr>
								<td> ${categoria.nome}</td>
								<td> ${categoria.getValorTotalFormatado()}</td>
								<td> ${categoria.getValorParcialFormatado()}</td>
								<td>
									<form action="listarRubricas" method="GET">
							         	<input type="hidden" class="form-control" value="${categoria.id}" name="categoriaId">
										<button type="submit" class="btn btn-link"><img src="../img/listarFamilia.png" alt="Logo" style="width:100%;"> </button>
							        </form>
								</td>
								<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
									<td>
										<div class="btn-group">
											<form action="atualizarCategoria" method="GET">
												<input type="hidden" class="form-control" value="${categoria.id}" name="categoriaId">												
												<button type="submit" class="btn btn-link"><img src="../img/atualizar.png" alt="Logo"></button>
											</form>
								           												
											<form action="excluirCategoria" method="POST">
												<input type="hidden" class="form-control" value="${categoria.id}" name="categoria_id">
												<button type="submit" class="btn btn-link"><img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
								           	</form>
									           	
							            </div>
			   						</td>
		   						</c:if>	
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>
	  	</div>
  	</div>
</div>