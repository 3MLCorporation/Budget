<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <title>Budget</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="../css/estilo.css">
  <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.png" />
  <meta name="viewport" content="width=device-width, initial-scale=1">
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
          <!-- Imagem do usu�rioe -->
          <img src="../img/cadastrarUsuario.png" alt="Profile Picture" class="img-responsive" width="100" height="100">
        </div>
      </div>

      <div class="container" style="width: 80%;">
        <h2>Cadastro</h2>
        <br/>
        <form action="cadastrarUsuario" method="POST">

          <div class="form-group">
            <label for="text">Nome</label> <input type="text"
            class="form-control" placeholder="Fornecer o nome do usu�rio"
            name="nome" required="required">
          </div>
          
          <div class="form-group">
            <label for="text">Email</label> <input type="email"
            class="form-control" placeholder="Fornecer o email do usu�rio"
            name="email" required="required">
          </div>
          
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
          
          <div class="form-group">
            <label for="text">Confirmar senha</label> <input type="password"
            class="form-control" placeholder="Fornecer novamente a senha do usu�rio"
            name="confirmacao" required="required">
          </div>
          
          <br/>
          <button type="submit" class="btn btn-dark" title="Cadastrar usu�rio">Cadastrar</button> 
        </form>
        
        <form action="login" method="GET">
         <div class="botaoVoltar"><button class="btn btn-dark" title="Voltar a tela de login">Voltar</button></div>
       </form>
     </div>

   </div>
 </div>
</body>