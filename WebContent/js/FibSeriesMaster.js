
/// <reference path="angular.js" />

var app = angular
			.module("fibIndicator", [])
			.controller("fibSeriesMasterController", function($scope, $http) {
				$http.get('../data/fibSeriesMasterData.jsp')
					.then(function(response) {
						$scope.fibSeriesMasterData = response.data;
				});
			});


/*var app = angular
			.module("fibIndicator", [])
			.controller("fibSeriesMasterController", function($scope) {
					$scope.fibSeriesMasterData = {"id":"58c6235073ce173e2c116ac4", "diff":2.0, 
							"range":[{"start": 1.9, "end" : 23.5},
							         {"start": 23.6, "end" : 38.1}, 
							         {"start": 38.2, "end" : 50.1}, 
							         {"start": 50.0, "end" : 61.7}, 
							         {"start": 61.8, "end" : 100.0}, 
							         {"start": 100.1, "end" : 138.1}, 
							         {"start": 138.2, "end" : 168.1},
							         {"start": 168.2, "end" : 200.0}]};
				

				});*/