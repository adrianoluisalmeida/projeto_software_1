/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.database;

/**
 *
 * @author adriano
 */
public abstract class Record {
    
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