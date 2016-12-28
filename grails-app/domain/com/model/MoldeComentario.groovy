package com.model

class MoldeComentario {

	String nombre;
	String comentario;
	
	
    static constraints = {
    }
	static mapping = {
		version false
		comentario sqlType:'text'
	}
}
