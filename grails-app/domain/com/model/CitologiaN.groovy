package com.model

import java.util.Date;

class CitologiaN extends Caso  {

	//datos generales de caso

	String idCaso;
	Servicio servicio;
	IPS ips;
	EPS eps;
	MedicoRemitente medicoRemitente;
	String materialRemitido;
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
	String observacionesParaHistotecnologia


	//Datos generales de caso regular

	String descripcionMacroscopica;
	Boolean seProcesoTodoElmaterial;
	String descripcionMicroscopica;
	Boolean casoEnJunta;
	Boolean casoPrioritario;
	Patologo patologoResponsableDeLaMacroDescripcion;
	Patologo patologoResponsableDeLaMicroDescripcion;
	String diagnosticoPatologico

	//Datos propios del caso
	Integer numeroDeTubos;
	Integer numeroDeLaminas;
	Integer numeroDeBloques;

	//Informes preliminar y adicionales del caso
	CitologiaN preliminar;
	static hasMany = [adicionales: CitologiaN, otrosPatologos:Patologo]

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
		numeroDeLaminas (blank:true, nullable:true, min:0)
		numeroDeTubos (blank:true, nullable:true, min:0)
		numeroDeBloques (blank:true, nullable:true, min:0)
		materialRemitido (blank:true, nullable:true)
		preliminar (nullable:true)
		patologoResponsableDeLaMacroDescripcion (nullable:true, blank: true)
		patologoResponsableDeLaMicroDescripcion (nullable:true, blank: true)
		observacionesParaHistotecnologia (nullable:true)
	}
	static mapping = {
		version false
		tablePerHierarchy false
		descripcionMacroscopica sqlType:'text'
		descripcionMicroscopica sqlType:'text'
		diagnosticoPatologico sqlType:'text'
		diagnosticoClinico sqlType:'text'
	}

	public String getTipo(){
		return "Citologia no ginecológica"
	}
}
