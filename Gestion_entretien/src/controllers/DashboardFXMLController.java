/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author itsme
 */
public class DashboardFXMLController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label LocationDash;
    @FXML
    private AnchorPane Container;
    @FXML
    private FontAwesomeIcon Minimize_Button;
    @FXML
    private FontAwesomeIcon Close_Button;
    @FXML
    private GridPane GP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void LoadLocation(MouseEvent event) {
        try {
            Container.getChildren().removeAll(Container.getChildren());
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/LocationFXML.fxml"));
                 Container.getChildren().add(pane);
                    Container.setOpacity(0);
                     FadeTransition ft = new FadeTransition(Duration.millis(700), Container);
                     ft.setFromValue(0);
                     ft.setToValue(1);
                     ft.play();
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void Minimize_App(MouseEvent event) {
        
       
    }

    @FXML
    private void Close_App(MouseEvent event) {
        
                Platform.exit();
                System.exit(0);
        
    }

    @FXML
    private void LoadEntretien(MouseEvent event) {
        try {
            Container.getChildren().removeAll(Container.getChildren());
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/EntretienFXML.fxml"));
                 Container.getChildren().add(pane);
                    Container.setOpacity(0);
                     FadeTransition ft = new FadeTransition(Duration.millis(700), Container);
                     ft.setFromValue(0);
                     ft.setToValue(1);
                     ft.play();
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
