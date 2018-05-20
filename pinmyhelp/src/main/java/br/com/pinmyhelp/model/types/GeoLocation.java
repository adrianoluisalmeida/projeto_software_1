/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.types;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author roger
 */
@Embeddable
public class GeoLocation implements Serializable {
    
    private double x;
    private double y;
    
    public GeoLocation(){
        this.x = 0;
        this.y = 0;
    }
    
    public GeoLocation(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    @Column(name="x", nullable=false, precision=22, scale=0)
    public double getX() {
        return this.x;
    }
    
    public void setX(double x) {
        this.x = x;
    }
    
    @Column(name="y", nullable=false, precision=22, scale=0)
    public double getY() {
        return this.y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
}