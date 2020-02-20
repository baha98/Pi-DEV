/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Location;
import Service.LocationsService;
import Service.VeloService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author itsme
 */
public class LocationInsertFXMLController implements Initializable {
    VeloService VS;
    int id_membre;
    @FXML
    private JFXDatePicker Date_debut;
    @FXML
    private JFXDatePicker Date_fin;
    @FXML
    private ChoiceBox<String> veloChoice;
    @FXML
    private Button InsertButton;
    @FXML
    private JFXTextField Prix_Field;
    @FXML
    private Button Retour_Button;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* try {
            
            VS = new VeloService();
            ResultSet res = VS.afficherVelo();
           while(res.next())
           {
              veloChoice.getItems().add(res.getString("libelle"));
           }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LocationInsertFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LocationInsertFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }    

    @FXML
    private void insertLocation(ActionEvent event) throws ClassNotFoundException, SQLException {    
         LocationsService LS = new LocationsService();
         java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());
         java.sql.Date A = java.sql.Date.valueOf(Date_debut.getValue());
         java.sql.Date B = java.sql.Date.valueOf(Date_fin.getValue());
         long diff1 = A.getTime() - sqlDate.getTime();
         long diff2 = B.getTime() - A.getTime();
         if(diff2>0 && diff1 >0)
         {
           double prix =0;
         int id_velo = 0;   
         /*ResultSet res = VS.afficherVelo();
         while(res.next())
           {
             if(res.getString("libelle").equals(veloChoice.getValue()))
             {
                 id_velo=res.getInt(1);
                 prix = res.getDouble("Prix");
             }
           }*/
         LS.ajouterlocation(new Location(id_membre,id_velo,A,B,prix));  
         }
         else if(diff1<0)
         {
             JOptionPane.showMessageDialog(null , "Date de debut ne peut pas étre avant la date d'aujourd'hui" );
         }
         else if(diff2<0)
         {
             JOptionPane.showMessageDialog(null , "Date fin ne peut pas étre avant la date de début" );
         }
         
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/LocationFXML.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void setId_membre(int id_membre)
    {
        this.id_membre=id_membre;
    }
}
