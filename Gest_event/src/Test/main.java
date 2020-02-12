/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import Entite.event;
import Entite.participation;
import Service.eventsService;
import Service.participationService;
import Entite.partenaire;
import Service.partenaireService;
import Entite.partenariat;
import Service.partenariatService;
import Technique.TrayIconDemo;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.*; 
/**
 *
 * @author Baha Dammak
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, AWTException, SQLException, DocumentException, IOException {
       // TODO code application logic here
      eventsService P = new eventsService();
              String date = "2018-10-08";
    java.sql.Date javaSqlDate = java.sql.Date.valueOf(date);
        event a = new event (1,"Bike","Rades","ejri ejri",javaSqlDate,"competition");
       //P.ajouterevent(a);
       //System.out.println(P.findById(1));
    //   String ll="Bike";
      // System.out.println(P.findByNom(ll));
       //P.modifierevent(a);
       
       //int y=3;
       //P.supprimerevent(y);
  
     
   participationService Z=new participationService();
       String time="12:33:24";
          java.sql.Time javaSqlTime = java.sql.Time.valueOf(time);
         participation o = new participation (13,javaSqlTime,4);
        
       if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }

        //Z.modifierparticipation(o);
        //Z.listeevent(1);
        //System.out.println(Z.listeevent(1));
      //System.out.println(Z.listeparticipant(1));
   //   Z.FacturePdf(1);
        
        

    
  /*   partenaireService W = new partenaireService();
     partenaire e = new partenaire ("GoSport","Vente Produit","sa7a l omek ya yan");
     //W.ajouterpartenaire(e);
     System.out.println(W.afficherpartenaire());
*/
     partenariatService T = new partenariatService();
     partenariat e = new partenariat (1,1,15500);
          partenariat i = new partenariat (4,100);
     //T.ajouterpartenariat(e);
    // T.supprimerpartenariat(3);
    // T.modifierpartenariat(i);
    //System.out.println(T.listepartenaire(1));
    
        
    }
    
}
