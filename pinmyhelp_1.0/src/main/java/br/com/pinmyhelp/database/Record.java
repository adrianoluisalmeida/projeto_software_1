/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.database;

import java.io.Serializable;

/**
 *
 * @author adriano
 */
public abstract class Record implements Serializable {
    
    protected Integer id;

    public Record() {
    }
    
    public Record(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}