/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Paiement;
import Service.PaiementService;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class GestionPaiementController implements Initializable {
    Paiement pa;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Paiement> TablePaiement;
    @FXML
    private TableColumn<Paiement, String> Nom1;
    @FXML
    private TableColumn<Paiement, String> Prenom1;
    @FXML
    private TableColumn<Paiement, Integer> NumCarte;
    @FXML
    private TableColumn<Paiement, Date> DateExpiration;
    @FXML
    private TableColumn<Paiement, Integer> CodeSec;
    @FXML
    private TableColumn<Paiement, String> TypePaiement;
    @FXML
    private TableColumn<Paiement, String> Pays;
    @FXML
    private TableColumn<Paiement, String> Email1;
    @FXML
    private TextField IdPaiement;
    @FXML
    private Button SupprimerP;
    @FXML
    private TextField idMembre;
    @FXML
    private TextField nomMembre;
    @FXML
    private TextField emailMembre;
    @FXML
    private TextField prenomMembre;
    @FXML
    private DatePicker dateExpiration;
    @FXML
    private TextField typePaiement;
    @FXML
    private TextField codeSec;
    @FXML
    private ChoiceBox pays;
    //Liste Des Pays 
    ObservableList<String> ListPays = FXCollections.observableArrayList("Tunisia","Algeria","America");
    
    @FXML
    private Button ModifierP;
    @FXML
    private TextField numCarte;
    @FXML
    private TextField search;
    @FXML
    private Button trier;
    @FXML
    private ImageView imgUpdate;
    @FXML
    private ImageView imgDelete;
            
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
       
        try {
            
            
            Image imgUpdatee = new Image("/Icons/update-png-3.png");
            imgUpdate.setImage(imgUpdatee);
            Image imgDeletee = new Image("/Icons/supp2.png");
            imgDelete.setImage(imgDeletee);
            PaiementService p= new PaiementService();
             
             
                ArrayList<Paiement> l;
            l = (ArrayList<Paiement>) p.AfficherPaiement1();
            ObservableList<Paiement> ListPaiement = FXCollections.observableArrayList(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("prenom"));
         NumCarte.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Num_Carte"));
         DateExpiration.setCellValueFactory(new PropertyValueFactory<Paiement, Date>("Date_Expiration"));
         CodeSec.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Code_Sec"));
         TypePaiement.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Type_Paiement"));
         Pays.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Pays"));
         Email1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Email"));
         
         
         
         TablePaiement.setItems(ListPaiement);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
       
    }    
    private void Actualiser() throws SQLException{
        
         try {
             PaiementService p= new PaiementService();
                ArrayList<Paiement> l;
            l = (ArrayList<Paiement>) p.AfficherPaiement1();
            ObservableList<Paiement> ListPaiement = FXCollections.observableArrayList(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("prenom"));
         NumCarte.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Num_Carte"));
         DateExpiration.setCellValueFactory(new PropertyValueFactory<Paiement, Date>("Date_Expiration"));
         CodeSec.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Code_Sec"));
         TypePaiement.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Type_Paiement"));
         Pays.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Pays"));
         Email1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Email"));
         
         
         TablePaiement.getItems().clear();
         TablePaiement.setItems(ListPaiement);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        pa = TablePaiement.getSelectionModel().getSelectedItem();
        if(pa == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
            
        }
        else
        {
            
            IdPaiement.setText(String.valueOf(pa.getId_Paiement()));
            IdPaiement.setDisable(true);
            
            idMembre.setText(String.valueOf(pa.getId_Membre()));
            idMembre.setDisable(true);
            
            nomMembre.setText(pa.getNom());
            nomMembre.setDisable(true);
            
            prenomMembre.setText(pa.getPrenom());
            prenomMembre.setDisable(true);
            
            emailMembre.setText(pa.getEmail());
            emailMembre.setDisable(true);
            
            numCarte.setText(String.valueOf(pa.getNum_Carte()));
            numCarte.setDisable(true);
            
            
            
            typePaiement.setText(pa.getType_Paiement());
            typePaiement.setDisable(true);
            
            dateExpiration.setValue(pa.getDate_Expiration().toLocalDate());
            dateExpiration.setDisable(true);
            
            codeSec.setText(String.valueOf(pa.getCode_Sec()));
            codeSec.setDisable(true);
            
            pays.setValue(pa.getPays());
            
            pays.setItems(ListPays);
           
            
            
            
            
            
            
        }
    }

    @FXML
    private void SupprimerPaiement(ActionEvent event) throws SQLException {
        PaiementService p= new PaiementService();
        pa = TablePaiement.getSelectionModel().getSelectedItem();
        
        if(p.SupprimerPaiement(new Paiement(pa.getId_Paiement())))
            {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("Paiement Supprimé");
                                a.show(); 
        }
        else {
             Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setContentText("Echec de Supression");
                                a.show(); 
        }
        Actualiser();
    }

    @FXML
    private void ModifierPaiement(ActionEvent event) throws SQLException {
        PaiementService p= new PaiementService();
        pa = TablePaiement.getSelectionModel().getSelectedItem();
        
        int NumC = Integer.parseInt(numCarte.getText());
        
        String typeP=typePaiement.getText();
         
        java.sql.Date DateE = java.sql.Date.valueOf(dateExpiration.getValue());
        
        
        int CodeS = Integer.parseInt(codeSec.getText());
        
        
        String PaysSel = (String) pays.getSelectionModel().getSelectedItem();
         
        if(p.ModifierPaiement(new Paiement(pa.getId_Paiement(),PaysSel)))
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
                                a.setContentText("Paiement Modifié");
                                a.show(); 
        }
        else {
             Alert a = new Alert(Alert.AlertType.ERROR);
                                 a.setContentText("Echec de modification");
                                a.show(); 
        }
        
        
        Actualiser();
        
       }
    
    
    
    @FXML
    private void SearchPaiement(KeyEvent event) throws SQLException {

        String msg = search.getText().concat("%");
        //(!(msg.equals(""))){ 

        
        PaiementService p= new PaiementService();
                ArrayList<Paiement> l;
            l = (ArrayList<Paiement>) p.RecherchePaiementParNomMembre(msg);
            ObservableList<Paiement> ListPaiement = FXCollections.observableArrayList(l);
            
             Nom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("prenom"));
         NumCarte.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Num_Carte"));
         DateExpiration.setCellValueFactory(new PropertyValueFactory<Paiement, Date>("Date_Expiration"));
         CodeSec.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Code_Sec"));
         TypePaiement.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Type_Paiement"));
         Pays.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Pays"));
         Email1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Email"));
         
         
         TablePaiement.getItems().clear();
         

         TablePaiement.setItems(ListPaiement);
        
    }

    @FXML
    private void TrierParPrenom(ActionEvent event) {
        
        
         try {
             PaiementService p= new PaiementService();
                ArrayList<Paiement> l;
            l = (ArrayList<Paiement>) p.TrierPaiement();
            ObservableList<Paiement> ListPaiement = FXCollections.observableArrayList(l);
            
         Nom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Nom"));
         Prenom1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("prenom"));
         NumCarte.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Num_Carte"));
         DateExpiration.setCellValueFactory(new PropertyValueFactory<Paiement, Date>("Date_Expiration"));
         CodeSec.setCellValueFactory(new PropertyValueFactory<Paiement, Integer>("Code_Sec"));
         TypePaiement.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Type_Paiement"));
         Pays.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Pays"));
         Email1.setCellValueFactory(new PropertyValueFactory<Paiement, String>("Email"));
         
         
         TablePaiement.getItems().clear();
         TablePaiement.setItems(ListPaiement);
        } catch (SQLException ex) {
            Logger.getLogger(GestionPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
