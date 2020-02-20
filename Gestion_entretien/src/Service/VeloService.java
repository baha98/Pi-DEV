/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.AffichageLocation;
import Entite.Velo;
import IService.IVelo;
import Utilis.ConnexionDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;

/**
 *
 * @author itsme
 */
public class VeloService implements IVelo {
    Connection  myConnex;
       Statement ste;
    
    public VeloService() throws ClassNotFoundException {
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
    public void ajouterVelo(Velo v) {
        
    }

    @Override
    public void modifierVelo(Velo v) {
        
    }

    @Override
    public void supprimerVelo(int id_velo) {
        
    }

    @Override
    public ArrayList<Velo> afficherVelo() {
         try {
              ArrayList<Velo> B = new ArrayList<Velo>();
              String req3 =
                      "select * from velo";
              ResultSet res =  ste.executeQuery(req3);
              while(res.next())
                {
                    ImageView Image = new ImageView("/Images/img/"+res.getString("Image"));
                    Image.setFitHeight(100);
                    Image.setFitWidth(100);
                    B.add(new Velo(res.getInt("id_velo"),res.getString("Libelle"),res.getString("Marque"),res.getDouble("Prix"), res.getInt("Qte"), res.getString("Couleur"),Image, res.getString("descrption"), res.getString("Etat"),res.getInt("Id_Membre")));
                }
              return (ArrayList<Velo>) B;
             
          } catch (SQLException ex) {
              Logger.getLogger(LocationsService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return null;
    }
    
}
