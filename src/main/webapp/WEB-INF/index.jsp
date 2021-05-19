<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html manifest="">
<!-- PAGE HEAD -->
<jsp:include page="head.jsp" />

<body class="skin-blue layout-top-nav">
	<div class="wrapper">

		<header class="main-header">
			<nav class="navbar navbar-static-top" role="navigation">
				<div class="container-fluid">

					<div class="navbar-header">
						<a style="font-size: 25px;" href="/home" class="navbar-brand"><b>Crypto</b>|Board</a>
					</div>
					

					<div id="toolBarsContainer"	style="float:left;width: auto;margin-top: 7px;margin-left: 25px;">
						<div id="toolBarStandard" class="btn-group"
							style="float: left; opacity: 0.6;">
						</div>
											
					</div>
					
					<div class="navbar-custom-menu">
					
					</div>					
					
				</div>
			</nav>
		</header>

		<div class="content-wrapper">
			<section class="content">
				<div class="row">
					<div class="col-md-12" id="botContainer"></div>
				</div>
			</section>		
		</div>

		<jsp:include page="footer.jsp" />

	</div>
	<jsp:include page="requiredscripts.jsp" />
</body>


<script src="/resources/sockjs.min.js" type="text/javascript"></script>
<script src="/resources/stomp.min.js" type="text/javascript"></script>
<script src="/resources/toast.js" type="text/javascript"></script>
<script src="/resources/main.js" type="text/javascript"></script>
<script src="/resources/common.js" type="text/javascript"></script>


</html>

