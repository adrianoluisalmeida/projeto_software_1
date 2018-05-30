/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.database.Record;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author rhau
 */
public class HelpSolicitation extends Record {
    
    private Claimant claimant;
    private Entity entity;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double lagitude;
    private Double longitude;
    private Integer status;
    private Collection<HelpOffer> helpOffers = new ArrayList<>();
    private Feedback feedback; // feedback do Voluntario (ou Entidade) sobre a solicitacao

    public HelpSolicitation() {
    }

    public HelpSolicitation(Integer id) {
        super(id);
    }

    public HelpSolicitation(Claimant claimant, Entity entity, String type, LocalDateTime startDate, LocalDateTime endDate, Double lagitude, Double longitude, Integer status) {
        this.claimant = claimant;
        this.entity = entity;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lagitude = lagitude;
        this.longitude = longitude;
        this.status = status;
    }

    /**
     * @return the claimant
     */
    public Claimant getClaimant() {
        return claimant;
    }

    /**
     * @param claimant the claimant to set
     */
    public void setClaimant(Claimant claimant) {
        this.claimant = claimant;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the lagitude
     */
    public Double getLagitude() {
        return lagitude;
    }

    /**
     * @param lagitude the lagitude to set
     */
    public void setLagitude(Double lagitude) {
        this.lagitude = lagitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the helpOffers
     */
    public Collection<HelpOffer> getHelpOffers() {
        return helpOffers;
    }

    /**
     * @param helpOffers the helpOffers to set
     */
    public void setHelpOffers(Collection<HelpOffer> helpOffers) {
        this.helpOffers = helpOffers;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "HelpSolicitation{" + "claimant=" + claimant + ", entity=" + entity + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + ", lagitude=" + lagitude + ", longitude=" + longitude + ", status=" + status + ", helpOffers=" + helpOffers + ", feedback=" + feedback + '}';
    }
    
}
