<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Budget</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="estilo.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
    <!-- Barra de menu lateral
    <div class="sidenav">
        <h1 class="nav-link dropdown-toggle"  href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true"
        aria-expanded="false">Category</h1>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
            <a class="dropdown-item" href="#">New</a>
        </div>

        <button class="dropdown-btn">Apartamento<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-container">
            <a href="#">Banheiro</a>
            <a href="#">Quarto</a>
            <a href="#">Cozinha</a>
        </div>

        <button class="dropdown-btn">Casa<i class="fa fa-caret-down"></i></button>
        <div class="dropdown-container">
            <a href="#">Banheiro</a>
            <a href="#">Quarto</a>
            <a href="#">Cozinha</a>
        </div>
    </div>

    <script>
        /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
        var dropdown = document.getElementsByClassName("dropdown-btn");
        var i;

        for (i = 0; i < dropdown.length; i++) {
          dropdown[i].addEventListener("click", function() {
            this.classList.toggle("active");
            var dropdownContent = this.nextElementSibling;
            if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
          } else {
              dropdownContent.style.display = "block";
          }
      });
      }
  </script>-->


  <div class="header">
        <div class="bg-color">
            <header id="main-header">
                <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                    <a class="navbar-brand" href="base.jsp">Inicio</a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                        <ul class="navbar-nav mr-auto">
                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
                                data-toggle="dropdown" role="button" aria-haspopup="true"
                                aria-expanded="false">Orçamento <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="criarOrcamento.jsp">Criar</a></li>
                                    <li><a class="dropdown-item" href="#">Atualizar</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
                                data-toggle="dropdown" role="button" aria-haspopup="true"
                                aria-expanded="false">Categoria <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="criarCategoria.jsp">Criar</a></li>
                                    <li><a class="dropdown-item" href="#">Atualizar</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
                                data-toggle="dropdown" role="button" aria-haspopup="true"
                                aria-expanded="false">Rubrica <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="criarRubrica.jsp">Criar</a></li>
                                    <li><a class="dropdown-item" href="#">Atualizar</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
                                data-toggle="dropdown" role="button" aria-haspopup="true"
                                aria-expanded="false">UsuÃ¡rio <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">Logar</a></li>
                                    <li><a class="dropdown-item" href="#">Cadastrar</a></li>
                                </ul>
                            </li>
                                <a class="nav-link" href="#">Sobre nós</a>
                            </li>

                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="text" placeholder="Pesquisa">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
                        </form>
                    </div>
                </nav>
            </header>
        </div>
    </div> 
<!-- Barra de menu lateral
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris
                condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod.
            Donec sed odio dui. </p>
            <p>
                <a class="btn btn-secondary" href="#" role="button">View details &raquo;</a>
            </p>
        </div>
        <div class="col-md-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris
                condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod.
            Donec sed odio dui. </p>

            <p>
                <a class="btn btn-secondary" href="#" role="button">View details &raquo;</a>
            </p>

        </div>
        <div class="col-md-4">
            <h2>Heading</h2>
            <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta
                felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum
            massa justo sit amet risus.</p>
            <p>
                <a class="btn btn-secondary" href="#" role="button">View details &raquo;</a>
            </p>
        </div>
    </div>
</div>
-->
</body>

</html>