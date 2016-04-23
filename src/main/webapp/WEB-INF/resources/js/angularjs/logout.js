var app = angular.module("logoutApp", ['ngCookies']);
app.run(function($window, $cookies){
	delete $cookies['rol'];
	$cookies.remove('rol');
	$cookies.rol = undefined;
	delete $cookies['id_user'];
	$cookies.remove('id_user');
	$cookies.id_user = undefined;
	$window.location.href="login";
})