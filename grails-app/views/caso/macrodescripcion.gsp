 <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
    		 <div class="panel panel-pt-primary">
				<legend>Datos del paciente</legend>

				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Documento de identidad<b style="color:red">*</b></label> <input type="text" id="documentoDeIdentidad" value="${caso.paciente.numeroDeIdentificacion}" class="form-control">
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Apellidos, nombres<b style="color:red">*</b></label> <input type="text" id="nombrePaciente" value="${caso.paciente.getNombre()}" class="form-control">
						<br/>
					</div>

					
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Fecha de radicado<b style="color:red">*</b></label>
						<input required="" type="text" class="form-control" value="${caso.fechaDeRadicado.format('dd/MM/yyyy')}"  id="fechaDeRadicado" />
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
						
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Patólogo asignado al caso<b style="color:red">*</b></label> 
						
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">IPS<b style="color:red">*</b></label> 
						
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">EPS<b style="color:red">*</b></label> 
						
						<br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Médico remitente<b style="color:red">*</b></label> 
						
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Material remitido<b style="color:red">*</b></label> 
						
						</br>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label for="">Departamento de residencia<b style="color:red">*</b></label> 
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
						
						</br>
					</div>
				</div>
			</div>
        </div>
        <!-- /page content -->
    <g:render template="/layouts/footer"/>
