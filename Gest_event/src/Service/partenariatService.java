/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.event;
import Entite.partenariat;
import Entite.partnership;
import IService.Ipartenariat;

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
public class partenariatService implements Ipartenariat {
     
      Connection  myConnex;
       Statement ste;
    
    public partenariatService() throws ClassNotFoundException {
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
    public void ajouterpartenariat(partenariat p) {
         try {
              String req =
                      "INSERT INTO partenariat"
                      + "(id_partenaire,id_event,contribution) VALUES "
                      + "(?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, p.getId_partenaire());
            
             ps.setInt(2, p.getId_event());
      
             ps.setInt(3, p.getConribution());
            
             
             
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierpartenariat(partenariat p) {
         try {
            String req = "update partenariat set contribution=? where id_partenariat =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
       
            ps.setInt(1, p.getConribution());
             ps.setInt(2, p.getId_partenariat());
        
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerpartenariat(int id_partenariat) {
          try {
            String req = "delete from partenariat where id_partenariat =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id_partenariat);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public List<partenariat> afficherpartenariat() {
                ArrayList<partenariat> psr = new ArrayList<>();
         try {
              String req3 =
                      "select * from partenariat";
              ResultSet res =   ste.executeQuery(req3);
              
              while (res.next()) {
          
                    partenariat p = new partenariat();
                p.setId_partenaire(res.getInt("id_partenaire"));
                p.setId_event(res.getInt("id_event"));
                p.setConribution(res.getInt("contribution"));
               
                psr.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return psr;
    }

    @Override
    public List<partnership> listepartenaire( int id) {
          ArrayList<partnership> psr = new ArrayList<>();
                  try {
              String req3;
                      req3 = "select partenariat.*,partenaire.* from partenariat INNER JOIN partenaire ON partenariat.id_partenaire=partenaire.id_partenaire where partenariat.id_event = ?";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                      ps.setInt(1, id);
                      ResultSet res =   ps.executeQuery();
              
              while (res.next()) {
                                 partnership p = new partnership();
                p.setNom_partenaire(res.getString("nom_partenaire"));
                p.setSpec_partenaire(res.getString("spec_partenaire"));
                p.setContribution(res.getInt("contribution"));
              
               
                psr.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(partenariatService.class.getName()).log(Level.SEVERE, null, ex);
          }
            return psr;
    }

}
