/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entite.event;
import Service.eventsService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Baha Dammak
 */
public class FronteventController implements Initializable {

static ObservableList<event> obs;
public static int indice ;
    @FXML
    private AnchorPane flow;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        Afficher();

    }   
    public void Afficher()  {
    try {
        
        eventsService srv = new eventsService();
        
       
        EventcorController.i = 0;
        ArrayList<event> annonces = srv.afficherevent1();
        obs = FXCollections.observableArrayList(annonces);
        //obslsorted = FXCollections.observableArrayList((ArrayList) annonceService.trierParDate())
        //prixdesc = FXCollections.observableArrayList((ArrayList) annonceService.trierParPrixDESC());
        indice = 0;
        Node[] nodes = new Node[obs.size()];
       for (int i = 0; i < nodes.length; i++) {
           try {
                

            
                nodes[i] = FXMLLoader.load(getClass().getResource("/GUI/eventcor.fxml"));
//                e=FrontEventController.obsl.get(i);
                flow.getChildren().add(nodes[i]);
            } catch (IOException e) {
            }

        }

    } catch (ClassNotFoundException ex) {
        Logger.getLogger(FronteventController.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

}
    
