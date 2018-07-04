/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.types;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author roger
 */
public enum Rating {
    
    PESSIMO(1,"Péssimo"),
    RUIM(2,"Ruim"),
    RAZOAVEL(3, "Razoável"),
    BOM(4, "Bom"),
    OTIMO(5, "Ótimo");
    
    private final int value; //value to save on DB
    private final String description; //description to show
    
    Rating(int value, String description){
        this.value = value;
        this.description = description;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    public static Rating get(int value){
        for(Rating s : Rating.values()){
            if (s.getValue() == value)
                return s;
        }
        return null;
    }
    
    public static List<Rating> getAll(){
        return Arrays.asList(Rating.values());
    }
    
    @Override
    public String toString(){
        return String.format("%s - %s",value, description);
    }
    
}
