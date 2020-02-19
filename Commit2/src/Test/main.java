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
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author Baha Dammak
 */
public class main extends Application{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
    */
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/event2.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
    public static void main(String[] args){
      
        
  launch(args);
 
// TODO code application logic here
    /* eventsService P = new eventsService();
     System.out.println(P.afficherevent1());*/
     //  P.ajouterevent(a);
      // System.out.println(P.afficherevent());
     //  System.out.println(P.findById(1));
    //  String ll="Bike";
      // System.out.println(P.findByNom(ll));
       //P.modifierevent(a);
       
       //int y=3;
       //P.supprimerevent(y);
 /* 
     
   participationService Z=new participationService();
       String time="12:33:24";
          java.sql.Time javaSqlTime = java.sql.Time.valueOf(time);
         participation o = new participation (3,6,javaSqlTime,12);
 **/      
    /*   if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
*/
   //Z.ajouterparticipation(o);
        //Z.modifierparticipation(o);
       // Z.listeevent(3);
     //   System.out.println(Z.listeevent(3));
      //System.out.println(Z.listeparticipant(6));
  //Z.FacturePdf(6);
        
        

    
  /*   partenaireService W = new partenaireService();
     partenaire e = new partenaire ("GoSport","Vente Produit","sa7a l omek ya yan");
     //W.ajouterpartenaire(e);
     System.out.println(W.afficherpartenaire());
*/
   /*  partenariatService T = new partenariatService();
     partenariat e = new partenariat (1,1,15500);
          partenariat i = new partenariat (4,100);
     //T.ajouterpartenariat(e);
    // T.supprimerpartenariat(3);
    // T.modifierpartenariat(i);
    //System.out.println(T.listepartenaire(1));
    */
        
    }
    
}
