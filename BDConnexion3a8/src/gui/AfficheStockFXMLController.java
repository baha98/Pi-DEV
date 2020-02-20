/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entites.Accessoire;
import entites.Velo;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import service.AccessoireService;
import service.VeloService;
import service.iAccessoireService;
import service.iVeloService;

/**
 * FXML Controller class
 *
 * @author zoula
 */
public class AfficheStockFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tblView;
    @FXML
    private ComboBox cmbVeloAccess;
    @FXML 
    private Button btnquit;
    @FXML
    private TextField txtMarque;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbVeloAccess.getItems().addAll("Velo", "Accessoire");
    }   
    @FXML
    private void afficher(ActionEvent event) {
    if ("Velo".equals(cmbVeloAccess.getSelectionModel().getSelectedItem())) {
        affichervelo();
    }
    else if ("Accessoire".equals(cmbVeloAccess.getSelectionModel().getSelectedItem())) {
        afficheraccessoire();
    }
     

//      StackPane root = new StackPane();
//      root.setPadding(new Insets(5));
//      root.getChildren().add(tblView);
 
//      stage.setTitle("TableView (o7planning.org)");
// 
//      Scene scene = new Scene(root, 450, 300);
//      stage.setScene(scene);
//      stage.show();
  }
    private void afficheraccessoire () {
        tblView.getItems().remove(tblView.getItems());
        TableColumn<Accessoire, Integer> id //
              = new TableColumn<Accessoire, Integer>("id");
        id.setVisible(false);
      TableColumn<Accessoire, String> libelle //
              = new TableColumn<Accessoire, String>("Libelle");
 
      // Create column Email (Data type of String).
      TableColumn<Accessoire, String> marque//
              = new TableColumn<Accessoire, String>("marque");
 
      // Create column FullName (Data type of String).
      TableColumn<Accessoire, Double> prix//
              = new TableColumn<Accessoire, Double>("prix");
 
      // Create 2 sub column for FullName.
      TableColumn<Accessoire, Integer> quantite //
              = new TableColumn<Accessoire, Integer>("quantité");
 
      TableColumn<Accessoire, String> couleur //
              = new TableColumn<Accessoire, String>("couleur");
      TableColumn<Accessoire, String> description //
              = new TableColumn<Accessoire, String>("description");
      TableColumn<Accessoire, String> image //
              = new TableColumn<Accessoire, String>("image");
      TableColumn<Accessoire, Integer> membre //
              = new TableColumn<Accessoire, Integer>("membre");
  id.setCellValueFactory(
        new PropertyValueFactory<Accessoire, Integer>("Id_Accessoire"));
  libelle.setCellValueFactory(
        new PropertyValueFactory<Accessoire, String>("Libelle"));
  prix.setCellValueFactory(
        new PropertyValueFactory<Accessoire, Double>("Prix"));
  quantite.setCellValueFactory(
        new PropertyValueFactory<Accessoire, Integer>("Qte"));
  couleur.setCellValueFactory(
        new PropertyValueFactory<Accessoire, String>("Couleur"));
  marque.setCellValueFactory(
        new PropertyValueFactory<Accessoire, String>("Marque"));
  description.setCellValueFactory(
        new PropertyValueFactory<Accessoire, String>("Description"));
  image.setCellValueFactory(
        new PropertyValueFactory<Accessoire, String>("Image"));
  membre.setCellValueFactory(new PropertyValueFactory<Accessoire, Integer>("Id_Membre"));
  tblView.getColumns().clear();
 iAccessoireService accessService = new AccessoireService();
 List list;
 if (txtMarque.getText() != null && !txtMarque.getText().isEmpty()) {
     list = accessService.rechercheParMarque(txtMarque.getText());
 } else
  list = accessService.triParPrix();
       ObservableList obs = FXCollections.observableArrayList(list.toArray());
       
      tblView.getColumns().addAll(id,libelle, prix,quantite,couleur, marque,description,image,membre);
      addButtonModifierToTable();
      addButtonSupprimerToTable();
      addButtonViewToTable();
       
      tblView.setItems(obs);
    }
    private void affichervelo() {
        tblView.getItems().remove(tblView.getItems());
        TableColumn<Velo, Integer> id //
              = new TableColumn<Velo, Integer>("id");
        id.setVisible(false);
      TableColumn<Velo, String> libelle //
              = new TableColumn<Velo, String>("Libelle");
 
      // Create column Email (Data type of String).
      TableColumn<Velo, String> marque//
              = new TableColumn<Velo, String>("marque");
 
      // Create column FullName (Data type of String).
      TableColumn<Velo, Double> prix//
              = new TableColumn<Velo, Double>("prix");
 
      // Create 2 sub column for FullName.
      TableColumn<Velo, Integer> quantite //
              = new TableColumn<Velo, Integer>("quantité");
 
      TableColumn<Velo, String> couleur //
              = new TableColumn<Velo, String>("couleur");
      TableColumn<Velo, String> description //
              = new TableColumn<Velo, String>("description");
      TableColumn<Velo, String> etat //
              = new TableColumn<Velo, String>("etat");
      TableColumn<Velo, String> image //
              = new TableColumn<Velo, String>("image");
      TableColumn<Velo, Integer> membre //
              = new TableColumn<Velo, Integer>("membre");
  id.setCellValueFactory(
        new PropertyValueFactory<Velo, Integer>("Id_Velo"));
  libelle.setCellValueFactory(
        new PropertyValueFactory<Velo, String>("Libelle"));
  prix.setCellValueFactory(
        new PropertyValueFactory<Velo, Double>("Prix"));
  quantite.setCellValueFactory(
        new PropertyValueFactory<Velo, Integer>("Qte"));
  couleur.setCellValueFactory(
        new PropertyValueFactory<Velo, String>("Couleur"));
  marque.setCellValueFactory(
        new PropertyValueFactory<Velo, String>("Marque"));
  description.setCellValueFactory(
        new PropertyValueFactory<Velo, String>("Description"));
  image.setCellValueFactory(
        new PropertyValueFactory<Velo, String>("Image"));
  membre.setCellValueFactory(new PropertyValueFactory<Velo, Integer>("Id_Membre"));
  etat.setCellValueFactory(new PropertyValueFactory<Velo, String>("Etat"));
    tblView.getColumns().clear();

 iVeloService veloService = new VeloService();
 List list;
 if (txtMarque.getText() != null && !txtMarque.getText().isEmpty()) {
     list = veloService.rechercheParMarque(txtMarque.getText());
 } else
  list = veloService.triParPrix();
       ObservableList obs = FXCollections.observableArrayList(list.toArray());
      tblView.getColumns().addAll(id,libelle, prix,quantite,couleur, marque,description,etat,image,membre);
      addButtonModifierToTable();
      addButtonSupprimerToTable();
      addButtonViewToTable(); 
      tblView.setItems(obs);
//      if (tblView.getColumns().isEmpty()) {
//      addButtonModifierToTable();
//      addButtonSupprimerToTable();
//      addButtonViewToTable();}
    }
 
