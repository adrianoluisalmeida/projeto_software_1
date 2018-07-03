/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model;

import br.com.pinmyhelp.database.Record;

/**
 *
 * @author roger
 */
public class Message extends Record{
    
    private User user;
    private String title;
    private String content;
    private boolean isReaded;

    public Message(){}
    
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
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Message{ id = ");
        builder.append(id);
        builder.append(", userId = ");
        builder.append(user.getId());
        builder.append(", title = ");
        builder.append(title);
        builder.append(", content = ");
        builder.append(content);
        builder.append(", isReaded = ");
        builder.append(isReaded);
        builder.append("}");
        return builder.toString();
    }
    
}
