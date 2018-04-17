<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
   <div class="container" style="width: 60%;">
       <h2>Cadastro de itens</h2>
   <br/>
   <form action="cadastroCategoria" method="post">
       <div class="form-group">
           <label for="descricao">Rubrica:</label> <input type="text"
           class="form-control" placeholder="Fornecer o nome de uma rubrica já existente"
           name="rubrica" value="${requestScope.orcamento}" required="required">
       </div>
       <div class="form-group">
           <label for="text">Item:</label> <input type="text"
           class="form-control" placeholder="Fornecer o nome do novo item"
           name="itens" value="${requestScope.itens}" required="required">
       </div>
       <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o nome do novo item"
           name="itens" value="${requestScope.itensValor}" required="required">
       </div>
       <button type="submit" class="btn btn-default">Cadastrar</button>
   </form>
   </div>