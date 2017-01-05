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


	public String getNombre(){

		return primerApellido+" "+(segundoApellido?segundoApellido:"")+", "+primerNombre+" "+(segundoNombre?segundoNombre:"")
	}
	
	def calcularEdad( ){

		if(!fechaDeNacimiento){
			return "Sin dato"
		}
		def date1 = new Date()
		def date2 = fechaDeNacimiento
		def monthDay = [
			31,
			-1,
			31,
			30,
			31,
			30,
			31,
			31,
			30,
			31,
			30,
			31] as int[]

		Calendar fromDate;
		Calendar toDate;
		int increment = 0;

		int year;
		int month;
		int day;

		Calendar d1 = new GregorianCalendar().getInstance();
		d1.setTime(date1);

		Calendar d2 = new GregorianCalendar().getInstance();
		d2.setTime(date2);

		if (d1.getTime().getTime() > d2.getTime().getTime()) {
			fromDate = d2;
			toDate = d1;
		} else {
			fromDate = d1;
			toDate = d2;
		}

		if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
			increment = monthDay[fromDate.get(Calendar.MONTH)];
		}

		GregorianCalendar cal = new GregorianCalendar();
		boolean isLeapYear = cal.isLeapYear(fromDate.get(Calendar.YEAR));

		if (increment == -1) {
			if (isLeapYear) {
				increment = 29;
			} else {
				increment = 28;
			}
		}

		// DAY CALCULATION
		if (increment != 0) {
			day = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH);
			increment = 1;
		} else {
			day = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
		}

		// MONTH CALCULATION
		if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
			month = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment);
			increment = 1;
		} else {
			month = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment);
			increment = 0;
		}

		// YEAR CALCULATION
		year = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);
		System.out.println(day+" "+ month+" "+ year)
		return day+" "+ month+" "+ year;
	}
		
	
}
