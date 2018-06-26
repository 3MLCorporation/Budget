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

    

   	<div class="card mb-3">
   		<div class="card-header">
        	<i class="fa fa-area-chart"></i> Detalhes do item
        </div>
        <div class="card-body">
			Nome: <strong>${item.nome}</strong>&emsp;
			Descrição: <strong>${item.descricao}</strong>&emsp;
			Valor: <strong>${item.getValorFormatado()}</strong>&emsp;
			Quantidade: <strong>${item.quantidade}</strong>&emsp;
			Valor uniforme: <strong>${item.getValorUniformeFormatado()}</strong>&emsp;
			Unidade de medida: 
			<strong>
				<c:choose>
					<c:when test = "${item.unidadeMedida == 0}">Verba</c:when>
					<c:when test = "${item.unidadeMedida == 1}">Unidade</c:when>
					<c:when test = "${item.unidadeMedida == 2}">Mês</c:when>
					<c:when test = "${item.unidadeMedida == 3}">Quilograma</c:when>
					<c:when test = "${item.unidadeMedida == 4}">Metro</c:when>
					<c:when test = "${item.unidadeMedida == 5}">Litro</c:when>
					<c:otherwise>---</c:otherwise>
				</c:choose>
			</strong>

			<br/>
			<br/>

			<div class="btn-group">
				<form action="abrirArquivoDetalhesItem" target="_blank" method="GET">
					<input type="hidden" name="itemId" value="${item.id}">
					<button type="submit" class="btn btn-dark">Ver detalhes</button>
				</form>
				
				<form action="abrirArquivoAuxiliarItem" target="_blank" method="GET">
					<input type="hidden" name="itemId" value="${item.id}">
					&emsp;<button type="submit" class="btn btn-dark">Ver arquivo auxiliar</button>
				</form>
				
				<%-- <c:if test="${ notaId != null }"> 
					<form action="visualizarNotaFiscal" method="GET">
						<input type="hidden" name="itemId" value="${item.id }">
						&emsp;<button type="submit" class="btn btn-dark">Ver nota fiscal</button>
					</form>
				</c:if> --%>
			</div>
        </div>
        
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Notas fiscais cadastradas
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			   				<th>Fornecedor</th>
							<th>Valor total</th>
							<th>Valor parcial</th>
							<th>Data</th>
							<th></th>
      					</tr>
			   		</thead>
			   		<tbody>
			   			<c:forEach items="${notas}" var="nota" varStatus="id">
							<tr>
								<td> ${nota.fornecedor()}</td>
								<td> ${nota.getValorTotalFormatado()}</td>
								<td> ${nota.getValorParcialFormatado()}</td>
								<td> ${nota.getDataFormatada()}</td>
								<td style="width: 14%;">
									<div class="btn-group"> 
			                            <form action="visualizarNotaFiscal" target="_blank" method="GET">
			                                <input type="hidden" value="${nota.id }" name="notaId">
			                                <button type="submit" class="btn btn-link"><img src="../img/visualizar.png"></button>
			                            </form>

										<form action="atualizarNotaFiscal" method="GET">
											<input type="hidden" class="form-control" value="${nota.id}" name="notaId">
											<button type="submit" class="btn btn-link"><img src="../img/atualizar.png" alt="Logo"></button>
										</form>

										<form action="excluirNotaFiscal" method="POST">
											<input type="hidden" class="form-control" value="${nota.id}" name="nota_id">
											<button type="submit" class="btn btn-link"> <img src="../img/excluir.png" alt="Logo"> </button>
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

	
  	<%-- <c:if test="${empty notaId }"> 
   		<div class="card mb-3">
   			<div class="card-header">
		        Nota fiscal não cadastrada
		    </div>
   			<div class="card-body">
				<form action="cadastrarNotaFiscal" method="GET">
					<input type="hidden" name="itemId" value="${item.id }">
					<button class="btn btn-dark"> Cadastrar nota </button>
	       		</form>
	       	</div>
       	</div> 
	</c:if> --%>
</div>