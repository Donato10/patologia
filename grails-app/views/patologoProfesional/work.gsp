    <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
        <div class="row">
			<h1>Hoja de trabajo</h1>
				<table class="table table-bordered table-condensed">
					<thead >
						<th class="th-pt">Nº Caso</th>
						<th class="th-pt">Documento</th>
						<th class="th-pt">Paciente</th>
						<th class="th-pt">Material</th>
						<th class="th-pt">Tipo de caso</th>
						<th class="th-pt">Fecha de radicado</th>
						<th class="th-pt">Estado</th>
						<th class="th-pt">Acción</th>
					</thead>
					<tbody>
						<g:each in="${casos}" var="caso">
							<tr style="backgorund-color: ${caso.color}" >
								<td style="background-color: ${caso.color}">${caso.idCaso}</td>
								<td style="background-color: ${caso.color}">${caso.documento} </td>
								<td style="background-color: ${caso.color}">${caso.nombrePaciente} </td>
								<td style="background-color: ${caso.color}">${caso.material} </td>
								<td style="background-color: ${caso.color}">${caso.tipo}</td>
								<td style="background-color: ${caso.color}">${caso.fechaDeRadicado}</td>
								<td style="background-color: ${caso.color}">${caso.estado}</td>
								<td style="background-color: ${caso.color}">
									<g:if test='${caso.accion=="Macro"}'>
										<g:link controller="caso" action="macrodescribir" id="${caso.id}">
											<button class="btn btn-pt-primary"> Macrodescribir <i class="fa fa-pencil"></i></button>
										</g:link>
									</g:if>
									<g:elseif test='${caso.accion=="Micro"}'>
										<button class="btn btn-pt-primary"> Microdescribir <i class="fa fa-pencil"></i></button>
									</g:elseif>
									<g:elseif test='${caso.accion=="Alertar"}'>
										<button class="btn btn-pt-primary" onclick="showAlertModal(${caso.id}, '${caso.idCaso}')"> Enviar alerta <i class="fa fa-bell"></i></button>
									</g:elseif>
									<g:elseif test='${caso.accion=="Revisar"}'>
										<button class="btn btn-pt-primary"> Revisar <i class="fa fa-eye"></i></button>
									</g:elseif>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>  
			</div>        
        </div>
        <!-- /page content -->

        <!-- Modal de creación de alertas -->
		<div id="newAlertModal" class="modal fade" role="dialog">
		  <div class="modal-dialog">

		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header alert alert-pt-primary">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title" id="alert-modal-title"></h4>
		      </div>
		      <div class="modal-body">
		      	<label>Nivel de urgencia</label>
		      	<br/>
		      	<div class="btn-group" data-toggle="buttons" id="nivel">
					  <label class="btn btn-default nivel-btn active">
					    <input type="radio" autocomplete="off" icon-class="fa fa-flag" icon-color="green" checked nivel="1"> Bajo
					  </label>
					  <label class="btn nivel-btn btn-default">
					    <input  type="radio" autocomplete="off" icon-class="fa fa-exclamation-triangle" icon-color="orange" nivel="2"> Medio
					  </label>
					  <label class="btn nivel-btn btn-default">
					    <input type="radio" autocomplete="off" icon-class="fa fa-exclamation-circle" icon-color="red" nivel="3"> Alto
					  </label>
				</div>
				<i class="fa fa-flag" id="nivel-icon" style="color:green"></i>
				<br/>
				<br/>
				<label>Texto de la alerta</label>
				<textarea class="form-control" rezise="vertical" id="newAlertText"></textarea>
		      </div>
		      <div class="modal-footer">
		      	<button class="btn btn-pt-primary pull-right" onclick="crearAlerta()">Listo, enviar</button>
		      	<button class="btn btn-default pull-right" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
    <g:render template="/layouts/footer"/>

		<script type="text/javascript">
			var curCaseAlertId = null
			var nivel =1
			function showAlertModal(id, name){
				curCaseAlertId = id
				$("#alert-modal-title").text("Nueva alerta "+name)
				$('#newAlertModal').modal('show')
			}


			$('.nivel-btn').click(function(){
				$('#nivel-icon').removeClass()
				$('#nivel-icon').addClass($(this).find('input').attr('icon-class'))
				$('#nivel-icon').css('color', $(this).find('input').attr('icon-color'))
				nivel = $(this).find('input').attr('nivel')
			})

			function crearAlerta(){
				$('#newAlertModal').modal('hide')
				jQuery.ajax({
					type:'POST',
					data:{
						newAlertText:$("#newAlertText").val(),
						id:curCaseAlertId,
						nivel:nivel
					},
					url:"${createLink(controller:alert, action:crear)}",
					success:function(){
						alert("exitoso")
					},
					error:function(status){
						alert(status.responseText)
					}

				})
			}
		</script>
