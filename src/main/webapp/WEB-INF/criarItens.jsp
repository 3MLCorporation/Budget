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
     	 	<select class="form-control" name="orcamento">
					<c:forEach items="${orcamentos}" var="orcamento">
						<option>${orcamento.nome}</option>
					</c:forEach>
			</select>
		</div>
       <div class="form-group">
     	 <label>Categoria:</label>
     	 	<select class="form-control" name="categoria">
					<c:forEach items="${categorias}" var="categoria">
						<option>${categoria.nome}</option>
					</c:forEach>
			</select>
		</div>       
     <div class="form-group">
       <label>Rubrica:</label>
     	 	<select class="form-control" name="rubricaId">
					<c:forEach items="${rubricas}" var="rubrica">
						<option value="${rubrica.id }">${rubrica.nome}</option>
					</c:forEach>
			</select>
     </div>  
     <button type="submit" class="btn btn-default">Cadastrar</button>
   </form>
   </div>