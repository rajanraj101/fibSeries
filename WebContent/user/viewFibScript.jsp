<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Fib Script</title>
	<script type="text/javascript" src="../js/angular.js"></script>
	<script type="text/javascript" src="../js/FibScript.js"></script>
</head>
<body ng-app="fibIndicator" >
	<div ng-controller="fibScriptController">
	Search : <input type="text" ng-model="searchText" placeholder="Search Script"/>
	 <table border=5>
			<thead>
				<tr>
					<th> Script </th>
					<th> Trigger Info</th> 
					 
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="script in fibScriptData | filter:searchText" >
					<td>{{script.script_name}}</td>
					<td> 
					 <ul ng-repeat="t in  script.tigger_info">
					            <p>{{t.type}}, {{ t.low}}, {{ t.high}}</p>
					        </ul>
					</td>
				</tr>
			</tbody>
		</table> 
	
	</div>


</body>
</html>