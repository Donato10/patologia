	    <%@page defaultCodec="none" %>
	    <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
        <h1>Nuevo paciente </h1>
	        <div class="panel panel-pt-primary">
				<legend>Datos generales</legend>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Número de documento<b style="color:red">*</b></label> <input type="text" id="numeroDeIdentificacion" class="form-control"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Fecha de nacimiento </label> <input required=""  type="text" class="date-picker form-control" placeholder="dd/mm/aaaa" id="fechaDeNacimiento" /><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Primer nombre<b style="color:red">*</b></label> <input type="text" id="primerNombre" class="form-control nombre"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Segundo nombre</label> <input type="text" id="segundoNombre" class="form-control nombre"><br/>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Primer Apellido<b style="color:red">*</b></label> <input type="text" id="primerApellido" class="form-control nombre">
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Segundo Apellido</label> <input type="text" id="segundoApellido" class="form-control nombre">
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-lg-3 col-md-3 col-xs-12 col-sm-6">
						<label for="">Tipo de identificación<b style="color:red">*</b></label>
						<select id="tipoDeId" name="tipoDeId" class="form-control">
							<option  value="CC" selected>CC</option>
							<option>RC</option>
							<option>TI</option>
							<option>CE</option>
							<option>Sin dato</option>
						</select>
					</div>
					<div class="col-lg-3 col-md-3 col-xs-12 col-sm-6">
						<label for="">Régimen de salud<b style="color:red">*</b></label>
						<select id="regimen" name="regimen" class="form-control">
							<option value="" selected>--</option>
							<option>Contributivo</option>
							<option>Subsidiado</option>
							<option>Sin dato</option>
						</select>
					</div>
					<div class="col-lg-3 col-md-3 col-xs-12 col-sm-6">
						<label for="">Estado civil<b style="color:red">*</b></label>
						<select id="estadoCivil" name="estadoCivil" class="form-control">
							<option value="" selected>--</option>
							<option>Soltero(a)</option>
							<option>Casado(a)</option>
							<option>Unión libre</option>
							<option>Separado(a)</option>
							<option>Sin dato</option>
						</select>
					</div>
					<div class="col-lg-3 col-md-3 col-xs-12 col-sm-6">
						<label for="">Sexo<b style="color:red">*</b></label>
						<select id="sexo" name="sexo" class="form-control">
							<option value="" selected>--</option>
							<option>Femenino</option>
							<option>Masculino</option>
							<option>Otro</option>
							<option>Sin dato</option>
						</select>
					</div>
				</div>
				<br/>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						 <label for="">Profesión</label> <input type="text" id="profesion" class="form-control">
					</div>
				</div>
			</div>
			<div class="panel panel-pt-primary">
				<legend>Datos de contacto</legend>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Dirección</label> <input type="text" id="direccion" class="form-control"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Teléfono fijo</b></label> <input type="text" id="telefonoFijo" class="form-control"></br>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Teléfono celular</label><input type="text" id="telefonoCelular" class="form-control"/><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label>Email</label> <input type="email" id="email" class="form-control">
					</div>
				</div>
			</div>
			<div class="panel panel-pt-primary">
				<legend>Observaciones</legend>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<label for="">Comentarios</label>
						<textArea id="comentarios" class="form-control"></textArea>
					</div>
				</div>
			</div>
			<div align="center">
			<button type="button" id="btnSave1" class="btn btn-lg btn-pt-primary " onclick="guardarPaciente()">
				Listo, guardar
			</button>
			</div>
        </div>
        <!-- /page content -->
    <g:render template="/layouts/footer"/>

	<!-- Modal de éxitos -->
	<div id="successModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header alert alert-pt-primary">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Exitoso</h4>
	      </div>
	      <div class="modal-body">
	        <p id="successMessage"></p>
	      </div>
	    </div>

	  </div>
	</div>

	<!-- Modal de errores -->
	<div id="errorModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header alert alert-danger">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Error</h4>
	      </div>
	      <div class="modal-body">
	        <p id="errorMessage"></p>
	      </div>
	    </div>
	  </div>
	</div>

	<asset:javascript src="model/nuevoPaciente.js"/>
	<asset:javascript src="bootstrap-daterangepicker/daterangepicker.js"/>
    <script>
      $(document).ready(function() {
        $('#fechaDeNacimiento').daterangepicker({
        	autoUpdateInput: false,
        	singleDatePicker: true,
		    "showDropdowns": true,
		    "maxDate": "${today.format('dd/MM/yyyy')}",
        	calender_style: "picker_4",
        	"locale": {
		        "format": "DD/MM/YYYY",
		        "separator": " - ",
		        "applyLabel": "Aceptar",
		        "cancelLabel": "Cancelar",
		        "weekLabel": "S",
		        "daysOfWeek": [
		            "Do",
		            "Lu",
		            "Ma",
		            "Mi",
		            "Ju",
		            "Vi",
		            "Sa"
		        ],
		        "monthNames": [
		            "Enero",
		            "Febrero",
		            "Marzo",
		            "Abril",
		            "Mayo",
		            "Junio",
		            "Julio",
		            "Agosto",
		            "Septiembre",
		            "Octubre",
		            "Noviembre",
		            "Diciembre"
		        ],
	    	},
        }, function(start, end, label) {
        	$("#fechaDeNacimiento").val(start.format('MM/DD/YYYY'));
        });
      });
    </script>