/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.polo.blog.model.data;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Gerenciador de sessao do Hibernate
 * @author Roger
 */
public class SessionManager {
    
    private static final SessionFactory SESSION_FACTORY;
    
    private static Session activeSession = null;
    
    private static Class<?> sessionOpenerClass = null;
    private static String sessionOpenerMethodName = null;
    
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) config file            
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static boolean beginSession(boolean beginTransaction){
        if (activeSession == null){
            activeSession = SESSION_FACTORY.openSession();
            if (beginTransaction)
                activeSession.beginTransaction();
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement ee = stacktrace[2];
            sessionOpenerClass = ee.getClass();
            sessionOpenerMethodName = ee.getMethodName();
            System.out.println("Session begin!");
            return true;
        }
        return false;
    } 
    
    public static Session getSession(){
        return activeSession;
    }
    
    public static boolean endSession(boolean commit){
        if (activeSession != null){
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement ee = stacktrace[2];
            if (sessionOpenerClass.equals(ee.getClass())
            &&sessionOpenerMethodName.compareTo(ee.getMethodName()) == 0){
                if (commit)
                    activeSession.getTransaction().commit();
                activeSession.close();
                activeSession = null;
                sessionOpenerClass = null;
                sessionOpenerMethodName = null;
                System.out.println("Session end!");
                return true;
            }
        }
        return false;
    }
    
}
