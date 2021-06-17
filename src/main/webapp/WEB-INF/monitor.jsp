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

			
			<div class="col-md-4 col-12">
			
          <!-- Carousel Slider Only Slide -->
          <div class="box">
            <div class="box-body">
				<div id="carousel-example-generic-captions" class="carousel slide" data-ride="carousel">
			  		<!-- Indicators -->
					  <ol class="carousel-indicators">
						<li data-target="#carousel-example-generic-captions" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic-captions" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic-captions" data-slide-to="2"></li>
					  </ol>                      
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
					  <img src="resources/propaganda/anuncie-aqui.jpg" class="img-fluid" alt="slide-1">
					  <div class="carousel-caption">
						<h3>Seu Token Visível</h3>
						<p>alcance mais compradores dando visibilidade</p>
					  </div>
					</div>
					<div class="carousel-item">
					  <img src="resources/propaganda/anuncie-aqui.jpg" class="img-fluid" alt="slide-2">
					  <div class="carousel-caption">					  
						<h3>Não Fazemos Perguntas</h3>
						<p>o que é seu, é seu.</p>
					  </div>
					</div>
					<div class="carousel-item">
					  <img src="resources/propaganda/anuncie-aqui.jpg" class="img-fluid" alt="slide-3">
					  <div class="carousel-caption">					  
						<h3>Alcance o Sucesso</h3>
						<p>mais alcance significa mais compradores</p>
					  </div>
					</div>
				  </div>
				  <!-- Controls -->
				  <a class="carousel-control-prev" href="#carousel-example-generic-captions" role="button" data-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="sr-only">Anterior</span>
				  </a>
				  <a class="carousel-control-next" href="#carousel-example-generic-captions" role="button" data-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="sr-only">Próximo</span>
				  </a>
				</div>               
            </div>
          </div>
          <!-- /.box -->			
			
			
			</div>
			
			<div class="col-md-4 col-12">
			</div>
			
		
			<div class="col-md-4 col-12">
				<div class="box">
				  <div id="newTokensContainer" class="media-list media-list-divided media-list-hover">
				  </div>
				</div>
			</div>

			
		</div>
    </section>
  </div>
  <jsp:include page="footer.jsp" /> 
</div>
<jsp:include page="scripts.jsp" />


  <script src="https://cdnjs.cloudflare.com/ajax/libs/web3/1.3.5/web3.min.js"></script>
  <script src="resources/js/newtokencard.js"></script>
  <script src="resources/js/monitor.js"></script>


</body>
</html>
