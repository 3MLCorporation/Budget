<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div>
	<div class="container" style="width: 60%;">
		<h3><strong>Nota fiscal</strong></h3> <!--TEM QUE MUDAR O CÓDIGO-->
		<br/>
		<h4>Fornecedor: <strong>${nota.fornecedor}</strong> Valor: <strong>${nota.valor}</strong> Data: <strong>${nota.data}</h4>
		<br/>
		<form action="abrirNotaFiscal" method="GET">
			<input type="hidden" name="notaId" value="${nota.id}">
			<button type="submit" class="btn btn-dark">Ver arquivo</button>
		</form>
		<br/>
		<h3><strong>Pagamentos cadastrados</strong></h3>
		<table class="table">
	    	<thead>
	      		<tr>
	      			<th>Valor</th>
	        		<th>Data</th>
	        		<th>Arquivo</th>
	      		</tr>
	    	</thead>
	    	<tbody>
	    		<c:forEach items="${usuarios}" var="usuario" varStatus="id">
		    		<tr>
		    			<td> ${pagamento.valor}</td>
		    			<td> ${pagamento.data}</td>
		    			<td> 
							<form action="visualizarPagamento" method="GET">
								<button type="submit" class="btn btn-dark"><img src="../img/visualizar.png" style="width: 80%;"></button>
							</form>
		    			</td>
		    		</tr>
	    		</c:forEach>
	   	 	</tbody>
		</table>

		<form action="cadastrarPagamento" method="GET">
			<button type="submit" class="btn btn-dark">Cadastrar pagamento</button>
		</form>
	</div>
</div>