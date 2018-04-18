<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   
   <div class="container" style="width: 60%;">
       <h2>Cadastro de itens</h2>
   <br/>
   <form action="cadastroCategoria" method="post">
   <div class="form-group">
         <label for="text">Item:</label> <input type="text"
         class="form-control" placeholder="Fornecer o nome do novo item"
         name="nome" required="required">
     </div>
     <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o valor do item"
           name="valor" required="required">
       </div>
       <div class="form-group">
       <label>Rubrica:</label>
       	<select>
     	 	<select class="form-control" name="rubrica">
					<c:forEach items="${requestScope.rubrica}" var="rubricaDB">
					<option ${requestScope.rubrica == rubricaDB.codigo ? 'selected' : ''}>${rubricaDB.codigo}</option>
			</c:forEach>
			</select>
		</select>
       </div>  
       <button type="submit" class="btn btn-default">Cadastrar</button>
   </form>
   </div>