//  public static void main(String[] args) {
//      launch(args);
//  }
 private void addButtonModifierToTable() {
        TableColumn<Accessoire, Void> colBtn = new TableColumn("Modifier");

        Callback<TableColumn<Accessoire, Void>, TableCell<Accessoire, Void>> cellFactory = new Callback<TableColumn<Accessoire, Void>, TableCell<Accessoire, Void>>() {
            @Override
            public TableCell<Accessoire, Void> call(final TableColumn<Accessoire, Void> param) {
                final TableCell<Accessoire, Void> cell = new TableCell<Accessoire, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Parent root;
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierStockFXML.fxml"));
                                root = loader.load();
                                ModifierStockFXMLController controller = loader.getController();
                                controller.transfert(tblView.getSelectionModel().getSelectedItem());
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheStockFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
      
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tblView.getColumns().add(colBtn);

    }
    private void addButtonSupprimerToTable() {
        TableColumn<Accessoire, Void> colBtn = new TableColumn("Supprimer");

        Callback<TableColumn<Accessoire, Void>, TableCell<Accessoire, Void>> cellFactory = new Callback<TableColumn<Accessoire, Void>, TableCell<Accessoire, Void>>() {
            @Override
            public TableCell<Accessoire, Void> call(final TableColumn<Accessoire, Void> param) {
                final TableCell<Accessoire, Void> cell = new TableCell<Accessoire, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Object obj = tblView.getSelectionModel().getSelectedItem();
                            if (obj == null) {
                                Alert a = new Alert(Alert.AlertType.ERROR);
                                 a.setContentText("Selectionner un produit");
                                a.show(); 
                            }
                            if ("Velo".equals(cmbVeloAccess.getSelectionModel().getSelectedItem())) {
                                iVeloService veloService = new VeloService();
                                veloService.supprimerVelo((Velo) obj);
                                 Alert a = new Alert(Alert.AlertType.INFORMATION);
                                 a.setContentText("Supprimer avec succes");
                                a.show(); 
                            } else if ("Accessoire".equals(cmbVeloAccess.getSelectionModel().getSelectedItem())) {
                                iAccessoireService accessoireService = new AccessoireService();
                                accessoireService.supprimerAccessoire((Accessoire) obj);
                                 Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Supprimer avec succes");
            a.show(); 
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
//                        tblView.getItems().clear();
                     
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tblView.getColumns().add(colBtn);

    }
    
    private void addButtonViewToTable() {
        TableColumn<Accessoire, Void> colBtn = new TableColumn("Details");

        Callback<TableColumn<Accessoire, Void>, TableCell<Accessoire, Void>> cellFactory = new Callback<TableColumn<Accessoire, Void>, TableCell<Accessoire, Void>>() {
            @Override
            public TableCell<Accessoire, Void> call(final TableColumn<Accessoire, Void> param) {
                final TableCell<Accessoire, Void> cell = new TableCell<Accessoire, Void>() {

                    private final Button btn = new Button("Details");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Parent root;
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherVenteFXML.fxml"));
                                root = loader.load();
                                AfficherVenteFXMLController controller = loader.getController();
                                controller.remplir(tblView.getSelectionModel().getSelectedItem());
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficheStockFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
//                        tblView.getItems().clear();
                     
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tblView.getColumns().add(colBtn);

    }

    
     @FXML 
    private void quitter(ActionEvent event) {
        System.exit(0);
    }

 
    }
    
                
