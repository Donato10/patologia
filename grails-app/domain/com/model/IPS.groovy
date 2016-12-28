package com.model

class IPS {

	String codigoIPS;
	String razonSocial;
	String sigla;
	String nombreDelRepresentante;
	String direccion
	String telefonoFijo;
	String email;
	String telefonoCelular;
	String nombreDeContacto;
	String tipoDeIdentificacion;
	Tarifario tarifario;
	
    static constraints = {
		codigoIPS unique:true 
    }
	static mapping = {
		version false
	}
}
