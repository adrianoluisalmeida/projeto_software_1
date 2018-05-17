/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.si.pinmyhelp.model.data;

import static com.si.pinmyhelp.model.data.SessionManager.beginSession;
import static com.si.pinmyhelp.model.data.SessionManager.endSession;
import static com.si.pinmyhelp.model.data.SessionManager.getSession;

import static javax.persistence.GenerationType.IDENTITY;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TypedQuery;

/**
 *
 * @author roger
 */
@MappedSuperclass
public abstract class Model implements java.io.Serializable{
    
    protected Integer id;
    
    @Id 
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void save(){
        beginSession();
        getSession().beginTransaction();
        if (id == null)
            getSession().save(this);
        else
            getSession().update(this);
        getSession().getTransaction().commit();
        endSession();
    }
    
    public void delete(){
        beginSession();
        getSession().beginTransaction();
        getSession().delete(this);
        getSession().getTransaction().commit();
        endSession();
    }
    
    public static <T>List<T> getAll(Class<T> c){
        beginSession();
        StringBuilder builder = new StringBuilder();
        builder.append("from ");
        builder.append(c.getSimpleName());
        TypedQuery<?> query = getSession().createQuery(builder.toString());
        List<T> list = (List<T>)query.getResultList();
        endSession();
        return list;
    }
    
    public static <T>List<T> find(Class<T> c,String filter, String[] params){
        beginSession();
        StringBuilder builder = new StringBuilder();
        builder.append("from ");
        builder.append(c.getSimpleName());
        builder.append(" where ");
        builder.append(filter);
        TypedQuery<?> query = getSession().createQuery(builder.toString());
        String[] paramNames = getParamNames(filter);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(paramNames[i], params[i]);
        }
        List<T> list = (List<T>)query.getResultList();
        endSession();
        return list;
    }
    
    public static <T>List<T> find(Class<T> c,String filter, String param){
        String[] params = new String[getParamNames(filter).length];
        for (int i = 0; i < params.length; i++) {
            params[i] = param;
        }
        return find(c,filter, params);
    }
    
    private static String[] getParamNames(String filter){
        StringBuilder builder = new StringBuilder();
        List<String> list = new ArrayList<>();
        char[] ca = filter.toCharArray();
        boolean in = false;
        for(char c : ca){
            if (c == ':'){
                in = true;
                continue;
            }
            if (in && c == ' '){
                list.add(builder.toString());
                builder = new StringBuilder();
                in = false;
                continue;
            }
            if (in){
                builder.append(c);
            }
        }
        if (in)
            list.add(builder.toString());
        return list.stream().toArray(String[]::new);
    }
    
    public boolean equals(Model m){
        return id.compareTo(m.getId()) == 0;
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append(" - ");
        int count = 0;
        for (Field field : this.getClass().getDeclaredFields()){
            try {
                field.setAccessible(true);
                Object o = field.get(this);
                if (o instanceof Collection)
                    continue;
                if (count > 0)
                    builder.append(", ");
                builder.append(field.getName());
                builder.append(" : ");
                builder.append(o.toString());
                count++;
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return builder.toString();
    }
    
}
