

function guardarCaso() {

	var tipoDeCaso = document.getElementById("tipoDeCaso").value
	var documentoDeIdentidad = $('#documentoDeIdentidad').val();
	var servicio = document.getElementById("servicios").value
	var patologoAsignadoId = document.getElementById("patologoAsignadoAlCaso").value
	var ips = document.getElementById("ips").value
	var eps = document.getElementById("eps").value
	var medicoRemitente = document.getElementById("medicoRemitente").value
	var materialRemitido = document.getElementById("materialRemitido").value
	var departamentoDeResidencia = $('#departamentoDeResidencia option:selected').html()
	var ciudadDeResidencia = $('#ciudadDeResidencia option:selected').html()
	var dxClinico = document.getElementById("dxClinico").value
	var patologoMacro = document.getElementById("patologoMacro").value
	var patologoMicro = document.getElementById("patologoMicro").value
	var citologo = document.getElementById("citologo").value
	var historiaClinica = document.getElementById("historiaClinica").value
	var fechaDeRadicado = document.getElementById("fechaDeRadicado").value

		var nuevoCaso = {
			"tipoDeCaso" : tipoDeCaso,
			"numeroDeIdentificacion" : documentoDeIdentidad,
			"servicio" : servicio,
			"patologoAsignado" : patologoAsignadoId,
			"ips" : ips,
			"eps" : eps,
			"medicoRemitente" : medicoRemitente,
			"materialRemitido" : materialRemitido,
			"departamentoDeResidencia": departamentoDeResidencia,
			"ciudadDeResidencia" : ciudadDeResidencia,
			"dxClinico" : dxClinico,
			"patologoMicro": patologoMicro,
			"patologoMacro": patologoMacro,
			"citologo": citologo,
			"historiaClinica": historiaClinica,
			"fechaDeRadicado": fechaDeRadicado
		};

		console.log(nuevoCaso);
		console.log(ciudadDeResidencia);
		console.log(departamentoDeResidencia);

		/*
		 * Por medio de AJAX se logra llamar el metodo saveNewEvent de home y se
		 * le pasa newEvent como parametro para guardar el evento en la DB.
		 */

		jQuery.ajax({
			type : 'POST',
			data : nuevoCaso,
			url : "registrarCaso",
			async : true,
			success : function(data, textStatus) {
				$('#successMessage').html("Se registró el caso correctamente con el identificador <b>"+data+"</b>")
				$('#successModal').modal('show')
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {

			}
		});
}

function cargarCiudades() {
	var departamento = $('#departamentoDeResidencia option:selected').html();

	jQuery.ajax({
		type : 'POST',
		url : 'cargarCiudades',
		data : {
			"departamento" : departamento
		},
		success : function(data, textStatus) {
			document.getElementById("ciudadDeResidencia").options.length = 0;
			$("#ciudadDeResidencia").append(
			// Append an object to the inside of the select box
			$("<option></option>")
			// Yes you can do this.
			.text("--").val("--"));
			for (var i = 0; i < data.length; i++) {

				$("#ciudadDeResidencia").append(
				// Append an object to the inside of the select box
				$("<option></option>")
				// Yes you can do this.
				.text(data[i].ciudad).val(data[i].ciudad));
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});

}
