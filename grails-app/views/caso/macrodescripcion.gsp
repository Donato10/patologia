 <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
       		<h1>${caso.idCaso}</h1>
       		<div class="row">
	    		<div class="panel panel-pt-primary">
					<legend>Datos del paciente</legend>

					<div class="row">
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Tipo de caso</label> <input type="text" id="documentoDeIdentidad" value="${caso.getTipo()}" class="form-control" readonly="">
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Fecha de radicado</label>
							<input required="" type="text" class="form-control" value="${caso.fechaDeRadicado.format('dd/MM/yyyy')}"  id="fechaDeRadicado" readonly="" />
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Documento de identidad</label> <input type="text" id="documentoDeIdentidad" value="${caso.paciente.numeroDeIdentificacion}" class="form-control" readonly="">
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Servicio<b style="color:red">*</b></label>
							<select id="servicios" name="servicios" class="form-control">
								<option selected>--</option>
								<script>
									jQuery.ajax({
										type : 'POST',
										url : '${createLink(controller:"caso", action:"cargarServicios")}',
										success : function(data, textStatus) {

											for (var i = 0; i < data.length; i++) {
												$("#servicios").append(
														// Append an object to the inside of the select box
														$("<option></option>") // Yes you can do this.
														.text(data[i].nombre).val(
																data[i].id));
											}

											$("#servicios").val(${caso.getServicio().id})
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
							<label for="">Patólogo asignado al caso</label> <input type="text" id="nombrePaciente" value="${caso.paciente.getNombre()}" class="form-control" readonly="">
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">IPS</label> <input type="text" id="ips" value="${caso.getIps().razonSocial}" class="form-control" readonly="" >
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">EPS</label> <input type="text" id="eps" value="${caso.getEps().nombre}" class="form-control" readonly="" >
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Médico remitente</label> <input type="text" id="nombrePaciente" value="${caso.getMedicoRemitente().nombre}" class="form-control" readonly="" >
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Material remitido<b style="color:red">*</b></label>
							<input required="" type="text" class="form-control" value="${caso.getMaterialRemitido()}" list="materiales" id="materialRemitido"  />
							<br/>
						</div>

						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Departamento de residencia</label>
							<input required="" type="text" class="form-control" value="${caso.getCiudad().departamento}"  id="deptResidencia" readonly="" />
							<br/>
						</div>


						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Ciudad de residencia</label> <input type="text" id="ciudadResidencia" value="${caso.getCiudad().ciudad}" class="form-control" readonly="">
							<br/>
						</div>

						
						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Diagnóstico clínico<b style="color:red">*</b></label>
							<input required="" type="text" class="form-control" value="${caso.getDiagnosticoClinico()}"  list="dxClinicos" id="dxClinico" />
							<br/>
						</div>


						<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
							<label for="">Edad</label>
							<input required="" type="text" class="form-control" value="${caso.paciente.calcularEdad()}"  id="edad" readonly="" />
							<br/>
						</div>
					</div>

					<div class="row">
					<label>Historia clínica</label>
						<textarea class="form-control" style="resize:vertical;" id="historiaClinica">${caso.getHistoriaClinica()}</textarea>
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
					<g:render template="macro"/>
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

		<datalist id="materiales">
		${materiales.size()}
			<g:each in="${materiales}" var="material">
				<option value="${material.nombreDelMaterial}" >
			</g:each>
		</datalist>

		<datalist id="dxClinicos">
			<g:each in="${dxClinicos}" var="dxClinico">
				<option value="${dxClinico.nombre}" >
			</g:each>
		</datalist>
        <!-- /page content -->

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





    <g:render template="/layouts/footer"/>

    
<script type="text/javascript">
	$("#macroClave").on("input", function(){
		$("#macroDesc").val(($('[value="'+$(this).val()+'"]').attr("descripcion")))
	});
	$("#microClave").on("input", function(){
		$("#microDesc").val(($('[value="'+$(this).val()+'"]').attr("descripcion")))
	});

	$(".check-proceso-macro").change(function(){
		if($(this).prop("checked")){
			$(".check-proceso-macro").not(this).each(function(){
				$(this).prop("checked", false)
			})
		}
		else{
			$(".check-proceso-macro").not(this).each(function(){
				$(this).prop("checked", true)
			})
		}

	})

</script>