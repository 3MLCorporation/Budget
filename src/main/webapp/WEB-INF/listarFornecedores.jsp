<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
       <div class="card mb-3">
           <div class="card-header">
            <i class="fa fa-area-chart"></i> Fornecedores cadastrados
        </div>
           <div class="card-body">
               <div class="table-responsive">
                <table class="table table-bordered" id="dataTable">
                       <thead>
                           <tr>
                            <th>Nome de fantasia</th>
                            <th>Razão Social</th>
                            <th>CNPJ</th>
                            <th>UF</th>
                            <th style="width: 5%;"></th>
                          </tr>
                       </thead>
                       <tbody id="myTable">
                        <c:forEach items="${fornecedores}" var="fornecedor">
                            <tr>
                                <td> ${fornecedor.nomeFantasia}</td>
                                <td> ${fornecedor.razaoSocial}</td>
                                <td> ${fornecedor.cnpj}</td>
                                <td> ${fornecedor.uf}</td>
                                <td>
                                    <div class="btn-group">
                                        <form action="atualizarFornecedor" method="GET">
                                            <input type="hidden" class="form-control" value="${fornecedor.id}" name="fornecedorId">
                                            <button type="submit" class="btn btn-link" title="Editar fornecedor">
                                              <!--<img src="../img/atualizar.png" alt="Logo">--><i class="material-icons" style="color:black" >mode_edit</i></button> <!--EDITAR-->
                                        </form>

                                        <form action="excluirFornecedor" method="POST">
                                            <input type="hidden" class="form-control" value="${fornecedor.id}" name="fornecedorId">
                                            <button type="submit" class="btn btn-link" title="Apagar fornecedor">
                                              <!--<img src="../img/excluir.png" alt="Logo">--> <i class="material-icons" style="color:black" >delete_forever</i> </button> <!--DELETAR-->
                                           </form>
                                    </div>
                                   </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                  </table>
              </div>
          </div>
      </div>
</div>
