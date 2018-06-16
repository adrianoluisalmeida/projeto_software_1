/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.database.Record;

/**
 *
 * @author rhau
 */
public class User extends Record {
    
    protected String email;
    protected String password;
    protected boolean isAdmin = false;

    public User() {
    }
    
    public User(Integer id) {
        super(id);
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String email, String password) {
        super(id);
        this.email = email;
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return true if the user is an admin
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the boolean to set if the user is admin or not
     */
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + ", isAdmin=" + isAdmin + '}';
    }
    
}