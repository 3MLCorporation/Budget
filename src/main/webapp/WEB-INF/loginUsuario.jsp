<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<title>Budget</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/estilo.css">
	<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.png" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

	<body>
		<div class="container" style="width: 60%;">
			<h2>Login</h2>
			<br/>
			<form action="login" method="POST">
				<div class="form-group">
					<label for="text">Usu�rio</label> <input type="text"
					class="form-control" placeholder="Fornecer o login do usu�rio"
					name="login" required="required">
				</div>
				<div class="form-group">
					<label for="text">Senha</label> <input type="password"
					class="form-control" placeholder="Fornecer a senha do usu�rio"
					name="senha" required="required">
				</div>
				<br/>
				<button type="submit" class="btn btn-green">Logar</button>&emsp;
			</form>
			<form action="cadastrarUsuario" method="GET">
   				 <div class="botaoCadastroVoltar"><button class="btn btn-green">Cadastrar</button></div>
			</form>
		</div>
	</body>
</html>