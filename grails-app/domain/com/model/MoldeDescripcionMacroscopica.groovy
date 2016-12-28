package com.model

class MoldeDescripcionMacroscopica {

	String nombreClave;
	String descripcion;
	
	
    static constraints = {
    }
	static mapping = {
		version false
		descripcion sqlType:'text'
	}
}
