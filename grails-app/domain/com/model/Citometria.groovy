package com.model

import java.util.Date;



class Citometria extends Caso {

	
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
	
	//Datos generales de caso regular 
	
	String descripcionMacroscopica;
	Boolean seProcesoTodoElmaterial;
	String descripcionMicroscopica;
	Boolean casoEnJunta;
	Boolean casoPrioritario;
	Patologo patologoResponsableDeLaMacroDescripcion;
	Patologo patologoResponsableDeLaMicroDescripcion;
	String diagnosticoPatologico
	String observacionesParaHistotecnologia

	
	Integer numeroDeTubos;

	//Informes preliminar y adicionales del caso
	Citometria preliminar;
	static hasMany = [adicionales: Citometria, otrosPatologos:Patologo]
	
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
		numeroDeTubos (blank:true, nullable:true)
		preliminar (nullable:true)
		observacionesParaHistotecnologia (nullable:true)
		
		patologoResponsableDeLaMacroDescripcion (nullable:true, blank: true)
		patologoResponsableDeLaMicroDescripcion (nullable:true, blank: true)
		materialRemitido (blank:false,nullable:false)
    }
	
	static mapping = {
		version false
		tablePerHierarchy false
		diagnosticoClinico sqlType:'text'
		diagnosticoPatologico sqlType:'text'

	}

	public String getTipo(){
		return "Citometria"
	}
}
