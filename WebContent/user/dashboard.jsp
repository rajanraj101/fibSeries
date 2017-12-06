<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title>
	<script type="text/javascript" src="../js/angular.js"></script>
	<script type="text/javascript" src="../js/dashboard.js"></script>
	<script type="text/javascript" src="../js/FileSaver.js"></script>
	<link href="../css/Style.css" rel="stylesheet" />
</head>
<body>
<div ng-app="fibIndicator" >
	<div ng-controller="dashboadController">
	
		Search : <input type="text" ng-model="searchText" placeholder="Search Script"/>   
		 <button ng-click="exportData()">Export</button> &nbsp; &nbsp; <a target="new" href="viewFibSeriesMaster.jsp">[Fib Series]</a>
		<br><br> 
		<div id="mainTable">
			 <table border="2">
					<thead>
						<tr>
							<th class="thText"> Script </th>
							<th class="thNum"> CMP </th>
							<th> Trigger Info <br>
							<table border="1">
							        	<tr>
							        		<th class="thText">Type</th>
							        		<th class="thNum">Low</th>
							        		<th class="thNum">High</th>
							        		<th class="thNum">Cur%</th>
							        		<th class="thText">Diff</th>
							        		<th class="thText">Range</th>
							        	</tr> </table></th> 
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="script in fibScriptData | filter:searchText"  
							ng-class-odd="'odd'" ng-class-even="'even'" >
							<td class="tdText">{{script.script_name}}</td>
							<td class="tdAmount">{{script.cmp}}</td>
							<td> 	
							        <table border="1">
							        	<tr ng-repeat="t in  script.tigger_info"  ng-class="{bgDiff:t.diff=='yes'}">
							        		<td class="tdText">{{ t.type}}</td>
							        		<td class="tdNum">{{ t.low}}</td>
							        		<td class="tdNum">{{ t.high}}</td>
							        		<td class="tdNum">{{ t.percent}}</td>
							        		<td class="tdText">{{ t.diff}}</td>
							        		<td class="tdText">{{ t.range.start}} - {{ t.range.end}} </td>
							        	</tr>
							        </table>
							        
							</td>
						</tr>
					</tbody>
				</table> 
			</div>
	</div>


</div>
</body>
</html>