<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="container" style="width: 60%;">
   <h2>Cadastro do or�amento</h2>
<br/>
<form action="cadastrarOrcamento" method="post">
    <div class="form-group">
        <label for="descricao">Or�amento:</label> <input type="text"
        class="form-control" placeholder="Fornecer o nome do novo or�amento"
        name="codigo" value="${requestScope.orcamento}" required="required">
   </div>
   <div class="form-group">
       <label for="text">Valor:</label> <input type="text"
       class="form-control" placeholder="Fornecer o valor do or�amento"
       name="disciplina" value="${requestScope.valor}" required="required">
    </div>
    <button type="submit" class="btn btn-default">Cadastrar</button>
</form>
</div>