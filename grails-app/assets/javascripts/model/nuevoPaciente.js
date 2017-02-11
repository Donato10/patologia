	function guardarPaciente() {
		$("#btnSave1").html('<i class="fa fa-spin fa-spinner"></i>')
		$("#btnSave1").prop('disabled', true);
		if (document.getElementById("fechaDeNacimiento").value.trim() == ""){
			var anularFecha = true
		}
		else{
			var anularFecha = false
		}
		var numero_de_identificacion = $('#numeroDeIdentificacion').val().trim();
		var primer_nombre = $('#primerNombre').val().trim();
		var segundo_nombre = $('#segundoNombre').val().trim();
		var primer_apellido = $('#primerApellido').val().trim();
		var segundo_apellido = $('#segundoApellido').val().trim();
		var fecha_de_nacimiento = $('#fechaDeNacimiento').val().trim();
		var tipo_de_id = $('#tipoDeId option:selected').html().trim();
		var regimen = $('#regimen option:selected').html().trim();
		var estadoCivil = $('#estadoCivil option:selected').html().trim();
		var sexo = $('#sexo option:selected').html().trim();
		var profesion = $('#profesion').val().trim();
		var direccion = $('#direccion').val().trim();
		var telefono_fijo = $('#telefonoFijo').val().trim();
		var telefono_celular = $('#telefonoCelular').val().trim();
		var email = $('#email').val().trim();
		var observaciones = $('#comentarios').val().trim();

		if (primer_nombre != "" && primer_apellido != ""
				 && tipo_de_id != '--'
				&& regimen != '--' && estadoCivil!="--" && sexo!="--"  ) {


			var nuevoPaciente = {
				"anularFecha" : anularFecha,
				"numeroDeIdentificacion" : numero_de_identificacion,
				"primerNombre" : primer_nombre,
				"segundoNombre" : segundo_nombre,
				"primerApellido" : primer_apellido,
				"segundoApellido" : segundo_apellido,
				"fechaDeNacimiento" : fecha_de_nacimiento,
				"tipoDeId" : tipo_de_id,
				"regimen" : regimen,
				"estadoCivil" : estadoCivil,
				"sexo" : sexo,
				"profesion" : profesion,
				"direccion" : direccion,
				"telefonoFijo" : telefono_fijo,
				"telefonoCelular" : telefono_celular,
				"email" : email,
				"observaciones" : observaciones
			};


			
			jQuery.ajax({
				type : 'POST',
				data : nuevoPaciente,
				url : "registrarPaciente",
				async : true,
				success : function(data, textStatus) {
					$("#btnSave1").html('Listo, guardar')
					$("#btnSave1").prop('disabled', false);
					$('#successMessage').html("El paciente se ha registrado exitosamente!")
					$('#successModal').modal('show')
					
				},
				error : function(status, text, result, xhr) {
					$("#btnSave1").html('Listo, guardar')
					$("#btnSave1").prop('disabled', false);
					$('#errorMessage').html(status.responseText)
					$('#errorModal').modal('show')
				}
			});
		} else {
			$("#btnSave1").html('Listo, guardar')
			$("#btnSave1").prop('disabled', false);
			$('#errorMessage').html('Debes completar todos los campos marcados con <b style="color:red">*</b>')
			$('#errorModal').modal('show')

		}
	}





