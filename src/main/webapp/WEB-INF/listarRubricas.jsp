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
        <li class="breadcrumb-item active">
        	<a href="/listarRubricas">Rubricas</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Rubricas cadastradas
        	
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			        		<th>Categoria</th>
			        		<th>Rubrica</th>
			        		<th>Valor</th>
			        		<th>Valor Parcial</th>
			        		<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
			        			<th>Excluir</th>
			        		</c:if>
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
						<c:forEach items="${rubricasMaps}" var="rubricaMap">
							<tr>
								<td> ${rubricaMap.nomeCategoria}</td>
								<td> ${rubricaMap.rubrica.nome}</td>
								<td> ${rubricaMap.rubrica.getValorTotalFormatado()}</td>
								<td> ${rubricaMap.rubrica.getValorParcialFormatado()}</td>
								<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
									<td style="width: 16%">
										<form action="excluirRubrica" method="POST">
											<input type="hidden" class="form-control" value="${rubricaMap.rubrica.id}" name="rubrica_id">
											<button type="submit" class="btn btn-link"><img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
							           	</form>	
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