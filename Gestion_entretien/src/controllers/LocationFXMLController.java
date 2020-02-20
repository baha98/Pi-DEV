/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import Technique.TrayIconDemo;
import Entite.AffichageLocation;
import Entite.Location;
import Service.LocationsService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author itsme
 */
public class LocationFXMLController implements Initializable {
    AffichageLocation Alm;
    ObservableList<AffichageLocation> obs;
    @FXML
    private TableColumn<AffichageLocation, String> Nom_membre;
    @FXML
    private TableColumn<AffichageLocation, String> Prenom_membre;
    @FXML
    private TableColumn<AffichageLocation, String> Nom_velo;
    @FXML
    private TableColumn<AffichageLocation, Date> date_debut;
    @FXML
    private TableColumn<AffichageLocation, Date> date_fin;
    @FXML
    private TableView<AffichageLocation> Location_Table;
    @FXML
    private TableColumn<AffichageLocation, Double> prix;
    @FXML
    private DatePicker Date_debut;
    @FXML
    private DatePicker Date_fin;
    @FXML
    private Button Update_Location_Button;
    @FXML
    private Button Delete_Location_Button;
    @FXML
    private TextField filter;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableColumn<AffichageLocation, Image> imgcol;
    @FXML
    private TableColumn<AffichageLocation, String> Desccol;
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Load();   
    }
    private void Load()
    {
         LocationsService loc;
        
        try {
            loc = new LocationsService();
      ArrayList loca =  loc.afficherlocation();
        obs = FXCollections.observableArrayList();
       /*while (loca.next())
       {
           if(loca.getDate("date_fin").compareTo(java.sql.Date.valueOf(LocalDate.now()))<0)
           {
               loc.supprimerlocation(loca.getInt("id_location"));
               
           }
           else
           {
               ImageView A = new ImageView("/Images/img/"+loca.getString("Image"));
               A.setFitHeight(100);
               A.setFitWidth(100);
               obs.add(new AffichageLocation(A,loca.getString("Nom_membre"),loca.getString("prenom_membre"),loca.getString("libelle"),loca.getString("Descrption"),loca.getDate("date_debut"),loca.getDate("date_fin"),loca.getDouble("prix_l")));
           }
       }*/
       obs.addAll(loca);
       imgcol.setCellValueFactory(new PropertyValueFactory<>("image"));
       imgcol.setPrefWidth(100);
       Nom_membre.setCellValueFactory(new PropertyValueFactory<AffichageLocation, String>("nom_user"));
       Prenom_membre.setCellValueFactory(new PropertyValueFactory<AffichageLocation, String>("prenom_user"));
       Nom_velo.setCellValueFactory(new PropertyValueFactory<AffichageLocation, String>("nom_velo"));
       Desccol.setCellValueFactory(new PropertyValueFactory<AffichageLocation, String>("Descrption"));
       date_debut.setCellValueFactory(new PropertyValueFactory<AffichageLocation, Date>("date_debut"));
       date_fin.setCellValueFactory(new PropertyValueFactory<AffichageLocation, Date>("date_fin"));
       prix.setCellValueFactory(new PropertyValueFactory<AffichageLocation, Double>("prix"));
       Location_Table.setItems(obs);
        
       FilteredList<AffichageLocation> filteredData = new FilteredList<>(obs, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filter.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(aff -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (aff.getNom_user().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} 
				else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<AffichageLocation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(Location_Table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		Location_Table.setItems(sortedData);
                Update_Location_Button.setDisable(true);
                Delete_Location_Button.setDisable(true);
                Date_debut.setDisable(true);
                Date_fin.setDisable(true);
       } catch (ClassNotFoundException ex) {
            Logger.getLogger(LocationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @FXML
    private void getSelected(MouseEvent event) {
        Alm = Location_Table.getSelectionModel().getSelectedItem();
        if(Alm == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
            Update_Location_Button.setDisable(true);
            Delete_Location_Button.setDisable(true);
            Date_debut.setDisable(true);
            Date_fin.setDisable(true);
        }
        else
        {
            Date date = Alm.getDate_debut();
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
            String strDate = dateFormat.format(date);  
            Date_debut.getEditor().setText(strDate);
            date = Alm.getDate_fin();
            dateFormat = new SimpleDateFormat("dd/mm/yyyy");  
            strDate = dateFormat.format(date);  
            Date_fin.getEditor().setText(strDate);
            Update_Location_Button.setDisable(false);
            Delete_Location_Button.setDisable(false);
            Date_debut.setDisable(false);
            Date_fin.setDisable(false);
        }
    }

    @FXML
    private void updateLocation(ActionEvent event) throws ClassNotFoundException {
       LocationsService Sloc = new LocationsService();
       int id_location = Sloc.recherche(Alm);
       java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());
       java.sql.Date A = java.sql.Date.valueOf(Date_debut.getValue());
       java.sql.Date B = java.sql.Date.valueOf(Date_fin.getValue());
       long diff1 = A.getTime() - sqlDate.getTime();
       long diff2 = B.getTime() - A.getTime();
       if(diff2>0 && diff1 >0)
         {
           long diff = A.getTime() - B.getTime();
            float days = (diff / (1000*60*60*24));
            Sloc.modifierlocation(new Location(id_location,A,B));
            Date_debut.getEditor().clear();
            Date_fin.getEditor().clear();    
            Load();
         }
         else if(diff1<0)
         {
             JOptionPane.showMessageDialog(null , "Date de debut de la location ne peut pas étre avant la date d'aujourd'hui" );
         }
         else if(diff2<0)
         {
             JOptionPane.showMessageDialog(null , "Date de fin ne la location peut pas étre avant la date de début" );
         }
       
    }

    @FXML
    private void deleteLocation(ActionEvent event) throws ClassNotFoundException {
       LocationsService Sloc = new LocationsService();
       int id_location = Sloc.recherche(Alm);
       Sloc.supprimerlocation(id_location);  
       Load();
    }  
}    
