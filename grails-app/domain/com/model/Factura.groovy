package com.model

class Factura {
	String codigoFactura;
	IPS ips;
	Date fechaDeExpedicion;
	Date fechaDeInicio;
	Date fechaDeCorte;
	String numeroDeContrato;
	String copago;
	Float valorTotalDeDescuentos;
	Float valorNetoAPagar;
	
    static constraints = {
    }
	static mapping = {
		version false
	}
}
