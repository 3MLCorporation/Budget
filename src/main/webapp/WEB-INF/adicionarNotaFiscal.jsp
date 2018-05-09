<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div>
	<div class="container" style="width: 60%;">
		<h2>Adicionar nota fiscal</h2>
		<br/>
    <form action="cadastrarNotaFiscal" method="POST" enctype="multipart/form-data">

      <div class="form-group">
        <label for="text">Fornecedor</label> <input type="text"
        class="form-control" placeholder="Fornecer o fornecedor"
        name="fornecedor" required="required">
      </div>

      <div class="form-group">
        <label for="text">Valor</label> <input type="text"
        class="form-control" placeholder="Fornecer o valor da nota"
        name="valor" required="required">
      </div>

      <div class="form-group">
        <label for="text">Data</label> <input type="data"
        class="form-control" placeholder="Fornecer a data de emissão da nota"
        name="data" required="required">
      </div>

      <div class="form-group">
        <label for="text">Arquivo</label> <input type="file"
        class="form-control" placeholder="Fornecer o arquivo da nota"
        name="arquivo" required="required">
      </div>

      <br/>
      <button type="submit" class="btn btn-dark">Cadastrar</button> 
    </form>
  </div>
</div>