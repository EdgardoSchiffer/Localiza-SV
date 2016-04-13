var login = angular.module('loginApp', ['ngMaterial'])
.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('teal')
		.accentPalette('indigo');
});


login.controller("loginCtrl", function($scope, $http, $httpParamSerializer, $window){
	$scope.login = {};
	$scope.flash = flash;
	$scope.validateLogin = function(){
		console.log($scope.login);
		$http({
			type: "application/json",
			method: 'POST',
			url: 'loginRequestValidate',
			data: $httpParamSerializer($scope.login),
			dataType: "JSON",
			headers: {
			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
			  }
		})
		.success(function(data){
			console.log(data)
			if(!data){
				data = "Usuario o contrase&ntilde;a incorrectos."
				document.getElementById("flash").innerHTML = data + "<a id='failCredentials' onClick='JavaScript:clean()'>x</a>";
			} else{
				$window.location.href = "app/index";
			}
		})
	};
});

function clean(){
	document.getElementById("flash").innerHTML = "";
}

login.run(function(){
	//alert("Angular test")
});
