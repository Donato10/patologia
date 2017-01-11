 <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
       		<h1>${caso.idCaso}</h1>
       		<div class="row">
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

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Fecha de nacimiento</label>
							<input required="" type="text" class="form-control" value="${caso.paciente.fechaDeNacimiento?.format('dd/MM/yyyy')}"  id="fechaDeNacimiento" />
							<br/>
						</div>
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Edad</label>
							<input required="" type="text" class="form-control" value="${caso.paciente.calcularEdad()}"  id="edad" />
							<br/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<ul class="nav nav-tabs">
				    <li class="active"><a data-toggle="tab" href="#macrodescripcion">Descripción macroscópica</a></li>
				    <li><a data-toggle="tab" href="#menu5">Solicitudes</a></li>
				    <li><a data-toggle="tab" href="#microdescripcion">Descripción microscópica</a></li>
				    <li><a data-toggle="tab" href="#menu3">Dx patológicos</a></li>
				    <li><a data-toggle="tab" href="#facturacion">Facturación y seguimiento</a></li>
				</ul>
			
				<div class="tab-content">
				    <div id="macrodescripcion" class="tab-pane fade in active" style="padding:15px">
						<button class="btn btn-pt-primary pull-right" style="margin-top:10px">Guardar</button>
					    <div class="panel panel-pt-primary">
							<h3>Descripción macroscópica</h3>
							<label>Nombre</label>
							<input type="text" list="macrodescripciones" class="form-control" id="macroClave" ${caso.estadoDelCaso=="Registrado"?"":"readonly"}/></br>
							<label>Descripción</label>
							<textarea id="macroDesc" class="form-control" rows="6" ${caso.estadoDelCaso=="Registrado"?"":"readonly"}></textarea>
							<br/>
							<div class="row">
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<label>Número de bloques</label>
									<input type="number" id="numeroDeBloques" min="1"/>
								</div>
								<div class="col-md-6 col-lg-6 col-sm-12 col-xs-12">
									<label class="pull-right">Patólogo responsable: ${caso.patologoResponsableDeLaMacroDescripcion.obtenerNombreCompleto()}</label>
								</div>
							</div>
							<br/>
							<label>Observaciones</label>
							<textarea id="ObservacionesMacro" class="form-control" rows="6" ${caso.estadoDelCaso=="Registrado"?"":"readonly"}></textarea>

					    </div>
				    </div>
				    <div id="menu5" class="tab-pane fade">
				      <h3>Solicitudes</h3>
				      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
				    </div>
				    <div id="microdescripcion" class="tab-pane fade">
				    	<button class="btn btn-pt-primary pull-right" style="margin-top:10px">Guardar</button>
					    <div class="panel panel-pt-primary">
							<h3>Descripción microscópica</h3>
							<label>Nombre</label>
							<input type="text" list="microdescripciones" class="form-control" id="microClave" ${caso.estadoDelCaso=="Procesado"?"":"readonly"}/></br>
							<label>Descripción</label>
							<textarea id="microDesc" class="form-control" rows="10" ${caso.estadoDelCaso=="Procesado"?"":"readonly"}></textarea>
					    </div>
				    </div>
				    <div id="menu3" class="tab-pane fade">
				      <h3>Dx patológicos</h3>
				      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
				    </div>
				    <div id="facturacion" class="tab-pane fade">
				      <h3>Dx patológicos</h3>
				      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
				    </div>
				</div>
			</div>
        </div>

        <datalist id="macrodescripciones">
			<g:each in="${macrodescripciones}" var="macro">
				<option value="${macro.nombreClave}" descripcion="${macro.descripcion}" >
		  	</g:each>
		</datalist>

		<datalist id="microdescripciones">
			<g:each in="${microdescripciones}" var="micro">
				<option value="${micro.nombreClave}" descripcion="${micro.descripcion}" >
		  	</g:each>
		</datalist>
        <!-- /page content -->
    <g:render template="/layouts/footer"/>
<script type="text/javascript">
	$("#macroClave").on("input", function(){
		$("#macroDesc").val(($('[value="'+$(this).val()+'"]').attr("descripcion")))
	});
	$("#microClave").on("input", function(){
		$("#microDesc").val(($('[value="'+$(this).val()+'"]').attr("descripcion")))
	});
</script>