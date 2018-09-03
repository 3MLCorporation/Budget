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
        	<i class="fa fa-area-chart"></i> Cadastrar orçamento
        </div>
	   	<div class="card-body">
			<form action="cadastrarOrcamento" method="POST">
			    <div class="form-group">
			        <label for="descricao">Orcamento:</label> <input type="text"
			        class="form-control" placeholder="Fornecer o nome do novo orçamento"
			        name="nome" required="required">
			   </div>
			   <div class="form-group">
			       <label for="text">Valor:</label> <input type="number" pattern="[0-9.]"
			       class="form-control" placeholder="Fornecer o valor estimado do orçamento" id="valor"
			       name="valor">
			    </div>
			    <button type="submit" class="btn btn-dark botaoCadastro" title="Cadastrar orçamento">Cadastrar</button>
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