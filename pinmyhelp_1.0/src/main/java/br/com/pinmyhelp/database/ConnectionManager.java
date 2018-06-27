/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pinmyhelp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Connection factory singleton
 * @author roger
 */
public class ConnectionManager {
    
    //Print every change on console
    public static final boolean PRINT_CONN = true;
    //Number of connections
    public static int connections = 0;
    //Connection
    public static Connection connection = null;
    
    /**
     * Create a new connection
     * @return Connection created
     */
    private static Connection createConnection(){
        try {
            Class.forName(Database.JDBC_DRIVER);
            return DriverManager.getConnection(Database.URL, Database.USER, Database.PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Driver " + Database.JDBC_DRIVER + " não foi encontrado na memória.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Create a new connection or get the current connection
     * @return database connection 
     */
    public static Connection openConnection(){
        connections++;
        if (PRINT_CONN)
            System.out.println(String.format("Connection open!(%d)",connections));
        if (connection == null)
            connection = createConnection();
        return connection;
    }
    
    public static void beginTransaction(){
        try {
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void commitTransaction(){
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Close the connection (only if numbers of connection equals zero)
     */
    public static void closeConnection(){
        connections--;
        if (PRINT_CONN)
            System.out.println(String.format("Connection close!(%d)",connections));
        try {
            if (connections == 0) {
                connection.close();
                connection = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
