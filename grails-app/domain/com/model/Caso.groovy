package com.model

class Caso {

    static constraints = {
    }

    public static getCaso(String id){
    	id = Long.parseLong(id)
    	if(Quirurgico.get(id)){
    		return Quirurgico.get(id)
    	}
    	if(Citometria.get(id)){
    		return Citometria.get(id)
    	}
    	if(Citologia.get(id)){
    		return Citologia.get(id)
    	}
    	if(Necropsia.get(id)){
    		return Necropsia.get(id)
    	}
    	return null
    }
}
