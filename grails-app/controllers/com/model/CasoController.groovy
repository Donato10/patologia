package com.model
import com.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON
import java.text.SimpleDateFormat
import java.text.DateFormat


@Secured(["ROLE_SECRETARIA", 'ROLE_PATOLOGO'])
class CasoController {

    def index() { }


    def nuevoPaciente(){
		render view:'nuevoPaciente', model:[today:new Date()]
	}

	def nuevoCaso(){
		def materialRemitido = MaterialRemitido.list(sort:"nombreDelMaterial", order:"asc")
		println materialRemitido.size()
		def dxClinicos = MoldeDiagnosticoClinico.list(sort:"nombre", order:"asc")
		render view:'nuevoCaso', model:[today:new Date(), materialRemitido:materialRemitido, dxClinicos:dxClinicos]
	}

	def registrarPaciente(){
			println params
			Paciente p = Paciente.findByNumeroDeIdentificacion(params.numeroDeIdentificacion)
			if(p){
				println "lo encontró"
				response.setCharacterEncoding("UTF-8");
				render status:400, text:"Ya existe un paciente registrado con el documento <b>${params.numeroDeIdentificacion}</b>"
				return
			}
			def errors = "<ul>"
			if( params.anularFecha== "true"){
				System.out.println("se debe anular la fecha")
				Paciente nuevoPaciente = new Paciente(
					numeroDeIdentificacion : params.numeroDeIdentificacion?params.numeroDeIdentificacion.trim().toUpperCase():"",
					regimenDeSalud : params.regimen?params.regimen.trim():"",
					primerNombre: params.primerNombre?params.primerNombre.trim():"",
					segundoNombre: params.segundoNombre?params.segundoNombre.trim():"",
					primerApellido: params.primerApellido?params.primerApellido.trim():"",
					segundoApellido: params.segundoApellido?params.segundoApellido.trim():"",
					direccion: params.direccion?params.direccion.trim():"",
					tipoDeIdentificacion: params.tipoDeId,
					observaciones: params.observaciones?params.observaciones.trim():"",
					telefonoCelular: params.telefonoCelular?params.telefonoCelular.trim():"",
					telefonoFijo: params.telefonoFijo?params.telefonoFijo.trim():"",
					email: params.email!=null?params.email.trim():"",
					fechaDeNacimiento:  null,
					estadoCivil: params.estadoCivil,
					sexo: params.sexo,
					profesion : params.profesion?params.profesion:"" )
			if(!nuevoPaciente.validate()){
				def fields = grailsApplication.getDomainClass('com.model.Paciente').persistentProperties.collect { it.name }
	    		for(field in fields){
	    			if(message(error: nuevoPaciente.errors.getFieldError(field))!="")
	    				errors+= "<li>"+message(error: nuevoPaciente.errors.getFieldError(field))+"</li>"
	    		}
	    		errors+="</ul>"
	    		render status:400, text:errors
	    		return
			} 
			nuevoPaciente.save(flush: true, failOnError:true)
			render status:200
			return
			}
			else{
				println params.fechaDeNacimiento
				DateFormat sdf =  new SimpleDateFormat('dd/MM/yyyy')
				Date fechaDeNacimiento = sdf.parse(params.fechaDeNacimiento.trim())
				println fechaDeNacimiento.toString()+"******"
				Paciente nuevoPaciente = new Paciente(
						numeroDeIdentificacion : params.numeroDeIdentificacion?params.numeroDeIdentificacion.trim():"",
						regimenDeSalud : params.regimen?params.regimen.trim():"",
						primerNombre: params.primerNombre?params.primerNombre.trim():"",
						segundoNombre: params.segundoNombre?params.segundoNombre.trim():"",
						primerApellido: params.primerApellido?params.primerApellido.trim():"",
						segundoApellido: params.segundoApellido?params.segundoApellido.trim():"",
						direccion: params.direccion?params.direccion.trim():"",
						tipoDeIdentificacion: params.tipoDeId,
						observaciones: params.observaciones?params.observaciones.trim():"",
						telefonoCelular: params.telefonoCelular?params.telefonoCelular.trim():"",
						telefonoFijo: params.telefonoFijo?params.telefonoFijo.trim():"",
						email: params.email!=null?params.email.trim():"",
						fechaDeNacimiento:  Date.parse('dd/MM/yyyy', params.fechaDeNacimiento),
						estadoCivil: params.estadoCivil,
						sexo: params.sexo,
						profesion : params.profesion )
				if(!nuevoPaciente.validate()){
					def fields = grailsApplication.getDomainClass('com.model.Paciente').persistentProperties.collect { it.name }
		    		for(field in fields){
		    			if(message(error: nuevoPaciente.errors.getFieldError(field))!="")
		    				errors+= "<li>"+message(error: nuevoPaciente.errors.getFieldError(field))+"</li>"
		    		}
		    		errors+="</ul>"
		    		render status:400, text:errors
		    		return
				} 
				nuevoPaciente.save(flush: true, failOnError:true)
				render status:200
				return
			}
			println params
	}


