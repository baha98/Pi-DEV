/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entite.*;
import Service.eventsService;
import Service.participationService;
import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Time;
import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Baha Dammak
 */
public class EventController implements Initializable {
    resultat Aem ;
    @FXML
    private TableView<resultat> listparticipant;
    @FXML
    private TableColumn<resultat, String> nomP;
    @FXML
    private TableColumn<resultat, String> prenomP;
    @FXML
    private TableColumn<resultat, Time> recordP;
    @FXML
    private TableColumn<resultat, String> rankingP;
    @FXML
    private TextField yarab;
    @FXML
    private Button pdf;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<resultat, Integer> id_participant;
    @FXML
    private Button modifier;
    @FXML
    private Button delete;
    @FXML
    private JFXTimePicker recordfield;
    @FXML
    private TextField rankingfield;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
            // TODO
            id_participant.setCellValueFactory(new PropertyValueFactory<resultat, Integer>("id_participant"));
          nomP.setCellValueFactory(new PropertyValueFactory<resultat, String>("nom"));
            prenomP.setCellValueFactory(new PropertyValueFactory<resultat, String>("prenom"));
            recordP.setCellValueFactory(new PropertyValueFactory<resultat, Time>("record"));
            rankingP.setCellValueFactory(new PropertyValueFactory<resultat,String>("ranking"));

     modifier.setDisable(true);
            delete.setDisable(true);
        
        
     
    }

    public TextField getYarab() {
        return yarab;
    }

    public void setYarab(int yarab) {
        this.yarab.setText(Integer.toString(yarab));
    }

    @FXML
    private void aaaaaaaaaa(ActionEvent event) {
        

        try {
            participationService cc = new participationService();
            ObservableList<resultat> obs;
            obs = FXCollections.observableArrayList();
            obs.addAll(cc.listeparticipant(Integer.parseInt(yarab.getText())));
            listparticipant.setItems(obs);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void pdf(ActionEvent event) throws ClassNotFoundException {
        try {
            participationService cc = new participationService();
            cc.FacturePdf(Integer.parseInt(yarab.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
          
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/event2.fxml"));
            rootPane.getChildren().setAll(pane);
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            Aem = listparticipant.getSelectionModel().getSelectedItem();
            participationService SEnt = new participationService();
            int id = Aem.getId_participant();
            int rank = Integer.parseInt(rankingfield.getText());
            java.sql.Time javaSqlTime = java.sql.Time.valueOf(recordfield.getValue());
            participation a = new participation(id, javaSqlTime ,rank);
            SEnt.modifierparticipation(a);
             aaaaaaaaaa(event);
        rankingfield.clear();
      ;
        recordfield.getEditor().clear();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
     
    }

    @FXML
    private void delete(ActionEvent event) {
        try {
            Aem = listparticipant.getSelectionModel().getSelectedItem();
            participationService SEnt = new participationService();
            int id = Aem.getId_participant();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer ce cadeau");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                
                if (id != 0) {
                    SEnt.supprimerparticipation(id);
                }
            }
            aaaaaaaaaa(event);
            rankingfield.clear();
            
            recordfield.getEditor().clear();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void getselected(MouseEvent event) {
                Aem = listparticipant.getSelectionModel().getSelectedItem();

        if (Aem == null) {
            JOptionPane.showMessageDialog(null, "Nothing selected");
            modifier.setDisable(true);
            delete.setDisable(true);
       
        } else {

            modifier.setDisable(false);
            delete.setDisable(false);
        
            rankingfield.setText(Integer.toString(Aem.getRanking()));
            
           
            
            recordfield.setValue(Aem.getRecord().toLocalTime());
             recordfield.setIs24HourView(true) ;
             
     
        }
    }
    

  
    
}
