/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.si.pinmyhelp.model;

import com.si.pinmyhelp.model.data.Model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author roger
 */
@MappedSuperclass
public class Person extends Model{
    
    private String name;
    private String cpf;
    private String password;
    private String identity;
    private Date bornDate;
    private Address adress;
    private String primPhone;
    private String secPhone;
    private String email;
    private String bio;
    private String photo;
    private String obs;
    private BigDecimal score;
    protected List<Help> helps = new ArrayList<>();
    protected List<Feedback> feedbacks = new ArrayList<>();
    
    @Column(name="name", nullable=false)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="cpf", nullable=false, length=11)
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Column(name="password", nullable=false, length=35)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="identity", length=10)
    public String getIdentity() {
        return this.identity;
    }
    
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="born_date", length=10)
    public Date getBornDate() {
        return this.bornDate;
    }
    
    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    
    @Column(name="prim_phone", nullable=false, length=11)
    public String getPrimPhone() {
        return this.primPhone;
    }
    
    public void setPrimPhone(String primPhone) {
        this.primPhone = primPhone;
    }

    
    @Column(name="sec_phone", length=11)
    public String getSecPhone() {
        return this.secPhone;
    }
    
    public void setSecPhone(String secPhone) {
        this.secPhone = secPhone;
    }

    
    @Column(name="email", nullable=false)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    
    @Column(name="bio", length=65535)
    public String getBio() {
        return this.bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }

    
    @Column(name="photo", length=11)
    public String getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
    @Column(name="obs", length=65535)
    public String getObs() {
        return this.obs;
    }
    
    public void setObs(String obs) {
        this.obs = obs;
    }

    
    @Column(name="score", precision=8)
    public BigDecimal getScore() {
        return this.score;
    }
    
    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public void setHelps(List<Help> helps) {
        this.helps = helps;
    }
    
    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    
    /**
     * @return the adress
     */
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="adress_id", nullable=false)
    public Address getAddress() {
        return adress;
    }

    /**
     * @param adress the adress to set
     */
    public void setAddress(Address adress) {
        this.adress = adress;
    }
}
