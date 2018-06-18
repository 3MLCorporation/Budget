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
            <a href="/listarProjetos">Projeto</a>
          </li>
        </c:if>
        <li class="breadcrumb-item active">
          <a href="/listarOrcamentos">Orçamento</a>
        </li>
        <li class="breadcrumb-item active">
          <a href="/listarCategorias">Categoria</a>
        </li>
        <li class="breadcrumb-item active">
        <a href="/listarRubricas">Rubrica</a>
        </li>
        <li class="breadcrumb-item active">
          <a href="/listarItens">Item</a>
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
            <button type="submit" class="btn btn-dark">Atualizar</button> 
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