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
		def quirurgicosMicro = Quirurgico.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado").findAll{!(it in quirurgicosMacro)}
		def quirurgicosResp = Quirurgico.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito").findAll{!(it in quirurgicosMacro || it in quirurgicosMicro)}
		def quirurgicosOtros = Quirurgico.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito").findAll{!(it in quirurgicosMacro || it in quirurgicosMicro || it in quirurgicosResp)}

		def citologiasNMacro = CitologiaN.findAllByPatologoResponsableDeLaMacroDescripcionAndEstadoDelCaso(usuario, "Registrado")
		def citologiasNMicro = CitologiaN.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado").findAll{!(it in citologiasNMacro)}
		def citologiasNResp = CitologiaN.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito").findAll{!(it in citologiasNMacro || it in citologiasNMicro)}
		def citologiasNOtros = CitologiaN.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito").findAll{!(it in citologiasNMacro || it in citologiasNMicro || it in citologiasNResp)}


		def citometriasMacro = Citometria.findAllByPatologoResponsableDeLaMacroDescripcionAndEstadoDelCaso(usuario, "Registrado")
		def citometriasMicro = Citometria.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado").findAll{!(it in citometriasMacro)}
		def citometriasResp = Citometria.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito").findAll{!(it in citometriasMacro || it in citometriasMicro)}
		def citometriasOtros = Citometria.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito").findAll{!(it in citometriasMacro || it in citometriasMicro || it in citometriasResp)}

		
		def necropsiasMacro = Necropsia.findAllByPatologoResponsableDeLaMacroDescripcionAndEstadoDelCaso(usuario, "Registrado")
		def necropsiasMicro = Necropsia.findAllByPatologoResponsableDeLaMicroDescripcionAndEstadoDelCaso(usuario, "Procesado").findAll{!(it in necropsiasMacro)}
		def necropsiasResp = Necropsia.findAllByPatologoResponsableAndEstadoDelCaso(usuario, "Microdescrito").findAll{!(it in necropsiasMacro || it in necropsiasMicro)}
		def necropsiasOtros = Necropsia.findAllByPatologoResponsableAndEstadoDelCasoNotEquals(usuario, "Microdescrito").findAll{!(it in necropsiasMacro || it in necropsiasMicro || it in necropsiasResp)}

		def citologias = Citologia.findAllByPatologoResponsable(usuario)

		def casos = []


		for(quirurgico in quirurgicosMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, nombrePaciente:quirurgico.paciente.getNombre(), material:quirurgico.materialRemitido, accion:"Macro"])		}

		for(quirurgico in quirurgicosMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, nombrePaciente:quirurgico.paciente.getNombre(), material:quirurgico.materialRemitido, accion:"Micro"])
		}

		for(quirurgico in quirurgicosResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, nombrePaciente:quirurgico.paciente.getNombre(), material:quirurgico.materialRemitido, accion:"Revisar"])
		}

		for(quirurgico in quirurgicosOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(quirurgico.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(quirurgico.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: quirurgico.id, idCaso:quirurgico.idCaso, estado:quirurgico.estadoDelCaso, fechaDeRadicado:quirurgico.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Quirurgico", color:color, documento: quirurgico.paciente.numeroDeIdentificacion, nombrePaciente:quirurgico.paciente.getNombre(), material:quirurgico.materialRemitido, accion:"Alertar"])
		}


		for(citologiaN in citologiasNMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citologiaN.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citologiaN.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citologiaN.id, idCaso:citologiaN.idCaso, estado:citologiaN.estadoDelCaso, fechaDeRadicado:citologiaN.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citologiaN", color:color, documento: citologiaN.paciente.numeroDeIdentificacion, nombrePaciente:citologiaN.paciente.getNombre(), material:citologiaN.materialRemitido, accion:"Macro"])		}

		for(citologiaN in citologiasNMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citologiaN.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citologiaN.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citologiaN.id, idCaso:citologiaN.idCaso, estado:citologiaN.estadoDelCaso, fechaDeRadicado:citologiaN.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citologiaN", color:color, documento: citologiaN.paciente.numeroDeIdentificacion, nombrePaciente:citologiaN.paciente.getNombre(), material:citologiaN.materialRemitido, accion:"Micro"])
		}

		for(citologiaN in citologiasNResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citologiaN.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citologiaN.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citologiaN.id, idCaso:citologiaN.idCaso, estado:citologiaN.estadoDelCaso, fechaDeRadicado:citologiaN.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citologiaN", color:color, documento: citologiaN.paciente.numeroDeIdentificacion, nombrePaciente:citologiaN.paciente.getNombre(), material:citologiaN.materialRemitido, accion:"Revisar"])
		}

		for(citologiaN in citologiasNOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citologiaN.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citologiaN.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citologiaN.id, idCaso:citologiaN.idCaso, estado:citologiaN.estadoDelCaso, fechaDeRadicado:citologiaN.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"citologiaN", color:color, documento: citologiaN.paciente.numeroDeIdentificacion, nombrePaciente:citologiaN.paciente.getNombre(), material:citologiaN.materialRemitido, accion:"Alertar"])
		}
		


		for(citometria in citometriasMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, nombrePaciente:citometria.paciente.getNombre(), material:citometria.materialRemitido, accion:"Macro"])		}

		for(citometria in citometriasMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, nombrePaciente:citometria.paciente.getNombre(), material:citometria.materialRemitido, accion:"Micro"])
		}

		for(citometria in citometriasResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, nombrePaciente:citometria.paciente.getNombre(), material:citometria.materialRemitido, accion:"Revisar"])
		}

		for(citometria in citometriasOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citometria.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citometria.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citometria.id, idCaso:citometria.idCaso, estado:citometria.estadoDelCaso, fechaDeRadicado:citometria.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Citometria", color:color, documento: citometria.paciente.numeroDeIdentificacion, nombrePaciente:citometria.paciente.getNombre(), material:citometria.materialRemitido, accion:"Alertar"])
		}



		for(citologia in citologias){
			def color = "rgba(74, 216, 74, 0.67);"
			if(citologia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(citologia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: citologia.id, idCaso:citologia.idCaso, estado:citologia.estadoDelCaso, fechaDeRadicado:citologia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Citologia", color:color, documento: citologia.paciente.numeroDeIdentificacion, nombrePaciente:citologia.paciente.getNombre(), material:citologia.materialRemitido, accion:"Alertar"])
		}
		


		for(necropsia in necropsiasMacro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, nombrePaciente:necropsia.paciente.getNombre(), material:necropsia.materialRemitido, accion:"Macro"])		}

		for(necropsia in necropsiasMicro){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, nombrePaciente:necropsia.paciente.getNombre(), material:necropsia.materialRemitido, accion:"Micro"])
		}

		for(necropsia in necropsiasResp){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, nombrePaciente:necropsia.paciente.getNombre(), material:necropsia.materialRemitido, accion:"Revisar"])
		}

		for(necropsia in necropsiasOtros){
			def color = "rgba(74, 216, 74, 0.67);"
			if(necropsia.fechaDeRadicado <= new Date().minus(2)){
				color = "rgba(255, 165, 0, 0.59);"
				if(necropsia.fechaDeRadicado <= new Date().minus(5))
					color = " rgba(228, 92, 92, 0.38)"
			}
			casos.push([id: necropsia.id, idCaso:necropsia.idCaso, estado:necropsia.estadoDelCaso, fechaDeRadicado:necropsia.fechaDeRadicado?.format('dd/MM/yyyy'), tipo:"Necropsia", color:color, documento: necropsia.paciente.numeroDeIdentificacion, nombrePaciente:necropsia.paciente.getNombre(), material:necropsia.materialRemitido, accion:"Alertar"])
		}

		casos.sort{Date.parse( "dd/MM/yyyy", it.fechaDeRadicado)}
		render view:"work", model:[casos:casos]

	}
}
