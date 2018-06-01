/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import java.sql.Date;

/**
 *
 * @author rhau
 */
public class Voluntary extends Person {

    public Voluntary() {
    }
    
    public Voluntary(Integer id) {
        super(id);
    }  

    public Voluntary(String email, String password) {
        super(email, password);
    }

    public Voluntary(Integer id, String email, String password) {
        super(id, email, password);
    }

    //constructor for create controller
    public Voluntary(Integer id, String type, String name, String cpf, String rg, Date bornDate, String firstPhone) {
        super(id, TYPE_VOLUNTARY, name, cpf, rg, bornDate, firstPhone);
    }
      
}