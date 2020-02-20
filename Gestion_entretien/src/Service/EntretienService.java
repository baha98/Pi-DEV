/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.AffichageEntretien;
import java.sql.*;
import Entite.Entretien;
import Utilis.ConnexionDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IService.IEntretien;
/**
 *
 * @author itsme
 */
public class EntretienService implements IEntretien{
    
            Connection  myConnex;
       Statement ste;
    
    public EntretienService() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              
              Logger.getLogger(EntretienService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void ajouterEntretien(Entretien p) {
                    try {
              String req =
                      "INSERT INTO Entretien"
                      + "(id_cycliste,id_velo,id_user,etat) VALUES "
                      + "(?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, p.getId_cycliste());
            ps.setInt(2, p.getId_velo());
            ps.setInt(3, p.getId_user());
            ps.setString(4, p.getEtat());
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(EntretienService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierEntretien(Entretien p) {
         try {
            String req = "update Entretien set etat=? where id_entretien =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, p.getEtat());
            ps.setInt(2, p.getId_entretien());
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(EntretienService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerEntretien(int id) {
                  try {
            String req = "delete from Entretien where id_entretien =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(EntretienService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public ResultSet afficherEntretien() {
          try {
              int id=1;
              String req3 =
                      "select m.nom_membre,m.prenom_membre,v.libelle as nom_velo,e.etat from entretien as e INNER JOIN membre as m  ON e.id_membre=m.id_membre INNER JOIN velo as v ON e.id_velo = v.id_velo";
              
              ResultSet res =   ste.executeQuery(req3);
              return res;
          } catch (SQLException ex) {
              Logger.getLogger(EntretienService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return null;
    }

    @Override
    public int recherche(AffichageEntretien Ae) {
        try {
                String req3 = "SELECT e.*,m.* from entretien as e INNER JOIN membre as m ON m.id_membre=e.id_membre WHERE m.Nom_membre='"+Ae.getNom_user()+"'";
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
