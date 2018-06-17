/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.database.Record;
import br.com.pinmyhelp.model.types.GeoLocation;
import br.com.pinmyhelp.model.types.HelpStatus;
import br.com.pinmyhelp.model.types.HelpType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author rhau
 */
public class HelpSolicitation extends Record {
    
    private Claimant claimant;
    private Entity entity;
      @NotNull(message = "O tipo deve ser preenchido")
    private HelpType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private GeoLocation location;
    @Pattern(regexp="[\\d]{6}", message = "Tipo de dado inválido no campo status.")
    private HelpStatus status;
    private Collection<HelpOffer> helpOffers = new ArrayList<>();
    private Feedback feedback; // feedback do Voluntario (ou Entidade) sobre a solicitacao

    public HelpSolicitation() {
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
    public HelpType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(HelpType type) {
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
     * @return the status
     */
    public HelpStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HelpStatus status) {
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
        return "HelpSolicitation{" + "claimant=" + claimant + ", entity=" + entity + ", type=" + type + ", startDate=" + startDate + ", endDate=" + endDate + ", lagitude=" + location.getLatitude() + ", longitude=" + location.getLongitude() + ", status=" + status + ", helpOffers=" + helpOffers + ", feedback=" + feedback + '}';
    }

    /**
     * @return the location
     */
    public GeoLocation getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(GeoLocation location) {
        this.location = location;
    }
    
}
