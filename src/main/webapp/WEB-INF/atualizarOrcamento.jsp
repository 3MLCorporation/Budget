<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <c:if test="${not empty orcamento}">
	          <a href="">
				${orcamento.nome}
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
        	<i class="fa fa-area-chart"></i> Atualizar orçamento
        </div>
	   	<div class="card-body">
			<form action="atualizarOrcamento" method="POST">
			    <div class="form-group">
			        <label for="descricao">Orcamento:</label> <input type="text"
			        class="form-control" placeholder="Fornecer o novo nome do novo orçamento"
			        name="nome" value="${orcamento.nome}">
			   </div>
			   <div class="form-group">
			       <label for="text">Valor:</label> <input type="number" pattern="[0-9.]"
			       id="valor" class="form-control" placeholder="Fornecer o novo valor estimado do orçamento"
			       name="valor" value="${orcamento.valorTotal}">
			    </div>
			    <input type="hidden" class="form-control" value="${orcamento.id}" name="orcamentoId">
			    <button type="submit" class="btn btn-dark botaoCadastro">Atualizar</button>
			</form>
	  	</div>
  	</div>
</div>

<script>
	$(document).ready(function(){
	    $("form").delegate('#valor', 'focusout', function(){
	        if($(this).val() < 0){
	            $(this).val('0');
	        }
	    });
	});
</script>