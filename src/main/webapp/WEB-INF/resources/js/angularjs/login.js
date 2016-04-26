var login = angular.module('loginApp', ['ngMaterial', 'ngCookies'])
.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('teal')
		.accentPalette('indigo');
});


login.controller("loginCtrl", function($scope, $http, $httpParamSerializer, $window, $cookies){
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
			data = { "id": ""+data+""}
			if(data.id < 1){
				data = "Usuario o contrase&ntilde;a incorrectos."
				document.getElementById("flash").innerHTML = data + "<a id='failCredentials' onClick='JavaScript:clean()'>x</a>";
			} else{
				$http({
					type: "application/json",
					method: "POST",
					url: "getRole",
					data: $httpParamSerializer(data),
					dataType: "JSON",
					headers: {
					    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
					  }
				})
				.success(function(role){
					console.log(role);
					console.log($cookies.put('id_user', data.id));
					console.log($cookies.put('rol', role.rol));
					if ($cookies.get('rol')!=undefined && $cookies.get('id_user')!=undefined) {
						if ($cookies.get('rol')== "Admin") {
							$window.location.href = "admin/admin";
						}else{
							$window.location.href = "app/index";
						}
					}
				})	
			}
		})
	};
});

function clean(){
	document.getElementById("flash").innerHTML = "";
}

login.run(function($window, $cookies){
	if ($cookies.get('rol')!=undefined && $cookies.get('id_user')!=undefined) {
		if ($cookies.get('rol')== "Admin") {
			$window.location.href = "admin/admin";
		}else{
			$window.location.href = "app/index";
		}
	}
});
