package br.com.pinmyhelp.util;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author aluno
 */
public class ConectaPostgres {

    
    public static Connection getConexao() throws Exception{
         Class.forName("com.mysql.jdbc.Driver");
             // abrir conexao
             String url = "jdbc:mysql://localhost:5432/pinmyhelp";
             String user = "root";
             String pass = "968574";
             Connection conn = 
                     DriverManager.getConnection(url, user, pass);
            return conn;
    }
    
    
     
    
    
    
    
    
   
    
}
