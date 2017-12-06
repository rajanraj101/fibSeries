<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Fib Series Master</title>
	<script type="text/javascript" src="../js/angular.js"></script>
	<script type="text/javascript" src="../js/FibSeriesMaster.js"></script>
	<link href="../css/Style.css" rel="stylesheet" />
</head>
<body ng-app="fibIndicator" >
	<div ng-controller="fibSeriesMasterController">
	
	<h3> Fib Series Master 
	</h3>
	
	difference : {{  fibSeriesMasterData.diff }} %
	<br><br>
		 <table > 
			<thead>
				<tr>
					<th class="thNum"> Range </th>
					<th class="thNum"> Start </th>
					<th class="thNum"> End</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="r in fibSeriesMasterData.range">
					<td class="tdText"> range {{$index}}</td>
					<td class="tdNum">{{r.start}}</td>
					<td class="tdNum">{{r.end}}</td>
				</tr>
			</tbody>
		</table> 
	
	</div>


</body>
</html>