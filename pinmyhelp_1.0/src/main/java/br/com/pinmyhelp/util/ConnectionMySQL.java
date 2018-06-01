package br.com.pinmyhelp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author aluno
 */
public class ConnectionMySQL {
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(Database.JDBC_DRIVER);
            conn = DriverManager.getConnection(Database.URL_CONEXAO, Database.USUARIO, Database.SENHA);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Driver " + Database.JDBC_DRIVER + " não foi encontrado na memória.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    
     
    
    
    
    
    
   
    
}
