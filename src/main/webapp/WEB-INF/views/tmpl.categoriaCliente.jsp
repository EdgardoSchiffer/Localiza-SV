<md-dialog aria-label=""  ng-cloak>
  <form>
    <md-toolbar>
      <div class="md-toolbar-tools">
        <h2 ng-model="title">Ingresar categoria cliente</h2>
        <span flex></span>
      </div>
    </md-toolbar>
    <md-dialog-content>
      <div class="md-dialog-content">
        <md-input-container class="md-block">
	      <input ng-model="newTipo.tipo_cliente" type="text" placeholder="Categoria cliente" ng-required="true">
	    </md-input-container>
	    <md-input-container class="md-block">
          <label>Descripci&oacute;n (Opcional)</label>
          <textarea ng-model="newTipo.descripcion" md-maxlength="300" rows="3" md-select-on-focus></textarea>
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