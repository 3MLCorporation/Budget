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
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Atualizar categoria
        </div>
	   	<div class="card-body">
		   	<form action="atualizarCategoria" method="POST">
		  		<div class="form-group">
		       	   <label for="text">Categoria:</label> <input type="text"
		        	class="form-control" placeholder="Fornecer o novo nome da nova categoria"
		       	   name="nome" value="${categoria.nome}">
		      </div>
	    	<%-- <div class="form-group">
		     	 <label>Orçamento:</label>
		     	 	<select class="form-control" name="orcamentoId">
							<c:forEach items="${orcamentos}" var="orcamento">
								<option value="${orcamento.id }">${orcamento.nome}</option>
							</c:forEach>
					</select>
				</div> --%>
		      <input type="hidden" class="form-control" value="${categoria.id}" name="categoriaId">
		      <button type="submit" class="btn btn-dark botaoCadastro" title="Atualizar categoria">Atualizar</button>
		  	</form>
	  	</div>
  	</div>
</div>