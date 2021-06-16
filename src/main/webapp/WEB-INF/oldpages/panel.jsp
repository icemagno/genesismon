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

<!-- 
							<button title="Engine" id="btnStartEngine" style="margin-left:10px;" 
								type="button" class="btn btn-primary btn-flat">
								<i id="startEngineBtnIcon" class="fa fa-play"></i>
							</button>

							<button title="Configure API Keys" id="btnConfigureKeys" style="margin-left:10px;" 
								type="button" class="btn btn-primary btn-flat">
								<i class="fa fa-key"></i>
							</button>

							<button title="Binance" id="btnBinance" style="background-color: #214b63;padding:5px;margin-left:10px;" type="button" class="btn btn-primary btn-flat" >
								<img src="/resources/img/binance.png"  class="fa topmnuimg">
							</button>

							<button title="Logout" id="btnLogout" style="margin-left:10px;" 
								type="button" class="btn btn-danger btn-flat">
								<i class="fa fa-sign-out"></i>
							</button>					
							
 -->

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
					<div class="col-md-4">
					
			          <div class="box box-widget">
			            <div class="box-header with-border">
			            	<div class="user-block">
								<img class="img-circle" src="/resources/img/rocket.png" alt="User Image">
			              		<span class="username">Scoreboard</span>
			              		<span class="description">The Rocket Launch Alert</span>
							</div>
							
							<div class="box-tools pull-right">
							   <button style="position: absolute; right: 10px;top: 5px;" type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
							</div>							
							
			            </div>
			            <div class="box-body no-padding" style="overflow: auto;">
			              <table id="scoreboardTable" class="table table-striped">
			                <tr>
			                  <th style="width: 15%">Pair</th>
			                  <th style="width: 1%"><span class="pull-right">Score</span></th>
			                  <th style="width: 1%;"><span class="pull-right">Stay</span></th>
			                  <th style="width: 15%;"><span class="pull-right">Cross</span></th>
			                  <th style="width: 15%;"><span class="pull-right">%</span></th>
			                  <th style="width: 15%;"><span class="pull-right">Velocity</span></th>
			                  <th style="width: 20%;"><span class="pull-right">Price</span></th>
			                </tr>
			              </table>
			            </div>
			          </div>

						
					  <!-- 	
			          <div class="box box-widget">
			            <div class="box-header with-border">
			            	<div class="user-block">
								<i style="font-size: 38px; position: absolute;" class="text-light-blue fa fa-ticket"></i>
			              		<span class="username">Orders</span>
			              		<span id="walletUpdatedTime" class="description">Waiting data...</span>
							</div>
		              		<div style="position: absolute; top: 15px; right: 10px;" class="box-tools">
								<button type="button" class="pull-right btn btn-default btn-xs" id="updateWalletBtn">Update	<i class="fa fa-refresh"></i></button>
							</div>	
			            </div>
			            <div class="box-body no-padding">
			              <table id="ordersTable" class="table table-striped">
			                <tr>
			                  <th style="width: 15px">Date</th>
			                  <th><span class="pull-right">Pair</span></th>
			                  <th><span class="pull-right">Type</span></th>
			                  <th><span class="pull-right">Side</span></th>
			                  <th><span class="pull-right">Price</span></th>
			                  <th><span class="pull-right">Amount</span></th>
			                  <th><span class="pull-right">Total</span></th>
			                </tr>
			              </table>
			            </div>
			          </div>
					  -->	
						
					</div>
					
					<div class="col-md-8">
						<div class="box box-widget">
							<div class="box-header with-border">
								<div class="user-block">							
									<i style="font-size: 38px; position: absolute;" class="text-light-blue fa fa-line-chart"></i>
					          		<span class="username">Pair variation (%)</span>
					          		<span class="description">Be Wise!</span>
					          		
					          		<div class="info-box bg-light-blue-active" style="padding:7px;min-height: 40px; position: absolute;top: 5px;left: 455px;width: 200px;height: 50px;" >
					          			<div><span style="position:absolute;top:2px;font-size: 22px;" id='clickdata' class="info-box-text"></span></div>
					          			<div><span style="position:absolute;top:27px;" id='tooltip' class="info-box-text"></span></div>
					          			<i id="percentArrowUp" style="font-size: 28px; position: absolute; top: 5px; right:5px;display:none;" class="text-green fa fa-arrow-up"></i>
					          			<i id="percentArrowDown" style="font-size: 28px; position: absolute; top: 5px; right:5px;display:none;" class="text-red fa fa-arrow-down"></i>
					          		</div>
					          		
					          		<div class="info-box bg-light-blue-active" style="padding:7px;min-height: 40px;position: absolute;top: 5px;left: 250px;width: 200px;height: 50px;" >
					          			<div><span style="position:absolute;top:2px;font-size: 22px;" id='marketUpdatedTime' class="info-box-text"></span></div>
					          			<div><span style="position:absolute;top:27px;" id='pairPriceValue' class="info-box-text"></span></div>
					          		</div>
					          		
									<div class="box-tools pull-right">
									   <button style="position: absolute; right: 10px;top: 5px;" type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
									</div>							
					          		
					          		
				          		</div>
								<div class="box-tools" style="position: absolute; top: 20px; right: 70px;z-index:99999">
									<div class="input-group input-group-sm hidden-xs" style="width: 150px;float: right;">
										<input id="tokenSearchVal" type="text" name="table_search" class="form-control pull-right" placeholder="Search">
										<div class="input-group-btn">
											<button id="tokenSearchBtn" type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
											<button style="display:none" id="tokenSearchWait" class="btn btn-default"><i class="fa fa-refresh fa-spin"></i></button>
										</div>
									</div>
									
									<div style="background-color: white; display:none" id="tokenSearchDiv">
										<table id="tokenSearchTable" class="table table-bordered" style="width: 450px;display:none">
										<tr>
											<th style="text-align:center;"><i title="Close Results" onClick="$('#tokenSearchTable').hide();$('#tokenSearchVal').val('')" style="cursor:pointer;font-size: 17px;" 
												class="fa fa-close text-red"></i></th>
											<th>Symbol</th>
											<th>Net</th>
											<th>Name</th>
											<th>#</th>
										</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="box-body">
								<div style="height: 570px;" id="tokenGraphPanel" class="box-body table-responsive no-padding"></div>
							</div>
							<div class="box-footer"></div>	
						</div>
					
						
					
					</div>
					
					
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
<script src="/resources/panel.js" type="text/javascript"></script>
<script src="/resources/tokensearch.js" type="text/javascript"></script>
<script src="/resources/scoreboard.js" type="text/javascript"></script>
<script src="/resources/common.js" type="text/javascript"></script>

<script>
$( document ).ready(function() {

	connect();
	bindButtons();
	createGraphContainer();
	
	
});
</script>


</html>

