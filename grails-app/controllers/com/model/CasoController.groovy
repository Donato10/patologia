package com.model
import com.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON

@Secured(["ROLE_SECRETARIA", 'ROLE_PATOLOGO'])
class CasoController {

    def index() { }


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
			fechaDeRadicado = Date.parse('dd/MM/yyyy',params.fechaDeRadicado)
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

			println "pasa el primer try **************************"
		}
		catch(Exception e){
			println e.getMessage()
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
				System.out.println(idCaso)
				nuevaCitologia.setIdCaso(idCaso)
				nuevaCitologia.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e.getMessage())
				render status:400, text:e.getMessage()
			}
			System.out.println("Es una citología")
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
				System.out.println(idCaso)
				nuevaCitometria.setIdCaso(idCaso)

				nuevaCitometria.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e)
				render status:400, text:e.getMessage()
				return
			}

			System.out.println("Es una citometría")
			render nuevaCitometria.idCaso
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
				System.out.println(idCaso)
				nuevoQuirurgico.setIdCaso(idCaso)
				nuevoQuirurgico.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e)
				render status:400, text:e.getMessage()

			}
			System.out.println("Es un Quirurgico")
			render nuevoQuirurgico.idCaso
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
				System.out.println(idCaso)
				nuevaNecropsia.setIdCaso(idCaso)

				nuevaNecropsia.save(flush:true)
			}
			catch(Exception e){
				System.out.println("Hubo un error  "+ e.getMessage())
				render status:400, text:e.getMessage()
			}
			System.out.println("Es una necropsia")
			render nuevaNecropsia.idCaso
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
		println "quirurgicos: "+ quirurgicos
		println "citologias: "+ citologias
		println "citometrias: "+ citometrias
		println "necropsias: "+ necropsias


		if(quirurgicos.size() > 0){
			for(quirurgico in quirurgicos){
				results.push([idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, paciente:quirurgico.paciente.primerApellido+" "+(quirurgico.paciente.segundoApellido?quirurgico.paciente.segundoApellido:"")+", "+quirurgico.paciente.primerNombre+" "+(quirurgico.paciente.segundoNombre?quirurgico.paciente.segundoNombre:""), fechaDeIngreso:quirurgico.fechaDeRadicado.format("yyyy-MM-dd"), responsable:quirurgico.patologoResponsable.apellidos+", "+quirurgico.patologoResponsable.nombres, dxClinico:quirurgico.diagnosticoClinico, tipo:"Quirurgico"])
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

}
