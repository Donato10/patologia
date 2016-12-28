package com.model

class MoldeDiagnosticoClinico {

	String nombre
	
    static constraints = {
    }
	static mapping = {
		version false
		nombre sqlType:'text'

	}
}
