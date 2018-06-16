/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.util.FormatUtils;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author rhau
 */
public class Person extends User {
    
    
    private String type;
    @NotNull(message = "O nome deve ser preenchido")
    @Size(min = 5, message = "Nome deve ter pelo menos 5 carateres")
    private String name;
     @NotNull(message = "O CPF deve ser preenchido")
    private String cpf;
    private String rg;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "A Data de Nascimento deve ser preenchido")
    private LocalDate bornDate;
    @NotNull(message = "O Primeiro telefone deve ser preenchido")
    private String firstPhone;
    private String secondPhone;
    private String biography;
    private Double score;
    private String profilePicture;
    private Address address;
    private String notes; // observacoes
    
    public static final String TYPE_CLAIMANT = "Claimant";
    public static final String TYPE_VOLUNTARY = "Voluntary";

    public Person() {
    }

    public Person(Integer id) {
        super(id);
    }

    public Person(String email, String password) {
        super(email, password);
    }

    public Person(Integer id, String email, String password) {
        super(id, email, password);
    }
    
    //constructor for create controller
    public Person(Integer id, String type, String name, String cpf, String rg, LocalDate bornDate, String firstPhone) {
        super(id);
        this.type = type;
        this.name = name;
        this.cpf = cpf;
        this.rg = rg;
        this.bornDate = bornDate;
        this.firstPhone = firstPhone;
    }
   
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the bornDate
     */
    public LocalDate getBornDate() {
        return bornDate;
    }

    /**
     * @param bornDate the bornDate to set
     */
    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
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
     * @return the biography
     */
    public String getBiography() {
        return biography;
    }

    /**
     * @param biography the biography to set
     */
    public void setBiography(String biography) {
        this.biography = biography;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
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
        return "Person{" + "name=" + name + ", cpf=" + cpf + ", rg=" + rg + ", bornDate=" + bornDate + ", firstPhone=" + firstPhone + ", secondPhone=" + secondPhone + ", biography=" + biography + ", score=" + score + ", address=" + address + ", notes=" + notes + '}';
    }
    
    /**
     * Remove mascaras de entrada
     */
    public void removeMasks(){
        if (cpf != null)
            cpf = FormatUtils.unmaskNumber(cpf);
        if (firstPhone != null)
            firstPhone = FormatUtils.unmaskNumber(firstPhone);
        if (secondPhone != null)
            firstPhone = FormatUtils.unmaskNumber(firstPhone);
        if (address != null && address.getPostalCode() != null)
            address.setPostalCode(FormatUtils.unmaskNumber(address.getPostalCode()));
    }
   
}