/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.database.Record;
import br.com.pinmyhelp.model.types.OfferStatus;

/**
 *
 * @author rhau
 */
public class HelpOffer extends Record {
    
    private Voluntary voluntary;
    private Entity entity;
    private OfferStatus status;
    private HelpSolicitation helpSolicitation;
    private Feedback feedback; // feedback do Requerente (ou Entidade) sobre a oferta
    
    public HelpOffer() {
    }
    
    public HelpOffer(Integer id) {
        super(id);
    }

    public HelpOffer(Voluntary voluntary, Entity entity, OfferStatus status, HelpSolicitation helpSolicitation) {
        this.voluntary = voluntary;
        this.entity = entity;
        this.status = status;
        this.helpSolicitation = helpSolicitation;
    }

    /**
     * @return the voluntary
     */
    public Voluntary getVoluntary() {
        return voluntary;
    }

    /**
     * @param voluntary the voluntary to set
     */
    public void setVoluntary(Voluntary voluntary) {
        this.voluntary = voluntary;
    }

    /**
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * @return the status
     */
    public OfferStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    /**
     * @return the helpSolicitation
     */
    public HelpSolicitation getHelpSolicitation() {
        return helpSolicitation;
    }

    /**
     * @param helpSolicitation the helpSolicitation to set
     */
    public void setHelpSolicitation(HelpSolicitation helpSolicitation) {
        this.helpSolicitation = helpSolicitation;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "HelpOffer{" + "voluntary=" + voluntary + ", entity=" + entity + ", status=" + status + ", helpSolicitation=" + helpSolicitation + ", feedback=" + feedback + '}';
    }

}
