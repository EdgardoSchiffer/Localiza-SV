var app = angular.module("trabajoAjustesApp", ['ngMaterial'])
	.config(function($mdThemingProvider) {
		$mdThemingProvider.theme('default')
			.primaryPalette('teal')
			.accentPalette('indigo');
	})
	app.factory('getList', function($http){
		return {
			list: function(){
				return $http({
					type: "application/json",
					method: 'POST',
					url: 'getTipoTrabajo',
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
app.controller('trabajoAjustesCtrl',  function($mdSidenav, $scope, $mdDialog, $mdMedia, $http, $httpParamSerializer, getList, $mdToast) {
	  var vm = this;

	  vm.toggleSidenav = function(menuId) {
	    $mdSidenav(menuId).toggle();
	  };
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
	  $scope.deleteFunction = function(ev, work){
		  var confirm = $mdDialog.confirm()
	          .title('Eliminar categoria')
	          .textContent('Desea eliminar la categoria '+work.tipo_trabajo+'? (Esta accion no se puede deshacer)')
	          .ariaLabel('Lucky day')
	          .targetEvent(ev)
	          .ok('Eliminar')
	          .cancel('Cancelar');
	    $mdDialog.show(confirm).then(function() {
	    	console.log(JSON.parse('{"id":"'+work.id+'"}'));
	      $http({
	    	  method: "POST",
	    	  url: "deleteTipoTrabajo",
	    	  data: $httpParamSerializer(JSON.parse('{"id":"'+work.id+'"}')),
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
	  				        .textContent('Categoria eliminada con exito')
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
	  
	  $scope.updateFunction = function(ev, work){
		  delete work.$$hashKey;
		  var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'trabajoDialog',
		      scope: $scope,
		      preserveScope: true,
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen: useFullScreen,
		      locals: {
		    	  items: work,
		    	  title: "Actualizar trabajo tecnico"
		      }
		    })
		    .then(function(answer) {
		    	if ($scope.newTipo.descripcion === undefined) {
		    		$scope.newTipo.descripcion = " ";
				}
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'updateTipoTrabajo',
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
		  			        .textContent('Categoria modificada con exito')
		  			        .position(pinTo )
		  			        .hideDelay(3000)
		  			    );
		  		})
		      }
		    });
	  }
	  
	  $scope.newFunction = function(ev) {
		    var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'trabajoDialog',
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
		    	if ($scope.newTipo.descripcion === undefined) {
		    		$scope.newTipo.descripcion = " ";
				}
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'newTipoTrabajo',
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
		  			        .textContent('Categoria guardada con exito')
		  			        .position(pinTo )
		  			        .hideDelay(3000)
		  			    );
		  		})
		      }
		    });
		  };
});

function DialogController($scope, $mdDialog, locals) {
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
			  if ($scope.newTipo.tipo_trabajo != "" && $scope.newTipo.tipo_trabajo != undefined) {
			    	$mdDialog.hide(answer);
				}else{
					alert("Llene el tipo")
					$scope.newTipo = values;
				}
		  }else{
			  document.getElementById("trabajoAjustesForm").submit();
			  $mdDialog.hide(answer);
		  }
	  };
	}

