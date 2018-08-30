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
        <li class="breadcrumb-item active">
        	<a href="/listarItens">Itens</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Itens cadastrados
        	
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			        		<th>Categoria</th>
			        		<th>Rubrica</th>
			        		<th>Item</th>
		        			<th>Valor estimado</th>
		        			<th>Valor orçado</th>
		        			<th>Valor realizado</th>
			        		<!--<th>Detalhes</th>-->
			        		<th>Editar Item</th>
			        		<!-- <th>Nota fiscal</th> -->
      					</tr>
			   		</thead>
			   		<tbody id="myTable">
		        		<c:forEach items="${itensMaps}" var="itemMap">
							<tr>
								<td> ${itemMap.nomeCategoria}</td>
								<td> ${itemMap.nomeRubrica}</td>
								<td> ${itemMap.item.nome}</td>
								<td> ${itemMap.item.getValorFormatado()}</td>
								<td> ${itemMap.item.getValorOrcadoFormatado()}</td>
								<td> ${itemMap.item.getValorRealizadoFormatado()}</td>
								<td>
									<div class="btn-group">
										<form action="visualizarItem" method="GET">
											<input type="hidden" class="form-control" value="${itemMap.item.id}" name="itemId">
											<button type="submit" class="btn btn-link"> <!--<img src="../img/detalhes.png" alt="Logo">--><i class="material-icons" style="color:black">info</i></button> <!--Detalhes-->
								         </form>
										<form action="atualizarItem" method="GET">
											<input type="hidden" class="form-control" value="${itemMap.item.id}" name="itemId">	
											<button type="submit" class="btn btn-link"><!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
										</form>

										<form action="excluirItem" method="POST">
											<input type="hidden" class="form-control" value="${itemMap.item.id}" name="item_id">
											<button type="submit" class="btn btn-link"><!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i> </button> <!--DELETAR-->
								        </form>
							     	</div>
								</td>
								<%-- <td>
									<div class="btn-group">
										<c:if test="${itemMap.nota == null}">
											<form action="cadastrarNotaFiscal" method="GET">
												<input type="hidden" name="itemId" value="${itemMap.item.id }">
												<button class="btn btn-link"> <img src="../img/adicionar.png" alt="Logo"> </button>
								           	</form> 
							           	</c:if>
							           	<c:if test="${itemMap.nota != null}">
											<form action="visualizarNotaFiscal" method="GET">
												<input type="hidden" name="itemId" value="${itemMap.item.id }">
												<button class="btn btn-link"><img src="../img/visualizar.png" alt="Logo"> </button>
								           	</form>
							           	</c:if>
						           	</div> 
								</td> --%>
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
		  	</div>
	  		<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
			  	<form action="cadastrarItem" method="GET">
					&emsp;<button type="submit" class="btn btn-dark">Cadastrar item</button>
				</form>
			</c:if>
	  	</div>
  	</div>
</div>