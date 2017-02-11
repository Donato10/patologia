package com.model

import com.security.User;

class PatologoProfesional extends Patologo{

	 String registroMedico
	 String nombres;
	 String apellidos;
	 String telefonoFijo;
	 String telefonoCelular;
	 String email;
	 User secUser;

	static constraints = {
		registroMedico blank:true 
		registroMedico nullable:true 
	}

	static mapping = {
		version false
		tablePerHierarchy false
	}

	public PatologoProfesional(String registroMedico, String nombres,
			String apellidos, String telefonoFijo, String telefonoCelular,
			String email, User secUser) {
		super();
		this.registroMedico = registroMedico;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefonoFijo = telefonoFijo;
		this.telefonoCelular = telefonoCelular;
		this.email = email;
		this.secUser = secUser;
	}

	public String getNombreCompleto(){
		return "Dr (a)."+this.nombres +" "+this.apellidos
	}
	
	
}

