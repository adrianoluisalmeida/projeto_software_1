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
    T1(1,"Type 1"),
    T2(2,"Type 2");
    
    private final int id; //number to save on DB
    private final String type; //description to show
    
    HelpType(int id, String type){
        this.id = id;
        this.type = type;
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
    public String getType() {
        return type;
    }
    
    public static HelpType get(int id){
        for(HelpType t : HelpType.values()){
            if (t.getId() == id)
                return t;
        }
        return null;
    }
    
}
