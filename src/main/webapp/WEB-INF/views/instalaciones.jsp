<!DOCTYPE html>
<html ng-app="instalacionesApp">
<head>
<title>Administraci&oacute;n de trabajos</title>
<meta charset="UTF-8" />
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.css"></link>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//css/app/style.css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//css/app/table.css"></link>
</head>
<body layout="column" ng-controller="instalacionesCtrl as vm">
	<md-toolbar layout="row">
		<div class="md-toolbar-tools">
			<md-button ng-click="vm.toggleSidenav('left')" hide-gt-sm class="md-icon-button"> 
				<md-icon aria-label="Menu" md-svg-icon="https://s3-us-west-2.amazonaws.com/s.cdpn.io/68133/menu.svg"></md-icon> 
			</md-button>
			<h1>Instalaciones</h1>
		</div>
	</md-toolbar>
	<div layout="row" flex>
		<md-sidenav ng-include="'menu'" layout="column" class="md-sidenav-left md-whiteframe-4dp" md-component-id="left" md-is-locked-open="$mdMedia('gt-sm')">
			
		</md-sidenav>
		<div layout="column" flex id="content" layout-align="center center">
			<md-content layout="column" flex class="md-padding">
				<div class="table-responsive-vertical shadow-z-1">
				  <table id="table" class="table table-hover table-mc-light-blue" at-table at-paginated at-list="list" at-config="config" rules="none">
				      <thead id="tblTitle">
				        <tr>
				          <th colspan="3">Instalaciones</th>
				          <th colspan="2">
				          	<form class="form-inline">
						        <div class="form-group">
						            <input class="searchInput" type="text" ng-model="search" class="form-control" placeholder="Buscar">
						        </div>
						    </form>
				          </th>
				          <th colspan="2">
				          	<md-button id="insertBtn" class="md-primary md-raised" ng-click="newFunction($event)">
						      Nuevo...
						    </md-button>
				          </th>
				        </tr>
				      </thead>
				      <thead>
				      	<tr>
				      		<th>Boleta</th>
				      		<th>Fecha</th>
				      		<th>Cliente</th>
				      		<th>Unidad</th>
				      		<th>T&eacute;cnico</th>
				      		<th>Monitoreo</th>
				      		<th></th>
				      	</tr>
				      </thead>
				      <tbody>
				        <tr ng-repeat="work in list|filter:search">
				          <td data-title="Boleta">{{work.boleta}}</td>
				          <td data-title="Fecha">{{work.fecha | date:'longDate'}}</td>
				          <td data-title="Cliente">{{work.cliente.tipoCliente.tipo_cliente + ', '+work.cliente.cliente}}</td>
				          <td data-title="Unidad">{{work.unidad}}</td>
				          <td data-title="T&eacute;cnico">{{work.tecnico.nombre + ' ' + work.tecnico.apellidos}}</td>
				          <td data-title="Monitoreo">{{work.monitoreo.nombre + ' ' + work.monitoreo.apellido}}</td>
				          <td class="right-align">
				          	<a ng-click="deleteFunction($event, work)" href="">
				          		<i class="material-icons crudEliminar">delete_forever</i>
				          	</a>
				          	<a ng-click="updateFunction($event, work)" href="">
				          		<i class="material-icons crudModificar" >border_color</i>
				          	</a>
				          </td>
				        </tr>
				      </tbody>
				    </table>
				    <at-pagination at-list="list" at-config="config"></at-pagination>
				  </div>
			</md-content>
		</div>
	</div>

</body>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-animate.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-aria.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-messages.min.js"></script>
<script 
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-i18n/1.5.3/angular-locale_es-sv.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/moment.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/angularjs/instalaciones.js"></script>
</html>