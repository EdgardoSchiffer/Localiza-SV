var app = angular.module("localizaApp", ['ngMaterial'])
.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default')
		.primaryPalette('teal')
		.accentPalette('indigo');
});

app.factory('getList', function($http){
	return {
		list: function(){
			return $http({
				type: "application/json",
				method: 'POST',
				url: 'getUserDetails',
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
app.controller('AppController', function($mdSidenav, $scope, $mdDialog, $mdMedia, $http, $httpParamSerializer, getList, $mdToast) {
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
	  json = [{ "nombre": "Edgardo", "apellido": "Argueta", "user": "operador8", "pass": "********" }];
	  $scope.list = json;
	  console.log($scope.list)
	  /*getList.list().then(function(response){
		  $scope.list = response.data
	  })*/
	  
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
		  			$scope.list = undefined;
		  			getList.list().then(function(response){
		  			  $scope.list = response.data
		  			})
		  			$mdToast.show(
		  			      $mdToast.simple()
		  			        .textContent('Categoria modificada con exito')
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

function DialogController($scope, $mdDialog, locals) {
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
			  if(!notEmpty($scope.updateUser.newPass, "contraseña")){
				  return false;
			  }
			  if(!notEmpty($scope.updateUser.newPassConfirm, "confirmar contraseña")){
				  return false;
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