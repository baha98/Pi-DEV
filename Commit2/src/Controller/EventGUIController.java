/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entite.event;
import Entite.participation;
import Service.eventsService;
import Service.participationService;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Baha Dammak
 */
public class EventGUIController implements Initializable {

    ObservableList<event> obs;
   
    @FXML
    private Button modifier;
    @FXML
    private Button delete;
    @FXML
    private TableColumn<participation, Time> record;
    @FXML
    private TableColumn<participation, Integer> ranking;
    @FXML
    private TableView<participation> tablepart;
    @FXML
    private TableColumn<participation, Integer> id_participant;
    @FXML
    private TableColumn<participation, Integer> id_membre;
    @FXML
    private TableColumn<participation, Integer> id_event;
    @FXML
    private JFXTimePicker recordfield;
    @FXML
    private TextField rankingfield;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
           
    }    
    public void load() {
        


    }

    
    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    

}


