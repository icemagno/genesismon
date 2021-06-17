var engineStarted = false;
var configuration = null;

function queryEngineStatus(){
    $.ajax({
		url:"/engine/status", 
		type: "GET", 
		success: function( obj ) {
			console.log( obj );
			if( (obj == 'RUNNING') || ( obj == 'ALREADY_RUNNING' ) ){
				engineStarted = true;
				$("#startEngineBtnIcon").removeClass('fa-play');
				$("#startEngineBtnIcon").addClass('fa-refresh');
				$("#startEngineBtnIcon").addClass('fa-spin');
			}
		},
	    error: function(xhr, textStatus) {
	    	//
	    }, 		
    });
}



function startEngine(){
	
	if( userId === "" ){
		fireToast( 'error', 'Error', 'You must register your API keys.', '' );
		return;
	}
	
	if( engineStarted ) return;
	
	engineStarted = true;
	$("#startEngineBtnIcon").removeClass('fa-play');
	$("#startEngineBtnIcon").addClass('fa-refresh');
	$("#startEngineBtnIcon").addClass('fa-spin');
	
	$.ajax({
		url:"/engine/start",
		type: "GET", 
		success: function( obj ) {
			
			if( obj == 'NO_API_KEYS'){
				$("#startEngineBtnIcon").addClass('fa-play');
				$("#startEngineBtnIcon").removeClass('fa-refresh');
				$("#startEngineBtnIcon").removeClass('fa-spin');
				fireToast( 'error', 'Error', 'You must register your API keys.', '' );
			}
			
		},
	    error: function(xhr, textStatus) {
	    	//
	    }, 		
    });
}


function bindButtons(){

	jQuery.fn.rotate = function(degrees) {
	    $(this).css({'-webkit-transform' : 'rotate('+ degrees +'deg)',
	                 '-moz-transform' : 'rotate('+ degrees +'deg)',
	                 '-ms-transform' : 'rotate('+ degrees +'deg)',
	                 'transform' : 'rotate('+ degrees +'deg)'});
	    return $(this);
	};	
	
	
    $("#updateWalletBtn").click( function(){
    	requestWallet();
    });
    
    $("#btnConfigureKeys").click( function(){
    	fireToast( 'error', 'Error', 'Not Implemented Yet.', '' );
    });
    
    $("#btnLogout").click( function(){
    	window.location.href = '/logout';
    });

    
    $("#btnBinance").click( function(){
    	window.location.href = '/binance';
    });

    
    $("#btnStartEngine").click( function(){
    	startEngine();
    });
    
    
    $("#tokenSearchBtn").click(function() {
    	var val = $("#tokenSearchVal").val();
    	
    	$("#tokenSearchWait").show();
    	$("#tokenSearchBtn").hide();
    	
    	searchResultPool = [];
    	
    	$( ".tokenSearchRow" ).remove();
    	
    	
    	
        $.ajax({
    		url:"/dexguru/token/search",
    		data: { 's' : val, 'network' : '' },
    		type: "GET", 
    		success: function( obj ) {
    			$( "#tokenSearchDiv" ).show();
    			processSearchResult( JSON.parse( obj ) );
    	    	$("#tokenSearchWait").hide();
    	    	$("#tokenSearchBtn").show();
    		},
    	    error: function(xhr, textStatus) {
    	    	$("#tokenSearchWait").hide();
    	    	$("#tokenSearchBtn").show();
    	    }, 		
        });
    });
    
    
}

