package com.model

class MoldeDiagnosticoPatologico {

	String organo;
	String nombreClave;
	String procedimiento;
	String diagnostico;
	
	
	
    static constraints = {
    }
	static mapping = {
		version false
		procedimiento sqlType:'text'
		diagnostico sqlType:'text'


	}
}
