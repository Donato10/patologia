package com.model

import com.security.User
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON

@Secured("ROLE_SECRETARIA")
class SecretariaController {

	def springSecurityService

    def index() {
		
		User current = springSecurityService.currentUser
		Secretaria usuario = Secretaria.findBySecUser(current)
		def nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
			render( view: "index", model : [ tipoDeUsuario: current.profesion, username :
				nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}


	def calcularEdad( date1,  date2){

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

	
	def facturar() {

		User current = User.findByUsername(SecurityUtils.getSubject().getPrincipal())
		Secretaria usuario = Secretaria.findBySecUser(current)
		nickName = usuario.email.substring(0,usuario.email.indexOf("@"));
		render( view: "nuevoCaso", model : [ tipoDeUsuario: current.profesion, username :
			nickName, idUserProf : usuario.id, idSecUser: current.id ] )
	}

}
