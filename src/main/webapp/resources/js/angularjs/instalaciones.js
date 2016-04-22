var app = angular.module("instalacionesApp", ['ngMaterial'])
	.config(function($mdThemingProvider) {
		$mdThemingProvider.theme('default')
			.primaryPalette('teal')
			.accentPalette('indigo');
	})
	
	
var json = {};
var id;
/**
 * 
 * FUNCTION TO RETRIEVE DATABASE DATA
 * 
 */
app.factory('getList', function($http, $httpParamSerializer){
	return {
		list: function(tipo){
			return $http({
				type: "application/json",
				method: 'POST',
				url: 'getTrabajos',
				data: $httpParamSerializer(tipo),
				dataType: "JSON",
				headers: {
				    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
				  }
			})
		}
	}
});
var json;
var tipo;
app.controller('instalacionesCtrl',  function($mdSidenav, $scope, $mdDialog, $mdMedia, $http, $httpParamSerializer, getList, $mdToast, $rootScope) {
	  var vm = this;
	  $scope.tipoCliente = {};
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
	  
	  getList.list({"tipo": "Instalacion"}).then(function(response){
		  $scope.list = response.data
	  })
	  
	  
	  /**
	   * 
	   * DELETE FUNCTION
	   * 
	   */
	  
	  $scope.deleteFunction = function(ev, trabajo){
		  var confirm = $mdDialog.confirm()
	          .title('Eliminar cliente')
	          .textContent('Desea eliminar el trabajo con boleta '+trabajo.boleta+'? (Esta accion no se puede deshacer)')
	          .ariaLabel('Lucky day')
	          .targetEvent(ev)
	          .ok('Eliminar')
	          .cancel('Cancelar');
	  $mdDialog.show(confirm).then(function() {
		  console.log(JSON.parse('{"boleta":"'+trabajo.boleta+'"}'));
	      $http({
	    	  method: "POST",
	    	  url: "deleteTrabajo",
	    	  data: $httpParamSerializer(JSON.parse('{"boleta":"'+trabajo.boleta+'"}')),
	  			headers: {
	  			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
	  			  }
	      })
	      .success(function(data){
	    	  $scope.list = {};
		  			getList.list({"tipo": "Instalacion"}).then(function(response){
		  			  $scope.list = response.data
		  		  })
	  			if (data) {
	  				$mdToast.show(
	  				      $mdToast.simple()
	  				        .textContent('Trabajo eliminado con exito')
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
	  
	  $scope.updateFunction = function(ev, trabajo){
		  var original = angular.copy(trabajo);
		  delete trabajo.$$hashKey;
		  var useFullScreen = ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen;
		    $mdDialog.show({
		      controller: DialogController,
		      templateUrl: 'trabajosDialog',
		      //scope: $scope,
		      //preserveScope: true,
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen:true,
		      locals: {
		    	  items: trabajo,
		    	  title: "Actualizar trabajo"
		      }
		    })
		    .then(function(answer) {
		    	json.id = id;
			    console.log(json)
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'updateTrabajo',
		  			data: $httpParamSerializer(json),
		  			dataType: "JSON",
		  			headers: {
		  			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		  			  }
		  		})
		  		.success(function(data){
		  			$scope.list = undefined;
		  			getList.list({"tipo": "Instalacion"}).then(function(response){
		  			  $scope.list = response.data
		  		  })
		  			$mdToast.show(
		  			      $mdToast.simple()
		  			        .textContent('Trabajo modificado con exito')
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
		      templateUrl: 'trabajosDialog',
		      scope: $scope,
		      preserveScope: true,
		      parent: angular.element(document.body),
		      targetEvent: ev,
		      clickOutsideToClose:true,
		      fullscreen:true,
		      locals: {
		    	  items: {}
		      }
		    })
		    .then(function(answer) {
		      if (answer == "save") {
		    	  $http({
		  			type: "application/json",
		  			method: 'POST',
		  			url: 'newTrabajo',
		  			data: $httpParamSerializer(json),
		  			dataType: "JSON",
		  			headers: {
		  			    'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		  			  }
		  		})
		  		.success(function(data){
		  			$scope.list = undefined;
		  			getList.list({"tipo": "Instalacion"}).then(function(response){
		  			  $scope.list = response.data
		  		  })
		  			$mdToast.show(
		  			      $mdToast.simple()
		  			        .textContent('Trabajo guardado con exito')
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

function DialogController($scope, $mdDialog, locals, $http, $rootScope) {
	var originalData = angular.copy(locals.items);
	$scope.work = 'Instalacion'
	$scope.title = locals.title;
	$scope.newTipo = locals.items;
	$scope.newTipo.fecha = new Date($scope.newTipo.fecha);
	if (isNaN(Date.parse($scope.newTipo.fecha))) {
		$scope.newTipo.fecha = new Date();
	}
	
	$http({
		type: "application/json",
		method: 'POST',
		url: 'getMarca',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.marcas = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getModelo',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.modelos = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getTipoTrabajo',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.tipoTrabajo = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getTecnicos',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.tecnicos = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getEjecutivas',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.ejecutivas = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getMonitoreo',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.operadores = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getClientes',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.clientes = data;
  })
  $http({
		type: "application/json",
		method: 'POST',
		url: 'getUbicacion',
		headers: {
			  'Content-Type': 'application/x-www-form-urlencoded' // Note the appropriate header
		}
  })
  .success(function(data){
	  $scope.ubicaciones = data;
  })
  id = $scope.newTipo.boleta;
	  $scope.hide = function() {
	    $mdDialog.hide();
	  };
	  $scope.cancel = function() {
	    $mdDialog.cancel();
	  };
	  $scope.answer = function(answer) {
		  if (answer == "save") {
			  if(!notEmpty($scope.newTipo.boleta, "boleta")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.tipoTrabajo, "tipo trabajo")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.fecha, "fecha")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.tecnico, "tecnico")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.ejecutiva, "ejecutiva")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.monitoreo, "monitoreo")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.cliente, "cliente")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.marca, "marca")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.modelo, "modelo")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.ubicacionGps, "ubicacion")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.imei, "imei")){
				  return false;
			  }
			  if(!notEmpty($scope.newTipo.sim, "sim")){
				  return false;
			  }
			  if(!$scope.newTipo.comentario){
				  $scope.newTipo.comentario = " ";
			  }
			  json = 
		    	{ 
		    			"boleta":	$scope.newTipo.boleta,
		    			"tipo": $scope.newTipo.tipoTrabajo,
		    			"fecha": moment($scope.newTipo.fecha).format("YYYY-MM-DD h:mm:ss"),
		    			"tecnico":	$scope.newTipo.tecnico,
		    			"ejecutiva": $scope.newTipo.ejecutiva,
		    			"monitoreo":	$scope.newTipo.monitoreo,
		    			"cliente":  $scope.newTipo.cliente,
		    			"marca": $scope.newTipo.marca,
		    			"modelo": $scope.newTipo.modelo,
		    			"ubicacion": $scope.newTipo.ubicacionGps,
		    			"unidad":	$scope.newTipo.unidad,
		    			"imei":	$scope.newTipo.imei,
		    			"sim": $scope.newTipo.sim,
		    			"comentario": $scope.newTipo.comentario,
		    			"estado": $scope.newTipo.estado    			
		    	}
		  }
		  angular.copy(originalData, $scope.newTipo);
		  $mdDialog.hide(answer);
	  };
	}

function notEmpty(field, fieldName){
	if (field == "" || field == undefined) {
    	alert("Verifique el campo " + fieldName)
    	return false;
	}
	return true;
}