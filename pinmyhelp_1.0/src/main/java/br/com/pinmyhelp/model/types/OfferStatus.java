/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.types;

/**
 *
 * @author roger
 */
public enum OfferStatus {
    
    OFERTADA(1,"Ofertada"),
    APROVADA(2,"Aprovada"),
    REJEITADA(3,"Rejeitada"),
    CANCELADA(4, "Cancelada");
    
    private final int id; //number to save on DB
    private final String status; //description to show
    
    OfferStatus(int id, String type){
        this.id = id;
        this.status = type;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the type
     */
    public String getStatus() {
        return status;
    }
    
    public static OfferStatus get(int id){
        for(OfferStatus s : OfferStatus.values()){
            if (s.getId() == id)
                return s;
        }
        return null;
    }
    
    
}
