<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


  <div class="container">
      <h2>Cadastrar rubrica</h2>
  <br/>
  <form action="cadastrarRubrica" method="POST">
 	 <div class="form-group">
          <label for="text">Rubrica:</label> <input type="text"
          class="form-control" placeholder="Fornecer o nome da nova rubrica"
          name="nome" required="required">
      </div>
      <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o valor estimado da rubrica"
           name="valor">
       </div>
       <%-- <div class="form-group">
     	 <label>Orçamento:</label>
     	 	<select class="form-control" name="orcamento">
					<c:forEach items="${orcamentos}" var="orcamento">
						<option>${orcamento.nome}</option>
					</c:forEach>
			</select>
		</div> --%>
       <div class="form-group">
     	 <label>Categoria:</label>
     	 	<select class="form-control" name="categoriaId">
					<c:forEach items="${categorias}" var="categoria">
						<option value="${categoria.id }">${categoria.nome}</option>
					</c:forEach>
			</select>
		</div>
      <button type="submit" class="btn btn-dark">Cadastrar</button>
  </form>
  </div> 