	def cargarServicios(){
		render Servicio.list(sort:"nombre", order:"asc") as JSON
	}

	def cargarPatologos(){
		render PatologoProfesional.list(sort:"nombres", order:"asc") as JSON
	}

	def cargarIPS(){
		render IPS.list(sort:"razonSocial", order:"asc") as JSON
	}

	def cargarEPS(){
		render EPS.list(sort:"nombre", order:"asc") as JSON
	}

	def cargarDepartamentos(){
		render Ciudad.getAll().unique{ it.departamento }.sort{it.departamento} as JSON
	}

	def cargarCiudades(){
		render Ciudad.findAllByDepartamento(params.departamento) as JSON
	}

	def cargarMedicoR(){
		render MedicoRemitente.list(sort:"nombre", order:"asc") as JSON
	}

	def cargarMaterialRemitido(){
		render MaterialRemitido.list(sort:"nombreDelMaterial", order:"asc") as JSON
	}

	def cargarDxClinicos(){
		render MoldeDiagnosticoClinico.list(sort:"nombre", order:"asc") as JSON
	}

	def cargarResidentes(){
		render Residente.list(sort:"nombres", order:"asc") as JSON
	}

	def cargarCitologos(){
		render Citologo.list(sort:"nombres", order:"asc") as JSON
	}

