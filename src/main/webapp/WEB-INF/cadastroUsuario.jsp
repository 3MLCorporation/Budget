<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
  <title>Budget</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->  
  <link rel="shortcut icon" href="../img/icons/favicon.png" type="image/x-icon">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../vendor/animate/animate.css">
<!--===============================================================================================-->  
  <link rel="stylesheet" type="text/css" href="../vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../vendor/select2/select2.min.css">
<!--===============================================================================================-->  
  <link rel="stylesheet" type="text/css" href="../vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="../css/util.css">
  <link rel="stylesheet" type="text/css" href="../css/main.css">
<!--===============================================================================================--><!--
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
  -->
</head>

<body>
  
  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100">
        <div class="login100-form-title" style="background-image: url(img/bg-01.jpg);">
          <span class="login100-form-title-1">
            Cadastrar usuário
          </span>
        </div>

          <form class="login100-form validate-form" action="cadastrarUsuario" method="POST">
            <div class="wrap-input100 validate-input m-b-26" data-validate="É necessário definir o nome do usuário">
              <span class="label-input100">Nome</span>
              <input class="input100" type="text" name="nome" title="Fornecer login do usuário">
              <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-18" data-validate = "É necessário definir o email">
              <span class="label-input100">Email</span>
              <input class="input100" type="email" name="email" title="Fornecer senha do usuário">
              <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-26" data-validate="É necessário definir o login">
              <span class="label-input100">Usuário</span>
              <input class="input100" type="text" name="login" title="Fornecer login do usuário">
              <span class="focus-input100"></span>
            </div>

            <div class="wrap-input100 validate-input m-b-26" data-validate="É necessário definir a senha">
              <span class="label-input100">Senha</span>
              <input class="input100" type="password" name="senha" title="Fornecer login do usuário">
              <span class="focus-input100"></span>
            </div>                    

            <div class="wrap-input100 validate-input m-b-26" data-validate="É necessário a confirmação da senha">
              <span class="label-input100">Confirmar senha</span>
              <input class="input100" type="password" name="confirmacao" title="Fornecer login do usuário">
              <span class="focus-input100"></span>
            </div>          


            <div class="txt1" style="position: relative; left: 19%;">
              <a href="/login" title="Voltar a tela de login">Faça login em vez disso</a>
            </div>

            <br/>
            <br/>

            <div class="container-login100-form-btn" style="position: relative; left: 20%;">
              <button class="login100-form-btn" type="submit" title="Cadastrar usuário">
                Cadastrar
              </button>
            </div>
          </form>
        </div>
      </form>
    </div>
  </div>
</div>
  
<!--===============================================================================================-->
  <script src="../vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
  <script src="../vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
  <script src="../vendor/bootstrap/js/popper.js"></script>
  <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
  <script src="../vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
  <script src="../vendor/daterangepicker/moment.min.js"></script>
  <script src="../vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
  <script src="../vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
  <script src="../js/main.js"></script>
</body>
</html>