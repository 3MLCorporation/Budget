<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>Budget</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/estilo.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="container" style="width: 60%;">
        <h2>Cadastro do orçamento</h2>
<!--
    <p>
        <font color="#1E8449">${requestScope.message}</font>
    </p>
    <p>
        <font color="red">${requestScope.errorMessage}</font>
    </p>
-->
    <br/>
    <form action="cadastroOrcamento" method="post">
        <div class="form-group">
            <label for="descricao">Orçamento:</label> <input type="text"
            class="form-control" placeholder="Fornecer o nome do novo orçamento"
            name="codigo" value="${requestScope.orcamento}" required="required">
        </div>
        <div class="form-group">
            <label for="text">Valor:</label> <input type="text"
            class="form-control" placeholder="Fornecer o valor do orçamento em reais"
            name="disciplina" value="${requestScope.valor}" required="required">
        </div>
        <button type="submit" class="btn btn-default">Cadastrar</button>
    </form>
    </div>
</body>
</html> 