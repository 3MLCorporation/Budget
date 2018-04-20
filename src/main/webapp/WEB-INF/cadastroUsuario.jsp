<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<h2>Cadastro</h2>
			<br/>
			<form action="cadastrarUsuario" method="POST">
				<div class="form-group">
					<label for="text">Email</label> <input type="text"
					class="form-control" placeholder="Fornecer o email do usuário"
					name="nome" required="required">
				</div>
				<div class="form-group">
					<label for="text">Usu�rio</label> <input type="text"
					class="form-control" placeholder="Fornecer o login do usuário"
					name="nome" required="required">
				</div>
				<div class="form-group">
					<label for="text">Senha</label> <input type="password"
					class="form-control" placeholder="Fornecer a senha do usuário"
					name="nome" required="required">
				</div>
				<div class="form-group">
					<label for="text">Confirmar senha</label> <input type="password"
					class="form-control" placeholder="Fornecer novamente a senha do usuário"
					name="nome" required="required">
				</div>
				<br/>
				<button class="btn btn-green">Cadastrar</button> 
			</form>
		</div>
	</body>
</html>