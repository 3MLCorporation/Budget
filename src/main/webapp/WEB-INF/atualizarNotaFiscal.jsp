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
          <i class="fa fa-area-chart"></i> Atualizar nota fiscal
        </div>
      <div class="card-body">
        <form action="atualizarNotaFiscal" method="POST" enctype="multipart/form-data">
          <input type="hidden" name="item_id" value="${nota.itemId }">
          <div class="form-group">
            <label for="text">Fornecedor</label>
            <div class="row-fluid">
              <select required data-live-search="true" data-live-search-style="startsWith" class="selectpicker form-control" title="Selecione..." name="fornecedor_id">
                <c:forEach items="${fornecedores}" var="fornecedor">
                  <option value="${fornecedor.id}">${fornecedor.nomeFantasia}</option>
                </c:forEach>
              </select>
            </div>
          </div> 

          <div class="form-group">
            <label for="text">Valor</label> <input type="number" pattern="[0-9.]"
            id="valor" class="form-control" placeholder="Fornecer o novo valor da nota"
            name="valor" value="${nota.valor }">
          </div>

          <div class="row">
            <div class="form-group col-lg-6">
              <label for="text">Data</label> <input type="date"
              class="form-control" placeholder="Fornecer a nova data de emissão da nota"
              name="data" value="${nota.getDataFormatadaUS() }">

            </div>

            <div class="form-group col-lg-6">
              <label for="text">Arquivo</label> <input type="file"
              class="form-control" placeholder="Fornecer o novao arquivo da nota"
              name="arquivo" accept=".pdf">
            </div>
          </div>

          <br/>
		  <input type="hidden" class="form-control" value="${nota.id}" name="id">
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