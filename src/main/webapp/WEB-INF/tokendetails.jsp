<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
   <jsp:include page="head.jsp" />
   <body class="hold-transition skin-yellow layout-top-nav">
      <!-- Site wrapper -->
      <div class="wrapper">
         <div class="content-wrapper" style="min-height:92%">
            <jsp:include page="barra.jsp" />
            <section class="content">
               <div class="row">
                  <div class="col-lg-6 col-12">
                     <div class="box">
                        <div class="box-header with-border">
                           <h3 class="box-title">Detalhes</h3>
                           <h6 class="box-subtitle"><code id="tknContrct"></code></h6>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                           <div class="table-responsive">
                              <table class="table">
                                 <tr>
                                    <td>Nome</td>
                                    <td><span id="tknName"></span></td>
                                 </tr>
                                 <tr>
                                    <td>Símbolo</td>
                                    <td><span id="tknSymbol"></span></td>
                                 </tr>
                                 <tr>
                                    <td>Decimais</td>
                                    <td><span id="tknDeci"></span></td>
                                 </tr>
                                 <tr>
                                    <td>Supply</td>
                                    <td><span id="tknSupply"></span></td>
                                 </tr>
                                 <tr>
                                    <td>1 BNB = </td>
                                    <td><span id="tknPrice"></span></td>
                                 </tr>
                              </table>
                           </div>
                        </div>
                        <!-- /.box-body -->
                     </div>
                     <!-- /.box -->
                  </div>
                  <div class="col-lg-6 col-12">
                     <div class="box" style="height: 447px;">
                         <div id="graphFrame" class="box-body"></div>
                     </div>
                  </div>
               </div>
               <div class="row">
                  <div class="col-lg-12 col-12">
                     <!-- Default box -->
                     <div class="box box-inverse box-dark">
                        <div class="box-header with-border">
                           <h3 class="box-title">Transações</h3>
                        </div>
                        <div class="box-body">
                           <div class="table-responsive">
                              <table id="txTable" class="table no-margin no-border border-gray bg-dark">
                                 <tbody>
                                 </tbody>
                              </table>
                           </div>
                        </div>
                        <!-- /.box-body -->
                     </div>
                     <!-- /.box -->
                  </div>
               </div>
            </section>
         </div>
         <jsp:include page="footer.jsp" />
      </div>
      <jsp:include page="scripts.jsp" />
      <script src="https://cdnjs.cloudflare.com/ajax/libs/web3/1.3.5/web3.min.js"></script>
      <script src="resources/abis/Arbitrage-abi.js"></script>
      <script src="resources/abis/Reau-abi.js"></script>
      <script src="resources/abis/PancakeFactory-abi.js"></script>
      <script src="resources/abis/PancakePair-abi.js"></script>
      <script src="resources/abis/BEP20-abi.js"></script>
      <script src="resources/js/tools-web3.js"></script>
   </body>
</html>