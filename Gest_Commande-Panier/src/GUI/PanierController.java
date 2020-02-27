/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Accessoire;
import Entities.Commande;
import Entities.ListeProduit;
import Entities.Velo;
import Service.AccessoireService;
import Service.CommandeService;
import Service.VeloService;
import Service.iAccessoireService;
import Service.iVeloService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<ListeProduit> Products;
    @FXML
    private TableColumn<ListeProduit, String> Libelle1;
    @FXML
    private TableColumn<ListeProduit, Double> Prix1;
    @FXML
    private TableColumn<ListeProduit, Integer> Quantite1;
    @FXML
    private Button ViderP;
    @FXML
    private TextField PrixTotal;
    @FXML
    private Button RetirerP;
    @FXML
    private Button UpdateQty;
    @FXML
    private TextField lib;
    @FXML
    private TextField NvQty;
    @FXML
    private TextField prix;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CommandeService c= new CommandeService();
                ArrayList<ListeProduit> l;
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            double total=c.CalculerTotalCommande(l);
            
         Libelle1.setCellValueFactory(new PropertyValueFactory<ListeProduit, String>("libelle"));
         Prix1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Double>("prix"));
         Quantite1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Integer>("quantite"));
         
         
         
       
         PrixTotal.setText(String.valueOf(total));
         PrixTotal.setDisable(true);
         prix.setVisible(false);
         
         
         Products.getItems().clear();
         Products.setItems(ListCommande);
    }    
    private void Actualiser()
    {
        CommandeService c= new CommandeService();
                ArrayList<ListeProduit> l;
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            double total=c.CalculerTotalCommande(l);
            
         Libelle1.setCellValueFactory(new PropertyValueFactory<ListeProduit, String>("libelle"));
         Prix1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Double>("prix"));
         Quantite1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Integer>("quantite"));
        
         
         
       
         PrixTotal.setText(String.valueOf(total));
         PrixTotal.setDisable(true);
         prix.setVisible(false);
         
         
         Products.getItems().clear();
         Products.setItems(ListCommande);
        
    }

    @FXML
    private void ViderPanier(ActionEvent event) {
         ArrayList<ListeProduit> l;
         CommandeService c= new CommandeService();
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            l.clear();
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            double total=c.CalculerTotalCommande(l);
         Libelle1.setCellValueFactory(new PropertyValueFactory<ListeProduit, String>("libelle"));
         Prix1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Double>("prix"));
         Quantite1.setCellValueFactory(new PropertyValueFactory<ListeProduit, Integer>("quantite"));
         
         
        PrixTotal.setText(String.valueOf(total));
        PrixTotal.setDisable(true);
        prix.setVisible(false);
         
         
         Products.getItems().clear();
         Products.setItems(ListCommande);
    }

    @FXML
    private void AjouterCommande(ActionEvent event) throws SQLException, IOException {
        prix.setVisible(false);
        
            
      
        //Ajout Commande
        CommandeService c=new CommandeService();
        double prixTotal = Double.parseDouble(PrixTotal.getText());
        Date DateE = new Date(System.currentTimeMillis());
        int id=3;
        c.ajouterCommande(new Commande(DateE,prixTotal,id));
        
        
        //Update Qte Stock pour chaque Produit
        ArrayList<ListeProduit> l;
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            ObservableList<ListeProduit> ListCommande = FXCollections.observableArrayList(l);
            for(ListeProduit e:ListCommande)
            {
                if(e.getType().equals("Accessoire"))
                {
                    AccessoireService ac = new AccessoireService();
                    ac.UpdateStock(e.getId_produit(), e.getQuantite());
                    
                }
                else if(e.getType().equals("Velo"))
                {
                    VeloService vl = new VeloService();
                    vl.UpdateStock(e.getId_produit(), e.getQuantite());
                    
                }
            }
        
        //passer au paiement et facutre
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/AjouterPaiement.fxml"));
            Parent root;
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void RetirerPanier(ActionEvent event) {
        prix.setVisible(false);
        ListeProduit lp;
        lp = Products.getSelectionModel().getSelectedItem();
        String lb=lp.getLibelle();
        Double prix=lp.getPrix();
        int qty=lp.getQuantite();
        ArrayList<ListeProduit> l;
         CommandeService c= new CommandeService();
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            l.remove(new ListeProduit(lp.getId_produit(),lb,prix,qty,lp.getType()));
            Actualiser();
        
    }
    
    
    @FXML
    private void getSelected(MouseEvent event) {
        ListeProduit lp;
        lp = Products.getSelectionModel().getSelectedItem();
        if(lp == null)
        {
            JOptionPane.showMessageDialog(null , "Nothing selected" );
            
        }
        else
        {
            
            lib.setText(lp.getLibelle());
            lib.setDisable(true);
            prix.setText(String.valueOf(lp.getPrix()));
            prix.setVisible(false);
            
            
            
            NvQty.setText(String.valueOf(lp.getQuantite()));

            
           
                  
        }
    }

    @FXML
    private void UpdateQty(ActionEvent event) {
        
        ListeProduit lp;
        lp = Products.getSelectionModel().getSelectedItem();
        String lb="";
        Double prix1=0.0;
        int qty=0;
        int qty2=0;
        int pos=0;
        String lb2="";
        double prix2=0.0;
        lb=lp.getLibelle();
        
        prix1=lp.getPrix();
        
        qty=lp.getQuantite();
        
        qty2=Integer.parseInt(NvQty.getText());
        prix2=Double.parseDouble(prix.getText());
        lb2=lib.getText();
        ArrayList<ListeProduit> l;
         //CommandeService c= new CommandeService();
            l = (ArrayList<ListeProduit>) AfficherVenteUserController.panier;
            l.remove(new ListeProduit(lp.getId_produit(),lb,prix1,qty,lp.getType()));
            
           // pos= l.indexOf(new ListeProduit(lb,prix1,qty));
           // System.out.println(pos);
            l.add(new ListeProduit(lp.getId_produit(),lb2,prix2,qty2,lp.getType()));
           // l.set(pos, new ListeProduit(lb2,prix2,qty2));
           
         
            Actualiser();
    }
    
  
    
}
