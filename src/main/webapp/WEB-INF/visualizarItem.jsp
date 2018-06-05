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
			Item: <strong>${item.nome}</strong>&emsp;
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

			<br>
			<div class="btn-group">
				<form action="abrirArquivoDetalhesItem" target="_blank" method="GET">
					<input type="hidden" name="itemId" value="${item.id}">
					<button type="submit" class="btn btn-primary">Ver detalhes</button>
				</form>
				
				<form action="abrirArquivoAuxiliarItem" target="_blank" method="GET">
					<input type="hidden" name="itemId" value="${item.id}">
					&emsp;<button type="submit" class="btn btn-primary">Ver arquivo auxiliar</button>
				</form>
				
				<form action="visualizarNotaFiscal" method="GET">
					<input type="hidden" name="itemId" value="${item.id }">
					&emsp;<button type="submit" class="btn btn-primary">Ver nota fiscal</button>
				</form>
			</div>
        </div>
  	</div>


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