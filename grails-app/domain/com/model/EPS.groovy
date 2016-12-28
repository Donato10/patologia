package com.model

class EPS {

	String codigo;
	String nombre;
	//String sigla;
	
    static constraints = {
		codigo unique:true 
    }
	
	static mapping = {
		version false
	}
}
