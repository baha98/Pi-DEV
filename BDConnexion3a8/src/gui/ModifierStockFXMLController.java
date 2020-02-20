/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Accessoire;
import entites.Velo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.AccessoireService;
import service.VeloService;
import service.iAccessoireService;
import service.iVeloService;

/**
 * FXML Controller class
 *
 * @author zoula
 */
public class ModifierStockFXMLController implements Initializable {

    @FXML
    private TextField txtfLib;
    @FXML
    private TextField txtfPrix;
    @FXML
    private TextField txtfID;
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
    private Button btnModif;
    @FXML
    private Label lblEtat;
    @FXML 
    private Button btnFile;
    private Object thisObj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    
    public void transfert(Object obj) {
        thisObj = obj;
        if (obj instanceof Velo) {
            txtfID.setText(((Velo) obj).getId_Velo()+"");
            cmbAccVelo.setValue("Velo");
            txtfLib.setText(((Velo) obj).getLibelle());
            txtDescr.setText(((Velo) obj).getDescription());
            txtfMarque.setText(((Velo) obj).getMarque());
            txtfPrix.setText(((Velo) obj).getPrix()+"");
            txtfQte.setText(((Velo) obj).getQte()+"");
            cmbEtat.setValue(((Velo) obj).getEtat());
            txtfCoul.setText(((Velo) obj).getCouleur());
        }
        else if (obj instanceof Accessoire) {
            txtfID.setText(((Accessoire) obj).getId_Accessoire()+"");
            cmbAccVelo.setValue("Accessoire");
            txtfLib.setText(((Accessoire) obj).getLibelle());
            txtDescr.setText(((Accessoire) obj).getDescription());
            txtfMarque.setText(((Accessoire) obj).getMarque());
            txtfPrix.setText(((Accessoire) obj).getPrix()+"");
            txtfQte.setText(((Accessoire) obj).getQte()+"");
            cmbEtat.setVisible(false);
//            cmbEtat.setValue(((Accessoire) obj).getEtat());
            txtfCoul.setText(((Accessoire) obj).getCouleur());
        }
        
    }
    @FXML
    void modifier() {
        String libelle = txtfLib.getText();
        String marque = txtfMarque.getText();
        double prix = Double.parseDouble(txtfPrix.getText());
        int quantite = Integer.parseInt(txtfQte.getText());
        int id = Integer.parseInt(txtfID.getText());
        String Description = txtDescr.getText();
        String couleur = txtfCoul.getText();
        String etat = "Velo".equals(cmbAccVelo.getSelectionModel().getSelectedItem()) ? cmbEtat.getSelectionModel().getSelectedItem() : null;
        if (etat == null) {
            cmbEtat.setEditable(false);
        } 
        if ("Velo".equals(cmbAccVelo.getSelectionModel().getSelectedItem())) {
            iVeloService veloService = new VeloService();
            Velo velo = new Velo(id,libelle, marque, prix, quantite, couleur, "image", Description, etat, 3);
            try {
            veloService.modifierVelo(velo);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("modifier avec succes");
            a.show(); 
            } catch (Exception e ) {
                Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("modifier avec succes");
            a.show(); 
            }
        }
        else if ("Accessoire".equals(cmbAccVelo.getSelectionModel().getSelectedItem())) {
            iAccessoireService accessoireService = new AccessoireService();
            Accessoire accessoire = new Accessoire(id,libelle, prix, quantite, couleur, marque, Description, "image", 3);
            accessoireService.modifierAccessoire(accessoire);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("modifier avec succes");
            a.show();
        }
        
    }
    
   
    
    
}
