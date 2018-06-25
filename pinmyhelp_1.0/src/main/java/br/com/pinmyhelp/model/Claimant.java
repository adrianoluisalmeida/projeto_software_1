/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import java.time.LocalDate;

/**
 *
 * @author rhau
 */
public class Claimant extends Person {

    public Claimant() {
    }
    
    public Claimant(Integer id) {
        super(id);
    }

    public Claimant(String email, String password) {
        super(email, password);
    }

    public Claimant(Integer id, String email, String password) {
        super(id, email, password);
    }

    public Claimant(Integer id, String type, String name, String cpf, String rg, LocalDate bornDate, String firstPhone) {
        super(id, TYPE_CLAIMANT, name, cpf, rg, bornDate, firstPhone);
    }

}