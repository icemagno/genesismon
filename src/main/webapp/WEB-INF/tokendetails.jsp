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
				  
					  <div class="box-header with-border bg-yellow">
						<h4 class="mb-0 font-weight-500"><span class="text-white icon-calendar mr-10"></span> Informações</h4>
					  </div>
					  
					  <div class="row mb-30">
						<div class="col-12 col-lg-6">
						  <h5 class="p-15 mb-0"><strong>Nome:</strong> <span id="tknName"></span></h5>
						  <h5 class="p-15 mb-0"><strong>Tipo:</strong> BEP20</h5>
						</div>
						<div class="col-12 col-lg-6">
						  <h5 class="p-15 mb-0"><strong>Símbolo:</strong> <span id="tknSymbol"></span></h5>
						  <h5 class="p-15 mb-0"><strong>Decimais:</strong> <span id="tknDeci"></span></h5>
						</div>
					  </div>				  
					  <div class="row mb-30">
						<div class="col-12 col-lg-12">
						  <h5 class="p-15 mb-0"><strong>Contrato:</strong> <span id="tknContrct"></span></h5>
   						  <h5 class="p-15 mb-0"><strong>Total Tokens:</strong> <span id="tknSupply"></span></h5>
						  <h5 class="p-15 mb-0">1 BNB = <span id="tknPrice"></span></h5>
						</div>				  
					  </div> 	
                  </div>
				  
				  
                  <div class="col-lg-6 col-12">
                     <!-- Default box -->
                     <div class="box box-inverse box-dark">
                        <div class="box-header with-border">
                           <h3 class="box-title">Transações</h3>
                        </div>
                        <div class="box-body">
                           <div class="table-responsive">
                              <table id="txTable" class="table no-margin no-border b-1 border-gray bg-dark">
                                 <thead>
                                    <tr class="bg-yellow">
                                       <th>Evento</th>
                                       <th>Valor</th>
                                       <th>TX</th>
                                    </tr>
                                 </thead>
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
