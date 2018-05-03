<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container" style="width: 60%;">
   <h2>Cadastrar orcamento</h2>
<br/>
<form action="cadastrarOrcamento" method="POST">
    <div class="form-group">
        <label for="descricao">Orcamento:</label> <input type="text"
        class="form-control" placeholder="Fornecer o nome do novo orçamento"
        name="nome" required="required">
   </div>
   <div class="form-group">
       <label for="text">Valor:</label> <input type="text"
       class="form-control" placeholder="Fornecer o valor estimado do orçamento"
       name="valor">
    </div>
    <div class="form-group">
     	 <label>Projeto:</label>
     	 	<select class="form-control" name="orcamentoId">
					<c:forEach items="${orcamento}" var="orcamento">
						<option value="${orcamento.id }">${orcamento.nome}</option>
					</c:forEach>
			</select>
		</div>
    <button type="submit" class="btn btn-dark">Cadastrar</button>
</form>
</div>