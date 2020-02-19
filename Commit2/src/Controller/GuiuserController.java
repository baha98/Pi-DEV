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
import Technique.TrayIconDemo;
import com.itextpdf.text.DocumentException;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Baha Dammak
 */
public class GuiuserController implements Initializable {
  ObservableList<event> obs;
    event Aem;
    @FXML
    private TableView<event> Afficherevent;
    @FXML
    private TableColumn<event, Date> dateevent;
    @FXML
    private TableColumn<event, String> nomevent;
    @FXML
    private TableColumn<event, String> lieuevent;
    @FXML
    private TableColumn<event, String> descriptionevent;
    @FXML
    private TableColumn<event, String> typeevent;
    @FXML
    private TableColumn<event, Double> idevent;

    @FXML
    private Button refresh;
    @FXML
    private Button participer;
    @FXML
    private Button performance;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           eventsService ent;
        
        
        try {
            ent = new eventsService();
            ResultSet Enta = ent.afficherevent();
            obs = FXCollections.observableArrayList();
            while (Enta.next()) {

                obs.add(new event(Enta.getInt("id_event"), Enta.getString("nom"), Enta.getString("lieu"), Enta.getString("description"), Enta.getDate("dateevent"), Enta.getString("type")));

            }

            nomevent.setCellValueFactory(new PropertyValueFactory<event, String>("nom"));
            lieuevent.setCellValueFactory(new PropertyValueFactory<event, String>("lieu"));
            descriptionevent.setCellValueFactory(new PropertyValueFactory<event, String>("description"));
            dateevent.setCellValueFactory(new PropertyValueFactory<event, Date>("dateevent"));
            typeevent.setCellValueFactory(new PropertyValueFactory<event, String>("type"));
            idevent.setCellValueFactory(new PropertyValueFactory<event, Double>("id_event"));

            Afficherevent.setItems(obs);
          
      
           delete.setDisable(true);
         
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void getSelected(MouseEvent event) {


    }

    @FXML
    private void refresh(ActionEvent event) throws ClassNotFoundException {
               try {
                       Aem = Afficherevent.getSelectionModel().getSelectedItem();
            participationService cc = new participationService();
            cc.FacturePdf(Aem.getId_event());
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void participer(ActionEvent event) throws AWTException {
      try {
          participationService P = new participationService();
              Aem = Afficherevent.getSelectionModel().getSelectedItem();
    
                String time="00:00:00";
          java.sql.Time javaSqlTime = java.sql.Time.valueOf(time);
          participation a = new participation(3,Aem.getId_event(),javaSqlTime,0);
          P.ajouterparticipation(a);
          if (SystemTray.isSupported()) {
              TrayIconDemo td = new TrayIconDemo();
              td.displayTray();
          } else {
              System.err.println("System tray not supported!");
          }
          
      } catch (ClassNotFoundException ex) {
          Logger.getLogger(GuiuserController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @FXML
    private void performance(ActionEvent event) {
          participationService ent;
        
        
        try {
            ent = new participationService();
            ResultSet Enta = ent.listeevent(3);
            obs = FXCollections.observableArrayList();
            while (Enta.next()) {

                obs.add(new event(Enta.getInt("id_event"), Enta.getString("nom"), Enta.getString("lieu"), Enta.getString("description"), Enta.getDate("dateevent"), Enta.getString("type")));

            }

            nomevent.setCellValueFactory(new PropertyValueFactory<event, String>("nom"));
            lieuevent.setCellValueFactory(new PropertyValueFactory<event, String>("lieu"));
            descriptionevent.setCellValueFactory(new PropertyValueFactory<event, String>("description"));
            dateevent.setCellValueFactory(new PropertyValueFactory<event, Date>("dateevent"));
            typeevent.setCellValueFactory(new PropertyValueFactory<event, String>("type"));
            idevent.setCellValueFactory(new PropertyValueFactory<event, Double>("id_event"));

            Afficherevent.setItems(obs);
          
            participer.setDisable(true);
           delete.setDisable(false);
            refresh.setDisable(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void delete(ActionEvent event) {
        try {
              Aem = Afficherevent.getSelectionModel().getSelectedItem();
            participationService SEnt = new participationService();
            int id = Aem.getId_event();
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer ce cadeau");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                
                if (id != 0) {
                    SEnt.supprimer2participation(id, 3);
                }
            }
            performance(event);
       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void aaaaaaaaaa(ActionEvent event) {
         eventsService ent;
        
        
        try {
            ent = new eventsService();
            ResultSet Enta = ent.afficherevent();
            obs = FXCollections.observableArrayList();
            while (Enta.next()) {

                obs.add(new event(Enta.getInt("id_event"), Enta.getString("nom"), Enta.getString("lieu"), Enta.getString("description"), Enta.getDate("dateevent"), Enta.getString("type")));

            }

            nomevent.setCellValueFactory(new PropertyValueFactory<event, String>("nom"));
            lieuevent.setCellValueFactory(new PropertyValueFactory<event, String>("lieu"));
            descriptionevent.setCellValueFactory(new PropertyValueFactory<event, String>("description"));
            dateevent.setCellValueFactory(new PropertyValueFactory<event, Date>("dateevent"));
            typeevent.setCellValueFactory(new PropertyValueFactory<event, String>("type"));
            idevent.setCellValueFactory(new PropertyValueFactory<event, Double>("id_event"));

            Afficherevent.setItems(obs);
          
            participer.setDisable(false);
           delete.setDisable(true);
            refresh.setDisable(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
