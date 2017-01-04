    <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
        <div class="row">
			<h1>Hoja de trabajo</h1>
				<table class="table table-bordered table-condensed">
					<thead>
						<th class="th-pt">Id Caso</th>
						<th class="th-pt">Paciente</th>
						<th class="th-pt">Tipo de caso</th>
						<th class="th-pt">Fecha de radicado</th>
						<th class="th-pt">Estado</th>
						<th class="th-pt">Acción</th>
					</thead>
					<tbody>
						<g:each in="${casos}" var="caso">
							<tr style="backgorund-color: ${caso.color}">
								<td style="background-color: ${caso.color}">${caso.idCaso}</td>
								<td style="background-color: ${caso.color}">${caso.documento} </td>
								<td style="background-color: ${caso.color}">${caso.tipo}</td>
								<td style="background-color: ${caso.color}">${caso.fechaDeRadicado}</td>
								<td style="background-color: ${caso.color}">${caso.estado}</td>
								
								<td>
									<g:if test='${caso.accion=="Macro"}'>
										<button class="btn btn-pt-primary"> Macrodescribir</button>
									</g:if>
									<g:elseif test='${caso.accion=="Micro"}'>
										<button class="btn btn-warning"> Microdescribir</button>
									</g:elseif>
									<g:elseif test='${caso.accion=="Alertar"}'>
										<button class="btn btn-warning"> Enviar alerta</button>
									</g:elseif>
									<g:elseif test='${caso.accion=="Revisar"}'>
										<button class="btn btn-warning"> Revisar</button>
									</g:elseif>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>  
			</div>        
        </div>
        <!-- /page content -->
    <g:render template="/layouts/footer"/>
