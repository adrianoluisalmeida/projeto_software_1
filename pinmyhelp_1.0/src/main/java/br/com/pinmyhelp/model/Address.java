/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

/**
 *
 * @author rhau
 */
public class Address {
    
    private String postalCode;
    private String state;
    private String city;
    private Integer neighborhood;
    private String streetNumber;
    private String complement;
    private Double latitude;
    private Double longitude;

    public Address() {
    }

    public Address(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(String postalCode, Integer neighborhood, String streetNumber, String complement) {
        this.postalCode = postalCode;
        this.neighborhood = neighborhood;
        this.streetNumber = streetNumber;
        this.complement = complement;
    }

    public Address(String postalCode, String state, String city, Integer neighborhood, String streetNumber, String complement, Double latitude, Double longitude) {
        this.postalCode = postalCode;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.streetNumber = streetNumber;
        this.complement = complement;
        this.latitude = latitude;
        this.longitude = longitude;
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
    public Integer getNeighborhood() {
        return neighborhood;
    }

    /**
     * @param neighborhood the neighborhood to set
     */
    public void setNeighborhood(Integer neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     * @return the streetNumber
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
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

    @Override
    public String toString() {
        return "Address{" + "postalCode=" + postalCode + ", state=" + state + ", city=" + city + ", neighborhood=" + neighborhood + ", streetNumber=" + streetNumber + ", complement=" + complement + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

}