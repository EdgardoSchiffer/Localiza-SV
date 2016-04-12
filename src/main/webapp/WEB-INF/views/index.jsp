<!DOCTYPE HTML>
<html ng-app="loginApp">
	<head>
		<title>Inicio</title>
		<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.css"></link>
		<meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"></link>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//css/style.css"></link>
		<meta charset="UTF-8"></meta>
	</head>
	<body>
		<div  layout="row" layout-align="center center" ng-cloak="">
			<form name="loginForm" layout="column" class="md-whiteframe-z1">
				<md-toolbar >
			    	<div class="md-toolbar-tools">
			      		<img id="logo" src="http://localiza.com.sv/theme/localiza/img/localiza.png" width="130px" />
			    	</div>
			    </md-toolbar>
				<md-content class="md-no-momentum">
					<md-input-container class="md-icon-float md-block">
						<label>Usuario</label>
						<md-icon class="user" md-font-set="material-icons">account_circle</md-icon>
						<input ng-model="login.user" required="" md-no-asterisk="" type="text"></input>
					</md-input-container>
					<md-input-container class="md-icon-float md-block">
						<label>Contrase&ntilde;a</label>
						<md-icon class="password" md-font-set="material-icons">https</md-icon>
						<input ng-model="login.password" required="" md-no-asterisk="" type="password"></input>
					</md-input-container>
					<md-input-container layout="column" layout-align="center">
						<md-button class="md-raised md-primary">Iniciar sesi&oacute;n</md-button>
						<md-button md-no-ink="" class="md-primary">Recuperar contrase&ntilde;a</md-button>
					</md-input-container>
				</md-content>
			</form>
		</div>
	</body>
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular.min.js"></script>
  	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-animate.min.js"></script>
  	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-aria.min.js"></script>
 	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.3/angular-messages.min.js"></script>
 	<script src="http://ajax.googleapis.com/ajax/libs/angular_material/1.1.0-rc2/angular-material.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/angularjs/login.js"></script>
</html>