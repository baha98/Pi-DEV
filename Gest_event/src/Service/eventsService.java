/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.sql.*;
import Entite.event;
import IService.IEvents;
import Utilis.ConnexionDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Baha Dammak
 */
public class eventsService implements IEvents {
    
        Connection  myConnex;
       Statement ste;
    
    public eventsService() throws ClassNotFoundException {
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
    public void ajouterevent(event p) {
                 try {
              String req =
                      "INSERT INTO evenement"
                      + "(nom,lieu,description,dateevent,type) VALUES "
                      + "(?,?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getLieu());
             ps.setString(3, p.getDescription());
            ps.setDate(4, p.getDateevent());
             ps.setString(5, p.getType());
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierevent(event p) {
        try {
            String req = "update evenement set nom=?,lieu=?,description=?,dateevent=?,type=? where id_event =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, p.getNom());
            ps.setString(2, p.getLieu());
             ps.setString(3, p.getDescription());
            ps.setDate(4, p.getDateevent());
             ps.setString(5, p.getType());
            ps.setInt(6, p.getId_event());
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerevent(int id_event) {
                  try {
            String req = "delete from evenement where id_event =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id_event);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public List<event> afficherevent() {
        ArrayList<event> psr = new ArrayList<>();
                  try {
              String req3 =
                      "select * from evenement";
              ResultSet res =   ste.executeQuery(req3);
              
              while (res.next()) {
                                 event p = new event();
                p.setNom(res.getString("nom"));
                p.setLieu(res.getString("lieu"));
                p.setDescription(res.getString("description"));
                p.setDateevent(res.getDate("dateevent"));
                p.setType(res.getString("type"));
                psr.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return psr;
    }

    @Override
    public event findById(Integer id) {
      event e= null;
        try {
            String req = "select * from evenement where id_event =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               e = new event(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),
                rs.getDate(5),rs.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;  
    }

    @Override
    public ArrayList<event> findByNom(String nom) {
              ArrayList<event> psr = new ArrayList<>();
        try {
            String req = "select * from evenement where nom =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                       event p = new event();
                       
                p.setNom(res.getString("nom"));
                p.setLieu(res.getString("lieu"));
                p.setDescription(res.getString("description"));
                p.setDateevent(res.getDate("dateevent"));
                p.setType(res.getString("type"));
                psr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(eventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return psr;  
    }
}
    

