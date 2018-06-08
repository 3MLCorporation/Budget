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
	        	<a href="/listarProjetos">Projeto</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active">
        	<a href="/listarOrcamentos">Orçamento</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarCategorias">Categoria</a>
        </li>
        <li class="breadcrumb-item active">
    		<a href="/listarRubricas">Rubrica</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarItens">Item</a>
        </li>
      </ol>
    </div>

    
    <c:if test="${not empty nota }"> 
	   	<div class="card mb-3">
	   		<div class="card-header">
	        	<i class="fa fa-area-chart"></i> Nota fiscal
	        </div>
	        <div class="card-body">
				<div>
					Fornecedor: <strong>${nota.fornecedor}</strong>&emsp;
					Valor: <strong>${nota.getValorFormatado()}</strong>&emsp;
					Data: <strong>${nota.getDataFormatada()}</strong>&emsp;
					Valor parcial: <strong>${nota.getValorParcialFormatado()}</strong>&emsp;
					Status: 
					<strong>
			            <c:choose>
			               <c:when test = "${nota.status == 1}">Parcial</c:when>
			               <c:when test = "${nota.status == 2}">Quitado</c:when>
			            </c:choose>
		            </strong>
				</div>
				
				<form action="abrirNotaFiscal" target="_blank" method="GET">
					<input type="hidden" name="notaId" value="${nota.id}">
					<div class="botaoVisualizarNotaFiscal">
						<button type="submit" class="btn btn-dark">Ver arquivo</button>
					</div>
				</form>
	        </div>

	   	    <div class="card-header">
	        	<i class="fa fa-area-chart"></i> Pagamentos cadastradas
	        </div>
		   	<div class="card-body">
			   	<div class="table-responsive">
		            <table class="table table-bordered" id="dataTable">
				   		<thead>
				   			<tr>
				   				<th>Valor</th>
								<th>Data</th>
								<th></th>
	      					</tr>
				   		</thead>
				   		<tbody>
				   			<c:forEach items="${pagamentos}" var="pagamento" varStatus="id">
								<tr>
									<td> ${pagamento.getValorFormatado()}</td>
									<td> ${pagamento.getDataFormatada()}</td>
									<td style="width: 14%">
										<div class="btn-group"> 
				                            <form action="abrirPagamento" target="_blank" method="GET">
				                                <input type="hidden" name="pagamentoId" value="${pagamento.id }">
				                                <button type="submit" class="btn btn-link"><img src="../img/visualizar.png" style="width: 80%;"></button>
				                            </form>
											<form action="excluirPagamento" method="POST">
												<input type="hidden" name="nota_fiscal_id" value="${nota.id }">
												<input type="hidden" class="form-control" value="${pagamento.id}" name="pagamento_id">
												<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo" style="width:100%;"> </button>
											</form>
										</div>	
									</td>
								</tr>
							</c:forEach>
				   	 	</tbody>
				  	</table>
				  	<c:if test="${nota.status == 1}"> 
					  	<form action="cadastrarPagamento" method="GET">
			           		<input type="hidden" name="nota_fiscal_id" value="${nota.id }">
			           		<div class="botaoCadastrarPagamento">
								&emsp;<button type="submit" class="btn btn-dark">Cadastrar pagamento</button>
							</div>
						</form>
					</c:if>
			  	</div>
		  	</div>
	  	</div>
  	</c:if>

  	<c:if test="${empty nota }"> 
		<div class="container">
			<h2><strong>Nota fiscal não cadastrada!</strong></h2>
			<br>
			<form action="cadastrarNotaFiscal" method="GET">
				<input type="hidden" name="itemId" value="${itemId }">
				<button class="btn btn-link"> Cadastrar nota </button>
	       	</form> 
       	</div>
	</c:if>
</div>