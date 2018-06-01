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
public class Feedback extends Record {
    
    private User sender;
    private Integer rating;
    private String comments;

    public Feedback() {
    }

    public Feedback(Integer id) {
        super(id);
    }

    public Feedback(User sender, Integer rating, String comments) {
        this.sender = sender;
        this.rating = rating;
        this.comments = comments;
    }

    /**
     * @return the sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Feedback{" + "sender=" + sender + ", rating=" + rating + ", comments=" + comments + '}';
    }
    
}