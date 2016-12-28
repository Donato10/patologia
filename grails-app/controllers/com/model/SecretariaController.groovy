package com.model

import com.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON

@Secured("ROLE_SECRETARIA")
class SecretariaController {

	def springSecurityService

    def index() {
		
		User current = springSecurityService.currentUser
		Secretaria usuario = Secretaria.findBySecUser(current)
		def nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
			render( view: "index", model : [ tipoDeUsuario: current.profesion, username :
				nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}


	def nuevoPaciente(){
		render view:'nuevoPaciente', model:[today:new Date()]
	}

	def nuevoCaso(){
		render view:'nuevoCaso', model:[today:new Date()]
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
					numeroDeIdentificacion : params.numeroDeIdentificacion?params.numeroDeIdentificacion:"",
					regimenDeSalud : params.regimen?params.regimen:"",
					primerNombre: params.primerNombre?params.primerNombre:"",
					segundoNombre: params.segundoNombre?params.segundoNombre:"",
					primerApellido: params.primerApellido?params.primerApellido:"",
					segundoApellido: params.segundoApellido?params.segundoApellido:"",
					direccion: params.direccion?params.direccion:"",
					tipoDeIdentificacion: params.tipoDeId,
					observaciones: params.observaciones?params.observaciones:"",
					telefonoCelular: params.telefonoCelular?params.telefonoCelular:"",
					telefonoFijo: params.telefonoFijo?params.telefonoFijo:"",
					email: params.email!=null?params.email:"",
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
				Paciente nuevoPaciente = new Paciente(
						numeroDeIdentificacion : params.numeroDeIdentificacion?params.numeroDeIdentificacion:"",
						regimenDeSalud : params.regimen?params.regimen:"",
						primerNombre: params.primerNombre?params.primerNombre:"",
						segundoNombre: params.segundoNombre?params.segundoNombre:"",
						primerApellido: params.primerApellido?params.primerApellido:"",
						segundoApellido: params.segundoApellido?params.segundoApellido:"",
						direccion: params.direccion?params.direccion:"",
						tipoDeIdentificacion: params.tipoDeId,
						observaciones: params.observaciones?params.observaciones:"",
						telefonoCelular: params.telefonoCelular?params.telefonoCelular:"",
						telefonoFijo: params.telefonoFijo?params.telefonoFijo:"",
						email: params.email!=null?params.email:"",
						fechaDeNacimiento:  Date.parse('yyyy-MM-dd', params.fechaDeNacimiento),
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
		render Servicio.getAll() as JSON
	}

	def cargarPatologos(){
		render PatologoProfesional.getAll() as JSON
	}
	def cargarIPS(){
		render IPS.getAll() as JSON
	}
	def cargarEPS(){
		render EPS.getAll() as JSON
	}
	def cargarDepartamentos(){

		render Ciudad.getAll().unique{ it.departamento } as JSON
	}
	def cargarCiudades(){
		render Ciudad.findAllByDepartamento(params.departamento) as JSON
	}
	def cargarMedicoR(){
		render MedicoRemitente.getAll() as JSON
	}
	def cargarMaterialRemitido(){
		render MaterialRemitido.getAll() as JSON
	}
	def cargarDxClinicos(){
		render MoldeDiagnosticoClinico.getAll() as JSON
	}
	def cargarResidentes(){
		render Residente.getAll() as JSON
	}
	def cargarCitologos(){
		render Citologo.getAll() as JSON
	}



	def registrarCaso(){
		print ("hay "+ Citologia.getAll().size()+ " citologías")
		print ("hay "+ Quirurgico.getAll().size()+ " quirurgicos")
		print ("hay "+ Citometria.getAll().size()+ " citometria")
		System.out.println(params)
		Paciente p
		Servicio servicio
		IPS ips
		EPS eps
		MedicoRemitente medicoRemitente
		MaterialRemitido materialRemitido
		String historiaClinica
		Ciudad ciudad
		Date fechaDeRadicado
		String diagnosticoClinico
		PatologoProfesional patologoAsignado
		String estadoDelCaso = "Registrado"
		Patologo patologoMicro
		Patologo patologoMacro
		Citologo citologo

		String idCaso


		try{
			if(!params.citologo.equals("--")){

				citologo= Citologo.get(params.citologo)
			}
			p = Paciente.findByNumeroDeIdentificacion(params.numeroDeIdentificacion)
			servicio = Servicio.get(params.servicio)
			ips = IPS.get(params.ips)
			eps = EPS.get(params.eps)
			medicoRemitente = MedicoRemitente.get(params.medicoRemitente)
			materialRemitido = MaterialRemitido.get(params.materialRemitido)
			historiaClinica = params.historiaClinica
			ciudad = Ciudad.findByCiudadAndDepartamento(params.ciudadDeResidencia, params.departamentoDeResidencia)
			fechaDeRadicado = parseDate(params.fechaDeRadicado)
			diagnosticoClinico = MoldeDiagnosticoClinico.get(params.dxClinico).nombre
			patologoAsignado = PatologoProfesional.get(params.patologoAsignado)
			estadoDelCaso = "Registrado"
			System.out.println(params.patologoMicro.equals("--"))
			if(!params.patologoMicro.equals("--")){
				if(params.patologoMicro.contains("residente")){
					System.out.println("el de la micro es residente")
					System.out.println(params.patologoMicro.substring(0,params.patologoMicro.indexOf(",")))
					patologoMicro = Residente.get(params.patologoMicro.substring(0,params.patologoMicro.indexOf(",")))
				}
				else{
					patologoMicro = PatologoProfesional.get(params.patologoMicro.substring(0,params.patologoMicro.indexOf(",")))
				}
			}
			if(!params.patologoMacro.equals("--")){
				if(params.patologoMacro.contains("residente")){
					System.out.println("el de la macro es residente")
					System.out.println(params.patologoMicro.substring(0,params.patologoMicro.indexOf(",")))
					patologoMacro = Residente.get(params.patologoMacro.substring(0,params.patologoMacro.indexOf(",")))
				}
				else{

					patologoMacro = PatologoProfesional.get(params.patologoMacro.substring(0,params.patologoMacro.indexOf(",")))
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		if(params.tipoDeCaso.equals("Citología")){



			try{

				Citologia nuevaCitologia = new Citologia(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1", citologo: citologo)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				idCaso = ips.sigla+"-CG-"+ year+ "-" + (Citologia.getAll().size()+1)
				System.out.println(idCaso)
				nuevaCitologia.setIdCaso(idCaso)
				nuevaCitologia.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e)
			}
			System.out.println("Es una citología")
			render "Es una citología"
		}
		if(params.tipoDeCaso.equals("Citometría")){
			try{

				Citometria nuevaCitometria = new Citometria(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				idCaso = ips.sigla+"-CT-"+ year+ "-" + (Citometria.getAll().size()+1)
				System.out.println(idCaso)
				nuevaCitometria.setIdCaso(idCaso)

				nuevaCitometria.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e)
			}

			System.out.println("Es una citometría")
			render "Es una citometria"
		}
		if(params.tipoDeCaso.equals("Quirúrgico")){

			try{
				Quirurgico nuevoQuirurgico = new Quirurgico(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				idCaso = ips.sigla+"-QR-"+ year+ "-" + (Quirurgico.getAll().size()+1)
				System.out.println(idCaso)
				nuevoQuirurgico.setIdCaso(idCaso)
				nuevoQuirurgico.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e)
			}
			System.out.println("Es un Quirurgico")
			render "Es una quirurgico"
		}
		if(params.tipoDeCaso.equals("Necropsia")){
			try{

				Necropsia nuevaNecropsia = new Necropsia(paciente: p, servicio: servicio, ips: ips, eps: eps,
				medicoRemitente: medicoRemitente, materialRemitido: materialRemitido, historiaClinica: historiaClinica,
				ciudad:ciudad, fechaDeRadicado: fechaDeRadicado, diagnosticoClinico: diagnosticoClinico,
				patologoResponsable: patologoAsignado, estadoDelCaso: estadoDelCaso, idCaso : "1",
				patologoResponsableDeLaMacroDescripcion: patologoMacro, patologoResponsableDeLaMicroDescripcion:patologoMicro)
				def year = Calendar.getInstance().get(Calendar.YEAR);
				idCaso = ips.sigla+"-NE-"+ year+ "-" +(Necropsia.getAll().size()+1)
				System.out.println(idCaso)
				nuevaNecropsia.setIdCaso(idCaso)

				nuevaNecropsia.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e)
			}

			System.out.println("Es una necropsia")
			render "Es una necropsia"
		}
	}



	def revisarFechaDeNacimiento(){

		Paciente p = Paciente.findByNumeroDeIdentificacion( params.numeroDeDocumento)

		if(p!=null){
			if(p.fechaDeNacimiento!=null)
				render "True"
			else{
				System.out.println("el paciente no tiene fecha de nacimiento y debe ingresarla o ingresar la edad")
				render "False"
			}
		}
		else{
			System.out.println("el paciente ni siquiera existe")
			render "False"
		}
	}


	def calcularEdad( date1,  date2){

		def monthDay = [
			31,
			-1,
			31,
			30,
			31,
			30,
			31,
			31,
			30,
			31,
			30,
			31] as int[]

		Calendar fromDate;
		Calendar toDate;
		int increment = 0;

		int year;
		int month;
		int day;

		Calendar d1 = new GregorianCalendar().getInstance();
		d1.setTime(date1);

		Calendar d2 = new GregorianCalendar().getInstance();
		d2.setTime(date2);

		if (d1.getTime().getTime() > d2.getTime().getTime()) {
			fromDate = d2;
			toDate = d1;
		} else {
			fromDate = d1;
			toDate = d2;
		}

		if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
			increment = monthDay[fromDate.get(Calendar.MONTH)];
		}

		GregorianCalendar cal = new GregorianCalendar();
		boolean isLeapYear = cal.isLeapYear(fromDate.get(Calendar.YEAR));

		if (increment == -1) {
			if (isLeapYear) {
				increment = 29;
			} else {
				increment = 28;
			}
		}

		// DAY CALCULATION
		if (increment != 0) {
			day = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH);
			increment = 1;
		} else {
			day = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
		}

		// MONTH CALCULATION
		if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
			month = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment);
			increment = 1;
		} else {
			month = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment);
			increment = 0;
		}

		// YEAR CALCULATION
		year = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);
		System.out.println(day+" "+ month+" "+ year)
		return day+" "+ month+" "+ year;
	}

	def verificarDocumento(){

		println params
		Paciente p = Paciente.findByNumeroDeIdentificacion( params.numeroDeDocumento)

		if(p!=null){
			System.out.println("el paciente ya existe")
			render "True"
		}
		else{
			System.out.println("el paciente no existe")
			render "False"
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

	def buscadorDePacientes() {

		User current = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		Secretaria usuario = Secretaria.findBySecUser(current)
		nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
		render( view: "nuevoCaso", model : [ tipoDeUsuario: current.profesion, username :
			nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}
	def buscadorDeCasos() {

		User current = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		Secretaria usuario = Secretaria.findBySecUser(current)
		nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
		render( view: "nuevoCaso", model : [ tipoDeUsuario: current.profesion, username :
			nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}
	def facturar() {

		User current = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		Secretaria usuario = Secretaria.findBySecUser(current)
		nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
		render( view: "nuevoCaso", model : [ tipoDeUsuario: current.profesion, username :
			nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}

}
