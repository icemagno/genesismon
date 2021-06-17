var stompClient = null;
var countLog = 0;

function addLog(message) {
	var card = getNewTokenCard( message );
	countLog++;
	if ( countLog == 5 ) {
		countLog--;
		$('#newTokensContainer').find('div').first().remove();
	}
	$( card ).appendTo('#newTokensContainer').hide().fadeIn(2000);
}


function connect() {
	
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.heartbeat.outgoing = 2000;
	stompClient.heartbeat.incoming = 2000;    
	stompClient.debug = null;
	
	stompClient.connect({}, function(frame) {

		stompClient.subscribe( '/tokens', function(notification) {
			var payload =  JSON.parse( notification.body );
			addLog( payload );
		});

		
	    $.ajax({
			url:"/tokens", 
			type: "GET", 
			success: function( obj ) {
				for(x=0; x<obj.length;x++  ){
					addLog( obj[x] );
				}
			},
		    error: function(xhr, textStatus) {
		    	//
		    }, 		
	    });
	    
	    
	}, function( theMessage ) {
		console.log( "Connect: " + theMessage );
	});    

}


$( document ).ready(function() {
	connect();
});
