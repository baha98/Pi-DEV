/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package velo.tn;
import Entities.Commande;
import Entities.ListeProduit;
import Entities.Paiement;
import Service.AccessoireService;
import java.sql.*;
import Service.CommandeService;
import Service.PaiementService;
import com.itextpdf.text.DocumentException;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class VeloTn  {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws ParseException, FileNotFoundException, DocumentException, IOException, AWTException, SQLException {
       DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
       java.sql.Date Date = new java.sql.Date(df.parse("02-04-2015").getTime());
       java.sql.Date Date1 = new java.sql.Date(df.parse("10-02-2020").getTime());
       
       CommandeService c = new CommandeService();
       PaiementService p = new PaiementService();
       AccessoireService ac = new AccessoireService();
       ac.UpdateStock(13, 3);
       
       
       
       System.out.println("****Calcul Total******");
       ListeProduit l1 = new ListeProduit(1,1200,2);
       ListeProduit l2 = new ListeProduit(1,70,4);
       List<ListeProduit> lpr = new ArrayList<ListeProduit>();
       lpr.add(l2);
       lpr.add(l1);
       for(ListeProduit eee: lpr)
            {System.out.println(lpr);}
       
       double total=c.CalculerTotalCommande(lpr);
       System.out.println("Total = "+total);
       
       Commande c1= new Commande(Date,12.3,3);
       Commande c2= new Commande(Date1,12.3,4);
       Commande c3 = new Commande(19);
       Commande c4 = new Commande(18,Date1,170.0);
       
           try {
               
               //c.ajouterCommande(c1);
               //c.ajouterCommande1(c2);
              
              if(c.SupprimerCommande(c3))
              {
                  System.out.println("Commande Suprimmée");
              
              }
              else {
               System.out.println("Echéc du Supression");
              }
              
              if(c.ModifierCommande(c4))
              {
                  System.out.println("Commande Modifié");
              
              }
              else {
               System.out.println("Echéc du Modification");
              }
              
              List<Commande> L= c.AfficherCommande();
           for(Commande e: L)
        {System.out.println(e);}
           
           System.out.println("*****Les Commande triés par Date*****");
           
            List<Commande> L_TriParDate= c.TrierCommande();
            for(Commande lp: L_TriParDate)
        {System.out.println(lp);}
            
           System.out.println("*****Résultat Recherche Par Id*****");
            List<Commande> L_r= c.RechercheCommandeParIdMembre(2);
            for(Commande lr: L_r)
        {System.out.println(lr);}
            
            System.out.println("*****Résultat Recherche Par Nom*****");
            List<Commande> L_n= c.RechercheCommandeParNomMembre("Walid");
            for(Commande ln: L_n)
        {System.out.println(ln);} 
            
            
           
           Paiement p1 = new Paiement(1,3,"Edinar",487477,Date1,2108,"Algerie");
           Paiement p2 = new Paiement(15);
           Paiement p3 = new Paiement(16,"Master Card",6666,Date,20566,"Morroco");
           
           p.ajouterPaiement(p1);
           
           if(p.SupprimerPaiement(p2))
               {
                  System.out.println("Paiement Suprimmée");
              
              }
              else {
               System.out.println("Echéc du Supression");
              }
           
           if(p.ModifierPaiement(p3))
           {
                  System.out.println("Paiement Modifié");
              
              }
              else {
               System.out.println("Echéc du Modification");
              }
           
           c.FacturePdf(3);
           
           
           List<Paiement> L1= p.AfficherPaiement1();
           System.out.println("****** Les PAIEMENTS ******");
           for(Paiement pp: L1)
            {System.out.println(pp);}
           
           
            System.out.println("*****Les Paiement triés par ID*****");
           
            List<Paiement> P_Tri=p.TrierPaiement();
            for(Paiement lp: P_Tri)
        {System.out.println(lp);}
            
            
            System.out.println("*****Résultat Recherche Paiement Par Nom*****");
            List<Paiement> Lnp= p.RecherchePaiementParNomMembre("Ahmed");
            for(Paiement ln: Lnp)
        {System.out.println(ln);} 
            
            //p.PaiementEffectue();
           
           } catch (SQLException ex) {
               Logger.getLogger(VeloTn.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
            
        
    }
    

