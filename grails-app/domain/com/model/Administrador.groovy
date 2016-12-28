package com.model

import com.security.User;

class Administrador {

	String nombres;
	String apellidos;
	String telefonoFijo;
	String telefonoCelular;
	String email;
	User secUser;
	

	
	
		
	public Administrador(String nombres, String apellidos, String telefonoFijo,
			String telefonoCelular, String email, User secUser) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefonoFijo = telefonoFijo;
		this.telefonoCelular = telefonoCelular;
		this.email = email;
		this.secUser = secUser;
	}
	static mapping = {
		version false
	}
	
	static constraints = {
	}
}
