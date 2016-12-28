package com.model

class MedicoRemitente {

	String nombre;
	
    static constraints = {
    }
	static mapping = {
		version false
		nombre sqlType:'text'
	}
}
