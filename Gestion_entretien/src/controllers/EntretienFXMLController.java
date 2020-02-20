/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.AffichageEntretien;
import Entite.AffichageLocation;
import Entite.Entretien;
import Service.EntretienService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author itsme
 */
public class EntretienFXMLController implements Initializable {
    AffichageEntretien Aem;
    @FXML
    private TableView<AffichageEntretien> Entretien_Table;
    @FXML
    private TableColumn<AffichageEntretien, String> Nom_membre;
    @FXML
    private TableColumn<AffichageEntretien, String> Prenom_membre;
    @FXML
    private TableColumn<AffichageEntretien, String> Nom_velo;
    @FXML
    private TableColumn<AffichageEntretien, String> Etat;
    @FXML
    private ChoiceBox<String> EtatChoice;
    @FXML
    private Button Update_Entretien_Button;
    @FXML
    private Button Delete_Entretien_Button;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField filter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Load();
        EtatChoice.getItems().add("En panne");
        EtatChoice.getItems().add("DISPONIBLE");
        EtatChoice.getItems().add("DIAGNOSTIC");
    }   

    @FXML
    private void getSelected(MouseEvent event) {
        Aem = Entretien_Table.getSelectionModel().getSelectedItem();
        if(Aem == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
             Update_Entretien_Button.setDisable(true);
             Delete_Entretien_Button.setDisable(true);
             EtatChoice.setDisable(true);
        }
        else
        {
            EtatChoice.setValue(Aem.getEtat());
            Update_Entretien_Button.setDisable(false);
            Delete_Entretien_Button.setDisable(false);
            EtatChoice.setDisable(false);
        }
    }
    private void Load()
    {
        EntretienService ent;
        try {
            ent = new EntretienService();
            ResultSet Enta =  ent.afficherEntretien();
            ObservableList<AffichageEntretien> obs = FXCollections.observableArrayList();
       while (Enta.next())
       {
           
         obs.add(new AffichageEntretien(Enta.getString("nom_membre"),Enta.getString("prenom_membre"),Enta.getString("nom_velo"),Enta.getString("etat")));
           
       }
       
       Nom_membre.setCellValueFactory(new PropertyValueFactory<AffichageEntretien, String>("nom_user"));
       Prenom_membre.setCellValueFactory(new PropertyValueFactory<AffichageEntretien, String>("prenom_user"));
       Nom_velo.setCellValueFactory(new PropertyValueFactory<AffichageEntretien, String>("nom_velo"));
       Etat.setCellValueFactory(new PropertyValueFactory<AffichageEntretien, String>("etat"));
       
       Entretien_Table.setItems(obs);
       FilteredList<AffichageEntretien> filteredData = new FilteredList<>(obs, b -> true);
		
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
		SortedList<AffichageEntretien> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(Entretien_Table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		Entretien_Table.setItems(sortedData);
                
                    Update_Entretien_Button.setDisable(true);
                    Delete_Entretien_Button.setDisable(true);
                    EtatChoice.setDisable(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LocationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LocationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    @FXML
    private void updateEntretien(ActionEvent event) throws ClassNotFoundException {
        EntretienService SEnt = new EntretienService();
       int id_entretien = SEnt.recherche(Aem);
       SEnt.modifierEntretien(new Entretien(id_entretien,EtatChoice.getValue()));
       Load();
    }

    @FXML
    private void deleteEntretien(ActionEvent event) throws ClassNotFoundException {
        EntretienService SEnt = new EntretienService();
       int id_entretien = SEnt.recherche(Aem);
       SEnt.supprimerEntretien(id_entretien);
       Load();
    }
    
}
