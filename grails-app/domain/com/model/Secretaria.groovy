package com.model

import com.security.User;

class Secretaria  {

	
	
	String nombres;
	String apellidos;
	String telefonoFijo;
	String telefonoCelular;
	String email;
	User secUser;

    static constraints = {
    }

	


	public Secretaria(String nombres, String apellidos, String telefonoFijo,
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
	
	
}
