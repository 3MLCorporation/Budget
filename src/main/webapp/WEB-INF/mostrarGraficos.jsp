<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  
<div>
  <div class="container-fluid">
    <!-- Breadcrumbs-->
    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="/mostrarGraficos">Gráficos</a>
      </li>
    </ol>
    <!-- Area Chart Example-->
    <div class="card mb-3">
      <div class="card-header">
        <i class="fa fa-area-chart"></i> Exemplo de gráfico de área</div>
      <div class="card-body">
        <canvas id="myAreaChart" width="100%" height="30"></canvas>
      </div>
      <div class="card-footer small text-muted">Atualizado ontem as 11:59 PM</div>
    </div>
    <div class="row">
      <div class="col-lg-8">
        <!-- Example Bar Chart Card-->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-bar-chart"></i> Exemplo de gráfico de barra</div>
          <div class="card-body">
            <canvas id="myBarChart" width="100" height="50"></canvas>
          </div>
          <div class="card-footer small text-muted">Atualizado ontem as 11:59 PM</div>
        </div>
      </div>
      <div class="col-lg-4">
        <!-- Example Pie Chart Card-->
        <div class="card mb-3">
          <div class="card-header">
            <i class="fa fa-pie-chart"></i> Exemplo de gráfico em pizza</div>
          <div class="card-body">
            <canvas id="myPieChart" width="100%" height="100"></canvas>
          </div>
          <div class="card-footer small text-muted">Atualizado ontem as 11:59 PM</div>
        </div>
      </div>
    </div>
  </div>

  <!-- Page level plugin JavaScript-->
  <script src="../vendor/chart.js/Chart.min.js"></script>
  <!-- Custom scripts for all pages-->
  <script src="../js/sb-admin.min.js"></script>
  <!-- Custom scripts for this page-->
  <script src="../js/sb-admin-charts.min.js"></script>
</div>
