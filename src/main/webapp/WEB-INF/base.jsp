<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Budget</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="../css/estilo.css">
    <script src="../js/script.js"></script>
    <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>
  <div class="header">
        <div class="bg-color">
            <header id="main-header">
                <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                	<img src="../img/favicon.png" alt="Logo" style="width:50px;">
                    <a class="navbar-brand" href="/principal">Inicio</a>
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
                                    <li><a class="dropdown-item" href="/cadastrarOrcamento">Criar</a></li>
                                    <li><a class="dropdown-item" href="/listarOrcamentos">Listar</a></li>
                                </ul>
                            </li>
                            <c:if test="${not empty sessionScope.orcamentoEditavel}">
	                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
	                                data-toggle="dropdown" role="button" aria-haspopup="true"
	                                aria-expanded="false">Categoria <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a class="dropdown-item" href="/cadastrarCategoria">Criar</a></li>
	                                    <li><a class="dropdown-item" href="/listarCategorias">Listar</a></li>
	                                </ul>
	                            </li>
	                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
	                                data-toggle="dropdown" role="button" aria-haspopup="true"
	                                aria-expanded="false">Rubrica <span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a class="dropdown-item" href="/cadastrarRubrica">Criar</a></li>
	                                    <li><a class="dropdown-item" href="/listarRubricas">Listar</a></li>
	                                </ul>
	                            </li>
	                            <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
	                                data-toggle="dropdown" role="button" aria-haspopup="true"
	                                aria-expanded="false">Item<span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                    <li><a class="dropdown-item" href="/cadastrarItem">Criar</a></li>
	                                    <li><a class="dropdown-item" href="/listarItens">Listar</a></li>
	                                </ul>
	                            </li>
	                        </c:if>
	                             <li class="dropdown"><a class="nav-link" href="#" class="dropdown-toggle"
	                                data-toggle="dropdown" role="button" aria-haspopup="true"
	                                aria-expanded="false">Perfil<span class="caret"></span></a>
	                                <ul class="dropdown-menu">
	                                <li><a class="dropdown-item" href="/sobre">Sobre nós</a></li>
	                                    <li><a class="dropdown-item" href="/logout">Sair</a></li>
	                                </ul>
	                            </li>
                        </ul>
                        <form class="form-inline my-2 my-lg-0">
                            <input class="form-control mr-sm-2" type="text" placeholder="Pesquisa">
                            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
                        </form>
                    </div>
                </nav>
                
                <div class="orcamentoPosicao">
		            <c:if test="${not empty orcamentoSelecionado}">
		                <h2><strong>${orcamentoSelecionado}</strong></h2>
		            </c:if>
	            </div>
            </header>
        </div>
    </div> 
    <br/>
    <c:import url="/WEB-INF/${page}.jsp" />
</body>

</html>