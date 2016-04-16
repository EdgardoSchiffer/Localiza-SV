<md-dialog aria-label=""  ng-cloak>
  <form>
    <md-toolbar>
      <div class="md-toolbar-tools">
        <h2 ng-model="title">Ingresar cliente</h2>
        <span flex></span>
      </div>
    </md-toolbar>
    <md-dialog-content>
      <div class="md-dialog-content">
        <md-input-container class="md-block">
	      <input ng-model="newTipo.cliente" type="text" placeholder="Cliente" ng-required="true">
	    </md-input-container>
	    <md-input-container class="md-block" flex-gt-sm>
            <label>Tipo cliente</label>
            <md-select ng-model="newTipo.tipoCliente">
              <md-option ng-repeat="tipo in tipoCliente" value="{{tipo.id_tipo_cliente}}">
                {{tipo.tipo_cliente}}
              </md-option>
            </md-select>
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