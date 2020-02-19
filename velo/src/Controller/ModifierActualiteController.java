/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sevices.ActualiteService;

/**
 *
 * @author ONS
 */
public class ModifierActualiteController implements Initializable {
    public static ActualiteService actualiteService = new ActualiteService();
    
    @FXML
    private AnchorPane id_page_modifé;

    @FXML
    private JFXTextField f_titre;

    @FXML
    private JFXButton id_modifier;

    @FXML
    private JFXTextArea id_description;

    @FXML
    private JFXComboBox<Categorie> id_categorie;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       f_titre.setText(Afficher_Mes_Actualites_Controller.getActualite_modifié().getTitre());
         id_description.setText(Afficher_Mes_Actualites_Controller.getActualite_modifié().getDescription());
          
        id_categorie.getItems().addAll(Categorie.piste,Categorie.route
        ,Categorie.montagne,Categorie.championat);
        id_categorie.setValue(Afficher_Mes_Actualites_Controller.getActualite_modifié().getCategorie());
    }
    @FXML
    private void modifer(ActionEvent event) {
        Afficher_Mes_Actualites_Controller.getActualite_modifié().setTitre(f_titre.getText());
        Afficher_Mes_Actualites_Controller.getActualite_modifié().setDescription(id_description.getText());
         Afficher_Mes_Actualites_Controller.getActualite_modifié().setCategorie(id_categorie.getValue());
         
       actualiteService.modifierActualite(Afficher_Mes_Actualites_Controller.getActualite_modifié());
        try {
                        
                       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/gui/MesActualites.fxml"));
                        id_page_modifé.getChildren().clear();
			id_page_modifé.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(Afficher_Mes_Actualites_Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        Image img = new Image("/aa.png");
         Notifications notificationBuilder = Notifications.create()
                 .title("Actualité Modifié")
                 .text("Vous avez modifé l'actualité")
                 .graphic(new ImageView(img))
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_RIGHT);
         
         notificationBuilder.show();
       
    }
}