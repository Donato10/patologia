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
}
