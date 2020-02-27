/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Paiement;
import Service.CommandeService;
import Service.PaiementService;
import com.itextpdf.text.DocumentException;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterPaiementController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //Liste Des Pays 
    ObservableList<String> ListPays = FXCollections.observableArrayList("Tunisia","Algeria","America");
    
    @FXML
    private TextField IdMembre;
    @FXML
    private RadioButton MasterCard;
     @FXML
    private ToggleGroup TypePaiement;
    @FXML
    private RadioButton Visa;
    @FXML
    private RadioButton Edinar;
    @FXML
    private TextField NumCarte;
    @FXML
    private DatePicker DateExpiration;
    @FXML
    private TextField CodeSec;
    @FXML
    private ChoiceBox Pays;
    @FXML
    private Button Payer;
    @FXML
    private Button facture;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Liste Choix Pays 
        Pays.setValue("Tunisia");
        Pays.setItems(ListPays);
    }    
    
    
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
    
    @FXML
    private void AjouterPaiement(ActionEvent event) throws AWTException {
        
       
        try {
        
        
        
        java.sql.Date DateE = java.sql.Date.valueOf(DateExpiration.getValue());
        Date DateS = new Date(System.currentTimeMillis());
        
        
        
       
             if(DateE.before(DateS))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Carte Expiré");
                    a.show(); 
        }
             
        else if(TypePaiement.getToggles().isEmpty())
                     {
                          Alert a = new Alert(Alert.AlertType.ERROR);
                                a.setContentText("Num Carte doit être des chiffres seuelement");
                                a.show(); 
                     }
        else if((!estUnEntier(NumCarte.getText()))&&(NumCarte.getText().isEmpty()))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Num Carte doit être des chiffres seuelement");
                    a.show(); 
        }
        else if((!estUnEntier(CodeSec.getText()))&&(CodeSec.getText().length()!=4 ))
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Code Sécurité doit être 4 chiffres seuelement");
                    a.show(); 
        }
   
        else 
        {
        int idM = Integer.parseInt(IdMembre.getText());
        RadioButton selectedRadioButton = (RadioButton) TypePaiement.getSelectedToggle();
        String TypeP = selectedRadioButton.getText();
        int NumC = Integer.parseInt(NumCarte.getText());
        int CodeS = Integer.parseInt(CodeSec.getText());  
        String PaysSel = (String) Pays.getSelectionModel().getSelectedItem();

        
        PaiementService p = new PaiementService();
        CommandeService c= new CommandeService();
        Paiement p1 = new Paiement(0,idM,TypeP,NumC,DateE,CodeS,PaysSel);
            p.ajouterPaiement(p1);
            c.PayerCommande(3);
            p.PaiementEffectue();
        }
            
            
//            FXMLLoader loader = new FXMLLoader
//                        (getClass()
//                         .getResource("AffichePersonne.fxml"));
//            try {
//                Parent root = loader.load();
//                AffichePersonneController apc = loader.getController();
//                apc.setResNom(tfNom);
//                apc.setResPrenom(tfPrenom);
//                tfNom.getScene().setRoot(root);
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//                }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterPaiementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Facture(ActionEvent event) throws SQLException, DocumentException, IOException {
         //int idM = Integer.parseInt(IdMembre.getText());
         CommandeService c= new CommandeService();
         c.FacturePdf(3);
         
    }
    
}
