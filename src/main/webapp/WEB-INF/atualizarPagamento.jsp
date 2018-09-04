<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  
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
          <a href="=#">Detalhes</a>
        </li>
        <li class="breadcrumb-item active" title="Visualizar nota fiscais">
          <a href="">Nota fiscal</a>
        </li>
        <li class="breadcrumb-item active" title="Visualizar pagamentos">
          <a href="">Pagamento</a>
        </li>                           
      </ol>
    </div>
      
    <div class="card mb-3">
      <div class="card-header">
          <i class="fa fa-area-chart"></i> Atualizar pagamento
        </div>
        <div class="card-body">
          <form action="atualizarPagamento" method="POST" enctype="multipart/form-data">
            <div class="form-group">
              <input type="hidden" name="nota_fiscal_id" value="${pagamento.notaFiscalId }">
              <label for="text">Arquivo</label> <input type="file"
              class="form-control" placeholder="Fornecer o novo arquivo da nota"
              name="arquivo" accept=".pdf">
            </div>
            
            <div class="form-group">
			     <label for="text">Tipo de pagamento: </label>
			     <select class="form-control" name="tipo">
			      <option value="1" ${pagamento.tipo == 1 ? 'selected' : ''}>Boleto bancário</option>
			      <option value="2" ${pagamento.tipo == 2 ? 'selected' : ''}>Depósito bancário</option>
			      <option value="3" ${pagamento.tipo == 3 ? 'selected' : ''}>Cheque</option>
			      <option value="4" ${pagamento.tipo == 4 ? 'selected' : ''}>Fatura</option>
			      <option value="5" ${pagamento.tipo == 5 ? 'selected' : ''}>DARF</option>
			      <option value="5" ${pagamento.tipo == 6 ? 'selected' : ''}>ISSQN</option>
			    </select>
		  	</div>
			  
            <div class="row">
              <div class="form-group col-lg-6">
                <label for="text">Valor</label> <input type="number" pattern="[0-9.]"
                id="valor" class="form-control" placeholder="Fornecer o novo valor da nota"
                name="valor" value="${pagamento.valor }">
              </div>


              <div class="form-group col-lg-6">
                <label for="text">Data</label> <input type="date"
                class="form-control" placeholder="Fornecer a nova data de emissão da nota"
                name="data" value="${pagamento.getDataFormatadaUS()}">
              </div>
            </div>
            
            <br/>
            <input type="hidden" class="form-control" value="${pagamento.id}" name="id">
            <button type="submit" class="btn btn-dark" title="Atualizar pagamento">Atualizar</button> 
          </form>
        </div>
    </div>
</div>

<script>
	$(document).ready(function(){
	    $("form").delegate('#valor', 'focusout', function(){
	        if($(this).val() < 0){
	            $(this).val('0');
	        }
	    });
	});
</script>