package com.model


class Paciente {

	String numeroDeIdentificacion
	String primerNombre;
	String segundoNombre;
	String primerApellido;
	String segundoApellido;
	String direccion;
	String tipoDeIdentificacion;
	String regimenDeSalud;
	String observaciones;
	String telefonoCelular;
	String telefonoFijo;
	String email;
	Date fechaDeNacimiento;
	String estadoCivil;
	String profesion;
	String sexo;
	String edad;
	
    static constraints = {
		numeroDeIdentificacion unique:true, nullable: false, blank:false
		edad  nullable: true, blank:false
		fechaDeNacimiento nullable: true, blank:false
		email email:true, blank:true, nullable:true
		primerNombre blank:false
		primerApellido blank:false
		segundoNombre blank:true, nullable:true
		segundoApellido blank:true, nullable:true
		direccion blank:true, nullable:true
		observaciones blank:true, nullable:true
		profesion blank:true, nullable:true
		telefonoCelular blank:true, nullable:true
		telefonoFijo blank:true, nullable:true
    }
	static mapping = {
		version false
	}

	@Override
	public String toString() {
		return "Paciente [numeroDeIdentificacion=" + numeroDeIdentificacion
				+ ", primerNombre=" + primerNombre + ", segundoNombre="
				+ segundoNombre + ", primerApellido=" + primerApellido
				+ ", segundoApellido=" + segundoApellido + ", direccion="
				+ direccion + ", tipoDeIdentificacion=" + tipoDeIdentificacion
				+ ", regimenDeSalud=" + regimenDeSalud + ", observaciones="
				+ observaciones + ", telefonoCelular=" + telefonoCelular
				+ ", telefonoFijo=" + telefonoFijo + ", email=" + email
				+ ", fechaDeNacimiento=" + fechaDeNacimiento + ", estadoCivil="
				+ estadoCivil + ", profesion=" + profesion + ", sexo=" + sexo
				+ "]";
	}
		
	
}