	def registrarCaso(){
		
		Paciente p
		Servicio servicio
		IPS ips
		EPS eps
		MedicoRemitente medicoRemitente
		String historiaClinica
		Ciudad ciudad
		Date fechaDeRadicado
		String materialRemitido
		String diagnosticoClinico
		PatologoProfesional patologoAsignado
		String estadoDelCaso = "Registrado"
		Patologo patologoMicro
		Patologo patologoMacro
		Citologo citologo
		String idCaso
		String errors ="<ul>"


		try{
			if(params.citologo.equals("--") && params.tipoDeCaso.equals("Citologia")){
				errors+="<li>Debe escoger un citologo</li>"
			}
			if(!params.citologo.equals("--")){
				citologo= Citologo.get(params.citologo)
			}
			p = Paciente.findByNumeroDeIdentificacion(params.numeroDeIdentificacion)
			servicio = Servicio.get(params.servicio)
			ips = IPS.get(params.ips)
			eps = EPS.get(params.eps)
			medicoRemitente = MedicoRemitente.get(params.medicoRemitente)
			materialRemitido = params.materialRemitido
			historiaClinica = params.historiaClinica
			ciudad = Ciudad.findByCiudadAndDepartamento(params.ciudadDeResidencia, params.departamentoDeResidencia)
			fechaDeRadicado = Date.parse('dd/MM/yyyy',params.fechaDeRadicado)
			diagnosticoClinico = params.dxClinico
			patologoAsignado = PatologoProfesional.get(params.patologoAsignado)
			estadoDelCaso = "Registrado"
			if(params.patologoMicro.equals("--") && !params.tipoDeCaso.equals("Citologia")){
				errors+="<li>Debe escoger un patólogo para la microdescripción</li>"
			}
			if(!params.patologoMicro.equals("--") ){
				if(params.patologoMicro.contains("residente")){
					patologoMicro = Residente.get(params.patologoMicro.substring(0,params.patologoMicro.indexOf(",")))
				}
				else{
					patologoMicro = PatologoProfesional.get(params.patologoMicro.substring(0,params.patologoMicro.indexOf(",")))
				}
			}
			if(params.patologoMacro.equals("--") && !params.tipoDeCaso.equals("Citologia")){
				errors+="<li>Debe escoger un patólogo para la macrodescripción</li>"
			}
			if(!params.patologoMacro.equals("--")){
				if(params.patologoMacro.contains("residente")){
					patologoMacro = Residente.get(params.patologoMacro.substring(0,params.patologoMacro.indexOf(",")))
				}
				else{

					patologoMacro = PatologoProfesional.get(params.patologoMacro.substring(0,params.patologoMacro.indexOf(",")))
				}
			}
		}
		catch(Exception e){
			render status:400, text:e.getMessage()
			return
		}

		if(params.tipoDeCaso.equals("Citologia")){
			def nuevaCitologia
			try{
				nuevaCitologia = new Citologia(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1", citologo: citologo)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				def lastCitologia = Citologia.list(sort:"id", order:"desc", max:1)? Citologia.list(sort:"id", order:"desc", max:1)[0]:null
				def consecutivo = lastCitologia?Integer.parseInt(lastCitologia.idCaso.substring(lastCitologia.idCaso.lastIndexOf("-")+1))+1:"1"
				idCaso = ips.sigla.trim()+"-CG-"+ year+ "-" + consecutivo
				nuevaCitologia.setIdCaso(idCaso)
				if(!nuevaCitologia.validate() || errors!="<ul>"){
					def fields = grailsApplication.getDomainClass('com.model.Citologia').persistentProperties.collect { it.name }
		    		for(field in fields){
		    			if(message(error: nuevaCitologia.errors.getFieldError(field))!="")
		    				errors+= "<li>"+message(error: nuevaCitologia.errors.getFieldError(field))+"</li>"
		    		}
		    		errors+="</ul>"
					render status:400, text:errors
					return
				}
				nuevaCitologia.save(flush:true, failOnError:true)
				p.setEdad(params.edad?params.edad:null)
			}
			catch(Exception e){
				render status:400, text:e.getMessage()
				return
			}
			render nuevaCitologia.idCaso
			return
		}
		if(params.tipoDeCaso.equals("Citometria")){
			def nuevaCitometria
			try{

				nuevaCitometria = new Citometria(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				def lastCitometria = Citometria.list(sort:"id", order:"desc", max:1)? Citometria.list(sort:"id", order:"desc", max:1)[0]:null
				def consecutivo = lastCitometria?Integer.parseInt(lastCitometria.idCaso.substring(lastCitometria.idCaso.lastIndexOf("-")+1))+1:"1"
				idCaso = ips.sigla.trim()+"-CT-"+ year+ "-" + consecutivo
				nuevaCitometria.setIdCaso(idCaso)
				if(!nuevaCitometria.validate() || errors!="<ul>"){
					def fields = grailsApplication.getDomainClass('com.model.Citometria').persistentProperties.collect { it.name }
		    		for(field in fields){
		    			if(message(error: nuevaCitometria.errors.getFieldError(field))!="")
		    				errors+= "<li>"+message(error: nuevaCitometria.errors.getFieldError(field))+"</li>"
		    		}
		    		errors+="</ul>"
					render status:400, text:errors
					return
				}
				nuevaCitometria.save(flush:true, failOnError:true)
				p.setEdad(params.edad?params.edad:null)

			}
			catch(Exception e){
				render status:400, text:e.getMessage()
				return
			}
			render nuevaCitometria.idCaso
			return
		}
		if(params.tipoDeCaso.equals("Quirurgico")){
			def nuevoQuirurgico
			try{
				nuevoQuirurgico = new Quirurgico(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				def lastQuirurgico = Quirurgico.list(sort:"id", order:"desc", max:1)? Quirurgico.list(sort:"id", order:"desc", max:1)[0]:null
				def consecutivo = lastQuirurgico?Integer.parseInt(lastQuirurgico.idCaso.substring(lastQuirurgico.idCaso.lastIndexOf("-")+1))+1:"1"
				idCaso = ips.sigla.trim()+"-QR-"+ year+ "-" + consecutivo
				nuevoQuirurgico.setIdCaso(idCaso)
				if(!nuevoQuirurgico.validate() || errors!="<ul>"){
					def fields = grailsApplication.getDomainClass('com.model.Quirurgico').persistentProperties.collect { it.name }
		    		for(field in fields){
		    			if(message(error: nuevoQuirurgico.errors.getFieldError(field))!="")
		    				errors+= "<li>"+message(error: nuevoQuirurgico.errors.getFieldError(field))+"</li>"
		    		}
		    		errors+="</ul>"
					render status:400, text:errors
					return
				}
				nuevoQuirurgico.save(flush:true, failOnError:true)
				p.setEdad(params.edad?params.edad:null)

			}
			catch(Exception e){
				render status:400, text:e.getMessage()
				return
			}
			render nuevoQuirurgico.idCaso
			return
		}
		if(params.tipoDeCaso.equals("CitologiaN")){
			println "Entra a citoN"
			def nuevaCitologiaN
			try{
				nuevaCitologiaN = new CitologiaN(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				def lastCitologiaN = CitologiaN.list(sort:"id", order:"desc", max:1)? CitologiaN.list(sort:"id", order:"desc", max:1)[0]:null
				def consecutivo = lastCitologiaN?Integer.parseInt(lastCitologiaN.idCaso.substring(lastCitologiaN.idCaso.lastIndexOf("-")+1))+1:"1"
				idCaso = ips.sigla.trim()+"-CNG-"+ year+ "-" + consecutivo
				nuevaCitologiaN.setIdCaso(idCaso)
				if(!nuevaCitologiaN.validate() || errors!="<ul>"){
					def fields = grailsApplication.getDomainClass('com.model.CitologiaN').persistentProperties.collect { it.name }
		    		for(field in fields){
		    			if(message(error: nuevaCitologiaN.errors.getFieldError(field))!="")
		    				errors+= "<li>"+message(error: nuevaCitologiaN.errors.getFieldError(field))+"</li>"
		    		}
		    		errors+="</ul>"
					render status:400, text:errors
					return
				}
				nuevaCitologiaN.save(flush:true, failOnError:true)
				p.setEdad(params.edad?params.edad:null)

			}
			catch(Exception e){
				render status:400, text:e.getMessage()
				return 
			}
			render nuevaCitologiaN.idCaso
			return
		}
		if(params.tipoDeCaso.equals("Necropsia")){
			def nuevaNecropsia
			try{

				nuevaNecropsia = new Necropsia(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				def lastNecropsia = Necropsia.list(sort:"id", order:"desc", max:1)? Necropsia.list(sort:"id", order:"desc", max:1)[0]:null
				def consecutivo = lastNecropsia?Integer.parseInt(lastNecropsia.idCaso.substring(lastNecropsia.idCaso.lastIndexOf("-")+1))+1:"1"
				idCaso = ips.sigla.trim()+"-NE-"+ year+ "-" +consecutivo
				nuevaNecropsia.setIdCaso(idCaso)
				if(!nuevaNecropsia.validate() || errors!="<ul>"){
					def fields = grailsApplication.getDomainClass('com.model.Necropsia').persistentProperties.collect { it.name }
		    		for(field in fields){
		    			if(message(error: nuevaNecropsia.errors.getFieldError(field))!="")
		    				errors+= "<li>"+message(error: nuevaNecropsia.errors.getFieldError(field))+"</li>"
		    		}
		    		errors+="</ul>"
					render status:400, text:errors
					return
				}
				nuevaNecropsia.save(flush:true, failOnError:true)
				p.setEdad(params.edad?params.edad:null)
			}
			catch(Exception e){
				render status:400, text:e.getMessage()
				return
			}
			render nuevaNecropsia.idCaso
			return
		}
	}

	def revisarFechaDeNacimiento(){
		Paciente p = Paciente.findByNumeroDeIdentificacion( params.numeroDeDocumento)

		if(p!=null){
			if(p.fechaDeNacimiento!=null)
			{
				render "True"
				return
			}
			else{
				System.out.println("el paciente no tiene fecha de nacimiento y debe ingresarla o ingresar la edad")
				render "False"
				return
			}
		}
		else{
			System.out.println("el paciente ni siquiera existe")
			render "False"
			return
		}
	}

	def verificarDocumento(){

		Paciente p = Paciente.findByNumeroDeIdentificacion( params.numeroDeDocumento)
		if(p!=null){
			System.out.println("el paciente ya existe")
			render "True"
			return
		}
		else{
			System.out.println("el paciente no existe")
			render "False"
			return
		}
	}

	def parseDate(String d){

		def t = d.split("-")
		int day = Integer.parseInt(t[0])
		int month = Integer.parseInt(t[1])-1
		int year = Integer.parseInt(t[2])

		Calendar date = Calendar.getInstance();
		date.set(Calendar.DAY_OF_MONTH, day);
		date.set(Calendar.MONTH, month);
		date.set(Calendar.YEAR, year);

		return date.getTime()
	}

	def buscador() {
		render view:"buscador"
	}

	def buscadorDeCasos() {
		println params
		def results = []
    	def fechaInicial = params.rango?Date.parse('dd/MM/yyyy', params.rango.split(" - ")[0]):new Date()
    	def fechaFinal = params.rango?Date.parse('dd/MM/yyyy', params.rango.split(" - ")[1]).plus(1):new Date()
		def pacientes = Paciente.findAllByNumeroDeIdentificacionLikeAndPrimerApellidoLikeAndPrimerNombreLikeAndSegundoApellidoLike(params.documentoDeIdentificacion?params.documentoDeIdentificacion.toUpperCase()+"%":'%', params.primerApellido?params.primerApellido.toUpperCase()+"%":'%', params.primerNombre?params.primerNombre.toUpperCase()+"%":'%', params.segundoApellido?params.segundoApellido.toUpperCase()+"%":'%')
		//def pacientes = Paciente.findAllByNumeroDeIdentificacionLikeAndPrimerApellidoLike('%', '%', '%')
		println pacientes
		if(pacientes.size()==0){
			render results as JSON
			return
		}

		def patologos = PatologoProfesional.findAllByNombresLikeOrApellidosLike(params.patologo?params.patologo.trim()+"%":"%", params.patologo?params.patologo.trim()+"%":"%")
		println "patologos: "+ patologos

		println "Fechas_: "+ (new Date() >= fechaInicial)+", "+ (new Date() <= fechaFinal)
		println "Inicial: "+fechaInicial +", Final:"+fechaFinal

		params.idCaso = params.idCaso?params.idCaso:""
		params.dxClinico = params.dxClinico?params.dxClinico:""
		def quirurgicos = Quirurgico.findAllByIdCasoLikeAndDiagnosticoClinicoLike(params.idCaso.trim().toUpperCase()+"%", params.dxClinico.trim()+"%").findAll{it.paciente in pacientes && it.patologoResponsable in patologos && it.fechaDeRadicado >= fechaInicial && it.fechaDeRadicado <= fechaFinal}
		def citologias = Citologia.findAllByIdCasoLikeAndDiagnosticoClinicoLike(params.idCaso.trim().toUpperCase()+"%", params.dxClinico.trim()+"%").findAll{it.paciente in pacientes && it.patologoResponsable in patologos && it.fechaDeRadicado >= fechaInicial && it.fechaDeRadicado <= fechaFinal}
		def citometrias = Citometria.findAllByIdCasoLikeAndDiagnosticoClinicoLike(params.idCaso.trim().toUpperCase()+"%", params.dxClinico.trim()+"%").findAll{it.paciente in pacientes && it.patologoResponsable in patologos && it.fechaDeRadicado >= fechaInicial && it.fechaDeRadicado <= fechaFinal}
		def necropsias = Necropsia.findAllByIdCasoLikeAndDiagnosticoClinicoLike(params.idCaso.trim().toUpperCase()+"%", params.dxClinico.trim()+"%").findAll{it.paciente in pacientes && it.patologoResponsable in patologos && it.fechaDeRadicado >= fechaInicial && it.fechaDeRadicado <= fechaFinal}
		def citologiasN = CitologiaN.findAllByIdCasoLikeAndDiagnosticoClinicoLike(params.idCaso.trim().toUpperCase()+"%", params.dxClinico.trim()+"%").findAll{it.paciente in pacientes && it.patologoResponsable in patologos && it.fechaDeRadicado >= fechaInicial && it.fechaDeRadicado <= fechaFinal}
		

		println "quirurgicos: "+ quirurgicos
		println "citologias: "+ citologias
		println "citometrias: "+ citometrias
		println "necropsias: "+ necropsias


		if(quirurgicos.size() > 0){
			for(quirurgico in quirurgicos){
				results.push([idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, paciente:quirurgico.paciente.primerApellido+" "+(quirurgico.paciente.segundoApellido?quirurgico.paciente.segundoApellido:"")+", "+quirurgico.paciente.primerNombre+" "+(quirurgico.paciente.segundoNombre?quirurgico.paciente.segundoNombre:""), fechaDeIngreso:quirurgico.fechaDeRadicado.format("yyyy-MM-dd"), responsable:quirurgico.patologoResponsable.apellidos+", "+quirurgico.patologoResponsable.nombres, dxClinico:quirurgico.diagnosticoClinico, tipo:"Quirurgico"])
			}
		}
		if(citologiasN.size() > 0){
			for(citologiaN in citologiasN){
				results.push([idCaso:citologiaN.idCaso, estado:citologiaN.estadoDelCaso, paciente:citologiaN.paciente.primerApellido+" "+(citologiaN.paciente.segundoApellido?citologiaN.paciente.segundoApellido:"")+", "+citologiaN.paciente.primerNombre+" "+(citologiaN.paciente.segundoNombre?citologiaN.paciente.segundoNombre:""), fechaDeIngreso:citologiaN.fechaDeRadicado.format("yyyy-MM-dd"), responsable:citologiaN.patologoResponsable.apellidos+", "+citologiaN.patologoResponsable.nombres, dxClinico:citologiaN.diagnosticoClinico, tipo:"citologiaN"])
			}
		}
		if(citologias.size() > 0){
			for(citologia in citologias){
				results.push([idCaso:citologia.idCaso, estado:citologia.estadoDelCaso, paciente:citologia.paciente.primerApellido+" "+(citologia.paciente.segundoApellido?citologia.paciente.segundoApellido:"")+", "+citologia.paciente.primerNombre+" "+(citologia.paciente.segundoNombre?citologia.paciente.segundoNombre:""), fechaDeIngreso:citologia.fechaDeRadicado.format("yyyy-MM-dd"), responsable:citologia.patologoResponsable.apellidos+", "+citologia.patologoResponsable.nombres, dxClinico:citologia.diagnosticoClinico, tipo:"Citologia"])
			}
			
		}
		if(citometrias.size() > 0){
			for(citometria in citometrias){
				results.push([idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, paciente:citometria.paciente.primerApellido+" "+(citometria.paciente.segundoApellido?citometria.paciente.segundoApellido:"")+", "+citometria.paciente.primerNombre+" "+(citometria.paciente.segundoNombre?citometria.paciente.segundoNombre:""), fechaDeIngreso:citometria.fechaDeRadicado.format("yyyy-MM-dd"), responsable:citometria.patologoResponsable.apellidos+", "+citometria.patologoResponsable.nombres, dxClinico:citometria.diagnosticoClinico, tipo:"Citometria"])
			}
		}
		if(necropsias.size() > 0){
			for(necropsia in necropsias){
				results.push([idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, paciente:necropsia.paciente.primerApellido+" "+(necropsia.paciente.segundoApellido?necropsia.paciente.segundoApellido:"")+", "+necropsia.paciente.primerNombre+" "+(necropsia.paciente.segundoNombre?necropsia.paciente.segundoNombre:""), fechaDeIngreso:necropsia.fechaDeRadicado.format("yyyy-MM-dd"), responsable:necropsia.patologoResponsable.apellidos+", "+necropsia.patologoResponsable.nombres, dxClinico:necropsia.diagnosticoClinico, tipo:"Necropsia"])
			}
		}			
		render results as JSON
	}

	def facturar() {
		User current = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		Secretaria usuario = Secretaria.findBySecUser(current)
		nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
		render( view: "nuevoCaso", model : [ tipoDeUsuario: current.profesion, username :
			nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}

	@Secured('ROLE_PATOLOGO')
	def macrodescribir(){
		def caso = Caso.getCaso(params.id)
		println caso
		if(caso==null){
			println "entra"
			render view:'/notfound'
			return
		}
		render view:"macrodescripcion", model:[caso:caso, servicio:caso.getServicioId(), tipo:caso.getClass(), macrodescripciones: MoldeDescripcionMacroscopica.list(sort:"nombreClave", order:"asc"), microdescripciones: MoldeDescripcionMicroscopica.list(sort:"nombreClave", order:"asc"), materiales:MaterialRemitido.list(sort:"nombreDelMaterial", order:"asc"), dxClinicos: MoldeDiagnosticoClinico.list(sort:"nombre", order:"asc") ]
		return 
	}



	def guardarMacroDescripcion(){
		println params
		def errors = "<ul>"
		def caso  = Caso.getCaso(params.id)
		if(!caso){
			render status:400, text:"No fue posible encontrar el caso."
			return
		}
		def servicio =  Servicio.get(Long.parseLong(params.servicio))
		if(!servicio){
			render status:400, text:"No fue posible encontrar el servicio."
			return
		}
		
		
		if(caso.idCaso.contains("-CT-") || caso.idCaso.contains("-CNG")){

			try{
				caso.numeroDeTubos = Integer.parseInt(params.numeroDeTubos?params.numeroDeTubos:"0")
			}
			catch(NumberFormatException nfe){
				render status:400, text:"El campo número de tubos solo admite enteros"
				return
			}
		}
		try{
			caso.numeroDeBloques = Integer.parseInt(params.numeroDeBloques?params.numeroDeBloques:"0")
		}
		catch(NumberFormatException nfe){
			render status:400, text:"El campo número de bloques solo admite enteros"
			return
		}
		try{
			caso.numeroDeLaminas = Integer.parseInt(params.numeroDeLaminas?params.numeroDeLaminas:"0")
		}
		catch(NumberFormatException nfe){
			render status:400, text:"El campo número de láminas solo admite enteros"
			return
		}



		def finalizado = Boolean.parseBoolean(params.finalizado?params.finalizado:"false")
		caso.materialRemitido = params.materialRemitido.trim()
		caso.servicio = servicio
		caso.diagnosticoClinico = params.dxClinico.trim()
		caso.descripcionMacroscopica = params.macrodescripcion.trim()
		caso.seProcesoTodoElmaterial = Boolean.parseBoolean(params.seProcesaTodo)
		caso.casoPrioritario = Boolean.parseBoolean(params.casoPrioritario)
		caso.observacionesParaHistotecnologia = params.observaciones
		if(!caso.descripcionMacroscopica && finalizado || caso.descripcionMacroscopica=="" && finalizado){
			render status:400, text:"La descripción macroscópica no puede estar vacía"
			return
		}
		if(finalizado){
			caso.estadoDelCaso ="Macrodescrito"
		}
		if(!caso.validate() || errors!="<ul>"){
			def fields = grailsApplication.getDomainClass('com.model.Necropsia').persistentProperties.collect { it.name }
    		for(field in fields){
    			if(message(error: nuevaNecropsia.errors.getFieldError(field))!="")
    				errors+= "<li>"+message(error: nuevaNecropsia.errors.getFieldError(field))+"</li>"
    		}
    		errors+="</ul>"
			render status:400, text:errors
			return
		}
		caso.save(flush:true, failOnError:true)
		render status:200
	}


}
