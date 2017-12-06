


/// <reference path="angular.js" />


var app = angular
			.module("fibIndicator", [])
			.controller("fibScriptController", function($scope, $http) {
				$http.get('../data/fibScriptData.jsp')
					.then(function(response) {
						$scope.fibScriptData = response.data;
				});
			});