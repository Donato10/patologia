package com.model

class MoldeDescripcionMicroscopica {

	String nombreClave;
	String descripcion;
	
    static constraints = {
    }
	static mapping = {
		version false
		descripcion sqlType:'text'
		
	}
}
