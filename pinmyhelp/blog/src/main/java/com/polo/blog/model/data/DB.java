/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polo.blog.model.data;

import static com.polo.blog.model.data.SessionManager.beginSession;
import static com.polo.blog.model.data.SessionManager.getSession;
import static com.polo.blog.model.data.SessionManager.endSession;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;


/**
 * Classe que faz a comunicação entre o Sistema e o Banco
 * @author roger
 */
public class DB {
    
    public static void insert(Object o){
        beginSession(true);
        getSession().save(o);
        endSession(true);
    }
    
    public static void update(Object o){
        beginSession(true);
        getSession().update(o);
        endSession(true);
    }
    
    public static void delete(Object o){
        beginSession(true);
        getSession().delete(o);
        endSession(true);
    }
    
    public static List<?> getAll(Class<?> c){
        beginSession(false);
        StringBuilder builder = new StringBuilder();
        builder.append("from ");
        builder.append(c.getSimpleName());
        TypedQuery<?> query = getSession().createQuery(builder.toString());
        List<?> list = query.getResultList();
        endSession(false);
        return list;
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
        String[] paramNames = new String[list.size()];
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = list.get(i);
        }
        return paramNames;
    }
    
   public static List<?> find(Class<?> c, String filter, String[] params){
        beginSession(false);
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
        List<?> list = query.getResultList();
        endSession(false);
        return list;
    }
    
    public static List<?> find(Class<?> c, String filter, String param){
        String[] params = new String[getParamNames(filter).length];
        for (int i = 0; i < params.length; i++) {
            params[i] = param;
        }
        return find(c, filter, params);
    }
    
        
}
