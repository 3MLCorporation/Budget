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
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Orçamentos cadastrados
        	
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			     			<th>Orçamento</th>
		        			<th>Valor estimado</th>
		        			<th>Valor orçado</th>
		        			<th>Valor realizado</th>
		        			<th>Status</th>
		        			<th>&emsp;Editar<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"> orçamento</c:if></th>
		        			<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
		        				<th>Usuários</th>
		        			</c:if>
		        		</tr>
			   		</thead>
			   		<tbody id="myTable">
			       		<c:forEach items="${orcamentos}" var="orcamento" varStatus="id">
							<tr>
								<td> ${orcamento.nome}</td>
								<td> ${orcamento.getValorEstimadoFormatado()}</td>
								<td> ${orcamento.getValorOrcadoFormatado()}</td>
								<td> ${orcamento.getValorRealizadoFormatado()}</td>
								<td>
								<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
									<select id="orcamentoSelect${id.count}">
		         						<option ${0 == orcamento.status ? 'selected' : ''} value = '0'>Elaboração</option>
										<option ${1 == orcamento.status ? 'selected' : ''} value = '1'>Controle</option>
										<option ${2 == orcamento.status ? 'selected' : ''} value = '2'>Finalizado</option>
									</select>
								</c:if>
								<c:if test="${sessionScope.usuario.perfil == 2}">
									<c:if test="${orcamento.status == 0}">Elaboração</c:if>
									<c:if test="${orcamento.status == 1}">Controle</c:if>
									<c:if test="${orcamento.status == 2}">Finalizado</c:if>
								</c:if>
								</td>
								<td>
									<div class="btn-group">
										<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
											<form action="atualizarStatusOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoId">
												<input type="hidden" class="form-control" id="statusOrcamento${id.count }" name="statusSelecionado">
												<button type="submit"  onclick="getStatusOrcamento('${id.count}')" class="btn btn-link"><img src="../img/salvar.png" alt="Logo"></button>
									    	</form>  
								    	 </c:if>
								    	 <!--Orcamento em elaboracao e usuarios admin/gerente  -->
								    	 <c:if test="${orcamento.status == 0 && (sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1) }">
							    	 		<form action="selecionarOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
												<button type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo"> </button>
								           	</form> 
								    	 </c:if>
								    	 <!--Orcamento em elaboracao e usuario padrao  -->
								    	 <c:if test="${orcamento.status == 0 && sessionScope.usuario.perfil == 2}">
								    	  	<!--TODO Mostrar alerta na tela!!!  -->
											<button  disabled="disabled" type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo"> </button>
								    	 </c:if>
								    	 <!--Orcamento em controle e usuarios admin/gerente  -->
								    	 <c:if test="${orcamento.status == 1 }">
								    	 	<form action="selecionarOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
												<button type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo"> </button>
								           	</form> 
								    	 </c:if>
										<!--Orcamento finalizado e usuarios admin/gerente  -->	
										<c:if test="${orcamento.status == 2 && (sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1) }">
								    	 	<form action="selecionarOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoEditavel">
												<button type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo"> </button>
								           	</form> 
								    	 </c:if>
								    	 <!--Orcamento finalizado e usuario padrao -->	
										<c:if test="${orcamento.status == 2 && sessionScope.usuario.perfil == 2 }">
											<!--TODO Mostrar alerta na tela!!!  -->
								    	 	<button  disabled="disabled" type="submit" class="btn btn-link"> <img src="../img/editar.png" alt="Logo"> </button>
								    	 </c:if>
								        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
											<form action="atualizarOrcamento" method="GET">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoId">												
												<button type="submit" class="btn btn-link"><img src="../img/atualizar.png" alt="Logo"></button>
											</form>

											<form action="excluirOrcamento" method="POST">
												<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamento_id">
												<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo"> </button>
								           	</form>
							           	</c:if>
						           	</div>
		   						</td>
		   						<c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
			   						<td style="width: 12%">
			   							<form action="adicionarEditor" method="GET">
											<input type="hidden" class="form-control" value="${orcamento.id}" name="orcamento">
											<button class="btn btn-link"> <img src="../img/adicionarUsuario.png" alt="Logo"> </button>
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