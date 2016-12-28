package com.model

import com.security.User;

class Residente  extends Patologo{

	
	 String nombres;
	 String apellidos;
	 String telefonoFijo;
	 String telefonoCelular;
	 String email;
	 User secUser;
	
    static constraints = {
    }
	static mapping = {
		version false
		tablePerHierarchy false
	}

	public Residente(String nombres, String apellidos, String telefonoFijo,
			String telefonoCelular, String email, User secUser) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefonoFijo = telefonoFijo;
		this.telefonoCelular = telefonoCelular;
		this.email = email;
		this.secUser = secUser;
	}
	
}
