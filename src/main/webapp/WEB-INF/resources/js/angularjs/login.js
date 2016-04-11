var login = angular.module('loginApp', ['ngMaterial'])
.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('teal')
		.accentPalette('indigo');
});

login.run(function(){
	//alert("Angular test")
});
