/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entities.Actualite;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sevices.ActualiteService;

/**
 *
 * @author ONS
 */
public class Afficher_Mes_Actualites_Controller implements Initializable{
    
    public static ActualiteService actualiteService = new ActualiteService();
    private static Actualite actualite_modifié = new Actualite();

    public static Actualite getActualite_modifié() {
        return actualite_modifié;
    }

    public static void setActualite_modifié(Actualite actualite_modifié) {
        Afficher_Mes_Actualites_Controller.actualite_modifié = actualite_modifié;
    }
    @FXML
    private AnchorPane id_affichage_Forum;

    @FXML
    private JFXComboBox<Categorie> id_categorie;

    @FXML
    private JFXTextField id_recherche;

    @FXML
    private ScrollPane x;

    @FXML
    private VBox pnItems;

    @FXML
    private JFXCheckBox id_nbre_vue;

    @FXML
    private JFXCheckBox id_date_creation;

    @FXML
    void afficherbycategories(ActionEvent event) throws SQLException {
        pnItems.getChildren().clear();
                loadCategories();
    }

    @FXML
    void rechercher(KeyEvent event) throws SQLException {
        pnItems.getChildren().clear();
                loadNames();
    }

    @FXML
    void trier_par_date_creation(ActionEvent event) throws SQLException {
        if( id_date_creation.isSelected()){
           id_nbre_vue.setSelected(false);
           pnItems.getChildren().clear();
           loadDateCreation();
       }
    }

