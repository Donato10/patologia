	    <%@page defaultCodec="none" %>
	    <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
        <h1>Nuevo Caso </h1>

	        <div class="panel panel-pt-primary">
				<legend>Datos generales</legend>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Tipo de caso<b style="color:red">*</b></label> 
						<select id="tipoDeCaso" name="tipoDeCaso" class="form-control">
							<option value="Quirurgico" selected>Quirúrgico</option>
							<option value="Citometria">Citometría</option>
							<option value="Necropsia">Necropsia</option>
							<option value="Citologia">Citología</option>
							<option value="CitologiaN">Citología no ginecológica</option>

						</select>
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Fecha de radicado<b style="color:red">*</b></label>
						<input required="" type="text" class="form-control" placeholder="dd/mm/aaaa"  id="fechaDeRadicado" />
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Documento de identidad<b style="color:red">*</b></label> <input type="text" id="documentoDeIdentidad" onkeyup="revisarDocumento()" class="form-control">
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" id="edadYFechaBlock">
						<div id="labelFechaDeNacimiento">
							<label for="">Fecha de nacimiento</label>
						</div>
						<div id="fechaDeNacimientoBlock">
							<div class="input-append" id="d2">
								<input type="text" id="fechaDeNacimiento" class="form-control"  placeholder="dd/mm/aaaa"> <span class="add-on"><i class="icon-th"></i></span>
							</div>
							<button type="button" id="cambiarAEdadButton" class="btn btn-primary pull-right" onclick="cambiarAEdad()" style="margin-top:10px">Cambiar a edad
							</button>
						</div>

						<div id="edadBlock" class="row" style="display:none">
							<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
								<label for="" id="labelAnios">Años</label>
								<input type="number" id="anios" onkeyup="habilitarMeses()" class="form-control">
							</div>
							<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
								<label for="" id="labelMeses" style="display:none">Meses</label>
								<input type="number" id="meses" class="form-control" style="display:none">
								</div>
							<div class="col-sm-4 col-xs-4 col-md-4 col-lg-4">
								<label for="" id="labelDias" style="display:none">Días</label>
								<input type="number" id="dias" class="form-control" style="display:none">
							</div>
							
							<button type="button" id="cambiarAFechaButton"
								class="btn btn-primary pull-right" onclick="cambiarAFechaDeNacimiento()" style="margin-top:10px">Cambiar a fecha de nacimiento</button>
							
						</div>
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Servicio<b style="color:red">*</b></label>
						<select id="servicios" name="servicios" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
									type : 'POST',
									url : 'cargarServicios',
									success : function(data, textStatus) {

										for (var i = 0; i < data.length; i++) {
											$("#servicios").append(
													// Append an object to the inside of the select box
													$("<option></option>") // Yes you can do this.
													.text(data[i].nombre).val(
															data[i].id));
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
									}
								});
							</script>
						</select>
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Patólogo asignado al caso<b style="color:red">*</b></label> 
						<select id="patologoAsignadoAlCaso" name="patologoAsignado" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
											type : 'POST',
											url : 'cargarPatologos',
											success : function(data, textStatus) {
												for (var i = 0; i < data.length; i++) {
													$("#patologoAsignadoAlCaso")
															.append($("<option></option>").text("Dr(a). "+ data[i].nombres+ " "+ data[i].apellidos).val(data[i].id));
												}
											},
											error : function(XMLHttpRequest,
													textStatus, errorThrown) {
											}
										});
							</script>
						</select>
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">IPS<b style="color:red">*</b></label> 
						<select id="ips" name="IPS" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
									type : 'POST',
									url : 'cargarIPS',
									success : function(data, textStatus) {
										for (var i = 0; i < data.length; i++) {
											$("#ips").append(
													$("<option></option>").text(data[i].razonSocial).val(data[i].id));
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
									}
								});
							</script>
						</select>
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">EPS<b style="color:red">*</b></label> 
						<select id="eps" name="EPS" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
									type : 'POST',
									url : 'cargarEPS',
									success : function(data, textStatus) {
										for (var i = 0; i < data.length; i++) {
											$("#eps").append($("<option></option>").text(data[i].nombre).val(data[i].id));
										}
									},
									error : function(XMLHttpRequest,textStatus, errorThrown) {
									}
								});
							</script>
						</select>
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Médico remitente<b style="color:red">*</b></label> 
						<select id="medicoRemitente" name="medicoRemitente" class="form-control">
							<option value="--" selected>--</option>
							<script>
								jQuery.ajax({
									type : 'POST',
									url : 'cargarMedicoR',
									success : function(data, textStatus) {
										for (var i = 0; i < data.length; i++) {
											$("#medicoRemitente").append(
													$("<option></option>").text(data[i].nombre).val(data[i].id));
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
									}
								});
							</script>
						</select>
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Material remitido<b style="color:red">*</b></label> 
						<input list="materiales" id="materialRemitido" class="form-control" name="materialRemitido"/>
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Departamento de residencia<b style="color:red">*</b></label> <select id="departamentoDeResidencia" name="departamentoDeResidencia" class="form-control">
							<option value="--" selected>--</option>
							<script>
								console.log("cargando departamentos");
								jQuery.ajax({
									type : 'POST',
									url : 'cargarDepartamentos',
									success : function(data, textStatus) {
										for (var i = 0; i < data.length; i++) {
											$("#departamentoDeResidencia").append($("<option></option>").text(data[i].departamento).val(data[i].departamento));
										}
									},
									error : function(XMLHttpRequest,textStatus, errorThrown) {
									}
								});
							</script>
						</select>
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Ciudad de residencia<b style="color:red">*</b></label>
						<select id="ciudadDeResidencia" name="ciudadDeResidencia" class="form-control">
							<option  value="--" selected>--</option>
						</select>
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Diagnóstico clínico<b style="color:red">*</b></label>
						<input list="dxClinicos" id="dxClinico" name="dxClinico" class="form-control"/>
						</br>
					</div>
				</div>
			</div>
	
			<div class="panel panel-pt-primary">
				<legend>Historia clínica</legend>
				<div class="row">
					<div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
						<label for="">Historia clínica</label>
						<textArea id="historiaClinica" class="form-control" style="resize:vertical;"></textArea>
					</div>
				</div>
			</div>


			<div class="panel panel-pt-primary">
				<legend>Datos especificos según el caso</legend>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label id="labelPatologoMacro" for="" style="display: block;">
							Patólogo asignado a la macro-descripción<b style="color:red">*</b>
						</label> 
						<select id="patologoMacro" name="patologoMacro" style="display: block;" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
											type : 'POST',
											url : 'cargarPatologos',
											success : function(data,textStatus) {
												for (var i = 0; i < data.length; i++) {
													$("#patologoMacro").append($("<option></option>").text("Dr(a). "+ data[i].nombres+ " "+ data[i].apellidos).val(data[i].id+ ", patologo"));
												}
											},
											error : function(XMLHttpRequest,textStatus, errorThrown){
											}
										});

								jQuery.ajax({
											type : 'POST',
											url : 'cargarResidentes',
											success : function(data,textStatus) {
												for (var i = 0; i < data.length; i++) {
													$("#patologoMacro").append($("<option></option>").text("Dr(a). "+ data[i].nombres+ " "+ data[i].apellidos).val(data[i].id+ ", residente"));
												}
											},
											error : function(XMLHttpRequest,textStatus, errorThrown){
											}
										});
							</script>
						</select>
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label id="labelPatologoMicro" for="" style="display: block;">
							Patólogo asignado a la micro-descripción<b style="color:red">*</b>
						</label> 
						<select id="patologoMicro" name="patologoMicro" style="display: block;" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
											type : 'POST',
											url : 'cargarPatologos',
											success : function(data,textStatus) {
												for (var i = 0; i < data.length; i++) {
													$("#patologoMicro").append($("<option></option>").text("Dr(a). "+ data[i].nombres+ " "+ data[i].apellidos).val(data[i].id+ ", patologo"));
												}
											},
											error : function(XMLHttpRequest,textStatus, errorThrown){
											}
										});

								jQuery.ajax({
											type : 'POST',
											url : 'cargarResidentes',
											success : function(data,textStatus) {
												for (var i = 0; i < data.length; i++) {
													$("#patologoMicro").append($("<option></option>").text("Dr(a). "+ data[i].nombres+ " "+ data[i].apellidos).val(data[i].id+ ", residente"));
												}
											},
											error : function(XMLHttpRequest,textStatus, errorThrown){
											}
										});
							</script>
						</select>
						<br/>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label id="labelCitologo" for="" style="display: none;">
						Citologo asignado al caso</label>
						<select id="citologo" name="citologo" style="display: none;" class="form-control">
							<option selected>--</option>
							<script>
								jQuery.ajax({
											type : 'POST',
											url : 'cargarCitologos',
											success : function(data,textStatus) {
												for (var i = 0; i < data.length; i++) {
													$("#citologo").append($("<option></option>").text("Dr(a). "+ data[i].nombres+ " "+ data[i].apellidos).val(data[i].id));
												}
											},
											error : function(XMLHttpRequest,textStatus, errorThrown) {
											}
										});
							</script>
						</select>
						<br/>
					</div>
				</div>
			</div>

			<div align="center">
				<button type="button" id="btnSave1" class="btn btn-lg btn-pt-primary " onclick="guardarCaso()">
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

	<datalist id="materiales">
		<g:each in="${materialRemitido}" var="material">
			<option value="${material.nombreDelMaterial}" >
		</g:each>
	</datalist>

	<datalist id="dxClinicos">
		<g:each in="${dxClinicos}" var="dxClinico">
			<option value="${dxClinico.nombre}" >
		</g:each>
	</datalist>

	<asset:javascript src="model/nuevoCaso.js"/>
	<asset:javascript src="model/validationSNuevoCaso.js"/>

			

	<script>
		$(window).load(function() {
			validar();
			revisarDocumento();

			$("#servicios").change(function() {
				validar();
			});
			
			$("#patologoAsignadoAlCaso").change(function() {
				validar();
			});
			
			$("#ips").change(function() {
				validar();
			});
			
			$("#eps").change(function() {
				validar();
			});
			

			$("#medicoRemitente").change(function() {
				validar();
			});
			
			$("#materialRemitido").change(function() {
				validar();
			});


			$("#departamentoDeResidencia").change(function() {
				validar();
				cargarCiudades();
			});
			
			$("#ciudadDeResidencia").change(function() {
				validar();
			});
			
			
			$("#dxClinico").change(function() {
				validar();
			});
			
			$("#patologoMacro").change(function() {
				validar();
			});
			
			$("#patologoMicro").change(function() {
				validar();
			});
			
			$("#citologo").change(function() {
				validar();
			});

			$("#tipoDeCaso").change(function() {

				validar();
				if (document.getElementById("tipoDeCaso").value == "Citologia") {
					document.getElementById("labelPatologoMacro").style.display = "none"
					document.getElementById("labelPatologoMicro").style.display = "none"
					document.getElementById("patologoMacro").style.display = "none"
					document.getElementById("patologoMicro").style.display = "none"
					document.getElementById("citologo").style.display = "block"
					document.getElementById("labelCitologo").style.display = "block"
				} else {
					document.getElementById("labelPatologoMacro").style.display = "block"
					document.getElementById("labelPatologoMicro").style.display = "block"
					document.getElementById("patologoMacro").style.display = "block"
					document.getElementById("patologoMicro").style.display = "block"
					document.getElementById("citologo").style.display = "none"
					document.getElementById("labelCitologo").style.display = "none"
				}
			});
		});
	</script>

	<script type="text/javascript">
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


	 $(document).ready(function() {
        $('#fechaDeRadicado').daterangepicker({
        	autoUpdateInput: true,
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
		        "firstDay": 1,
	    	},
        }, function(start, end, label) {
        	$("#fechaDeRadicado").val(start.format('MM/DD/YYYY'));
        });
      });
	</script>



</body>

</html>