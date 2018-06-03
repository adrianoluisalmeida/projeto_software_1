/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author rhau
 */
public class Entity extends User {
    
    private String name;
    private String cnpj;
    private Date foundationDate;
    private String firstPhone;
    private String secondPhone;
    private String description;
    private Double score;
    // atributo pra logo...
    private String logo;
    private Address address;
    private String notes; // observacoes

    public Entity() {
    }

    public Entity(Integer id) {
        super(id);
    }

    public Entity(String email, String password) {
        super(email, password);
    }

    public Entity(Integer id, String email, String password) {
        super(id, email, password);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the foundationDate
     */
    public Date getFoundationDate() {
        return foundationDate;
    }

    /**
     * @param foundationDate the foundationDate to set
     */
    public void setFoundationDate(Date foundationDate) {
        this.foundationDate = foundationDate;
    }

    /**
     * @return the firstPhone
     */
    public String getFirstPhone() {
        return firstPhone;
    }

    /**
     * @param firstPhone the firstPhone to set
     */
    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    /**
     * @return the secondPhone
     */
    public String getSecondPhone() {
        return secondPhone;
    }

    /**
     * @param secondPhone the secondPhone to set
     */
    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the score
     */
    public Double getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Double score) {
        this.score = score;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Entity{" + "name=" + name + ", cnpj=" + cnpj + ", foundationDate=" + foundationDate + ", firstPhone=" + firstPhone + ", secondPhone=" + secondPhone + ", description=" + description + ", score=" + score + ", address=" + address + ", notes=" + notes + '}';
    }

    /**
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }
   
}