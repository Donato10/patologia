package com.security

import grails.plugin.springsecurity.annotation.Secured

@Secured("IS_AUTHENTICATED_REMEMBERED")
class UserController {

	def springSecurityService

    def index() {
    	def currentUser = springSecurityService.currentUser
    	def authorities = currentUser.getAuthorities()*.authority
    	if("ROLE_ADMIN" in authorities){
    		redirect controller:'administrator', action:'index'
    	}
    	else if("ROLE_PATOLOGO" in authorities){
    		redirect controller:'patologoProfesional', action:'index'
    	}
    	else if("ROLE_SECRETARIA" in authorities){
    		redirect controller:'secretaria', action:'index'
    	}
    	else if("ROLE_RESIDENTE" in authorities){
    		redirect controller:'residente', action:'index'
    	}
    	else if("ROLE_CITOLOGO" in authorities){
    		redirect controller:'citologo', action:'index'
    	}
    	else if("ROLE_HISTOTECNOLOGO" in authorities){
    		redirect controller:'histotecnologo', action:'index'
    	}
    }
}
