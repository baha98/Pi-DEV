/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ONS
 */
public class ConnexionDB {
    String url="jdbc:mysql://localhost:3306/roue_tourne";
    String login="root";
    String pwd="";
   
    static Connection myConnex;
    static ConnexionDB myconnBD;
    
    private ConnexionDB() {       
        try {
          myConnex = DriverManager
                  .getConnection(url, login, pwd);

          System.out.println("connexion établie");
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      }
    }
    
    public static ConnexionDB getInstance(){
        
        if(myconnBD == null)
         myconnBD =   new ConnexionDB();
           
        return myconnBD;
    }
    
    
    public  Connection getConnection(){
        
       
            return myConnex;
    }
}
