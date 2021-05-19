var stompClientBinance = null;

function connect() {
	
	var socket = new SockJS('/ws');
	stompClientBinance = Stomp.over(socket);
	stompClientBinance.heartbeat.outgoing = 2000;
	stompClientBinance.heartbeat.incoming = 2000;    
	stompClientBinance.debug = null;
	
	stompClientBinance.connect({}, function(frame) {

		stompClientBinance.subscribe( '/tokens', function(notification) {
			var payload =  JSON.parse( notification.body );
			console.log( payload );
		});


	}, function( theMessage ) {
		console.log( "Connect: " + theMessage );
	});    

}


$( document ).ready(function() {
	connect();
});
