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
        <li class="breadcrumb-item active">
        	<a href="/listarProjetos">Projetos</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Atualizar projeto
        </div>
	   	<div class="card-body">
			<form action="atualizarProjeto" method="POST">
			    <div class="form-group">
			        <label for="descricao">Projeto:</label> <input type="text"
			        class="form-control" placeholder="Fornecer o nome do novo projeto"
			        name="nome" value="${projeto.nome}">
			   </div>
			   <div class="form-group">
			       <label for="text">Valor:</label> <input type="number" pattern="[0-9.]"
			       class="form-control" placeholder="Fornecer o novo valor estimado do projeto"
			       name="valor" value="${projeto.valor}">
			    </div>
			    <button type="submit" class="btn btn-dark botaoCadastro">Atualizar</button>
			</form>
	  	</div>
  	</div>
</div>