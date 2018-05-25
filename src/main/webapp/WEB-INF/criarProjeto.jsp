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
        	<i class="fa fa-area-chart"></i> Cadastrar projeto
        </div>
	   	<div class="card-body">
			<form action="cadastrarProjeto" method="POST">
			    <div class="form-group">
			        <label for="descricao">Projeto:</label> <input type="text"
			        class="form-control" placeholder="Fornecer o nome do novo projeto"
			        name="nome" required="required">
			   </div>
			   <div class="form-group">
			       <label for="text">Valor:</label> <input type="text"
			       class="form-control" placeholder="Fornecer o valor estimado do projeto"
			       name="valor">
			    </div>
			    <button type="submit" class="btn btn-primary botaoCadastro">Cadastrar</button>
			</form>
	  	</div>
  	</div>
</div>