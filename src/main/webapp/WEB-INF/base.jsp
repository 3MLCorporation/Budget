<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
  <title>Budget</title>
  <meta charset="UTF-8">
  <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon">
  <link rel="stylesheet" href="../css/estilo.css">
  <script src="../js/script.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <!-- Bootstrap core CSS-->
  <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom fonts for this template-->
  <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <!-- Page level plugin CSS-->
  <link href="../vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="../css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <a class="navbar-brand" href="/principal">Budget</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Dashboard">
          <a class="nav-link" href="/mostrarGraficos">
            <i class="fa fa-fw fa-dashboard"></i>
            <span class="nav-link-text">Gráficos</span>
          </a>
        </li>

        <c:if test="${sessionScope.usuario.perfil == 0}"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="usuarios">
            <a class="nav-link" href="/principal">
              <i class="fa fa-fw fa-user"></i>
              <span class="nav-link-text">Usuários</span>
            </a>
          </li>
        </c:if>

        <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }"><!--PERFIL_ADMIN || PERFIL_GERENTE-->
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Projetos">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseProjetos" data-parent="#exampleAccordion">
              <i class="fa fa-fw fa-list-ul"></i>
              <span class="nav-link-text">Projetos</span>
            </a>
            <ul class="sidenav-second-level collapse" id="collapseProjetos">
              <li>
                <a href="/cadastrarProjeto">Criar</a>
              </li>
              <li>
                <a href="/listarProjetos">Listar</a>
              </li>
            </ul>
          </li>
        </c:if>

        <c:if test="${not empty sessionScope.projetoEditavel || sessionScope.usuario.perfil == 2}"> <!--O orçamento só irá aparecer caso o projeto não esteja vazio ou caso o usuário seja padrão-->
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Orcamentos">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseOrcamentos" data-parent="#exampleAccordion">
              <c:if test="${sessionScope.usuario.perfil == 2}">
                <i class="fa fa-fw fa-list-ul"></i>
              </c:if>
              
              <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
                <i class="fa fa-fw fa-money-check-alt"></i>
              </c:if>
              <span class="nav-link-text">Orçamento</span>
            </a>
            <ul class="sidenav-second-level collapse" id="collapseOrcamentos">
              <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
                <li>
                  <a href="/cadastrarOrcamento">Criar</a>
                </li>
              </c:if>
              <li>
                <a href="/listarOrcamentos">Listar</a>
              </li>
            </ul>
          </li>
        </c:if>

        <c:if test="${not empty sessionScope.orcamentoEditavel}">
          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Categorias">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseCategorias" data-parent="#exampleAccordion">
              <i class="fa fa-fw fa-chart-line"></i>
              <span class="nav-link-text">Categoria</span>
            </a>
            <ul class="sidenav-second-level collapse" id="collapseCategorias">
              <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
                <li>
                  <a href="/cadastrarCategoria">Criar</a>
                </li>
              </c:if>
              <li>
                <a href="/listarCategorias">Listar</a>
              </li>
            </ul>
          </li>

          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Rubricas">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseRubricas" data-parent="#exampleAccordion">
              <i class="fa fa-fw fa-project-diagram"></i>
              <span class="nav-link-text">Rubrica</span>
            </a>
            <ul class="sidenav-second-level collapse" id="collapseRubricas">
              <c:if test="${sessionScope.usuario.perfil == 0 || sessionScope.usuario.perfil == 1 }">
                <li>
                  <a href="/cadastrarRubrica">Criar</a>
                </li>
              </c:if>
              <li>
                <a href="/listarRubricas">Listar</a>
              </li>
            </ul>
          </li>

          <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Itens">
            <a class="nav-link nav-link-collapse collapsed" data-toggle="collapse" href="#collapseItens" data-parent="#exampleAccordion">
              <i class="fa fa-fw fa-dolly"></i>
              <span class="nav-link-text">Item</span>
            </a>
            <ul class="sidenav-second-level collapse" id="collapseItens">
              <li>
                <a href="/cadastrarItem">Criar</a>
              </li>
              <li>
                <a href="/listarItens">Listar</a>
              </li>
            </ul>
          </li>
        </c:if>
      </ul>


      <ul class="navbar-nav sidenav-toggler">
        <li class="nav-item">
          <a class="nav-link text-center" id="sidenavToggler">
            <i class="fa fa-fw fa-angle-left"></i>
          </a>
        </li>
      </ul>
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
            <i class="fa fa-fw fa-sign-out"></i>Sair</a>
        </li>
      </ul>
    </div>
  </nav>
  <div class="content-wrapper">
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Deseja mesmo sair?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Pressione o botão "Sair" se deseja finalizar a sessão atual.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
            <a class="btn btn-primary" href="/logout">Sair</a>
          </div>
        </div>
      </div>
    </div>
    
    
    <script
	  src="https://code.jquery.com/jquery-3.3.1.min.js"
	  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	  crossorigin="anonymous"></script>
	  
    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Page level plugin JavaScript-->
    <script src="../vendor/datatables/jquery.dataTables.js"></script>
    <script src="../vendor/datatables/dataTables.bootstrap4.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin.min.js"></script>
    <!-- Custom scripts for this page-->
    <script src="../js/sb-admin-datatables.min.js"></script>
  

  <br/>
  <c:import url="/WEB-INF/${page}.jsp" />
  <!--<footer class="sticky-footer">
      <div class="container">
        <div class="text-center">
          <small>Copyright © Budget enterprise</small>
        </div>
      </div>
  </footer>
  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fa fa-angle-up"></i>
  </a>
</body>

</html>


