/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entite.event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Baha Dammak
 */
public class EventcorController implements Initializable {

static int i ;
public int t;
public static event e ;
    @FXML
    private Label nomfield;
    @FXML
    private Label descriptionfield;
    @FXML
    private VBox Vbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
      //  lignecommandeService = new LigneCommandeService();
     
               
                nomfield.setText(FronteventController.obs.get(i).getNom());
                descriptionfield.setText(FronteventController.obs.get(i).getDescription());
                i++;
               // t = FronteventController.obs.get(i).getId_event();
                //e=FronteventController.obs.get(i);
                //S=FrontEventController.obsl.get(i).getNomEvnet();
             
             
          /*
                participer.setOnAction((event) -> {
                    try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ParticipVol.fxml"));
                                Parent root = loader.load();
                                ParticipVolController rc = loader.getController();
                                participer.getScene().setRoot(root);
                                
                            } catch (IOException ex) {
                                System.out.println(ex.getMessage());

                            }
                });
*/
                 
                             
        // TODO
    }    
    
}
