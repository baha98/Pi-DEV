/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevices;

import entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilis.ConnexionDB;

/**
 *
 * @author ONS
 */
public class CommentaireService implements ICommentaireService {
    Connection  myConnex;
      Statement ste;
    
    public CommentaireService() {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
 public void ajouterCommentaire(Commentaire c) throws SQLException {
         java.util.Date date1= new java.util.Date();  
        String temp_commentaire = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
        c.setTemps_Commentaire(temp_commentaire);
        String req1 = "INSERT INTO `commentaire` (`id_actualite`, `id_user`, `contenu_Commentaire`,`temps_Commentaire`) VALUES ('" +
                c.getId_actualite() + "', '" + c.getId_user() + "', '" + c.getContenu_Commentaire() + "', '" + c.getTemps_Commentaire() + "');";
        ste.executeUpdate(req1);
        System.out.println("elment inseré");

    }
    
    public List<Commentaire> readAll(int id_actualite ) throws SQLException
    {
    List<Commentaire> list=new ArrayList<>();
    ResultSet res=ste.executeQuery("select * from commentaire WHERE id_actualite ='"+id_actualite +"'ORDER BY Id_Commentaire");
    Commentaire com=null;
    while (res.next()) {            
      com=new Commentaire(res.getInt(1),res.getInt(2),res.getInt(3),res.getString(4),res.getString(5));
      list.add(com);
        }
        System.out.println(list);
    return list;
    } 
    
   public void supprimerCommentaire(Commentaire c) {
            
        
        try { 
            String req="DELETE FROM `commentaire` WHERE `commentaire`.`id_Commentaire` = ?";
            PreparedStatement ste = myConnex.prepareStatement(req);
            ste.setInt(1, c.getId_Commentaire());
            ste.executeUpdate();
            System.out.println("element supprimé");
         
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
   public void modifierCommentaire(Commentaire c) {      
   String sql="UPDATE commentaire SET `id_actualite `=?,`id_user `=?,`contenu_Commentaire `=?,`temps_Commentaire`=? WHERE id_Commentaire ="+c.getId_Commentaire();
   PreparedStatement ste;
        try {
            ste =myConnex.prepareStatement(sql);
            ste.setInt(1, c.getId_actualite());
            ste.setInt(2, c.getId_user());
            
            ste.setString(3, c.getContenu_Commentaire());
            ste.setString(4, c.getTemps_Commentaire());
            
                
            ste.executeUpdate();
            int rowsUpdated=ste.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de produit"+c.getId_Commentaire()+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
