/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.database.Record;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author roger
 */
public class Message extends Record implements Serializable {
    
    private User user;
    private String title;
    private String content;
    private LocalDate createdDate;
    private boolean isReaded;

    public Message(){
    }
    
    public Message(User user) {
        this.user = user;
    }
        
    public Message(User user, String title, String content, boolean isReaded) {
        this.user = user;
        this.title = title;
        this.content = content;
    }
    
    public Message(Integer id, User user, String title, String content, boolean isReaded) {
        super(id);
        this.user = user;
        this.title = title;
        this.content = content;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * @return the createdDate
     */
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    /**
     * @return the isReaded
     */
    public boolean isIsReaded() {
        return isReaded;
    }

    /**
     * @param isReaded the isReaded to set
     */
    public void setIsReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }

    @Override
    public String toString() {
        return "Message{" + "user=" + user + ", title=" + title + ", content=" + content + ", createdDate=" + createdDate + ", isReaded=" + isReaded + '}';
    }
    
}