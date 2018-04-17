<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

  <div class="container" style="width: 60%;">
      <h2>Cadastro da rubrica</h2>
  <br/>
  <form action="cadastroCategoria" method="post">
 	 <div class="form-group">
          <label for="text">Rubrica:</label> <input type="text"
          class="form-control" placeholder="Fornecer o nome da nova rubrica"
          name="disciplina" value="${requestScope.categoria}" required="required">
      </div>
      <div class="form-group">
          <label for="descricao">Categoria:</label> <input type="text"
          class="form-control" placeholder="Fornecer o nome de uma categoria já existente"
          name="codigo" value="${requestScope.orcamento}" required="required">
      </div>
      <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o valor da rubrica"
           name="itens" value="${requestScope.rubricaValor}" required="required">
       </div>
      <button type="submit" class="btn btn-default">Cadastrar</button>
  </form>
  </div> 