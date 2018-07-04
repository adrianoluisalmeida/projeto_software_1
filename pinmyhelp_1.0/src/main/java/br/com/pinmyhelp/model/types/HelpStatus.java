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
    
    SOLICITADA(1,"Em aberto"),
    ENCERRADA(2,"Encerrada"),
    CANCELADA(3, "Cancelada"),
    INTERESSE(4, "Em aberto (há voluntários)"),
    CONCLUIDA(5, "Concluida"), //Avaliada pelo requerente
    AVALIADA(6, "Avaliada"); //Avaliada pelo voluntario
    
    private final int id; //number to save on DB
    private final String status; //description to show
    
    HelpStatus(int id, String status){
        this.id = id;
        this.status = status;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    
    public static HelpStatus get(int id){
        for(HelpStatus s : HelpStatus.values()){
            if (s.getId() == id)
                return s;
        }
        return null;
    }
    
    
}
