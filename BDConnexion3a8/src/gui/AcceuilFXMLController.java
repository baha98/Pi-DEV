/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zoula
 */
public class AcceuilFXMLController implements Initializable {

    @FXML
    private Button btnAfficher;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void ajoutInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterVeloAccess.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void afficheInterface() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficheStockFXML.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AcceuilFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
