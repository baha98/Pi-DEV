/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Accessoire;
import entites.Velo;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.AccessoireService;
import service.VeloService;
import service.iAccessoireService;
import service.iVeloService;
import utilis.FileChooserSample;

/**
 * FXML Controller class
 *
 * @author zoula
 */
public class AjouterVeloAccessController implements Initializable {

    @FXML
    private TextField txtfLib;
    @FXML
    private TextField txtfPrix;
    @FXML
    private TextField txtfQte;
    @FXML
    private TextField txtfCoul;
    @FXML
    private TextField txtfMarque;
    @FXML
    private TextArea txtDescr;
    @FXML
    private ComboBox<String> cmbEtat;
    @FXML
    private ComboBox cmbAccVelo;
    @FXML
    private Button btnajouter;
    @FXML
    private Label lblEtat;
    @FXML 
    private Button btnFile;
    
    /**
     * Initializes the controller class.
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbEtat.getItems().addAll("Vente", "Allocation");
        cmbAccVelo.getItems().addAll("Velo", "Accessoire");
    }    
    @FXML
    private void ajout(ActionEvent event) {
        String libelle = txtfLib.getText();
        String marque = txtfMarque.getText();
        double prix = Double.parseDouble(txtfPrix.getText());
        int quantite = Integer.parseInt(txtfQte.getText());
        String Description = txtDescr.getText();
        String couleur = txtfCoul.getText();
        String etat = "Velo".equals(cmbAccVelo.getSelectionModel().getSelectedItem()) ? cmbEtat.getSelectionModel().getSelectedItem() : null;
        if (etat == null) {
            cmbEtat.setEditable(false);
        } 
        if ("Velo".equals(cmbAccVelo.getSelectionModel().getSelectedItem())) {
            iVeloService veloService = new VeloService();
            Velo velo = new Velo(libelle, marque, prix, quantite, couleur, "image", Description, etat, 3);
            veloService.ajouterVelo(velo);
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("ajout avec succes");
            a.show();
        }
        else if ("Accessoire".equals(cmbAccVelo.getSelectionModel().getSelectedItem())) {
            iAccessoireService accessoireService = new AccessoireService();
            Accessoire accessoire = new Accessoire(libelle, prix, quantite, couleur, marque, Description, "image", 3);
            if(accessoireService.ajouterAccessoire(accessoire)) {
                 Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("ajout avec succes");
            a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.NONE);
            a.setContentText("ajout avec succes");
            a.show();
            }
                
           
        }
        
    }
    @FXML
    private void Annuler(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    private void hide(ActionEvent event) {
        if ("Accessoire".equals(cmbAccVelo.getSelectionModel().getSelectedItem())) {
            cmbEtat.setVisible(false);
            lblEtat.setVisible(false);
        } if ("Velo".equals(cmbAccVelo.getSelectionModel().getSelectedItem())) {
            cmbEtat.setVisible(true);
            lblEtat.setVisible(true);
        }
    }
    @FXML 
    private void chooseFile(ActionEvent event){
       FileChooserSample.main(new String[1]);
//    File file = fileChooser.showOpenDialog();
    }
    
}
