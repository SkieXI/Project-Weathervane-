google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(getWeatherData);

function getWeatherData(){
	   //make AJAX call to orders via rest service
	   $.ajax(
		{
		   type: "GET",
		   url: "/WeatherVane/rest/weather/getalldata",
		   dataType: "json",
		   success: function(data)
		{
			   //display orders
			   drawTempC(data);
			   drawTempF(data);
			   drawHumidity(data);
			   drawPressure(data);
			  
		 },
		
		   error: function( xhr, ajaxOptions, thrownError)
		 {
			   alert(xhr.status);
	   		   alart(thrownError);
		 }
	   }
	   );
}
function drawTempC(datas) {

    var data = new google.visualization.DataTable();
    data.addColumn('number', 'X');
    data.addColumn('number', 'TempC');
			i = 0;
    for(data2 of datas){
    data.addRows([
      [i,data2.tempC]
    ]);
    i++;
}
    var options = {
      hAxis: {
        title: 'Amount of Readings'
      },
      vAxis: {
        title: 'Tempurature'
      }
    };

    var chart = new google.visualization.LineChart(document.getElementById('chartTempC_div'));

    chart.draw(data, options);
  }
    function drawTempF(datas) {

        var data = new google.visualization.DataTable();
        data.addColumn('number', 'X');
        data.addColumn('number', 'TempF');
    			i = 0;
        for(data2 of datas){
        data.addRows([
          [i,data2.tempF]
        ]);
        i++;
    }
        var options = {
          hAxis: {
            title: 'Amount of Readings'
          },
          vAxis: {
            title: 'Tempurature'
          }
        };

        var chart = new google.visualization.LineChart(document.getElementById('chartTempF_div'));

        chart.draw(data, options);
      }
    
    function drawHumidity(datas) {

        var data = new google.visualization.DataTable();
        data.addColumn('number', 'X');
        data.addColumn('number', 'Humidity');
    			i = 0;
        for(data2 of datas){
        data.addRows([
          [i,data2.humidity]
        ]);
        i++;
    }
        var options = {
          hAxis: {
            title: 'Amount of Readings'
          },
          vAxis: {
            title: 'Humidity'
          }
        };

        var chart = new google.visualization.LineChart(document.getElementById('chartHumidity_div'));

        chart.draw(data, options);
      }
    function drawPressure(datas) {

        var data = new google.visualization.DataTable();
        data.addColumn('number', 'X');
        data.addColumn('number', 'Pressure');
    			i = 0;
        for(data2 of datas){
        data.addRows([
          [i,data2.pressure]
        ]);
        i++;
    }
        var options = {
          hAxis: {
            title: 'Amount of Readings'
          },
          vAxis: {
            title: 'Pressure'
          }
        };

        var chart = new google.visualization.LineChart(document.getElementById('chartPressure_div'));

        chart.draw(data, options);
      }
    