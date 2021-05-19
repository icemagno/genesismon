var searchResultPool = [];

function addTokenMonitor( id ){
	var data = searchResultPool[ id ];

	console.log( data );

	// https://goswappcharts.web.app/?isbsc=true&symbol=ADABUSD

	var iFrame = '<iframe style="width: 100%; height: 97%;" loading="lazy" src="https://goswappcharts.web.app/?isbsc=true&tokenId=' + data.tokenId + '" frameborder="0">';
	
	$("#tokenGraphPanel").html( iFrame );
	$("#tokenSearchTable").hide();

}

function processSearchResult( result ){
	var data = result.data;
	for( x=0; x < result.total; x++ ) {
		var token = data[x];
		var symbol = token.symbol;
		var name = token.name;
		var tokenId = token.id.replace( "-" + token.network, "" );
		
		token.tokenId = tokenId;
		
		var id = "tokenSearchId" + x;
		var imgId = "tokenSearchImg" + x;
		
		var cardsContent = '<tr class="tokenSearchRow" id="'+id+'">' + 
	    '<td><img style="width: 24px;" src="/resources/img/dexicon.jpg" id="'+imgId+'"></td>' + 
	    '<td>'+symbol+'</td>' +
	    '<td>'+token.network.toUpperCase()+'</td>' + 
	    '<td><span class="">'+name+'</span></td>' + 
	    '<td><i onClick="addTokenMonitor(\''+ id +'\')" style="cursor:pointer" class="fa fa-eye text-yellow"></i></td>' + 
		'</tr>'; 
		
		$("#tokenSearchTable").append( cardsContent );
		
		searchResultPool[ id ] = token;
		
		/*
		( function (index) {
			
		    $.ajax({
				url:"/dexguru/token/logo",
				data: { 'tokenhash' : tokenId },
				type: "GET", 
				success: function( data ) {
					console.log( data );
					$("#tokenSearchImg" + index).attr("src", data);
				},
			    error: function(xhr, textStatus) {
			    	//
			    }, 		
		    });
			
		})( x );		
		*/
		
	}
	
	$("#tokenSearchTable").show();
}