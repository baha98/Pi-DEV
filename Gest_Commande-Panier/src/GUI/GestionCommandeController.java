/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.Paiement;
import Service.CommandeService;
import Service.PaiementService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GestionCommandeController implements Initializable {
    Commande co;
    @FXML
    private TableView<Commande> TableCommande;
    @FXML
    private TableColumn<Commande, String> Nom1;
    @FXML
    private TableColumn<Commande, String> Prenom1;
    @FXML
    private TableColumn<Commande, Double> Prix_Total1;
    @FXML
    private TableColumn<Commande, Date> Date_Commande1;
    @FXML
    private TextField search;
    @FXML
    private TextField nomMembre;
    @FXML
    private TextField prenomMembre;
    @FXML
    private DatePicker dateCommande;
    @FXML
    private TextField idCommande;
    @FXML
    private TextField prixTotal;
    @FXML
    private Button suppC;
    @FXML
    private Button modifiierC;
    @FXML
    private Button TriC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            Actualiser();
        } catch (SQLException ex) {
            Logger.getLogger(GestionCommandeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void Actualiser() throws SQLException{
        
         try {
             CommandeService c= new CommandeService();
                ArrayList<Commande> l;
            l = (ArrayList<Commande>) c.AfficherCommande();
            ObservableList<Commande> ListCommande = FXCollections.observableArrayList(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<Commande, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Commande, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<Commande, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<Commande, Date>("Date_Commande"));
         
         
         
         TableCommande.getItems().clear();
         TableCommande.setItems(ListCommande);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        co = TableCommande.getSelectionModel().getSelectedItem();
        if(co == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
            
        }
        else
        {
            
            idCommande.setText(String.valueOf(co.getId_Commande()));
            idCommande.setDisable(true);
            
            
            
            nomMembre.setText(co.getNom());
            nomMembre.setDisable(true);
            
            prenomMembre.setText(co.getPrenom());
            prenomMembre.setDisable(true);

            
            prixTotal.setText(String.valueOf(co.getPrix_Total()));

            
            dateCommande.setValue(co.getDate_Commande().toLocalDate());
                  
        }
    }

    @FXML
    private void SupprimerCommande(ActionEvent event) throws SQLException {
       CommandeService c= new CommandeService();
        co = TableCommande.getSelectionModel().getSelectedItem();
        c.SupprimerCommande(new Commande(co.getId_Commande()));
        Actualiser();
    }

    @FXML
    private void ModifierCommande(ActionEvent event) throws SQLException {
        CommandeService c= new CommandeService();
        co = TableCommande.getSelectionModel().getSelectedItem();
        
        //int idC = Integer.parseInt(idCommande.getText());
        
         Double prixT = Double.parseDouble(prixTotal.getText());
         
        java.sql.Date DateE = java.sql.Date.valueOf(dateCommande.getValue());

         
        c.ModifierCommande(new Commande(co.getId_Commande(),DateE,prixT));
        
        Actualiser();
        
    }

    @FXML
    private void SearchCommande(KeyEvent event) throws SQLException {
         String msg = search.getText().concat("%");
        //(!(msg.equals(""))){ 
      
        
        CommandeService c= new CommandeService();
                ArrayList<Commande> l;
            l = (ArrayList<Commande>) c.RechercheCommandeParNomMembre(msg);
            ObservableList<Commande> ListCommande = FXCollections.observableArrayList(l);
         Nom1.setCellValueFactory(new PropertyValueFactory<Commande, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Commande, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<Commande, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<Commande, Date>("Date_Commande"));
            
             TableCommande.getItems().clear();
             TableCommande.setItems(ListCommande);
    }

    @FXML
    private void TrierParDate(ActionEvent event) throws SQLException {
        CommandeService c= new CommandeService();
                ArrayList<Commande> l;
            l = (ArrayList<Commande>) c.TrierCommande();
            ObservableList<Commande> ListCommande = FXCollections.observableArrayList(l);
         Nom1.setCellValueFactory(new PropertyValueFactory<Commande, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Commande, String>("Prenom"));
         Prix_Total1.setCellValueFactory(new PropertyValueFactory<Commande, Double>("Prix_Total"));
         Date_Commande1.setCellValueFactory(new PropertyValueFactory<Commande, Date>("Date_Commande"));
            
             TableCommande.getItems().clear();
             TableCommande.setItems(ListCommande);
        
    }
    
}
