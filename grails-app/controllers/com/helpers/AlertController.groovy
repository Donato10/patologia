package com.helpers

import com.security.User
import grails.plugin.springsecurity.annotation.Secured

class AlertController {

    def springSecurityService

    def index() { }

    @Secured(['ROLE_PATOLOGO','ROLE_RESIDENTE'])
    def crear(){
    	def currentUser = springSecurityService.currentUser
    	def texto = params.texto?.trim()
    	def nivel = Integer.parseInt(params.nivel?params.nivel:"0")
    	def caso = Caso.get(Long.parse(params.id?params.id:"0"))
    	if(!caso){
    		render status:400, text:"Lo sentimos, no fue posible encontrar el caso"
    		return 
    	}
    	if(texto=="" || !texto){
    		render status:400, text:"El texto de la alerta no puede ser vacío"
    		return 
    	}
    	if(nivel==0){
    		render status:400, text:"No es posible crear una alerta sin nivel de urgencia"
    		return
    	}
    	def newAlert =new Alert(emisor:currentUser, texto:texto, nivel:nivel).save(flush:true, failOnError:true)
    	render status:200
    }

}
