/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.model.data;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Gerenciador de sessao do Hibernate
 * @author Roger
 */
public class SessionManager {
    
    private static final SessionFactory SESSION_FACTORY;
    
    private static Session activeSession = null;
    private static Transaction activeTransaction = null;
    
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
    
    public static boolean beginSession(){
        if (activeSession == null){
            activeSession = SESSION_FACTORY.openSession();
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement ee = stacktrace[2];
            sessionOpenerClass = ee.getClass();
            sessionOpenerMethodName = ee.getMethodName();
            System.out.println("Session begin!");
            return true;
        }
        return false;
    } 
    
    private static boolean isSessionOpener(){
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement ee = stacktrace[2];
        return (sessionOpenerClass.equals(ee.getClass())
                &&sessionOpenerMethodName.compareTo(ee.getMethodName()) == 0);
    }
    
    public static Session getSession(){
        return activeSession;
    }
        
    public static boolean endSession(){
        if (activeSession != null){
            StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
            StackTraceElement ee = stacktrace[2];
            if (isSessionOpener()){
                activeSession.close();
                activeSession = null;
                sessionOpenerClass = null;
                sessionOpenerMethodName = null;
                activeTransaction = null;
                System.out.println("Session end!");
                return true;
            }
        }
        return false;
    }
    
    
}
