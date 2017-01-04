package com.model

import com.security.User
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_PATOLOGO")
class PatologoProfesionalController {

	def springSecurityService

    def index() {
		
		User current = springSecurityService.currentUser
		PatologoProfesional usuario = PatologoProfesional.findBySecUser(current)
		def nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
			render( view: "index", model : [ tipoDeUsuario: current.profesion, username :
				nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}

	def work(){
		User current = springSecurityService.currentUser
		PatologoProfesional usuario = PatologoProfesional.findBySecUser(current)
		def quirurgicosMacro = Quirurgico.findAllByPatologoResponsableDeLaMacroDescripcionAndEstadoDelCaso(usuario, "Registrado")
		def quirurgicosMicro = Quirurgico.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado")
		def quirurgicosResp = Quirurgico.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito")
		def quirurgicosOtros = Quirurgico.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito")


		def citometriasMacro = Citometria.findAllByPatologoResponsableDeLaMacroDescripcionAndEstadoDelCaso(usuario, "Registrado")
		def citometriasMicro = Citometria.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado")
		def citometriasResp = Citometria.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito")
		def citometriasOtros = Citometria.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito")

		
		def necropsiasMacro = necropsia.findAllByPatologoResponsableDeLaMacroDescripcionAndEstadoDelCaso(usuario, "Registrado")
		def necropsiasMicro = necropsia.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado")
		def necropsiasResp = necropsia.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito")
		def necropsiaOtros = necropsia.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito")

		def citologias = Citologia.findAllByPatologoResponsable(usuario)

		def casos = []


		for(quirurgico in quirurgicosMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, accion:"Macro"])		}

		for(quirurgico in quirurgicosMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, accion:"Micro"])
		}

		for(quirurgico in quirurgicosResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, accion:"Revisar"])
		}

		for(quirurgico in quirurgicosOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, accion:"Alertar"])
		}

		


		for(citometria in citometriasMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, accion:"Macro"])		}

		for(citometria in citometriasMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, accion:"Micro"])
		}

		for(citometria in citometriasResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, accion:"Revisar"])
		}

		for(citometria in citometriasOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, accion:"Alertar"])
		}



		for(citologia in citologias){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citologia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citologia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: citologia.id, idCaso:citologia.idCaso, estado:citologia.estadoDelCaso, fechaDeRadicado:citologia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Citologia", color:color, documento: citologia.paciente.numeroDeIdentificacion, accion:"Alertar"])
		}
		


		for(necropsia in necropsiasMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, accion:"Macro"])		}

		for(necropsia in necropsiasMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, accion:"Micro"])
		}

		for(necropsia in necropsiasResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, accion:"Revisar"])
		}

		for(necropsia in necropsiasOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(255, 0, 0, 0.44)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, accion:"Alertar"])
		}

		casos.sort{Date.parse( "dd/MM/yyyy", it.fechaDeRadicado)}
		render view:"work", model:[casos:casos]

	}
}
