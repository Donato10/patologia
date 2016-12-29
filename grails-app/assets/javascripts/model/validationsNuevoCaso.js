var documentoVerificado = false;

function validar() {

	var btnSave = document.getElementById("btnSave1");

	var errorCounter = 0;
	var serviciosInput = document.getElementById("servicios");
	var patologoAsignadoInput = document.getElementById("patologoAsignadoAlCaso")
	var ipsInput = document.getElementById("ips")
	var epsInput = document.getElementById("eps")
	var medicoRemitenteInput = document.getElementById("medicoRemitente")
	var materialRemitidoInput = document.getElementById("materialRemitido")
	var departamentoDeResidenciaInput = document.getElementById("departamentoDeResidencia")
	var ciudadDeResidenciaInput = document.getElementById("ciudadDeResidencia")
	var dxClinicoInput = document.getElementById("dxClinico")
	var patologoMacroInput = document.getElementById("patologoMacro")
	var patologoMicroInput = document.getElementById("patologoMicro")
	var citologoInput = document.getElementById("citologo")
	var historiaClinicaInput = document.getElementById("historiaClinica")
	var fechaDeRadicadoInput = document.getElementById("fechaDeRadicado")
	
	
	
	
	if (document.getElementById("tipoDeCaso").value == "Citologia") {

		if (citologoInput.value == "--") {
			citologoInput.style.border = "1px solid #ff0000";
			errorCounter++;
		} else {
			citologoInput.style.border = "1px solid #cdc9c9";

		}

	} else {
		if (patologoMacroInput.value == "--") {
			patologoMacroInput.style.border = "1px solid #ff0000";
			errorCounter++;
		} else {
			patologoMacroInput.style.border = "1px solid #cdc9c9";

		}
		if (patologoMicroInput.value == "--") {
			patologoMicroInput.style.border = "1px solid #ff0000";
			errorCounter++;
		} else {
			patologoMicroInput.style.border = "1px solid #cdc9c9";

		}

	}

	if (serviciosInput.value == "--") {
		serviciosInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		serviciosInput.style.border = "1px solid #cdc9c9";

	}

	if (patologoAsignadoInput.value == "--") {
		patologoAsignadoInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		patologoAsignadoInput.style.border = "1px solid #cdc9c9";

	}

	if (ipsInput.value == "--") {
		ipsInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		ipsInput.style.border = "1px solid #cdc9c9";

	}

	if (epsInput.value == "--") {
		epsInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		epsInput.style.border = "1px solid #cdc9c9";

	}

	if (medicoRemitenteInput.value == "--") {
		medicoRemitenteInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		medicoRemitenteInput.style.border = "1px solid #cdc9c9";

	}

	if (materialRemitidoInput.value == "--") {
		materialRemitidoInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		materialRemitidoInput.style.border = "1px solid #cdc9c9";

	}

	if (departamentoDeResidenciaInput.value == "--") {
		departamentoDeResidenciaInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		departamentoDeResidenciaInput.style.border = "1px solid #cdc9c9";

	}

	if (ciudadDeResidenciaInput.value == "--") {
		ciudadDeResidenciaInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		ciudadDeResidenciaInput.style.border = "1px solid #cdc9c9";

	}

	if (dxClinicoInput.value == "--") {
		dxClinicoInput.style.border = "1px solid #ff0000";
		errorCounter++;
	} else {
		dxClinicoInput.style.border = "1px solid #cdc9c9";

	}

	if (errorCounter == 0 && documentoVerificado) {
		btnSave.disabled = false;
	} else
		btnSave.disabled = true;

	console.log(errorCounter)

}



function revisarFechaDeNacimiento() {

	var documento = document.getElementById("documentoDeIdentidad").value

	jQuery.ajax({
		type : 'POST',
		data : {
			"numeroDeDocumento" : documento
		},
		url : "revisarFechaDeNacimiento",
		async : true,
		success : function(data, textStatus) {

			if (data == "Error") {
				alert("Ha ocurrido un error");
			} else if (data == "True") {
				document.getElementById("edadYFechaBlock").style.display = "none"
				console.log("el paciente tiene fecha de nacimiento")
			} else if (data == "False"){
				console.log(data);
				console.log("se debe ingresar la fecha de nacimiento")
				document.getElementById("edadYFechaBlock").style.display = "block"
				
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {

		}
	});
}


function habilitarMeses() {

	if (document.getElementById("anios").value != "") {
		if (document.getElementById("anios").value < 5
				&& document.getElementById("anios").value >= 1) {

			document.getElementById("meses").style.display = "block"
			document.getElementById("labelMeses").style.display = "block"

			document.getElementById("dias").style.display = "none"
			document.getElementById("labelDias").style.display = "none"

		} else if (document.getElementById("anios").value == 0) {

			document.getElementById("meses").style.display = "block"
			document.getElementById("labelMeses").style.display = "block"

			document.getElementById("dias").style.display = "block"
			document.getElementById("labelDias").style.display = "block"

		} else if (document.getElementById("anios").value >= 5) {

			document.getElementById("meses").style.display = "none"
			document.getElementById("labelMeses").style.display = "none"

			document.getElementById("dias").style.display = "none"
			document.getElementById("labelDias").style.display = "none"
		}
	} else {
		document.getElementById("meses").style.display = "none"
		document.getElementById("labelMeses").style.display = "none"

		document.getElementById("dias").style.display = "none"
		document.getElementById("labelDias").style.display = "none"

	}

}


function cambiarAEdad() {
	document.getElementById("edadBlock").style.display = "block"
	document.getElementById("fechaDeNacimientoBlock").style.display = "none"
	document.getElementById("labelFechaDeNacimiento").style.display = "none"
}

function cambiarAFechaDeNacimiento() {
	document.getElementById("edadBlock").style.display = "none"
	document.getElementById("fechaDeNacimientoBlock").style.display = "block"
	document.getElementById("labelFechaDeNacimiento").style.display = "block"
}

function revisarDocumento() {
	revisarFechaDeNacimiento();
	var suc = false;
	var documentInput = document.getElementById("documentoDeIdentidad");
	var documentNumber = document.getElementById("documentoDeIdentidad").value;
	if (documentNumber == "") {
		documentInput.style.border = "1px solid #ff0000";
		documentoVerificado = false
		return false;
	}

	jQuery.ajax({

		type : 'POST',
		data : {
			"numeroDeDocumento" : documentNumber
		},
		url : "verificarDocumento",
		async : false,
		success : function(data, textStatus) {
			if (data == "Error")
				alert("Ha ocurrido un error");
			if (data == "True")
				suc = true;
			if (data == "False")
				suc = false;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});

	if (suc) {
		documentInput.style.border = "1px solid #cdc9c9";
		documentoVerificado= true
		validar()
		return true;
	} else {
		documentInput.style.border = "1px solid #ff0000";
		documentoVerificado = false
		validar()
		return false;

	}

}

