var app = angular.module("trabajoAjustesApp", ['ngMaterial'])
.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('teal')
		.accentPalette('indigo');
});
var json;
app.controller('trabajoAjustesCtrl',  function($mdSidenav, $scope) {
	  var vm = this;

	  vm.toggleSidenav = function(menuId) {
	    $mdSidenav(menuId).toggle();
	  };
	  json = '[{"id":1,"trabajo":"Instalacion","descripcion":""},'+
	  			'{"id":2,"trabajo":"Revision","descripcion":""},'+
	  			'{"id":3,"trabajo":"Desmontaje","descripcion":""}]'
	  json = JSON.parse(json)
	  $scope.list = json;
	});