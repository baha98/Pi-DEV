/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.Accessoire;
import entites.Velo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilis.ConnexionDB;

/**
 *
 * @author zoula
 */
public class VeloService implements iVeloService{
      Connection  myConnex;
      Statement ste;
        public VeloService() {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void ajouterVelo(Velo V) {
         try {
              String req =
                      "INSERT INTO Velo"
                      + "(Libelle,Marque,Prix,Qte,Couleur,Image,Description,Etat,Id_Membre) VALUES "
                      + "(?,?,?,?,?,?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, V.getLibelle());
            ps.setString(2,  V.getMarque());
             ps.setDouble(3, V.getPrix());
            ps.setInt(4, V.getQte());
             ps.setString(5, V.getCouleur());
             ps.setString(6, V.getImage());
             ps.setString(7, V.getDescription());
             ps.setString(8, V.getEtat());
             ps.setInt(9,V.getId_Membre());
           
            
           
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierVelo(Velo V) {
      try {
            String req = "update Velo set Libelle=?,Marque=?,Prix=?,Qte=?,Couleur=?, Image=? ,Description=?,Etat=? where Id_Velo =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, V.getLibelle());
            ps.setString(2, V.getMarque());
             ps.setDouble(3, V.getPrix());
            ps.setInt(4, V.getQte());
             ps.setString(5, V.getCouleur());
             ps.setString(6, V.getImage());
             ps.setString(7, V.getDescription());
             ps.setString(8, V.getEtat());
             ps.setInt(9, V.getId_Velo());
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerVelo(Velo V) {
          try {
            String req = "delete from Velo where Id_Velo =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, V.getId_Velo());
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

     public  List<Velo> afficherVelo() {
        List<Velo> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from Velo ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Velo(
                          res.getInt("Id_Velo"),
                          res.getString("Libelle"),
                          res.getString("Marque"),
                          res.getDouble("Prix"),
                          res.getInt("Qte"),
                          res.getString("Couleur"),
                          res.getString("Description"),
                          res.getString("Image"),
                          res.getString("Etat"),
                          res.getInt("Id_Membre")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    
    public  List<Velo> rechercheParMarque(String marque) {
        List<Velo> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from Velo where Marque = '" + marque + "'";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Velo(
                        res.getInt("Id_Velo"),
                         res.getString("Libelle"),
                          res.getString("Marque"),
                          res.getDouble("Prix"),
                          res.getInt("Qte"),
                          res.getString("Couleur"),
                          res.getString("Image"),
                          res.getString("Description"),
                          res.getString("Etat"),
                          res.getInt("Id_Membre")));
                  
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
     public  List<Velo> triParPrix() {
        List<Velo> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from Velo ORDER BY Prix ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Velo(
                        res.getInt("Id_Velo"),
                         res.getString("Libelle"),
                          res.getString("Marque"),
                          res.getDouble("Prix"),
                          res.getInt("Qte"),
                          res.getString("Couleur"),
                          res.getString("Image"),
                          res.getString("Description"),
                          res.getString("Etat"),
                          res.getInt("Id_Membre")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
}
    

