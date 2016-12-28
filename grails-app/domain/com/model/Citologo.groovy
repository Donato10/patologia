package com.model

import com.security.User;

class Citologo  {
 
	String registroMedico;
	String nombres;
	String apellidos;
	String telefonoFijo;
	String telefonoCelular;
	String email;
	User secUser;
	
	
	public Citologo(String registroMedico, String nombres, String apellidos,
			String telefonoFijo, String telefonoCelular, String email,
			User secUser) {
		super();
		this.registroMedico = registroMedico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefonoFijo = telefonoFijo;
		this.telefonoCelular = telefonoCelular;
		this.email = email;
		this.secUser = secUser;
	}
	static constraints = {
    }
	static mapping = {
		version false
	}
}
