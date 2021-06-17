function saveToken( token ){

    $.ajax({
		url:"/savetoken",
		data: {'address':token},
		type: "GET", 
		success: function( obj ) {
			//
		},
	    error: function(xhr, textStatus) {
	    	//
	    }, 		
    });	
	
}
