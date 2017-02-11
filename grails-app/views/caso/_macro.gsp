
				    <div id="macrodescripcion" class="tab-pane fade in active" style="padding:15px">
					    <div class="panel panel-pt-primary">
							<h3>Descripción macroscópica</h3>
							<label>Nombre</label>
							<input type="text" list="macrodescripciones" class="form-control" id="macroClave" ${caso.estadoDelCaso=="Registrado"?"":"readonly"}/></br>
							<label>Descripción</label>
							<textarea id="macroDesc" class="form-control" rows="6" ${caso.estadoDelCaso=="Registrado"?"":"readonly"}>${caso.descripcionMacroscopica}</textarea>
							<br/>
							<div class="row">
								<div class="col-md-4 col-lg-4 col-sm-4 col-xs-12">
									<label style="border:none">
				                    	Se procesa todo
				                        <input id="seProcesaTodo" type="checkbox" class="ios-switch check-proceso-macro" name="seProcesaTodo" ${caso.seProcesoTodoElmaterial == null || caso.seProcesoTodoElmaterial ?"checked":""}/>
				                        <div class="switch"></div>
				                    </label>
								</div>
								<div class="col-md-4 col-lg-4 col-sm-4 col-xs-12">
				                    <label style="border:none">
				                    	Se procesa material representativo
				                        <input id="seProcesaMaterialRepresentativo" type="checkbox" class="ios-switch check-proceso-macro" name="seProcesaMaterialRepresentativo" ${caso.seProcesoTodoElmaterial==false ?"checked":""}/>
				                        <div class="switch"></div>
				                    </label>
			                    </div>
			                    <g:if test="${caso.idCaso.contains('-CT-') || caso.idCaso.contains('-CNG-')}">
			                    	<div class="col-md-4 col-lg-4 col-sm-4 col-xs-12">
										<label>Número de tubos</label>
										<input type="number" id="numeroDeTubos" min="0" value="${caso.numeroDeTubos?caso.numeroDeTubos:0}" />
									</div>
			                    </g:if>
			                    <g:if test="${caso.idCaso.contains('-CNG-') || caso.idCaso.contains('-QR-')}">
			                    	<div class="col-md-4 col-lg-4 col-sm-4 col-xs-12">
										<label>Número de laminas</label>
										<input type="number" id="numeroDeLaminas" min="0" value="${caso.numeroDeLaminas?caso.numeroDeLaminas:0}" />
									</div>
			                    </g:if>

			                    <g:if test="${caso.idCaso.contains('-QR-') || caso.idCaso.contains('-NE-') || caso.idCaso.contains('-CNG-')}">
									<div class="col-md-4 col-lg-4 col-sm-4 col-xs-12">
										<label>Número de bloques</label>
										<input type="number" id="numeroDeBloques" min="0" value="${caso.numeroDeBloques?caso.numeroDeBloques:0}"/>
									</div>
								</g:if>
								<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
									<label>Observaciones</label>
									<textarea id="observacionesMacro" class="form-control" rows="6" ${caso.estadoDelCaso=="Registrado"?"":"readonly"}>${caso.observacionesParaHistotecnologia}</textarea>
									<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12">
										<label class="pull-right">Patólogo responsable: ${caso.patologoResponsableDeLaMacroDescripcion.getNombreCompleto()}</label>
									</div>
									<div class="col-md-4 col-lg-4 col-sm-4 col-xs-12">
										<label style="border:none">
					                    	Caso prioritario
					                        <input id="casoPrioritario" type="checkbox" class="ios-switch" name="altaPrioridad" ${caso.casoPrioritario?"checked":""}/>
					                        <div class="switch"></div>
					                    </label>
									</div>
									<button class="btn btn-pt-primary pull-right" style="margin-top:10px" data-toggle="modal" data-target="#saveTypeModal">Guardar</button>
									<br/>
								</div>
							</div>
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

		            <!-- Modal de tipo de guardado -->
					<div id="saveTypeModal" class="modal fade" role="dialog">
					  <div class="modal-dialog">

					    <!-- Modal content-->
					    <div class="modal-content">
					      <div class="modal-header alert alert-pt-primary">
					        <button type="button" class="close" data-dismiss="modal">&times;</button>
					        <h4 class="modal-title">Por favor escoja una de las opciones</h4>
					      </div>
					      <div class="modal-body">
					      	<ul>
					      		<li><strong>Descripción finalizada:</strong> El proceso de descripción microscópica ha finalizado y el caso debe pasar a la siguiente etapa del proceso</li>
					      		<li><strong>Quiero seguir más tarde:</strong> El proceso de descripción microscópica aun no está completo, pero es importante guardar el avance actual para complementarlo posteriormente</li>
					      	</ul>
					      	
					      </div>
					      <div class="modal-footer">
					      	<button class="btn btn-pt-primary pull-right" onclick="finalizado=true; guardarMacroDescripcion()">Descripción finalizada</button><button class="btn btn-pt-primary pull-right" onclick="guardarMacroDescripcion()">Quiero seguir mas tarde</button>
					      </div>
					    </div>
					  </div>
					</div>
					<!--/ modal de tipo de guardado -->
				    <script type="text/javascript">

				    	var finalizado = false

				    	function guardarMacroDescripcion(){
				    		$("#saveTypeModal").modal("hide")
				    		var macrodescripcion = $("#macroDesc").val()
				    		var seProcesaTodo = $("#seProcesaTodo").is(":checked")?"true":"false"
				    		var materialRemitido = $("#materialRemitido").val()
				    		var dxClinico = $("#dxClinico").val()
				    		var historiaClinica = $("#historiaClinica").val()
				    		var observaciones = $("#observacionesMacro").val()
				    		var servicio = $("#servicios").val()
				    		var numeroDeBloques = $('#numeroDeBloques').val()
				    		var numeroDeTubos = $('#numeroDeTubos').val()
				    		var numeroDeLaminas = $('#numeroDeTubos').val()
				    		var casoPrioritario = $("#casoPrioritario").is(":checked")?"true":"false"

				    		jQuery.ajax({
										type : 'POST',
										url : '${createLink(controller:"caso", action:"guardarMacroDescripcion")}',
										data:{
											macrodescripcion: macrodescripcion,
											seProcesaTodo: seProcesaTodo,
											materialRemitido: materialRemitido,
											dxClinico:dxClinico,
											historiaClinica:historiaClinica,
											observaciones:observaciones,
											servicio:servicio,
											numeroDeBloques:numeroDeBloques,
			                   				numeroDeTubos:numeroDeTubos,
			                   				numeroDeLaminas:numeroDeLaminas,
			                   				casoPrioritario:casoPrioritario,
			                   				finalizado:finalizado,
											id:${caso.id}

										},
										success : function(data, textStatus) {
											$("#successMessage").html("Se guardó la macrodescripción correctamente")
											$("#successModal").modal("show")
											if(finalizado){
												$("#successModal").on("hidden.bs.modal", function(){
													window.location("${createLink(controller:'patologoProfesional', action:'work')}")
												})
											}
										},
										error : function(status, text, result, xhr) {
											$("#errorMessage").html(status.responseText)
											$("#errorModal").modal("show")
										}
									});
				    	}
				    </script>



				