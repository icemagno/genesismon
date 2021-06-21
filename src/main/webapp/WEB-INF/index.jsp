<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<jsp:include page="head.jsp" />
<body class="hold-transition skin-yellow layout-top-nav">
<!-- Site wrapper -->
<div class="wrapper">

  <div class="content-wrapper" style="min-height:92%">
    
    <section class="content">
	  <div class="row">
		<div class="col-12">
		  <h1 class="page-header text-center">
			  <span class="text-bold">Acompanhe</span> Nossa <span class="text-bold">Grande Revolução</span><br>
			  <p class="font-size-18 mb-0">Novo lançamento do token Nosferatu (NOSF). Participe do ICO e compre a preço fixo!</p>
		  </h1>
		  <!-- Default box -->
		  <div class="box box-dark bg-hexagons-white">
			<div class="box-body">
				<h5 class="text-white text-center">Lançamento em:</h5>
				<div class="countdownv2_holder text-center mb-50 mt-20">
					<div class="clock"></div>
			    </div>
				<div class="text-center">
				  <a target="_BLANK" href="https://pancakeswap.finance/" class="btn btn-warning">COMPRE</a>				
				</div>
				  <ul class="flexbox flex-justified text-center my-10">
					  <li class="br-1">
						<div class="font-size-14 text-primary">Suprimento Total:  <span class="text-bold">1.000.000.000</span></div>						
					  </li>

					  <li class="br-1">
						<div class="font-size-14 text-info">Tokens Queimados:  <span class="text-bold">500.000.000</span></div>
					  </li>

					  <li class="br-1">
						<div class="font-size-14 text-yellow">Pool de Liquidez:  <span class="text-bold">TRANCADO</span></div>						
					  </li>
					  <li>
						<div class="font-size-14 text-warning">Valor de ICO:  <span class="text-bold">BNB 0,01 por milhão</span></div>						
					  </li>
				  </ul>
			</div>
			<!-- /.box-body -->
		  </div>
		  <!-- /.box -->
		</div>		  
	  </div>
    </section>

    <jsp:include page="barra.jsp" />
    
  </div>
  <jsp:include page="footer.jsp" /> 
</div>
<jsp:include page="scripts.jsp" />

	<script>
	
		$(function () {
		  'use strict';
		   var date = new Date('2021-07-10 07:00:0 pm');
		   var clock = $('.clock').FlipClock(date, {
				clockFace: 'DailyCounter',
				countdown: true,
				language: 'pt',
				showSeconds: false
			}); 
		});
	
	</script>

</body>
</html>
