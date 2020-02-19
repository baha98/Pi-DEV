/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entite.event;
import Service.eventsService;
import Technique.TrayIconDemo;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/****
 * FXML Controller class
 *
 * @author Baha Dammak
 */
public class Event2Controller implements Initializable {

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
    private Button ajouter;
    @FXML
    private TextField type;
    @FXML
    private TextField lieu;
    @FXML
    private TextArea description;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker date;
    @FXML
    private Button delete;
    @FXML
    private Button modifier;
    @FXML
    private TextField search;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane rootPane;

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
            FilteredList<event> filteredData = new FilteredList<>(obs, b -> true);

            // 2. Set the filter Predicate whenever the filter changes.
            search.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(aff -> {
                    // If filter text is empty, display all persons.

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (aff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches first name.
                    } else {
                        return false; // Does not match.
                    }
                });
            });

            // 3. Wrap the FilteredList in a SortedList. 
            SortedList<event> sortedData = new SortedList<>(filteredData);

            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(Afficherevent.comparatorProperty());

            // 5. Add sorted (and filtered) data to the table.
            Afficherevent.setItems(sortedData);
            modifier.setDisable(true);
            delete.setDisable(true);
            refresh.setDisable(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {

        try {
            eventsService ent;
            ent = new eventsService();
            ResultSet Enta = ent.afficherevent();
            obs = FXCollections.observableArrayList();
            while (Enta.next()) {

                obs.add(new event(Enta.getInt("id_event"), Enta.getString("nom"), Enta.getString("lieu"), Enta.getString("description"), Enta.getDate("dateevent"), Enta.getString("type")));

            }
            Afficherevent.setItems(obs);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EventGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EventGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws ClassNotFoundException, AWTException {
        eventsService P = new eventsService();

        java.sql.Date javaSqlDate = java.sql.Date.valueOf(date.getValue());

        event a = new event(nom.getText(), lieu.getText(), description.getText(), javaSqlDate, type.getText());
        P.ajouterevent(a);
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
        load();
        nom.clear();
        lieu.clear();
        description.clear();
        type.clear();
        date.getEditor().clear();
        chercher();

    }

    @FXML
    private void delete(ActionEvent event) throws ClassNotFoundException {
        Aem = Afficherevent.getSelectionModel().getSelectedItem();
        eventsService SEnt = new eventsService();
        int id = Aem.getId_event();
     
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer ce cadeau");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        
                                                if (id != 0) {
                                           SEnt.supprimerevent(id);
                                                }
                                    }
        load();
        nom.clear();
        lieu.clear();
        description.clear();
        type.clear();
         date.getEditor().clear();
        chercher();

    }

    @FXML
    private void modifier(ActionEvent event) throws ClassNotFoundException {
        Aem = Afficherevent.getSelectionModel().getSelectedItem();
        eventsService SEnt = new eventsService();
        int id = Aem.getId_event();
        java.sql.Date javaSqlDate = java.sql.Date.valueOf(date.getValue());
        event a = new event(id, nom.getText(), lieu.getText(), description.getText(), javaSqlDate, type.getText());
        SEnt.modifierevent(a);
 load();
        nom.clear();
        lieu.clear();
        description.clear();
        type.clear();
        date.getEditor().clear();
        chercher();
       

    }

    @FXML
    private void getSelected(MouseEvent event) {
        Aem = Afficherevent.getSelectionModel().getSelectedItem();

        if (Aem == null) {
            JOptionPane.showMessageDialog(null, "Nothing selected");
            modifier.setDisable(true);
            delete.setDisable(true);
            refresh.setDisable(true);
        } else {

            modifier.setDisable(false);
            delete.setDisable(false);
                refresh.setDisable(false);
            nom.setText(Aem.getNom());
            lieu.setText(Aem.getLieu());
            description.setText(Aem.getDescription());
            date.setValue(Aem.getDateevent().toLocalDate());
            type.setText(Aem.getType());
        }

    }

    @FXML
    public void chercher() {
        FilteredList<event> filteredData = new FilteredList<>(obs, b -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(aff -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (aff.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Afficherevent.comparatorProperty());
        Afficherevent.setItems(sortedData);

        // 5. Add sorted (and filtered) data to the table.
    }

  
    @FXML
    private void refresh(ActionEvent event) {
        try {
            Aem = Afficherevent.getSelectionModel().getSelectedItem();
            int id = Aem.getId_event();
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Event.fxml"));
           Parent root = loader.load();
           EventController e = loader.getController();
           e.setYarab(id);
            rootPane.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(Event2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        chercher();
        
    }

 
}
