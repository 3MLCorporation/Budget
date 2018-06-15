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
        <form action="atualizarrNotaFiscal" method="POST" enctype="multipart/form-data">
          <input type="hidden" name="item_id" value="${item_id }">
          <div class="form-group">
            <label for="text">Fornecedor</label> <input type="text"
            class="form-control" placeholder="Fornecer o novo fornecedor"
            name="fornecedor">
          </div>

          <div class="form-group">
            <label for="text">Valor</label> <input type="number" pattern="[0-9.]"
            class="form-control" placeholder="Fornecer o novo valor da nota"
            name="valor">
          </div>

          <div class="row">
            <div class="form-group col-lg-6">
              <label for="text">Data</label> <input type="date"
              class="form-control" placeholder="Fornecer a nova data de emissão da nota"
              name="data">

            </div>

            <div class="form-group col-lg-6">
              <label for="text">Arquivo</label> <input type="file"
              class="form-control" placeholder="Fornecer o novao arquivo da nota"
              name="arquivo" accept=".pdf">
            </div>
          </div>

          <br/>
          <button type="submit" class="btn btn-dark">Atualizar</button> 
        </form>
      </div>
    </div>
</div>