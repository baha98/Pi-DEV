/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevices;

import entities.Jaime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilis.ConnexionDB;

/**
 *
 * @author ONS
 */
public class JaimeService {
    Connection myConnex;
    Statement ste;

    public JaimeService() {
        try {
            myConnex = ConnexionDB.
                    getInstance()
                    .getConnection();
            ste = myConnex.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(JaimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void ajouterJaime(Jaime j) throws SQLException {
   
  
        String req1 = "INSERT INTO `jaime` (`id_user`, `id_actualite`,`valeur_jaime`) "
                + "VALUES ('" + j.getId_user() + "', '" + j.getId_actualite()+ "', '" + j.getValeur_jaime()+ "');"; 
               
        ste.executeUpdate(req1);
        System.out.println("élement inseré");

    }
     public int FindValeurJaimeByIdUserAndIdActualite(int id_user,int id_actualite) throws SQLException{
    String req = "SELECT * from jaime WHERE id_user='" + id_user +"'AND id_actualite='"+id_actualite+ "'";
    ResultSet res = ste.executeQuery(req);
       
        int valeurJaime = 0;
        while (res.next()) {
          
           valeurJaime = res.getInt(3);
        }
        return valeurJaime;
}
       public void incrementerjaime(int id_user,int id_actualite) {
        try {
            PreparedStatement ste = myConnex.prepareStatement("update jaime set valeur_jaime=valeur_jaime+1  WHERE id_user='" +id_user +"'AND id_actualite='"+id_actualite+ "'");
            /*pt.setInt(1,b.getNbrVue());
            pt.setInt(2,b.getId());*/

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JaimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void decrementerjaime(int id_user,int id_actualite) {
        try {
            PreparedStatement ste = myConnex.prepareStatement("update jaime set valeur_jaime=valeur_jaime-1  WHERE id_user='" +id_user +"'AND id_actualite='"+id_actualite+ "'");
            /*pt.setInt(1,b.getNbrVue());
            pt.setInt(2,b.getId());*/

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(JaimeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
