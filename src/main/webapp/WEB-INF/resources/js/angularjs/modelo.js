var app = angular.module("modeloApp", ['ngMaterial', 'ngCookies'])
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
				url: 'getModelo',
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
var tipo;
app.controller('modeloCtrl',  function($mdSidenav, $scope, $mdDialog, $mdMedia, $http, $httpParamSerializer, getList, $mdToast) {
	  var vm = this;
	  $scope.marca = {};
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
	  
	  getList.list().then(function(response){
		  $scope.list = response.data
	  })
	  
	  
	  /**
	   * 
	   * DELETE FUNCTION
	   * 
	   */
	  
	  $scope.deleteFunction = function(ev, modelo){
		  var confirm = $mdDialog.confirm()
	          .title('Eliminar modelo')
	          .textContent('Desea eliminar el modelo '+modelo.marca+' '+modelo.modelo+'? (Esta accion no se puede deshacer)')
	          .ariaLabel('Lucky day')
	          .targetEvent(ev)
	          .ok('Eliminar')
	          .cancel('Cancelar');
	  $mdDialog.show(confirm).then(function() {
		  console.log(JSON.parse('{"id":"'+modelo.id+'"}'));
	      $http({
	    	  method: "POST",
	    	  url: "deleteModelo",
	    	  data: $httpParamSerializer(JSON.parse('{"id":"'+modelo.id+'"}')),
	  			headers: {
	  			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
	  			  }
	      })
	      .success(function(data){
	    	  $scope.list = {};
	  			getList.list().then(function(response){
	  			  $scope.list = response.data
	  			})
	  			if (data) {
	  				$mdToast.show(
	  				      $mdToast.simple()
	  				        .textContent('Modelo eliminado con exito')
	  				        .position(pinTo )
	  				        .hideDelay(3000)
	  				    );
				}else{
					$mdToast.show(
						      $mdToast.simple()
						        .textContent('Ha ocurrido un error')
						        .position(pinTo )
						        .hideDelay(3000)
						    );
				}
	      })
	    });
	  }
	  
	  /**
	   * 
	   * UPDATE FUNCTION
	   * 
	   */
	  
	  $scope.updateFunction = function(ev, modelo){
		  delete modelo.$$hashKey;
		  var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'modeloDialog',
		      scope: $scope,
		      preserveScope: true,
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: useFullScreen,
		      locals: {
		    	  items: modelo,
		    	  title: "Actualizar modelo"
		      }
		    })
		    .then(function(answer) {
		    	$scope.newTipo.id_marca = $scope.newTipo.marca;
			    delete $scope.newTipo.marca;
			    console.log($scope.newTipo)
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'updateModelo',
		  			data: $httpParamSerializer($scope.newTipo),
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
		  			        .textContent('Modelo modificado con exito')
		  			        .position(pinTo )
		  			        .hideDelay(3000)
		  			    );
		  		})
		      }
		    });
	  }
	  
	  /**
	   * 
	   * INSERT FUNCTION
	   * 
	   */
	  
	  $scope.newFunction = function(ev) {
		    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'modeloDialog',
		      scope: $scope,
		      preserveScope: true,
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: useFullScreen,
		      locals: {
		    	  items: {}
		      }
		    })
		    .then(function(answer) {
		    	$scope.newTipo.id_marca = $scope.newTipo.marca;
			    delete $scope.newTipo.marca;
			    console.log($scope.newTipo)
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'newModelo',
		  			data: $httpParamSerializer($scope.newTipo),
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
		  			        .textContent('Modelo guardado con exito')
		  			        .position(pinTo )
		  			        .hideDelay(3000)
		  			    );
		  		})
		      }
		    });
		  };
});

/**
 * 
 * DIALOG CONFIG
 * 
 */

function DialogController($scope, $mdDialog, locals, $http) {
	$http({
		type: "application/json",
		method: 'POST',
		url: 'getMarca',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.marcaDispositivo = data;
  })
	var originalData = angular.copy(locals.items);
	$scope.title = locals.title;
	$scope.newTipo = locals.items;
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
		  if (answer == "save") {
			  if ($scope.newTipo.modelo != "" && $scope.newTipo.modelo != undefined) {
			    	$mdDialog.hide(answer);	
				}else{
					angular.copy(originalData, $scope.newTipo);
					alert("Llene el modelo")
				}
		  }else{
			  angular.copy(originalData, $scope.newTipo);
			  $mdDialog.hide(answer);
		  }
	  };
	}

