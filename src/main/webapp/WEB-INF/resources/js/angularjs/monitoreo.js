var app = angular.module("monitoreoApp", ['ngMaterial', 'ngCookies'])
	.config(function($mdThemingProvider) {
		$mdThemingProvider.theme('default')
			.primaryPalette('teal')
			.accentPalette('indigo');
	})
	
app.run(function($window, $cookies){
	if ($cookies.get('rol')==undefined && $cookies.get('id_user')==undefined) {
		$window.location.href = "../";
	}
})

	
/**
 * 
 * FUNCTION TO RETRIEVE DATABASE DATA
 * 
 */
app.factory('getList', function($http){
	return {
		list: function(){
			return $http({
				type: "application/json",
				method: 'POST',
				url: 'getMonitoreo',
				//data: $httpParamSerializer($scope.login),
				//dataType: "JSON",
				headers: {
				    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
				  }
			})
		}
	}
});
var json;
app.controller('monitoreoCtrl',  function($mdSidenav, $scope, $mdDialog, $mdMedia, $http, $httpParamSerializer, getList, $mdToast) {
	  var vm = this;

	  vm.toggleSidenav = function(menuId) {
	    $mdSidenav(menuId).toggle();
	  };
	  
	  
	  /**
	   * 
	   * TOAST OPTIONS
	   * 
	   */
	  var last = {
			  bottom: false,
		      top: true,
		      left: false,
		      right: true
	  };
	  $scope.toastPosition = angular.extend({},last);
	  $scope.getToastPosition = function() {
		  sanitizePosition();
		  return Object.keys($scope.toastPosition)
		  .filter(function(pos) { return $scope.toastPosition[pos]; })
		  .join(' ');
	  };
	  function sanitizePosition() {
		  var current = $scope.toastPosition;
		  if ( current.bottom && last.top ) current.top = false;
		  if ( current.top && last.bottom ) current.bottom = false;
		  if ( current.right && last.left ) current.left = false;
		  if ( current.left && last.right ) current.right = false;
		  last = angular.extend({},current);
	  }
	  var pinTo = $scope.getToastPosition();
	  $scope.customFullscreen = $mdMedia('xs') || $mdMedia('sm');
	  $scope.$watch(function() {
		  return $mdMedia('xs') || $mdMedia('sm');
	  }, function(wantsFullScreen) {
		  $scope.customFullscreen = (wantsFullScreen === true);
	  });
	  
	  getList.list().then(function(response){
		  $scope.list = response.data
	  })
})
	  