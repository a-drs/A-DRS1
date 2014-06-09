
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Bar Chart 1</title>
<!-- slider files -->
 <link rel="stylesheet" type="text/css" href="css/slider.css" />
 <script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
 <link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
 <!-- end of slider files -->
 <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
 <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
 <link rel="stylesheet" type="text/css" href="css/style.css"/>

		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        data: {
            table: document.getElementById('datatable')
        },
        chart: {
            type: 'column'
        },
        title: {
            text: 'Hourly stastics for 14/May/2009'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 'Hits'
            }
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                    this.point.y +' '+ this.point.name.toLowerCase();
            }
        }
    });
});
		</script>
	</head>
	<body>
<script src="js/highcharts.js"></script>
<script src="js/modules/data.js"></script>
<script src="js/modules/exporting.js"></script>

<div class="navigation">
 <div class="container">
  <div class="row">
	<div class="span17 offset3">
	 <div class="navbar">
	  <div class="navbar-inner">
       <br>
	  <center><h4><b><a class="brand" href="#">A-DRS WEB ANALYSIS SUMMARY REPORT</i></a></b></h4></center>
      </div> 
	 </div> 
	</div>
   </div>
  </div>
</div>
<br>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>-->

<?PHP
$f1 =  fopen("output.txt", "r") or die("Unable to open file!");
 for($a=1;$a<=42;$a++)
  fgets($f1);
echo '<div class="row"><div class="span15 offset2">';
echo '<table id="datatable" class="table table-striped table-bordered span4">';
echo '<thead><tr><td>Hour</td>';
 for($i=0;$i<=23;$i++)
  echo '<td><b>'.$i."-".($i+1).'</b></td>';
echo '</tr></thead><tbody>';

echo '<tr><td>Hits</td>';
 for($i=43;$i<=66;$i++)
  echo '<td>'.fgets($f1).'</td>';
echo '</tr>';

echo '</tbody></table>';
echo '</div></div>'; 
 
fclose($f1);
?>				
 
<center>
<a class="btn btn-success" href="output_summary.php">BACK TO MENU</a>
</center>
<br><br>
	</body>
</html>
