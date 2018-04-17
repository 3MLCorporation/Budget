<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="container" style="width: 60%;">
   <h2>Cadastro do orçamento</h2>
<br/>
<form action="cadastroOrcamento" method="post">
    <div class="form-group">
        <label for="descricao">Orçamento:</label> <input type="text"
        class="form-control" placeholder="Fornecer o nome do novo orçamento"
        name="codigo" value="${requestScope.orcamento}" required="required">
   </div>
   <div class="form-group">
       <label for="text">Valor:</label> <input type="text"
       class="form-control" placeholder="Fornecer o valor do orçamento"
       name="disciplina" value="${requestScope.valor}" required="required">
    </div>
    <button type="submit" class="btn btn-default">Cadastrar</button>
</form>
</div>