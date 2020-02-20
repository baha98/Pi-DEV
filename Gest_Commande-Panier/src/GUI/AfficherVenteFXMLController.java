/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Accessoire;
import Entities.ListeProduit;
import Entities.Velo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author zoula
 */
public class AfficherVenteFXMLController implements Initializable {
    
    public static List<ListeProduit> panier = new ArrayList<ListeProduit>();
    
    
    @FXML 
    private Label lblMarque;
    @FXML
    private Label lblLibelle;
    @FXML
    private ImageView imgProduit;
    @FXML
    private Label lblPrix;
    @FXML
    private Label lblCouleur;
    @FXML
    private Button btnLouer;
    @FXML
    private Button AjouterP;
    @FXML
    private TextField qty;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
  
   public void remplir(Object obj) {
        if (obj instanceof Velo) {
//            txtfID.setText(((Velo) obj).getId_Velo()+"");
//            cmbAccVelo.setValue("Velo");
            lblLibelle.setText(((Velo) obj).getLibelle());
//            txtDescr.setText(((Velo) obj).getDescription());
            lblMarque.setText(((Velo) obj).getMarque());
            lblPrix.setText(((Velo) obj).getPrix()+"");
//            cmbEtat.setValue(((Velo) obj).getEtat());
            lblCouleur.setText(((Velo) obj).getCouleur());
            
        } else if (obj instanceof Accessoire) {
            btnLouer.setVisible(false);
            lblLibelle.setText(((Accessoire) obj).getLibelle());
            lblMarque.setText(((Accessoire) obj).getMarque());
            lblPrix.setText(((Accessoire) obj).getPrix()+"");
            lblCouleur.setText(((Accessoire) obj).getCouleur());


        }
        
   }

    @FXML
    private void AjouterPanier(ActionEvent event) {
        
        
        String libelle = lblLibelle.getText();
        Double prix = Double.parseDouble(lblPrix.getText());
        int quantite = Integer.parseInt(qty.getText());
        panier.add(new ListeProduit(libelle,prix,quantite,new Button("Retirer"),new Button("Update")));
        
        
    }
    
    
}
