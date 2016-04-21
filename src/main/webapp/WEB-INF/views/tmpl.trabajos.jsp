<md-dialog aria-label=""  ng-cloak>
  <form>
    <md-toolbar>
      <div class="md-toolbar-tools">
        <h2 ng-model="title">Ingresar trabajo</h2>
        <span flex></span>
      </div>
    </md-toolbar>
    <md-dialog-content>
      <div class="md-dialog-content">
       <div layout-gt-sm="row">
	        <md-input-container class="md-block">
		      <input ng-model="newTipo.boleta" type="text" placeholder="Boleta" ng-required="true">
		    </md-input-container>
		    <md-input-container class="md-block" flex-gt-sm>
	            <label>Tipo de trabajo</label>
	            <md-select ng-model="newTipo.tipoTrabajo">
	              <md-option ng-repeat="tipo in tipoTrabajo" value="{{tipo.id}}">
	                {{tipo.tipoTrabajo}}
	              </md-option>
	            </md-select>
	          </md-input-container>
	          <md-input-container class="md-block">
			    <input ng-model="newTipo.fecha" type="text" placeholder="Fecha" ng-required="true">
			  </md-input-container>
         </div>
         <div layout-gt-sm="row">
	          <md-input-container class="md-block" flex-gt-sm>
	            <label>Tecnico</label>
	            <md-select ng-model="newTipo.tecnico">
	              <md-option ng-repeat="tecnico in tecnicos" value="{{tecnico.id}}">
	                {{tecnico.nombre}}
	              </md-option>
	            </md-select>
	          </md-input-container>
	          <md-input-container class="md-block" flex-gt-sm>
	            <label>Ejecutiva</label>
	            <md-select ng-model="newTipo.ejecutiva">
	              <md-option ng-repeat="ejecutiva in ejecutivas" value="{{ejecutiva.id}}">
	                {{ejecutiva.nombre}}
	              </md-option>
	            </md-select>
	          </md-input-container>
	          <md-input-container class="md-block" flex-gt-sm>
	            <label>Monitoreo</label>
	            <md-select ng-model="newTipo.monitoreo">
	              <md-option ng-repeat="monitoreo in operadores" value="{{monitoreo.id}}">
	                {{monitoreo.nombre}}
	              </md-option>
	            </md-select>
	          </md-input-container>
	      </div>
          <md-input-container class="md-block" flex-gt-sm>
            <label>Cliente</label>
            <md-select ng-model="newTipo.cliente">
              <md-option ng-repeat="cliente in clientes" value="{{cliente.id}}">
                {{cliente.nombre}}
              </md-option>
            </md-select>
          </md-input-container>
           <div layout-gt-sm="row">
	          <md-input-container class="md-block" flex-gt-sm>
	            <label>Marca GPS</label>
	            <md-select ng-model="newTipo.marca">
	              <md-option ng-repeat="marca in marcas" value="{{marca.id}}">
	                {{marca.marca}}
	              </md-option>
	            </md-select>
	          </md-input-container>
	          <md-input-container class="md-block" flex-gt-sm>
	            <label>Modelo GPS</label>
	            <md-select ng-model="newTipo.modelo">
	              <md-option ng-repeat="modelo in modelos" value="{{modelo.id}}">
	                {{modelo.modelo}}
	              </md-option>
	            </md-select>
	          </md-input-container>
	        </div>
	        <div layout-gt-sm="row">
	          <md-input-container class="md-block">
			    <input ng-model="newTipo.unidad" type="text" placeholder="Unidad" ng-required="true">
			  </md-input-container>
	          <md-input-container class="md-block">
			    <input ng-model="newTipo.imei" type="text" placeholder="IMEI" ng-required="true">
			  </md-input-container>
			  <md-input-container class="md-block">
			    <input ng-model="newTipo.sim" type="text" placeholder="SIM" ng-required="true">
			  </md-input-container>
			</div>
		  <md-input-container class="md-block">
	          <label>Comentario (Opcional)</label>
	          <textarea ng-model="newTipo.comentario" md-maxlength="300" rows="3" md-select-on-focus></textarea>
	      </md-input-container>
	      <md-switch ng-model="newTipo.estado">{{newTipo.estado ? "Instalado" : "Por instalar"}}</md-switch>
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