/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import  Entities.Velo;
import Entities.Accessoire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.DataBase;

/**
 *
 * @author zoula
 */
public class AccessoireService implements iAccessoireService{
      Connection  myConnex;
      Statement ste;
          public AccessoireService() {
          try {
              myConnex  = DataBase.
                      getInstance()
                      .getConnection();
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public boolean ajouterAccessoire(Accessoire A) {
      try {
              String req =
                      "INSERT INTO Accessoire"
                      + "(Libelle,Prix,Qte,Couleur,Marque,Description,Image,Id_Membre) VALUES "
                      + "(?,?,?,?,?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, A.getLibelle());
            ps.setDouble(2, A.getPrix());
             ps.setInt(3, A.getQte());
            ps.setString(4, A.getCouleur());
            ps.setString(5, A.getMarque());
            ps.setString(6, A.getDescription());
            ps.setString(7, A.getImage());
             ps.setInt(8, A.getId_Membre());
           
            
           
            ps.executeUpdate();
           return true;
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
              return false;
          }
    }

    @Override
    public boolean modifierAccessoire(Accessoire A) {
     try {
            String req = "update Accessoire set Libelle=?,Prix=?,Qte=?,Couleur=?,Marque=? , Description=? ,Image=? , where Id_Accessoire =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, A.getLibelle());
            ps.setDouble(2, A.getPrix());
             ps.setInt(3, A.getQte());
            ps.setString(4, A.getCouleur());
             ps.setString(5, A.getMarque());
             ps.setString(6, A.getDescription());
             ps.setString(7, A.getImage());
            ps.setInt(8, A.getId_Accessoire());
            ps.executeUpdate();
            return true;
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
              return false;
          }
    }
    

    @Override
    public boolean supprimerAccessoire(Accessoire A) {
        try {
            String req = "delete from Accessoire where Id_Accessoire =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, A.getId_Accessoire());
            ps.executeUpdate();
           return true;
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
              return false;
          }
    }
    

    @Override
    public  List<Accessoire> afficherAccessoire() {
        List<Accessoire> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from Accessoire ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Accessoire(
                        res.getInt("Id_Accessoire"),
                         res.getString("Libelle"),
                          res.getDouble("Prix"),
                          res.getInt("Qte"),
                          res.getString("Couleur"),
                          res.getString("Marque"),
                          res.getString("Description"),
                          res.getString("Image"),
                          res.getInt("Id_Membre")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    
    public  List<Accessoire> rechercheParMarque(String marque) {
        List<Accessoire> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from Accessoire where Marque = '" + marque + "'";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Accessoire(
                        res.getInt("Id_Accessoire"),
                         res.getString("Libelle"),
                          res.getDouble("Prix"),
                          res.getInt("Qte"),
                          res.getString("Couleur"),
                          res.getString("Marque"),
                          res.getString("Description"),
                          res.getString("Image"),
                          res.getInt("Id_Membre")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
    
    public  List<Accessoire> triParPrix() {
        List<Accessoire> list = new ArrayList<>();
       try {
              String req3 =
                      "select * from Accessoire ORDER BY Prix ";
              ResultSet res =   ste.executeQuery(req3);
              while (res.next()) {
                  list.add(new Accessoire(
                        res.getInt("Id_Accessoire"),
                         res.getString("Libelle"),
                          res.getDouble("Prix"),
                          res.getInt("Qte"),
                          res.getString("Couleur"),
                          res.getString("Marque"),
                          res.getString("Description"),
                          res.getString("Image"),
                          res.getInt("Id_Membre")));
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(AccessoireService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return list;
    }
      
    }
    

