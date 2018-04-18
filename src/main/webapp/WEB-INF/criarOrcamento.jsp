<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container" style="width: 60%;">
   <h2>Cadastrar orçamento</h2>
<br/>
<form action="cadastrarOrcamento" method="post">
    <div class="form-group">
        <label for="descricao">Orçamento:</label> <input type="text"
        class="form-control" placeholder="Fornecer o nome do novo orçamento"
        name="nome" required="required">
   </div>
   <div class="form-group">
       <label for="text">Valor:</label> <input type="text"
       class="form-control" placeholder="Fornecer o valor do orçamento"
       name="valor" required="required">
    </div>
    <button type="submit" class="btn btn-default">Cadastrar</button>
</form>
</div>