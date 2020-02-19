/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo;

import entities.Actualite;
import entities.Categorie;
import entities.Commentaire;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import sevices.ActualiteService;
import sevices.CommentaireService;
import sevices.IActualiteService;
import sevices.UtilisateurService;
/**
 *
 * @author ONS
 */

public class Velo  {

  
    public static void main(String[] args) throws SQLException  {
      /* ActualiteService ac = new ActualiteService();
        Actualite a = new Actualite();
        
        a.setImage("ggg");
        a.setCategorie(Categorie.piste);
        a.setTitre("ff");
        a.setDescription("ggg");
        ac.chercherActualite(9);
      */
      CommentaireService ac = new CommentaireService();
      Commentaire c = new Commentaire();
      c.setId_actualite(16);
      c.setId_user(1);
      c.setContenu_Commentaire("onssssss");
      ac.readAll(16);
      
    }
}

    

