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

			
			<div class="col-md-8 col-12">
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
