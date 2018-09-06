<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
	<title>Budget</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/estiloLoginUsuario.css">
	<link rel="stylesheet" href="../css/estilo.css">
	<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.png" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" type="text/css" href="estilo.css">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>

<body>
	<div class="">
		<!-- UI - X Starts -->
		<div class="ui-67">
			<!-- Head Starts  -->
			<div class="ui-head bg-dark">
				<!-- Detalhes -->
				<div class="ui-details">
					<!-- Nome -->
					<h3 id="name-header">Budget</h3>
				</div>
				<!-- Imagem -->
				<div class="ui-image">
					<!-- Imagem do usuárioe -->
					<img src="../img/moeda.png" alt="Profile Picture" class="img-responsive" width="100" height="100">
				</div>
			</div>
			<!--
			<div class="container" style="width: 60%;">
				<h2>Login</h2>
				<br/>
				<form action="login" method="POST">
					<div class="form-group">
						<label for="text">Usuário</label> <input type="text"
						class="form-control" placeholder="Fornecer o login do usuário"
						name="login" required="required">
					</div>
					<div class="form-group">
						<label for="text">Senha</label> <input type="password"
						class="form-control" placeholder="Fornecer a senha do usuário"
						name="senha" required="required">
					</div>
					<div class="form-check">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox">&emsp;  Lembrar senha
						</label>
					</div>
					<br/>
					<button type="submit" class="btn btn-dark" title="Fazer login">Logar</button>&emsp;
				</form>
				<form action="cadastrarUsuario" method="GET">
					<div class="botaoCadastroUsuario"><button class="btn btn-dark" title="Cadastrar novo usuário">Cadastrar</button></div>
				</form>
			</div>
		</div>
	</div>-->

	<div class="container">
		<form action="login" method="POST">
			<div class="group">      
			    <input type="text" required="required" name="login">
			    <span class="highlight"></span>
			    <span class="bar"></span>
			    <label>Login</label>
		    </div>
		      
		    <div class="group">      
		     	<input type="text" required="required" name="senha">
		      	<span class="highlight"></span>
		      	<span class="bar"></span>
		      	<label>Senha</label>
		    </div>
		    <button type="submit" class="btn btn-dark btnLogin" title="Fazer login">Logar</button>&emsp;
	  	</form>
	      
		<p class="footer">
		<a href="/cadastrarUsuario">Não possui uma conta ainda?</a>
	  	</p>
	</div>
</body>
</html>