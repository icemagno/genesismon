<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html manifest="">
<!-- PAGE HEAD -->
<jsp:include page="head.jsp" />

<body class="skin-blue layout-top-nav" style="height: auto; min-height: 100%;">
	<div class="wrapper">

		<header class="main-header">
			<nav class="navbar navbar-static-top" role="navigation">
				<div class="container-fluid">
					<div class="navbar-header">
						<a style="font-size: 25px;" href="/home" class="navbar-brand"><b>Genesis</b> | Token Monitor</a>
					</div>
				</div>
			</nav>
		</header>

		<div class="content-wrapper">
			<section class="content">
				<div class="row">
					<div class="col-md-12" id="botContainer">
						<table id="tableToken" style="width:100%">
							<tr><td>Name</td><td>Symbol</td><td>Contract</td><td>Chart</td></tr>
						</table>
					</div>
				</div>
			</section>		
		</div>


	</div>
	<jsp:include page="requiredscripts.jsp" />
</body>


<script src="/resources/web3.min.js" type="text/javascript"></script>
<script src="/resources/sockjs.min.js" type="text/javascript"></script>
<script src="/resources/stomp.min.js" type="text/javascript"></script>
<script src="/resources/toast.js" type="text/javascript"></script>
<script src="/resources/main.js" type="text/javascript"></script>
<script src="/resources/common.js" type="text/javascript"></script>


</html>

