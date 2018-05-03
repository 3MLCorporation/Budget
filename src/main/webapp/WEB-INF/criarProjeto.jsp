<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="container" style="width: 60%;">
   <h2>Cadastrar projeto</h2>
<br/>
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
    <button type="submit" class="btn btn-dark">Cadastrar</button>
</form>
</div>