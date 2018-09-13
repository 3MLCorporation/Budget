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
	        	<a href="/listarProjetos" title="Listar projetos">Projetos</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active">
        	<a href="/listarOrcamentos" title="Listar orçamentos">Orçamentos</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarCategorias" title="Listar categorias">Categorias</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarRubricas" title="Listar rubricas">Rubricas</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarItens" title="Listar itens">Item</a>
        </li>
        <li class="breadcrumb-item active" title="Visualizar detalhes">
        	<a href="">Detalhes</a>
        </li> 
        <li class="breadcrumb-item active" title="Visualizar notas fiscais">
        	<a href="">Nota fiscal</a>
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
					Número: <strong>${nota.numero}</strong>&emsp;
					Fornecedor: <strong>${fornecedor}</strong>&emsp;
					Valor: <strong>${nota.getValorFormatado()}</strong>&emsp;
					Data: <strong>${nota.getDataFormatada()}</strong>&emsp;
					Valor comprovado: <strong>${nota.getValorComprovadoFormatado()}</strong>&emsp;
					Status: 
					<strong>
			            <c:choose>
			               <c:when test = "${nota.status == 1}">Parcial</c:when>
			               <c:when test = "${nota.status == 2}">Quitado</c:when>
			            </c:choose>
		            </strong>
				</div>
				
				<br/>
				<div class="btn-group botaoVisualizarNotaFiscal">
					<form action="abrirNotaFiscal" target="_blank" method="GET">
						<input type="hidden" name="notaId" value="${nota.id}">
						<div>
							<button type="submit" class="btn btn-dark" title="Visualizar nota fiscal">Ver nota</button>
						</div>
					</form>
					&emsp;
					<form action="atualizarNotaFiscal" method="GET">
						<input type="hidden" name="notaId" value="${nota.id}">
						<div>
							<button type="submit" class="btn btn-dark" title="Atualizar nota fiscal">Atualizar nota</button>
						</div>
					</form>					
				</div>
	        </div>

	   	    <div class="card-header">
	        	<i class="fa fa-area-chart"></i> Pagamentos cadastrados
	        </div>
		   	<div class="card-body">
			   	<div class="table-responsive">
		            <table class="table table-bordered" id="dataTable">
				   		<thead>
				   			<tr>
				   				<th>Tipo</th>
				   				<th>Valor</th>
								<th>Data</th>
								<th style="width: 5%;"></th>
	      					</tr>
				   		</thead>
				   		<tbody>
				   			<c:forEach items="${pagamentos}" var="pagamento" varStatus="id">
								<tr>
									<td>
										<c:choose>
											<c:when test = "${pagamento.tipo  == 1}">Boleto bancário</c:when>
											<c:when test = "${pagamento.tipo  == 2}">Depósito bancário</c:when>
											<c:when test = "${pagamento.tipo  == 3}">Cheque</c:when>
											<c:when test = "${pagamento.tipo  == 4}">Fatura</c:when>
											<c:when test = "${pagamento.tipo  == 5}">DARF</c:when>
											<c:when test = "${pagamento.tipo  == 6}">ISSQN</c:when>
											<c:otherwise>---</c:otherwise>
										</c:choose>
									</td>
									<td> ${pagamento.getValorFormatado()}</td>
									<td> ${pagamento.getDataFormatada()}</td>
									<td style="width: 14%;">
										<div class="btn-group"> 
				                            <form action="abrirPagamento" target="_blank" method="GET">
				                                <input type="hidden" value="${pagamento.id }" name="pagamentoId">
				                                <button type="submit" class="btn btn-link" title="Visualizar pagamento"><!--<img src="../img/visualizar.png">-->
				                                	<i class="material-icons" style="color:black">insert_drive_file</i></button> <!--Visualizar Pagamento-->
				                            </form>

											<form action="atualizarPagamento" method="GET">
												<input type="hidden" class="form-control" value="${pagamento.id}" name="pagamentoId">
												<button type="submit" class="btn btn-link" title="Editar pagamento"><!--<img src="../img/atualizar.png" alt="Logo">-->
													<i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
											</form>

											<form action="excluirPagamento" method="POST">
												<input type="hidden" name="nota_fiscal_id" value="${nota.id }">
												<input type="hidden" class="form-control" value="${pagamento.id}" name="pagamento_id">
												<button type="submit" class="btn btn-link" title="Apagar pagamento"> <!--<img src="../img/excluir.png" alt="Logo">--> 
													<i class="material-icons" style="color:black" >delete_forever</i> </button> <!--DELETAR-->
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
								&emsp;<button type="submit" class="btn btn-dark" title="Cadastrar pagamento">Cadastrar pagamento</button>
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
				<button class="btn btn-link" title="Cadastrar nota fiscal"> Cadastrar nota </button>
	       	</form> 
       	</div>
	</c:if>
</div>