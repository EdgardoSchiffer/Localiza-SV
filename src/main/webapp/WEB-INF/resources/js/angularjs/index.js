var app = angular.module("localizaApp", ['ngMaterial', 'ngCookies'])
.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('teal')
		.accentPalette('indigo');
});

app.run(function($window, $cookies){
	if ($cookies.get('rol')==undefined && $cookies.get('id_user')==undefined) {
		$window.location.href = "../";
	}
})

app.factory('getList', function($http, $httpParamSerializer){
	return {
		list: function(id){
			return $http({
				type: "application/json",
				method: 'POST',
				url: 'getUserInfo',
				data: $httpParamSerializer(id),
				//dataType: "JSON",
				headers: {
				    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
				  }
			})
		}
	}
});
var json;
app.controller('AppController', function($mdSidenav, $scope, $mdDialog, $mdMedia, $http, $httpParamSerializer, getList, $mdToast, $cookies) {
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
	  
	  /**
	   * 
	   * GETTING INFO FROM BD
	   * 
	   */
	  getList.list({"id": ""+$cookies.get("id_user")+""}).then(function(response){
		  $scope.list = response.data
		  $scope.list.id = $scope.list.user.id
		  $scope.list.user = $scope.list.user.user;
		  $scope.list.rol = $cookies.get("rol")
	  })
	  
	  /**
	   * 
	   * UPDATE FUNCTION
	   * 
	   */
	  
	  $scope.updateFunction = function(ev, data){
		  delete data.$$hashKey;
		  var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'indexDialog',
		      scope: $scope,
		      preserveScope: true,
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: useFullScreen,
		      locals: {
		    	  items: data,
		    	  title: "Actualizar informacion"
		      }
		    })
		    .then(function(answer) {
		    	delete $scope.updateUser.passwordConfirm
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'updateUser',
		  			data: $httpParamSerializer($scope.updateUser),
		  			dataType: "JSON",
		  			headers: {
		  			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		  			  }
		  		})
		  		.success(function(data){
		  			getList.list({"id": ""+$cookies.get("id_user")+""}).then(function(response){
		  			  $scope.list = response.data
		  			  $scope.list.id = $scope.list.user.id
		  			  $scope.list.user = $scope.list.user.user;
		  			  $scope.list.rol = $cookies.get("rol")
		  		  })
		  			$mdToast.show(
		  			      $mdToast.simple()
		  			        .textContent('Datos modificados con exito')
		  			        .position(pinTo )
		  			        .hideDelay(3000)
		  			    );
		  		})
		      }
		    });
	  }

	});

/**
 * 
 * DIALOG CONFIG
 * 
 */

function DialogController($scope, $mdDialog, locals, $http, $httpParamSerializer) {
	var originalData = angular.copy(locals.items);
	$scope.title = locals.title;
	$scope.updateUser = locals.items;
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
		  if (answer == "save") {
			  if(!notEmpty($scope.updateUser.nombre, "nombre")){
				  angular.copy(originalData, $scope.updateUser);
				  return false;
			  }
			  if(!notEmpty($scope.updateUser.apellido, "apellido")){
				  angular.copy(originalData, $scope.updateUser);
				  return false;
			  }
			  if(!notEmpty($scope.updateUser.user, "usuario")){
				  angular.copy(originalData, $scope.updateUser);
				  return false;
			  }
			  if($scope.passwordFlag){
				  if(!notEmpty($scope.updateUser.password, "contraseña")){
					  return false;
				  }
				  if(!notEmpty($scope.updateUser.passwordConfirm, "confirmar contraseña")){
					  return false;
				  }
				  $scope.updateUser.pwd = true;
				  if ($scope.updateUser.password != $scope.updateUser.passwordConfirm) {
					alert("Las contraseñas no coinciden");
					return false;
				}
			  }
			  if (!$scope.passwordFlag) {
				$scope.updateUser.password = "";
				$scope.updateUser.pwd = false;
			  }
			  if ($scope.updateUser.user != originalData.user) {
				  $http({
					  type: "application/json",
			  			method: 'POST',
			  			url: 'uniqueUser',
			  			data: $httpParamSerializer({ "user" : $scope.updateUser.user }),
			  			dataType: "JSON",
			  			headers: {
			  			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
			  			  }
				  })
				  .success(function(data){
					  if(data == 1){
						  alert("Ya existe el usuario, por favor ingrese otro");
						  return false;
					  }
					  else{
						  $mdDialog.hide(answer);
						  return true;
					  }
				  })
			  }else{
				  $mdDialog.hide(answer);
				  return true;
			  }
		  }else{
			  angular.copy(originalData, $scope.updateUser);
			  $mdDialog.hide(answer);
		  }
	  };
}

function notEmpty(field, fieldName){
	if (field == "" || field == undefined) {
    	alert("Verifique el campo " + fieldName)
    	return false;
	}
	return true;
}