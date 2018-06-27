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
public enum HelpType {
    
    //HelpTypes - change
    T1(1,"Compras"),
    T2(2,"Limpeza"),
    T3(3,"Animais de estimação"),
    T4(4, "Manutenção doméstica"),
    T5(5, "Culinária"),
    T6(6, "Outro");

    private final int idType; //number to save on DB
    private final String type; //description to show
    
    HelpType(int idType, String type){
        this.idType = idType;
        this.type = type;
    }

    /**
     * @return the id
     */
    public int getId() {
        return idType;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
    
    public static HelpType get(Integer idType){
        for(HelpType t : HelpType.values()){
            if (t.getId() == idType)
                return t;
        }
        return null;
    }
    
}