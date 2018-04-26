<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

  <div class="container" style="width: 60%;">
      <h2>Cadastrar categoria</h2>
  <br/>
  	<form action="cadastrarCategoria" method="POST">
  		<div class="form-group">
       	   <label for="text">Categoria:</label> <input type="text"
        	class="form-control" placeholder="Fornecer o nome da nova categoria"
       	   name="nome" required="required">
      </div>
      <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o valor da categoria"
           name="valor" required="required">
       </div>
<%--        <div class="form-group">
     	 <label>Orçamento:</label>
     	 	<select class="form-control" name="orcamentoId">
					<c:forEach items="${orcamentos}" var="orcamento">
						<option value="${orcamento.id }">${orcamento.nome}</option>
					</c:forEach>
			</select>
		</div> --%>
      <button type="submit" class="btn btn-dark">Cadastrar</button>
  	</form>
  </div>