    @FXML
    void trier_par_nbre_vue(ActionEvent event) throws SQLException {
        if(id_nbre_vue.isSelected()){
    
           id_date_creation.setSelected(false);
           pnItems.getChildren().clear();
           loadNbreVues();
       }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_categorie.getItems().addAll(Categorie.championat,Categorie.montagne,Categorie.piste,Categorie.route);
        try {
             Afficher_Actualite();
        } catch (SQLException ex) {
            Logger.getLogger(Afficher_Mes_Actualites_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void Afficher_Actualite() throws SQLException {
		List<Actualite> actualites = fetchActualite();
		Node[] nodes = new Node[actualites.size()];

		AtomicInteger i = new AtomicInteger(0);
		actualites.forEach(actualite -> {
			int j = i.getAndIncrement();
			Node node = nodes[j] = loadNewItemNode();

			displaySujetDetails(node, actualite);

			setupActions(node, actualite, j);

			setHoverStyleForNode(nodes, j);

			pnItems.getChildren().add(node);
		});

		if (nodes.length >= 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}


	private List<Actualite> fetchActualite() throws SQLException {
		try {
			return actualiteService.afficherActualite();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	private Node loadNewItemNode() {
		try {
			return FXMLLoader.load(getClass().getResource("/gui/Item_mes_actualites.fxml"));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private void displaySujetDetails(Node node, Actualite actualite) {
		Label item_titre = (Label) node.lookup(".item_titre");
		item_titre.setText(actualite.getTitre());
		Label item_description = (Label) node.lookup(".item_description");
		item_description.setText(actualite.getDescription());
                Label item_categorie = (Label) node.lookup(".item_categorie");
		item_categorie.setText(actualite.getCategorie().toString());
                Label item_date = (Label) node.lookup(".item_date");
		item_date.setText(actualite.getDate_actualite());
		
		// other properties
		// ...
	}

	private void setupActions(Node node, Actualite actualite, int index) {
            
		Button deleteButton = (Button) node.lookup(".item_action_supprimer");
		deleteButton.setOnMouseClicked(DeleteEventHandler(actualite, index));
                Button modifierButton = (Button) node.lookup(".item_action_modifier");
		modifierButton.setOnMouseClicked(UpdateEventHandler(actualite, index));
	}

	private void setHoverStyleForNode(Node[] nodes, int i) {
		final int j = i;
		nodes[i].setOnMouseEntered(even -> {
			nodes[j].setStyle("-fx-background-color : #0A0E3F");
		});
		nodes[i].setOnMouseExited(even -> {
			nodes[j].setStyle("-fx-background-color : #02030A");
		});
	}

	private EventHandler<MouseEvent> DeleteEventHandler(Actualite actualite, int index) {
		return event -> {
			
                  actualiteService.supprimerActualite(actualite.getId_actualite());
			
			pnItems.getChildren().remove(index);
                        
         Notifications notificationBuilder = Notifications.create()
                 .title("actualité Supprimée")
                 .text("Vous avez supprimé votre actualite")
                 .graphic(null)
                 .hideAfter(Duration.seconds(5))
                 .position(Pos.TOP_RIGHT);
         
         notificationBuilder.showError();
		};
	}
        
        private EventHandler<MouseEvent> UpdateEventHandler(Actualite actualite, int index) {
		return event -> {
			
                  try {
                         actualite_modifié=actualite;
                       AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/gui/ModifierActualite.fxml"));
                        id_affichage_Forum.getChildren().clear();
			id_affichage_Forum.getChildren().add(newLoadedPane);
                    } catch (IOException ex) {
                        Logger.getLogger(Afficher_Mes_Actualites_Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
				
		};
	}
        
        
        
         private List<Actualite> fetchCategories(Categorie categorie) throws SQLException {
		try {
			return actualiteService.afficherActualiteParCategorie(categorie);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
    
	private void loadCategories() throws SQLException {
		List<Actualite> actualites = fetchCategories(id_categorie.getSelectionModel().getSelectedItem());
		Node[] nodes = new Node[actualites.size()];

		AtomicInteger i = new AtomicInteger(0);
		actualites.forEach(actualite -> {
			int j = i.getAndIncrement();
			Node node = nodes[j] = loadNewItemNode();

			displaySujetDetails(node, actualite);

			setupActions(node, actualite, j);

			setHoverStyleForNode(nodes, j);

			pnItems.getChildren().add(node);
		});

		if (nodes.length > 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}

    
	private void loadNames() throws SQLException {
		List<Actualite> actualites = fetchActualite();
		Node[] nodes = new Node[actualites.size()];

		AtomicInteger i = new AtomicInteger(0);
		actualites.forEach(sujet -> {
			int j = i.getAndIncrement();
                        if(sujet.getTitre().contains(id_recherche.getCharacters())){
			Node node = nodes[j] = loadNewItemNode();

			displaySujetDetails(node, sujet);

			setupActions(node, sujet, j);

			setHoverStyleForNode(nodes, j);

			pnItems.getChildren().add(node);
                        }});

		if (nodes.length > 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}
        
        private List<Actualite> fetchNbreVues() throws SQLException {
		try {
			return actualiteService.TrierParNbreVue();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
    
	private void loadNbreVues() throws SQLException {
		List<Actualite> sujets = fetchNbreVues();
		Node[] nodes = new Node[sujets.size()];

		AtomicInteger i = new AtomicInteger(0);
		sujets.forEach(sujet -> {
			int j = i.getAndIncrement();
			Node node = nodes[j] = loadNewItemNode();

			displaySujetDetails(node, sujet);

			setupActions(node, sujet, j);

			setHoverStyleForNode(nodes, j);

			pnItems.getChildren().add(node);
		});

		if (nodes.length > 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}

     private List<Actualite> fetchDateCreation() throws SQLException {
		try {
			return actualiteService.TrierParDateCreation();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
    
	private void loadDateCreation() throws SQLException {
		List<Actualite> actualites = fetchDateCreation();
		Node[] nodes = new Node[actualites.size()];

		AtomicInteger i = new AtomicInteger(0);
		actualites.forEach(sujet -> {
			int j = i.getAndIncrement();
			Node node = nodes[j] = loadNewItemNode();

			displaySujetDetails(node, sujet);

			setupActions(node, sujet, j);

			setHoverStyleForNode(nodes, j);

			pnItems.getChildren().add(node);
		});

		if (nodes.length > 0) {
			pnItems.setStyle("-fx-background-color : #53639F");
			pnItems.toFront();
		}
	}
}
