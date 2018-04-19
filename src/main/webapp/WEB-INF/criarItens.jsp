<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../js/script.js"></script>
   
   <div class="container" style="width: 60%;">
       <h2>Cadastrar itens</h2>
   <br/>
   <form action="cadastrarItem" method="POST">
   <div class="form-group">
         <label for="text">Item:</label> <input type="text"
         class="form-control" placeholder="Fornecer o nome do novo item"
         name="nome" required="required">
     </div>
     <div class="form-group">
         <label for="text">Descrição:</label> <input type="text"
         class="form-control" placeholder="Fornecer a descrição do item"
         name="descricao" required="required">
     </div>
     <div class="form-group">
           <label for="text">Quantidade:</label> <input type="text"
           class="form-control" placeholder="Fornecer a quantidade de itens"
           name="quantidade" required="required" id="quantidadeItem" onblur="calcularValorTotalItem()">
       </div>
     <div class="form-group">
           <label for="text">Valor:</label> <input type="text"
           class="form-control" placeholder="Fornecer o valor uniforme do item em reais"
           name="valor_uniforme" required="required" id="valorUniformeItem" onblur="calcularValorTotalItem()">
     </div>
     <div class="form-group">
           <label for="text">Valor total:</label> <input type="text" readonly="true" placeholder="Valor total da soma dos itens" class="form-control" id="resultado">
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