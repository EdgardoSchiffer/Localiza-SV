<md-dialog aria-label=""  ng-cloak>
  <form>
    <md-toolbar>
      <div class="md-toolbar-tools">
        <h2 ng-model="title">Actualizar información</h2>
        <span flex></span>
      </div>
    </md-toolbar>
    <md-dialog-content>
      <div class="md-dialog-content">
        <md-input-container class="md-block">
	      <input ng-model="updateUser.nombre" type="text" placeholder="Nombre" ng-required="true">
	    </md-input-container>
	    <md-input-container class="md-block">
          <label>Apellido</label>
          <input ng-model="updateUser.apellido" type="text" placeholder="Apellido" ng-required="true">
        </md-input-container>
        <md-input-container class="md-block">
          <label>Usuario</label>
          <input ng-model="updateUser.user" type="text" placeholder="Usuario" ng-required="true">
        </md-input-container>
        <md-switch ng-model="passwordFlag">Modificar contraseña ( {{passwordFlag ? "Si" : "No"}} )</md-switch>
        <md-input-container ng-if="passwordFlag" class="md-block">
          <label>Contrase&ntilde;a</label>
          <input ng-model="updateUser.password" type="text" placeholder="Contrase&ntilde;a" ng-required="true">
        </md-input-container>
        <md-input-container ng-if="passwordFlag" class="md-block">
          <label>Confirmar contrase&ntilde;a</label>
          <input ng-model="updateUser.passwordConfirm" type="text" placeholder="Confirmar contrase&ntilde;a" ng-required="true">
        </md-input-container>
      </div>
    </md-dialog-content>
    <md-dialog-actions layout="row">
      <span flex></span>
      <md-button ng-click="answer('cancel')">
       Cancelar
      </md-button>
      <md-button ng-click="answer('save')" style="margin-right:20px;">
        Guardar
      </md-button>
    </md-dialog-actions>
  </form>
</md-dialog>