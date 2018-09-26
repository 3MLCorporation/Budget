<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div>
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
	        <li class="breadcrumb-item active">
	        	<a href="/listarProjetos" title="Listar projetos">Projetos</a>
	        </li>
        </c:if>
        <li class="breadcrumb-item active">
        	<a href="/listarOrcamentos" title="Listar orçamentos">Orçamentos</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarCategorias" title="Listar categorias">Categorias</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarRubricas" title="Listar rubricas">Rubricas</a>
        </li>
        <li class="breadcrumb-item active">
        	<a href="/listarItens" title="Listar itens">Item</a>
        </li>
        <li class="breadcrumb-item active" title="Visualizar detalhes">
        	<a href="=">Detalhes</a>
        </li>       
      </ol>
    </div>

    

   	<div class="card mb-3">
   		<div class="card-header">
        	<i class="fa fa-area-chart"></i> Detalhes do item <!--(${orcamentoSelecionado})-->
        </div>
        <div class="card-body">
			Nome: <strong>${item.nome}</strong>&emsp;
			Descrição: <strong>${item.descricao}</strong>&emsp;
			Valor: <strong>${item.getValorFormatado()}</strong>&emsp;
			Quantidade: <strong>${item.quantidade}</strong>&emsp;
			Preço unitário: <strong>${item.getPrecoUnitarioFormatado()}</strong>&emsp;
			Unidade de medida: 
			<strong>
				<c:choose>
					<c:when test = "${item.unidadeMedida == 0}">Verba</c:when>
					<c:when test = "${item.unidadeMedida == 1}">Unidade</c:when>
					<c:when test = "${item.unidadeMedida == 2}">Mês</c:when>
					<c:when test = "${item.unidadeMedida == 3}">Quilograma</c:when>
					<c:when test = "${item.unidadeMedida == 4}">Metro</c:when>
					<c:when test = "${item.unidadeMedida == 5}">Litro</c:when>
					<c:otherwise>---</c:otherwise>
				</c:choose>
			</strong>
			<br/>
			Valor realizado: <strong>${item.getValorRealizadoFormatado()}</strong>&emsp;
			Valor comprovado: <strong>${item.getValorComprovadoFormatado()}</strong>&emsp;
			<br/>
			<br/>

			<div class="btn-group">
				<form action="abrirArquivoDetalhesItem" target="_blank" method="GET">
					<input type="hidden" name="itemId" value="${item.id}">
					<button type="submit" class="btn btn-dark" title="Visualizar arquivo de detalhes">Arquivo de Detalhes</button>
				</form>
				
				<form action="abrirArquivoAuxiliarItem" target="_blank" method="GET">
					<input type="hidden" name="itemId" value="${item.id}">
					&emsp;<button type="submit" class="btn btn-dark" title="Visualizar arquivo auxiliar">Arquivo Auxiliar</button>
				</form>
				
				<%-- <c:if test="${ notaId != null }"> 
					<form action="visualizarNotaFiscal" method="GET">
						<input type="hidden" name="itemId" value="${item.id }">
						&emsp;<button type="submit" class="btn btn-dark">Ver nota fiscal</button>
					</form>
				</c:if> --%>
			</div>
        </div>
        
   	    <div class="card-header">
        	<i class="fa fa-area-chart"></i> Notas fiscais cadastradas
        </div>
	   	<div class="card-body">
		   	<div class="table-responsive">
	            <table class="table table-bordered" id="dataTable">
			   		<thead>
			   			<tr>
			   				<th>Fornecedor</th>
							<th>Valor total</th>
							<th>Valor comprovado</th>
							<th>Data</th>
							<th style="width: 5%;">Editar nota fiscal</th>
      					</tr>
			   		</thead>
			   		<tbody>
			   			<c:forEach items="${notas}" var="nota" varStatus="count">
							<tr>
								<td> ${fornecedores[count.index]}</td>
								<td> ${nota.getValorFormatado()}</td>
								<td> ${nota.getValorComprovadoFormatado()}</td>
								<td> ${nota.getDataFormatada()}</td>
								<td style="width: 14%;">
									<div class="btn-group"> 
			                            <form action="visualizarNotaFiscal" method="GET">
			                                <input type="hidden" value="${nota.id }" name="notaId">
			                                <button type="submit" class="btn btn-link" title="Visualizar nota fiscal">
			                                	<!--<img src="../img/visualizar.png">--><i class="material-icons" style="color:black">visibility</i></button> <!--Visualizar-->
			                            </form>

										<form action="atualizarNotaFiscal" method="GET">
											<input type="hidden" class="form-control" value="${nota.id}" name="notaId">
											<button type="submit" class="btn btn-link" title="Editar nota fiscal">
												<!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
										</form>						

										<button type="submit" class="btn btn-link open-delete-modal" title="Apagar nota" data-toggle="modal" data-target="#deletarEntidade" data-id="${nota.id}">
											<!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i></button>	<!--DELETAR NOTA FISCAL-->											
									</div>	
								</td>
							</tr>
						</c:forEach>
			   	 	</tbody>
			  	</table>
			  	<form action="cadastrarNotaFiscal" method="GET">
	           		<input type="hidden" name="itemId" value="${item.id }">
	           		<div class="botaoCadastrarPagamento">
						&emsp;<button type="submit" class="btn btn-dark" title="Cadastrar nova nota fiscal">Cadastrar nota</button>
					</div>
				</form>
		  	</div>

			<!--Caixa de dialogo do botão delete-->
		    <div class="modal fade" id="deletarEntidade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		      <div class="modal-dialog" role="document">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo apagar o nota fiscal?</h5>
		            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
		              <span aria-hidden="true">×</span>
		            </button>
		          </div>
		          <div class="modal-body">Pressione o botão "Deletar" se é o que deseja.</div>
		          <div class="modal-footer">
		            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
						<form action="excluirNotaFiscal" method="POST">
							<input type="hidden" class="form-control" id="entityId" value="" name="nota_id">
							<button type="submit" class="btn btn-primary" title="Apagar nota fiscal">Deletar</button> <!--DELETAR-->
				        </form>
		          </div>
		        </div>
		      </div>
		    </div>		  	
	  	</div> 
  	</div>
  	<%-- <c:if test="${empty notaId }"> 
   		<div class="card mb-3">
   			<div class="card-header">
		        Nota fiscal não cadastrada
		    </div>
   			<div class="card-body">
				<form action="cadastrarNotaFiscal" method="GET">
					<input type="hidden" name="itemId" value="${item.id }">
					<button class="btn btn-dark"> Cadastrar nota </button>
	       		</form>
	       	</div>
       	</div> 
	</c:if> --%>
</div>

<script>
	$(document).on("click",".open-delete-modal",function(){
		var selectedId = $(this).data('id');
		$(".modal-footer #entityId").val(selectedId);
	});
</script>