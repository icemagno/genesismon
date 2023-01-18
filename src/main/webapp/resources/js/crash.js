var updateInterval = 10 
var interactive_plot;
var data = [], totalPoints = 300

function getRandomData() {

	if (data.length > 0) data = data.slice(1)
	
  	//Do a random walk
	while (data.length < totalPoints) {
	    var prev = data.length > 0 ? data[data.length - 1] : 50;
		var y = prev + Math.random() * 10 - 5;
		if (y < -150) { y = -150 } else if (y > 150) { y = 150 };
		data.push(y);
	}

	//Zip the generated y values with the x values
	var res = [];
	for (var i = 0; i < data.length; ++i) {
		res.push([i, data[i]]);
	}
	return res;
}


function update() {
	interactive_plot.setData( [getRandomData()] );
	interactive_plot.setupGrid();
	interactive_plot.draw();
}

function makeChart(){

    interactive_plot = $.plot('#interactive', [getRandomData()], {       
    	grid: {
			color: "#AFAFAF",
			hoverable: false,
			borderWidth: 0,
			backgroundColor: '#fff',
        },
        
        series: {
        	threshold: {
        		below: 7,
        		color: "rgb(200, 20, 30)"
        	},  
        	shadowSize: 0, 
        	color : '#06d79c',
        },
        
        yaxis : {
        	min : -150,
        	max : 150,
        	show: true
        },
        
        xaxis : {
        	show: false
        }
	});	
	
}


makeChart();
setInterval(update, updateInterval);