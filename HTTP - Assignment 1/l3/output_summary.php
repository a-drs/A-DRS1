<html>

<head>
 <title>Log File Summary</title>
 <!-- slider files -->
 <link rel="stylesheet" type="text/css" href="css/slider.css" />
 <script type="text/javascript" src="js/modernizr.custom.28468.js"></script>
 <link href='http://fonts.googleapis.com/css?family=Economica:700,400italic' rel='stylesheet' type='text/css'>
 <!-- end of slider files -->
 <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
 <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
 <link rel="stylesheet" type="text/css" href="css/style.css"/>
</head>

<body>

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
<br>

<center>
<a class="btn btn-warning" href="pie1.html">PIE CHART for status code</a>
<a class="btn btn-danger" href="pie2.html">PIE CHART for file types</a>
<a class="btn btn-warning" href="pie3.html">PIE CHART for server hits</a>
<br><br>
<a class="btn btn-primary" href="bar1.php">BAR CHART 14/May/2009</a>
<a class="btn btn-info" href="bar2.php">BAR CHART 15/May/2009</a>
<a class="btn btn-primary" href="bar3.php">BAR CHART 16/May/2009</a>
<a class="btn btn-info" href="bar4.php">BAR CHART 17/May/2009</a>
<a class="btn btn-primary" href="bar5.php">BAR CHART 18/May/2009</a>
<a class="btn btn-info" href="bar6.php">BAR CHART 19/May/2009</a>
</center>

<?php
$myfile = fopen("output.txt", "r") or die("Unable to open file!");
$f1 =  fopen("output.txt", "r") or die("Unable to open file!");
$f2 =  fopen("output.txt", "r") or die("Unable to open file!");
$f3 =  fopen("output.txt", "r") or die("Unable to open file!");
$f4 =  fopen("output.txt", "r") or die("Unable to open file!");
$f5 =  fopen("output.txt", "r") or die("Unable to open file!");
$f6 =  fopen("output.txt", "r") or die("Unable to open file!");

echo '<div class="row"><div class="span8 offset2">';
 for($a=1;$a<=68;$a++)  
  fgets($f1);
 for($b=1;$b<=95;$b++)
  fgets($f2);
 for($c=1;$c<=122;$c++)
  fgets($f3);
 for($d=1;$d<=149;$d++)
  fgets($f4);  
 for($e=1;$e<=176;$e++)
  fgets($f5);
 for($f=1;$f<=41;$f++)
  fgets($f6);
 echo '<table class="table table-striped table-bordered span4"><tbody>';
 echo '<tr><td colspan="7"><h5><i>Date-wise hourly hits</i></u></h5></td></tr>';
 echo '<tr><td><b>24_hour_slot</b></td><td><b>'.fgets($f6).'</b></td><td><b>'.fgets($f1).'</b></td><td><b>'.fgets($f2).'</b></td><td><b>'.fgets($f3).'</b></td><td><b>'.fgets($f4).'</b></td><td><b>'.fgets($f5).'</b></td></tr>';
 for($cnt=0;$cnt<=23;$cnt++)
  echo "<tr><td>".$cnt.":00-".($cnt+1).":00</td><td>".fgets($f6)."</td><td>".fgets($f1)."</td><td>".fgets($f2)."</td><td>".fgets($f3)."</td><td>".fgets($f4)."</td><td>".fgets($f5)."</td></tr>";
 echo "<tr><td><b>Total Hits</b></td><td><b>".fgets($f6)."</td><td><b>".fgets($f1)."</b></td><td><b>".fgets($f2)."</b></td><td><b>".fgets($f3)."</b></td><td><b>".fgets($f4)."</b></td><td><b>".fgets($f5)."</b></td>   </tr>";
 echo '</tbody></table>';
echo '</div>';

echo '<div class="row">';
 echo '<table class="table table-striped table-bordered span4 offset1">';
 echo '<thead><td><b>HITS</b></td><td><b>RESOURCES</b></td></thead><tbody>';

 echo '<tr><td>'.fgets($myfile).'</td><td>'.fgets($myfile)."</td></tr>";
 echo '<tr><td>'.fgets($myfile).'</td><td>'.fgets($myfile)."</td></tr>";
 echo '<tr><td>'.fgets($myfile).'</td><td>'.fgets($myfile)."</td></tr>";
 echo '<tr><td>'.fgets($myfile).'</td><td>'.fgets($myfile)."</td></tr>";
 echo '<tr><td>'.fgets($myfile).'</td><td>'.fgets($myfile)."</td></tr>";
 echo '<tr><td>'.fgets($myfile).'</td><td>'.fgets($myfile)."</td></tr>";
 echo '</tbody></table>';
 fgets($myfile);
 echo '<table class="table table-striped table-bordered span4">';
 echo '<thead><tr><td><b>STATUS CODES</b></td><td><b>HITS</b></td></thead><tbody>';
 echo "<tr><td>Sucessfull responses (2XX)</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>Redirections (3XX)</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>Client errors (4XX)</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>Server errors (5XX) </td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td><b>Total</b></td><td><b>2778</b></td></tr>";
 echo '</tbody></table>';
 fgets($myfile);
 echo '<table class="table table-striped table-bordered span4">';
 echo '<thead><tr><td><b>FILE TYPES</b></td><td><b>REQUESTS</b></td></thead><tbody>';
 echo "<tr><td>.js  Java Script Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.css Cascading Style Sheets</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.gif Image Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.png Image Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.jpg Image Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.ico Image Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.php Dynaminc PHP Script Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>.txt Text Files</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td>     Others</td><td>".fgets($myfile)."</td></tr>";
 echo "<tr><td><b>Total</b></td><td><b>2778</b></td></tr>";
 echo '</tbody></table>';
echo '</div>'; 
 
fclose($myfile);
?>				

<hr>
<br><br>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.cslider.js"></script>

 </body>

</html> 