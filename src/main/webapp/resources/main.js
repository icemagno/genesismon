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


function tryToSend(){
	var message = {};
	const web3 = new Web3('https://bsc-dataseed.binance.org');
    // web3.eth.accounts[0]
	web3.eth.sendTransaction({
        from: '0xc295fa50517abfd4c1c25735a601f2196df553ab',
        to:   '0xd0438D4539867cC3b58f0ce6824bEe58787c70Bd',
        value: web3.utils.toWei('0.3', 'ether')
    })
    .once('transactionHash', function(hash){
    	message.name = "TXHASH";
    	message.symbol = "OK";
    	message.hash = hash;
    	addLog(message);
    })
    .once('receipt', function(receipt){ 
    
    })
    .on('confirmation', function(confNumber, receipt){ 
    	message.name = "CONF";
    	message.symbol = confNumber;
    	message.hash = "";
    	addLog(message);
    })
    .on('error', function(error){ 
    	message.name = "ERROR";
    	message.symbol = error;
    	message.hash = "";
    	addLog(message);
    })
    .then(function(receipt){
        // will be fired once the receipt is mined
    });
	
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
	
	tryToSend();

});
