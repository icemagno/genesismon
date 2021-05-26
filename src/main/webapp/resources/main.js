var stompClientBinance = null;
var countLog = 0;

function addLog(message) {
	countLog++;
	if ( countLog == 10 ) {
		countLog--;
		$('#tableToken tr:first').remove();
	}
	$("<tr><td>" + message.name + "</td><td>[ " + message.symbol + " ]</td><td><a target='_BLANK' href='https://bscscan.com/address/"+message.hash+"'><i class='fa fa-info-circle'></a></td><td><a target='_BLANK' href='https://poocoin.app/tokens/"+message.hash+"'><i class='fa fa-bar-chart'></a></td></tr>").appendTo('#tableToken tbody').hide().fadeIn(2000);
}


function connect() {
	
	var socket = new SockJS('/ws');
	stompClientBinance = Stomp.over(socket);
	stompClientBinance.heartbeat.outgoing = 2000;
	stompClientBinance.heartbeat.incoming = 2000;    
	stompClientBinance.debug = null;
	
	stompClientBinance.connect({}, function(frame) {

		stompClientBinance.subscribe( '/tokens', function(notification) {
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