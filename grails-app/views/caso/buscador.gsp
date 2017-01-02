	    <%@page defaultCodec="none" %>
	    <asset:stylesheet src="bootstrap-daterangepicker/daterangepicker.css"/>
	    <g:render template="/layouts/navbar"/>
        <!-- page content -->
        <div class="right_col" role="main">
        <h1>Buscador de casos </h1>
	        <div class="panel panel-pt-primary">
				<legend>Ingrese los datos para la busqueda y de click en buscar</legend>
				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input type="text" id="numeroDeIdentificacion" class="form-control" placeholder="Número de identificación"><br/>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input type="text" id="idCaso" class="form-control" placeholder="Id caso"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input required=""  type="text" class="form-control nombre"  placeholder="Apellido 1" id="apellido1"/><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input type="text" id="apellido2" class="form-control nombre" placeholder="Apellido 2"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input type="text" id="nombre1" class="form-control nombre" placeholder="Nombre 1"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input type="text" id="dxclinico" class="form-control nombre" placeholder="Diagnóstico clínico"><br/>
					</div>

					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<input type="text" id="patologo" class="form-control nombre" placeholder="Patólogo"><br/>
					</div>
				</div>

				<div class="row">
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12">
						<label>Rango de fechas</label><input type="text" id="rango" class="form-control"><br/>
					</div>
					<div class="col-lg-6 col-md-6 col-xs-12 col-sm-12" style="padding-top: 15px">
						<button type="button" id="btnSave1" class="btn btn-lg btn-pt-primary " onclick="buscar()">
							Buscar
						</button>
					</div>
				</div>
				<br/>
			</div>	
			<div class="row">
				<table class="table table-bordered table-condensed table-pt-primary" id="resultsTable">
					<thead>
						<th>Id caso</th>
						<th>Tipo</th>
						<th>Paciente</th>
						<th>Fecha de radicado</th>
						<th>Responsable</th>
						<th>Estado</th>

					</thead>
					<tbody>
						
					</tbody>
				</table>
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

	<asset:javascript src="bootstrap-daterangepicker/daterangepicker.js"/>
    <script type="text/javascript">
    	
    $(function() {

    var start = moment().subtract(29, 'days');
    var end = moment();

    function cb(start, end) {
        $('#rango span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
    }

    $('#rango').daterangepicker({
        startDate: start,
        endDate: end,
        ranges: {
           'Hoy': [moment(), moment()],
           'Ayer': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Últimos 7 días': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'Últimos 30 días': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    }, cb);

    cb(start, end);
});

     function buscar(){
    	var numeroDeIdentificacion = $('#numeroDeIdentificacion').val().trim()
    	var primerNombre = $('#nombre1').val().trim()
    	var primerApellido = $('#apellido1').val().trim()
    	var patologo = $('#patologo').val().trim()
    	var dxClinico = $('#dxclinico').val().trim()

    	jQuery.ajax({
				type : 'POST',
				data : {
					documentoDeIdentificacion:numeroDeIdentificacion,
					primerNombre:primerNombre,
					primerApellido:primerApellido,
					patologo:patologo,
					dxClinico:dxClinico
				},
				url : "buscadorDeCasos",
				async : true,
				success : function(data, textStatus) {
					console.log(data)
					$("#btnSave1").html('Buscar')
					$("#btnSave1").prop('disabled', false);
					$('#resultsTable > tbody').empty()
					for(var i=0; i < data.length;i++){
						$('#resultsTable > tbody').append($('<tr><td>'+data[i].idCaso+'</td><td>'+data[i].tipo+'</td><td>'+data[i].paciente+'</td><td>'+data[i].fechaDeIngreso+'</td><td>'+data[i].responsable+'</td><td>'+data[i].estado+'</td></tr>'))
					}
					
				},
				error : function(status, text, result, xhr) {
					$("#btnSave1").html('Buscar')
					$("#btnSave1").prop('disabled', false);
					$('#errorMessage').html(status.responseText)
					$('#errorModal').modal('show')
				}
			});
    }
</script>