function getNewTokenCard( message ){
console.log( message );

	var isGalaxy = "";
	if( message.isMine == true ){
		isGalaxy = '<i title="Familia Galaxy" class="text-red nav-link fa fa-check-square-o"></i>';
	}
	
	return '<div class="media" id="'+message.hash+'">' +
		  '<a target="_BLANK" class="avatar avatar-lg status-success" href="/tokendetails?address='+message.hash+'">' +
			'<i class="cc NVC"></i>' +
		  '</a>' +
	
		  '<div class="media-body">' +
			'<p>' +
			  '<a target="_BLANK" href="/tokendetails?address='+message.hash+'"><strong>'+message.name+'</strong></a>' +
			  '<small class="sidetitle">BEP20</small>' +
			'</p>' +
			'<p>'+message.symbol+'</p>' +
			'<p style="font-family:Monospace">'+message.hash+'</p>' +
	
			'<nav class="nav mt-2">' +
			  '<i title="Queima de suprimento" class="nav-link fa fa-fire"></i>' + 
			  '<i title="Renunciado" class="nav-link fa fa-user-times"></i>' +
			  '<i title="Liquidez Trancada" class="nav-link fa fa-balance-scale"></i>' +
			  '<i title="Possui Bonificação" class="nav-link fa fa-money"></i>' +
			  isGalaxy +
			'</nav>' +
		  '</div>' +
	
		  '<div class="media-right gap-items">' +
		    '<a class="media-action lead" onClick="saveToken(\''+message.hash+'\');" href="#" title="Salvar"><i class="ti-save"></i></a>' +
		    '<a class="media-action lead" target="_BLANK" href="tokendetails?address='+message.hash+'" title="Inspecionar"><i class="ti-search"></i></a>' +
			'<a class="media-action lead" target="_BLANK" href="https://bscscan.com/address/'+message.hash+'" title="Ver no BSCScan"><i class="ti-info-alt"></i></a>' +
			'<a class="media-action lead" target="_BLANK" href="https://poocoin.app/tokens/'+message.hash+'" title="Gráfico"><i class="ti-bar-chart"></i></a>' +
		  '</div>' +
		'</div>';
	
}

