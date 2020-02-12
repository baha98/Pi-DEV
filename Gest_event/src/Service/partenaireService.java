/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entite.partenaire;
import IService.Ipartenaire;

import Utilis.ConnexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Baha Dammak
 */
public class partenaireService implements Ipartenaire  {
    
      Connection  myConnex;
       Statement ste;
    
    public partenaireService() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }


    @Override
    public void ajouterpartenaire(partenaire p) {
          try {
              String req =
                      "INSERT INTO partenaire"
                      + "(nom_partenaire,spec_partenaire,desc_partenaire) VALUES "
                      + "(?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, p.getNom_partenaire());
            ps.setString(2, p.getSpec_partenaire());
             ps.setString(3, p.getDesc_partenaire());
         
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierpartenaire(partenaire p) {
        try {
            String req = "update partenaire set nom_partenaire=?,spec_partenaire=?,desc_partenaire=? where id_partenaire =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, p.getNom_partenaire());
            ps.setString(2, p.getSpec_partenaire());
             ps.setString(3, p.getDesc_partenaire());
             ps.setInt(4, p.getId_partenaire());
        
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerpartenaire(int id_partenaire) {
                  try {
            String req = "delete from partenaire where id_partenaire =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id_partenaire);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public List<partenaire> afficherpartenaire() {
        ArrayList<partenaire> psr = new ArrayList<>();
         try {
              String req3 =
                      "select * from partenaire";
              ResultSet res =   ste.executeQuery(req3);
              
              while (res.next()) {
                partenaire p = new partenaire();
                p.setNom_partenaire(res.getString("nom_partenaire"));
                p.setSpec_partenaire(res.getString("spec_partenaire"));
                p.setDesc_partenaire(res.getString("desc_partenaire"));
                psr.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
            return psr;
    }
    
}
