/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.model.types.GeoLocation;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author rhau
 */
public class Address implements Serializable {
    
    @Size(min = 1, message = "Informe o CEP")
    private String postalCode;
    @Size(min = 1, message = "Informe a UF")
    private String state;
    @Size(min = 1, message = "Informe a cidade")
    private String city;
    @Size(min = 1, message = "Informe o bairro")
    private String neighborhood;
    @Size(min = 1, message = "Informe a rua")
    private String street;
    @NotNull(message = "Infome o número")
    // @Pattern(regexp="[\\d]{6}", message = "Tipo de dado inválido no campo número.")
    private Integer number;
    private String complement;
    private GeoLocation location;

    public Address() {
        location = new GeoLocation();
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the neighborhood
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the complement
     */
    public String getComplement() {
        return complement;
    }

    /**
     * @param complement the complement to set
     */
    public void setComplement(String complement) {
        this.complement = complement;
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
    
    @Override
    public String toString() {
        return "Address{" + "postalCode=" + getPostalCode() + ", state=" + getState() + ", city=" + getCity() + ", neighborhood=" + getNeighborhood() + ", street="+street+", number=" + number + ", complement=" + getComplement() + ", latitude=" + location.getLatitude() + ", longitude=" + location.getLongitude() + '}';
    }
    
}