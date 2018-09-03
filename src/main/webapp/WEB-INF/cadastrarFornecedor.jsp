<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Cadastrar fornecedor
        </div>
	   	<div class="card-body">
		   	<form action="cadastrarFornecedor" method="POST">
		  		<div class="form-group">
		       	   <label for="text">Nome de fantasia:</label> <input type="text"
		        	class="form-control" placeholder="Fornecer o nome de fantasia"
		       	   name="nomeFantasia" required="required">
		      	</div>
		      	<div class="form-group">
		       	   <label for="text">Razão social:</label> <input type="text"
		        	class="form-control" placeholder="Fornecer a razão social"
		       	   name="razaoSocial" required="required">
		      	</div>
		      <div class="form-group">
		           <label for="text">CNPJ:</label> <input type="number"
		           class="form-control" placeholder="Fornecer o cnpj do fornecedor"
		           name="cnpj" required="required">
		       </div>
		      <div class="form-group">
		           <label for="text">UF:</label> 
			       <select class="form-control" name="uf">
			         <c:forEach items="${ufs}" var="uf">
			            <option value="${uf}">${uf}</option>
			          </c:forEach>
			       </select>		           
		       </div>		       
		      <button type="submit" class="btn btn-dark botaoCadastro" title="Cadastrar fornecedor" title="Cadastrar fornecedor">Cadastrar</button>
		  	</form>
	  	</div>
  	</div>
</div>