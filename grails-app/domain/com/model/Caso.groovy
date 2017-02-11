package com.model

class Caso {

    static constraints = {
    }

    public static getCaso(String id){

    	def idCaso = Long.parseLong(id)
    	if(Quirurgico.get(id)){
    		return Quirurgico.get(idCaso)
    	}
    	if(Citometria.get(id)){
    		return Citometria.get(idCaso)
    	}
    	if(Citologia.get(id)){
    		return Citologia.get(idCaso)
    	}
        if(CitologiaN.get(id)){
            return CitologiaN.get(idCaso)
        }
    	if(Necropsia.get(id)){
    		return Necropsia.get(idCaso)
    	}
    	return null
    }


    public String getTipo(){
        return "Caso"
    }

  
}
