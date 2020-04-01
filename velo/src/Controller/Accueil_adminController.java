/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author ONS
 */
public class Accueil_adminController {
        @FXML
    private HBox id_afficherTous;

    @FXML
    private HBox id_ajouter;

    @FXML
    private HBox id_statistique;

    @FXML
    private Pane content;

    @FXML
    void btn_afficherTous(MouseEvent event) {
 if (event.getTarget() == id_ajouter) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/gui/Ajouter_actualite.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(Accueil_adminController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
 
 if (event.getTarget() == id_afficherTous) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/gui/Affichage_actualite.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(Accueil_adminController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
    }
}
