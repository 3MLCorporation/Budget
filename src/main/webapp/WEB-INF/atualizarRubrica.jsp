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
        	<i class="fa fa-area-chart"></i> Atualizar rubrica
        </div>
	   	<div class="card-body">
		  <form action="atualizarRubrica" method="POST">
		    <div class="form-group">
		      <label for="text">Rubrica:</label> <input type="text"
		      class="form-control" placeholder="Fornecer o novo nome da nova rubrica"
		      name="nome" value="${rubrica.nome}">
		    </div>
		    <div class="form-group">
		       <label for="text">Valor:</label> <input type="number" pattern="[0-9.]"
		       id="valor" class="form-control" placeholder="Fornecer o novo valor estimado da rubrica"
		       name="valor" value="${rubrica.valorEstimado}">
		    </div>
		         <%-- <div class="form-group">
		       	 <label>Orçamento:</label>
		       	 	<select class="form-control" name="orcamento">
		  					<c:forEach items="${orcamentos}" var="orcamento">
		  						<option>${orcamento.nome}</option>
		  					</c:forEach>
		  			</select>
		  		</div> --%>
		     <div class="form-group">
		       <label for="text">Categoria:</label>
		       <select class="form-control" name="categoriaId">
		         <c:forEach items="${categorias}" var="categoria">
		            <option value="${categoria.id }" ${categoriaAtualId == categoria.id ? 'selected' : ''}>${categoria.nome}</option>
		          </c:forEach>
		       </select>
		      </div>
		      <input type="hidden" class="form-control" value="${rubrica.id}" name="rubricaId">
		      <button type="submit" class="btn btn-dark botaoCadastro" title="Atualizar rubrica">Atualizar</button>
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