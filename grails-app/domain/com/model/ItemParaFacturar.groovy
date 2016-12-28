package com.model

class ItemParaFacturar {

	CUP cup;
	IPS ips;
	String estado;
	Date fechaDeSolicitud;
	Date fechaDeFinalizado;
	Integer numeroDeBloque;
	String observaciones;
	Caso caso;
	
    static constraints = {
    }
	static mapping = {
		version false
	}
}
