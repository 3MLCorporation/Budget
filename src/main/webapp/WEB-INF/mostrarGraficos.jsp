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
        <canvas id="graficoArea" width="100%" height="30"></canvas>
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
            <canvas id="graficoBarra" width="100" height="50"></canvas>
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
            <canvas id="graficoPizza" width="100%" height="100"></canvas>
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


  <script>
    Chart.defaults.global.defaultFontFamily='-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif',Chart.defaults.global.defaultFontColor="#292b2c";

    /* Gráfico de área*/
    var ctx=document.getElementById("graficoArea"),graficoArea=new Chart(ctx,{type:"line",data:{labels:["Mar 1","Mar 2","Mar 3","Mar 4","Mar 5","Mar 6","Mar 7","Mar 8","Mar 9","Mar 10","Mar 11","Mar 12","Mar 13"],datasets:[{label:"Sessions",lineTension:.3,backgroundColor:"rgba(2,117,216,0.2)",borderColor:"rgba(2,117,216,1)",pointRadius:5,pointBackgroundColor:"rgba(2,117,216,1)",pointBorderColor:"rgba(255,255,255,0.8)",pointHoverRadius:5,pointHoverBackgroundColor:"rgba(2,117,216,1)",pointHitRadius:20,pointBorderWidth:2,data:[1e4,30162,26263,18394,18287,28682,31274,33259,25849,24159,32651,31984,38451]}]},options:{scales:{xAxes:[{time:{unit:"date"},gridLines:{display:!1},ticks:{maxTicksLimit:7}}],yAxes:[{ticks:{min:0,max:4e4,maxTicksLimit:5},gridLines:{color:"rgba(0, 0, 0, .125)"}}]},legend:{display:!1}}}),

    /* Gráfico de barra*/
    ctx=document.getElementById("graficoBarra"),graficoBarra=new Chart(ctx,{type:"bar",data:{labels:["Janeiro","Fevereiro","Março","Abril","Maio","Junho"],datasets:[{label:"Revenue",backgroundColor:"rgba(2,117,216,1)",borderColor:"rgba(2,117,216,1)",data:[4215,5312,6251,7841,9821,14984]}]},options:{scales:{xAxes:[{time:{unit:"month"},gridLines:{display:!1},ticks:{maxTicksLimit:6}}],yAxes:[{ticks:{min:0,max:15e3,maxTicksLimit:5},gridLines:{display:!0}}]},legend:{display:!1}}}),

    /* Gráfico de pizza*/
    ctx=document.getElementById("graficoPizza"),graficoPizza=new Chart(ctx,{type:"pie",data:{labels:["Valor livre","Valor gasto"/*,"Yellow","Green"*/],datasets:[{data:[50,50   /*valorLivre(),${projeto.getValorParcial()}*/],backgroundColor:["#007bff","#dc3545"/*,"#ffc107","#28a745"*/]}]}});


    //Fazer essa função no servlet
    /*
    function valorLivre(){
      var valorParcial = ${projeto.getValorParcial()};
      var valorMaximo = ${projeto.getValorTotal();
      var valorLivre = valorMaximo - valorParcial;
      return valorLivre;
    }    
    */

  </script>