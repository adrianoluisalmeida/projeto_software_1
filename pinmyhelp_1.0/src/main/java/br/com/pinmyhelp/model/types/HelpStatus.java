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
    INTERESSE(2, "Em aberto (há voluntários)"),
    ENCERRADA(3,"Encerrada"),
    AVALIADA(4, "Avaliada"), //Avaliada pelo voluntario
    CONCLUIDA(5, "Concluida"), //Avaliada pelo requerente
    CANCELADA(6, "Cancelada");
    
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
