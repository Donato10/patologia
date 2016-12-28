package com.model

class MoldeDescripcionMicroscopica {

	String nombreclave;
	String descripcion;
	
	
	
    static constraints = {
    }
	static mapping = {
		version false
		descripcion sqlType:'text'
		
	}
}
