<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


  <div class="container" style="width: 60%;">
      <h2>Cadastrar rubrica</h2>
  <br/>
  <form action="cadastroCategoria" method="post">
 	 <div class="form-group">
          <label for="text">Rubrica:</label> <input type="text"
          class="form-control" placeholder="Fornecer o nome da nova rubrica"
          name="nome" required="required">
      </div>
      <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o valor da rubrica"
           name="valor" required="required">
       </div>
       <div class="form-group">
     	 <label>Orçamento:</label>
     	 <select>
     	 	<select class="form-control" name="orcamento">
					<c:forEach items="${requestScope.orcamento}" var="orcamentoDB">
						<option ${requestScope.orcamento == orcamentoDB.codigo ? 'selected' : ''}>${orcamentoDB.codigo}</option>
					</c:forEach>
			</select>
     	 </select>
		</div>
       <div class="form-group">
     	 <label>Categoria:</label>
     	 <select>
     	 	<select class="form-control" name="categoria">
					<c:forEach items="${requestScope.categoria}" var="categoriaDB">
					<option ${requestScope.categoria == categoriaDB.codigo ? 'selected' : ''}>${categoriaDB.codigo}</option>
			</c:forEach>
			</select>
		</select>
		</div>
      <button type="submit" class="btn btn-default">Cadastrar</button>
  </form>
  </div> 