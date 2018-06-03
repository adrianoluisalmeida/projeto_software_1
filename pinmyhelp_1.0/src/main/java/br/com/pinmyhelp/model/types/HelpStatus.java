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
public enum HelpStatus {
    
    //HelpTypes - change
    S1(1,"Status 1"),
    S2(2,"Status 2");
    
    private final int id; //number to save on DB
    private final String type; //description to show
    
    HelpStatus(int id, String type){
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
    
    public static HelpStatus get(int id){
        for(HelpStatus s : HelpStatus.values()){
            if (s.getId() == id)
                return s;
        }
        return null;
    }
    
    
}
