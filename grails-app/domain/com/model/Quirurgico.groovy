package com.model

import java.util.Date;

class Quirurgico extends Caso  {

	//datos generales de caso

	String idCaso;
	Servicio servicio;
	IPS ips;
	EPS eps;
	MedicoRemitente medicoRemitente;
	MaterialRemitido materialRemitido;
	String historiaClinica;
	Ciudad ciudad;
	Date fechaDeRadicado;
	Paciente paciente;
	String comentarios;
	String diagnosticoClinico;
	PatologoProfesional patologoResponsable;
	Date fechaInterpretacion;
	Date fechaDeFinalizacion;
	String estadoDelCaso;

	//Datos generales de caso regular

	String descripcionMacroscopica;
	Boolean seProcesoTodoElmaterial;
	static hasMany= [otrosPatologos:PatologoProfesional]
	String descripcionMicroscopica;
	Boolean casoEnJunta;
	Boolean casoPrioritario;
	Patologo patologoResponsableDeLaMacroDescripcion;
	Patologo patologoResponsableDeLaMicroDescripcion;
	String diagnosticoPatologico



	//Datos propios del caso
	Integer numeroDeBloques;
	Integer numeroDeLaminas;


	static constraints = {
		idCaso (unique:true, blank:false, nullable: false)
		descripcionMacroscopica (blank:true, nullable:true)
		descripcionMicroscopica (blank:true, nullable:true)
		seProcesoTodoElmaterial (blank:true, nullable:true)
		casoEnJunta (blank:true, nullable:true)
		casoPrioritario (blank:true, nullable:true)
		diagnosticoPatologico (blank:true, nullable:true)
		comentarios (blank:true, nullable:true)
		historiaClinica (blank:true, nullable:true)
		fechaInterpretacion (blank:true, nullable:true)
		fechaDeFinalizacion (blank:true, nullable:true)
		numeroDeBloques (blank:true, nullable:true)
		numeroDeLaminas (blank:true, nullable:true)
		
		patologoResponsableDeLaMacroDescripcion (nullable:true, blank: true)
		patologoResponsableDeLaMicroDescripcion (nullable:true, blank: true)
	}
	static mapping = {
		version false
		tablePerHierarchy false
		descripcionMacroscopica sqlType:'text'
		descripcionMicroscopica sqlType:'text'
		diagnosticoPatologico sqlType:'text'
		diagnosticoClinico sqlType:'text'
	}
}
