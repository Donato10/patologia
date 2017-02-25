package com.helpers

import com.model.*

class Alert {

	Date dateCreated
	Patologo emisor
	String texto
	Boolean visto = false
	Histotecnologo histotecnologo
	Date fechaVisto
	Integer nivel //1. bajo nivel de urgencia, 2. Nivel medio de urgencia, 3. Urgente
	Boolean archivado = false
	Caso caso

    static constraints = {
    	texto blank:false
    	histotecnologo nullable:true
    	fechaVisto nullable:true
    }

    static mapping = {
    	texto type:'text'
    }
}
