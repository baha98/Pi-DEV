/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.AffichageLocation;
import java.sql.*;
import Entite.Location;
import Utilis.ConnexionDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IService.ILocation;
import Technique.TrayIconDemo;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
/**
 *
 * @author itsme
 */
public class LocationsService implements ILocation {
    
        Connection  myConnex;
       Statement ste;
    
    public LocationsService() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void ajouterlocation(Location p) {
                 try {           
                     long diff = p.getDate_fin().getTime() - p.getDate_debut().getTime();
            float days = (diff / (1000*60*60*24));
            DecimalFormat df = new DecimalFormat("###.##");
            double prix= days*p.getPrix();
              String req2 =
                      "INSERT INTO location"
                      + "(id_membre,id_velo,date_debut,date_fin,prix) VALUES "
                      + "(?,?,?,?,?)";
              
            PreparedStatement ps = myConnex.prepareStatement(req2);
            ps.setInt(1, p.getId_user());
            ps.setInt(2, p.getId_velo());
             ps.setDate(3, p.getDate_debut());
            ps.setDate(4, p.getDate_fin());
             ps.setDouble(5, prix);
            ps.executeUpdate();        
            
            String message = "Vous avez louez un velo au prix : "+df.format(prix)+"DT";
           EnvoyerMail test = new EnvoyerMail();
           test.envoyer(message);
          } catch (SQLException ex) {
              Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierlocation(Location p) {
        try {
            String req = "update location set date_debut=?,date_fin=? where id_location =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
             ps.setDate(1, p.getDate_debut());
            ps.setDate(2, p.getDate_fin());
            ps.setInt(3, p.getId_location());
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerlocation(int id_location) {
                  try {
            String req = "delete from location where id_location =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id_location);
            ps.executeUpdate();
            if (SystemTray.isSupported()) {
                    TrayIconDemo td = new TrayIconDemo();
                    td.displayTray();
        } else {
            System.err.println("Un nouveau Velo est dans la table entretien");
        }
           
          } catch (SQLException ex) {
              Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
          } catch (AWTException ex) {
                Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public ArrayList<AffichageLocation> afficherlocation() {
                  try {
              List<AffichageLocation> B = new ArrayList<AffichageLocation>();
              String req3 =
                      "select v.*,m.Nom_membre,m.Prenom_membre,l.id_location,l.date_debut,l.date_fin,l.prix as prix_l from location as l INNER JOIN membre as m ON l.id_membre=m.id_membre INNER JOIN velo as v ON l.id_velo = v.id_velo";
              ResultSet res =   ste.executeQuery(req3);
              while(res.next())
                {
                    ImageView A = new ImageView("/Images/img/"+res.getString("Image"));
                    A.setFitHeight(100);
                    A.setFitWidth(100);
                    String desc = res.getString("Descrption");
                        B.add(new AffichageLocation(A,res.getString("Nom_membre"),res.getString("prenom_membre"),res.getString("libelle"),res.getString("Descrption"),res.getDate("date_debut"),res.getDate("date_fin"),res.getDouble("prix_l")));
                }
              return (ArrayList<AffichageLocation>) B;
             
          } catch (SQLException ex) {
              Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return null;
    }

    @Override
    public int recherche(AffichageLocation Al) {  
            
            try {
                String req3 = "SELECT l.*,m.* from location as l INNER JOIN membre as m ON m.id_membre=l.id_membre WHERE m.Nom_membre='"+Al.getNom_user()+"'";
                ResultSet res =  ste.executeQuery(req3);
                
                if(res.next())
                {
                    return res.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
            }  
            return 1;
    }
}
