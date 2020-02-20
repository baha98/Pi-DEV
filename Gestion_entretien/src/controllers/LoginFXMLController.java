/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Utilis.ConnexionDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author itsme
 */
public class LoginFXMLController implements Initializable {
     Connection  myConnex;
       Statement ste;
       private double x=0,y=0;
    
    @FXML
    private JFXTextField Login_Field;
    @FXML
    private JFXPasswordField Password_Field;
    @FXML
    private JFXButton Login_Button;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane Login;
    @FXML
    private Label Inscri;
    @FXML
    private AnchorPane Container;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      makeDragable();
      loadInscriptionPage();
    } 
    public void loadInscriptionPage()
    {
        Inscri.setOnMouseClicked((event)->{
              
            try {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/InscriptionFXML.fxml"));
                pane.translateXProperty().set(Login.getWidth());

                 Container.getChildren().add(pane);
                     FadeTransition ft = new FadeTransition(Duration.millis(700), Login);
                     ft.setFromValue(1.0);
                     ft.setToValue(0.0);
                     ft.setCycleCount(4);
                     ft.setAutoReverse(true);
                     ft.play();
                     Login.setOpacity(0);
                    Timeline timeline = new Timeline();
                    KeyValue kv = new KeyValue(pane.translateXProperty(), 0, Interpolator.EASE_IN);
                    KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
                    timeline.getKeyFrames().add(kf);
                    timeline.setOnFinished(t -> {                  
                    Container.getChildren().remove(Login);
        });
        timeline.play();
            } catch (IOException ex) {
                Logger.getLogger(LoginFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
              
        });
    }
    public void makeDragable()
    {
        
        rootPane.setOnMousePressed(((event)->{
                x = event.getSceneX();
                y = event.getSceneY();
        }));    
        rootPane.setOnMouseDragged(((event)->{
        rootPane.getScene().getWindow().setX(event.getScreenX()-x);
        rootPane.getScene().getWindow().setY(event.getScreenY()-y);
        rootPane.getScene().getWindow().setOpacity(0.8f);
       }));
        rootPane.setOnDragDone((event)->{
            rootPane.getScene().getWindow().setOpacity(1.0f);
        });
        rootPane.setOnMouseReleased((event)->{
            rootPane.getScene().getWindow().setOpacity(1.0f);
        });
    }

    @FXML
    private void Login(ActionEvent event) throws ClassNotFoundException, SQLException, IOException, InterruptedException {
        myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
        ste = myConnex.createStatement();
        ResultSet rs=ste.executeQuery("select * from membre");       
        while(rs.next()){
            if(Login_Field.getText().equals(rs.getString("Login_Membre")) && Password_Field.getText().equals(rs.getString("Password_Membre")))
            {
                if(rs.getString("Role_membre").equals("admin"))
                {
                    
                     Parent root = (AnchorPane)FXMLLoader.load(getClass().getResource("/gui/DashboardFXML.fxml"));
                     
                     
                     for(double i=1;i>0;i=i-0.1)
                     {
                        
        
                            rootPane.getScene().getWindow().setOpacity(i);
                            Thread.sleep(100);
                     }
                     Thread.sleep(500);
                     rootPane.getChildren().setAll(root);
                     rootPane.getScene().getWindow().sizeToScene();
                     javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                     rootPane.getScene().getWindow().setX((primScreenBounds.getWidth() - rootPane.getScene().getWindow().getWidth()) / 2);
                     rootPane.getScene().getWindow().setY((primScreenBounds.getHeight() - rootPane.getScene().getWindow().getHeight()) / 2);
                     rootPane.getScene().getWindow().setOpacity(1);
                }
                else if(rs.getString("Role_membre").equals("membre"))
                {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LocationFront.fxml"));
                     Parent root = loader.load();
                     /*LocationInsertFXMLController lic = loader.getController();
                     lic.setId_membre(rs.getInt("id_membre"));*/
                     System.out.println(""+rs.getString("Role_membre")+" "+rs.getInt("id_membre"));
                     rootPane.getScene().setRoot(root);    
                }
                else if (rs.getString("Role_membre").equals("cycliste"))
                {
                     AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/EntretienFXML.fxml"));
                     rootPane.getChildren().setAll(pane);
                }
                
            }
        }     
    }
    
}
