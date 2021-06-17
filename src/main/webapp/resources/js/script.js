function saveToken( token ){
	console.log( token );
	
    $.ajax({
		url:"/savetoken",
		data: {'address':token},
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
	
}
