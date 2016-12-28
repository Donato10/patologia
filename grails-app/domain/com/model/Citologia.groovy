package com.model

import java.util.Date;

class Citologia extends Caso {


	//Datos propios del caso

	Date fechaUltimoParto;
	String aspectoDelCervix;
	String categorizacionGeneral;
	String microOrganismos;
	String cambiosNoNeoplasicos;
	String anormalidadesCelulasEscamosas;
	String anormalidadesCelulasGlandulares;
	String calidadDeLaMuestra;
	String esquema;
	Date fechaUltimoProcedimiento;
	String procedimientosEnCuello;
	String metodoDePlanificacion;
	String resultadoUltimaCitologia;
	Boolean enEmbarrazo;
	String edadDeInicioDeRelaciones;
	String formulaObstetrica;
	Date fechaUltimaRegla;
	Date fechaUltimaCitologia;
	String nombreDelTomador;
	String profesionDelTomador;
	Citologo citologo;
	Date fechaDeTomaDeLaMuestra;
	String otrasCelulasTumorales;
	String comentarios;


	//Datos generales de caso

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
	String diagnosticoClinico;
	PatologoProfesional patologoResponsable;
	Date fechaInterpretacion;
	Date fechaDeFinalizacion;
	String estadoDelCaso;
	String diagnosticoPatologico;

	static constraints = {

		idCaso (unique:true, blank:true, nullable: false)
		historiaClinica (blank:true, nullable:true)
		fechaUltimoParto (blank:true, nullable:true)
		aspectoDelCervix (blank:true, nullable:true)
		categorizacionGeneral (blank:true, nullable:true)
		microOrganismos (blank:true, nullable:true)
		cambiosNoNeoplasicos (blank:true, nullable:true)
		anormalidadesCelulasEscamosas (blank:true, nullable:true)
		anormalidadesCelulasGlandulares (blank:true, nullable:true)
		calidadDeLaMuestra (blank:true, nullable:true)
		esquema (blank:true, nullable:true)
		fechaUltimoProcedimiento (blank:true, nullable:true)
		procedimientosEnCuello (blank:true, nullable:true)
		metodoDePlanificacion (blank:true, nullable:true)
		resultadoUltimaCitologia (blank:true, nullable:true)
		enEmbarrazo (blank:true, nullable:true)
		edadDeInicioDeRelaciones (blank:true, nullable:true)
		formulaObstetrica (blank:true, nullable:true)
		fechaUltimaRegla (blank:true, nullable:true)
		fechaUltimaCitologia (blank:true, nullable:true)
		nombreDelTomador (blank:true, nullable:true)
		profesionDelTomador (blank:true, nullable:true)
		citologo (blank:true, nullable:true)
		fechaDeTomaDeLaMuestra (blank:true, nullable:true)
		otrasCelulasTumorales (blank:true, nullable:true)
		fechaInterpretacion (blank:true, nullable:true)
		fechaDeFinalizacion  (blank:true, nullable:true)
		comentarios (blank:true, nullable:true)
		diagnosticoPatologico (blank:true, nullable:true)
	}
	static mapping = {
		version false
		tablePerHierarchy true
		diagnosticoPatologico sqlType:'text'
		diagnosticoClinico sqlType:'text'

	}
}
