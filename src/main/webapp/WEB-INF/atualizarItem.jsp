<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="../js/script.js"></script>
 	
<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <c:if test="${not empty orcamentoSelecionado}">
	          <a href="">
				${orcamentoSelecionado}
	          </a>
          </c:if>
        </li>
        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
	        <li class="breadcrumb-item active">
	        	<a href="/listarProjetos">Projetos</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active">
        	<a href="/listarOrcamentos">Orçamentos</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarCategorias">Categorias</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarRubricas">Rubricas</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarItens">Itens</a>
        </li>
      </ol>
    </div>
      
   	<div class="card mb-3">
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Atualizar item
        </div>
	   	<div class="card-body">
			<form action="atualizarItem" method="POST" enctype="multipart/form-data" id="formAtualizarItem">
			   <div class="form-group">
			     <label for="text">Item:</label> <input type="text"
			     class="form-control" placeholder="Fornecer o novo nome do novo item"
			     name="nome" value="${item.nome}">
			   </div>
			   <div class="form-group">
			     <label for="text">Unidade de medida:</label>
			     <select class="form-control" name="unidade_medida">
			      <option value="0" ${item.unidadeMedida == 0 ? 'selected' : ''}>Verba</option>
			      <option value="1" ${item.unidadeMedida == 1 ? 'selected' : ''}>Unidade</option>
			      <option value="2" ${item.unidadeMedida == 2 ? 'selected' : ''}>Mês</option>
			      <option value="3" ${item.unidadeMedida == 3 ? 'selected' : ''}>Quilograma</option>
			      <option value="4" ${item.unidadeMedida == 4 ? 'selected' : ''}>Metro</option>
			      <option value="5" ${item.unidadeMedida == 5 ? 'selected' : ''}>Litro</option>
			    </select>
			  </div>
			  <div class="form-group">
			   <label for="text">Descrição:</label> <input type="text"
			   class="form-control" placeholder="Fornecer a nova descrição do item"
			   name="descricao" value="${item.descricao}">
			 </div>
			 <div class="form-group">
			   <label for="text">Quantidade:</label> <input type="number" pattern="[0-9.]"
			   class="form-control" placeholder="Fornecer a nova quantidade de itens"
			   name="quantidade" id="quantidadeItem" value="${item.quantidade}" onblur="calcularValorTotalItem()">
			 </div>
			 <div class="form-group">
			   <label for="text">Valor:</label> <input type="number"
			   id="valor" class="form-control" placeholder="Fornecer o novo valor uniforme do item em reais"
			   name="preco_unitario" id="valorUniformeItem" value="${item.precoUnitario}" onblur="calcularValorTotalItem()">
			 </div>
			 <div class="form-group">
			   <label for="text">Valor total:</label> <input type="text" readonly="readonly" placeholder="Valor total da soma dos itens" class="form-control" id="resultado">
			 </div>
			 
			<div class="row">
				<div class="form-group col-lg-6">
					<label for="text">Detalhes</label>
					<div class="btn-group">			    
					  	<c:if test="${!item.isArquivoDetalhesVazio()}"> 
								<a href="/abrirArquivoDetalhesItem?itemId=${item.id}" target="_blank">
									<img src="../img/visualizar.png" alt="Logo" style="width:60%;">
								</a>
													    	
								<a href="/excluirArquivoDetalhesItem?itemId=${item.id}" target="_blank">
									<img src="../img/excluir.png" alt="Logo" style="width:60%;">
							  	</a>
									
						</c:if>	
					</div>		
					<input type="file" class="form-control" placeholder="Fornecer o novo detalhes do item" name="arquivo_detalhes" accept=".pdf">
			  	</div>

				<div class="form-group col-lg-6">
					<label for="text">Auxiliar</label>
					<div class="btn-group">			    
					  	<c:if test="${!item.isArquivoAuxiliarVazio()}"> 
								<a href="/abrirArquivoAuxiliarItem?itemId=${item.id}" target="_blank">
									<img src="../img/visualizar.png" alt="Logo" style="width:60%;">
								</a>
								
								<a href="/excluirAuxiliarItem?itemId=${item.id}" target="_blank">
									<img src="../img/excluir.png" alt="Logo" style="width:60%;">
				           		</a>
						</c:if>	
					</div>						 
					<input type="file" class="form-control" placeholder="Fornecer o novo arquivo auxiliar" name="arquivo_auxiliar" accept=".pdf">
				</div>
			</div>
			<div class="row">
			     <div class="form-group col-lg-6">
			       <label>Categoria:</label>
			       <select id="categoria-select" class="form-control" name="categoria">
		         		<option value="">Selecione</option>
		         		<c:forEach items="${categorias}" var="categoria">
			        		 <option value="${categoria.id }" ${categoriaAtualId == categoria.id ? 'selected' : ''}>${categoria.nome}</option>
		       			</c:forEach>
			     	</select>
		   		 </div>
			
			     <div class="form-group col-lg-6">
				     <label>Rubrica:</label>
				     <select id="rubrica-select" class="form-control" name="rubrica_id">
		       			<c:forEach items="${rubricas}" var="rubrica">
				      	 	<option value="${rubrica.id }" ${rubricaAtualId == rubrica.id ? 'selected' : ''}>${rubrica.nome}</option>
			    		 </c:forEach>
				   	</select>
				 </div>
			</div>  
			<input type="hidden" class="form-control" value="${item.id}" name="id">
			<button type="submit" class="btn btn-dark botaoCadastro">Atualizar</button>
			</form>
	  	</div>
  	</div>
</div>


<script>

		/*function submitAtualizarItem(form){
			document.getElementById(form).submit();
		}*/

		$(document).ready(function(){
		    $("form").delegate('#valor', 'focusout', function(){
		        if($(this).val() < 0){
		            $(this).val('0');
		        }
		    });
		});
		
		$("#categoria-select").change(function() {
		    var id = $(this).val();
		    if(id){
		  	  $.get("escolherRubrica", {
				categoriaId : id
		       }, function(responseJson) {
		           var $rubricaSelect = $("#rubrica-select");
		           $rubricaSelect.empty(); 
		           $.each(responseJson, function(index, rubrica) {
		               $("<option>").val(rubrica.id).text(rubrica.nome).appendTo($rubricaSelect);
		          	});                   
		   		});
		    }
		});
		
</script>
