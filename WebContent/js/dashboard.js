
/// <reference path="angular.js" />


var app = angular
			.module("fibIndicator", [])
			.controller("dashboadController", function($scope, $http) {
				
				$http.get('../data/fibSeriesMasterData.jsp')
					.then(function(response) { 
						$scope.fibSeriesMasterData = response.data;
				});
				
				$http.get('../data/fibScriptData.jsp')
					.then(function(response) { 
						$scope.fibScriptData = response.data;
						
						var scriptList = "";
						angular.forEach($scope.fibScriptData, function(script){
							scriptList += script.script_name.toUpperCase() + ":NSE,";
						});	
						$scope.scriptList = scriptList;
				});
				
				// get current market price
				 $scope.getCMP = function(){
					 var accURL = 'http://finance.google.com/finance/info?client=ig&q=';
		             var scriptURL = accURL + $scope.scriptList;
		            
		             console.log("scriptURL: " + scriptURL);
	                 $http.get(scriptURL)
	                   		.then(function(response) { 
	                   				var strData =  response.data;
	                   				alert(strData);
	                   				strData = strData.substring(4,strData.length);
									$scope.stockData = angular.fromJson(strData);
									
									angular.forEach($scope.stockData, function (value, prop, obj){
										//alert( value.t + " : " + value.l_fix);
										
										angular.forEach($scope.fibScriptData, function(script){
											if(script.script_name.toUpperCase().trim() == value.t.trim())
												{
													script.cmp = value.l_fix;
													//alert( "set : " + value.t + " : " + value.l_fix);
												}
										});	
										
									});
									
									$scope.compute();
						    });
				
				  };//--- getCMP stop

				  $scope.compute = function() {
				
					  var triggeredScript = "[";
					  
					  angular.forEach($scope.fibScriptData, function(script){
							
						  	if(script.cmp > 0)
					  		{
						  		// check against each script.tigger_info : daily, monthly, yearly
						  		angular.forEach(script.tigger_info, function(triggerInfo){
						  			// alert(triggerInfo.high + ","+ triggerInfo.low + "," +triggerInfo.type );
						  			
						  			if(triggerInfo.high >= triggerInfo.low) {
						  				// up trend
						  				triggerInfo.percent = ( ( ( script.cmp - triggerInfo.low ) / (triggerInfo.high - triggerInfo.low) ) * 100).toFixed(1) ;
						  			}
						  			else {
						  				// low trend
						  				triggerInfo.percent =(  ((triggerInfo.low - script.cmp ) / (triggerInfo.low - triggerInfo.high) ) * 100 ).toFixed(1) ;
						  			}
						  			
						  			var pos_diff = triggerInfo.percent + $scope.fibSeriesMasterData.diff;
						  			var neg_diff = triggerInfo.percent - $scope.fibSeriesMasterData.diff;
						  			triggerInfo.diff = "-";
						  			
						  			// check against fibSeriesMasterData.range 
						  			angular.forEach($scope.fibSeriesMasterData.range, function(range){
						  				if( range.start <= triggerInfo.percent && triggerInfo.percent <= range.end) {
						  					triggerInfo.range.start = range.start;
						  					triggerInfo.range.end = range.end;
						  				}
						  				
						  				if(neg_diff <= range.start && range.start <= pos_diff) {
						  					triggerInfo.diff = "yes";
						  					
						  					triggeredScript += "{ 'script_name': " + script.script_name
						  										+" 'cmp':'"+ script.cmp 
						  										+" 'tigger_info' :" + triggerInfo
						  										+"},"
						  				}
						  				
						  			}); // end check against fibSeriesMasterData.range 
						  			
						  		});// end check against each script.tigger_info : daily, monthly, yearly
						  		
					  		}// script.cmp > 0
						  
						}); // iterate $scope.fibScriptData
						
				  		triggeredScript += "]";
				  		$scope.triggeredScript = triggeredScript;   
					  
				  }; // ---- compute stop

				  
				  setInterval($scope.getCMP, 15000); // 1 sec = 1000
				  
				

				  
				$scope.exportData = function () {
					  
				        var blob = new Blob([document.getElementById('mainTable').innerHTML], {
				            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
				        }); 
				        var d = new Date();
				        var filename = d.getFullYear()+"-"+ d.getMonth() +"-"+ d.getDate() 
				        				+ "_" + d.getHours() + d.getMinutes() + d.getSeconds()  
				        				+"_fibReport.xls";
				         
				        saveAs(blob, filename);
				    };
			
			});

