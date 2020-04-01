/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entities.Actualite;
import entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sevices.ActualiteService;

/**
 *
 * @author ONS
 */
public class Ajouter_actualiteController implements Initializable{
    public  static ActualiteService actualiteService = new ActualiteService();
     @FXML
    private AnchorPane id_page_ajout;

    @FXML
    private JFXTextField f_titre;

    @FXML
    private JFXButton id_add;

    @FXML
    private JFXTextArea id_description;

    @FXML
    private JFXComboBox<Categorie> id_categorie;

    @FXML
    void add(ActionEvent event) {
        
        if((f_titre.getText().isEmpty()==false)&&
                (id_description.getText().isEmpty()==false)&&(id_categorie.getSelectionModel().isEmpty()==false)){
        Actualite a = new Actualite(f_titre.getText(), id_description.getText(), "oko", id_categorie.getValue());
        actualiteService.ajouterActualite(a);
        try {

                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/gui/Ajouter_actualite.fxml"));

                id_page_ajout.getChildren().clear();
                id_page_ajout.getChildren().add(newLoadedPane);

            } catch (IOException ex) {
                Logger.getLogger(Ajouter_actualiteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Image img = new Image("/images/aa.png");
            Notifications notificationBuilder = Notifications.create()
                    .title("Ajout d'une actualité")
                    .text("Lactualité a été ajouté correctement")
                    .graphic(new ImageView(img))
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.show();
        } else {
            BoxBlur blur = new BoxBlur(3, 3, 3);
			id_page_ajout.setEffect(blur);
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Repetez svp");
			alert.setHeaderText("champs non validés");
			alert.setContentText("Verifiez vos champs svp!!");
			alert.showAndWait();
			id_page_ajout.setEffect(null);
            System.out.println("nest pas possible");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         id_categorie.getItems().removeAll(id_categorie.getItems());
        id_categorie.getItems().addAll(Categorie.championat, Categorie.montagne,
                Categorie.piste, Categorie.route);

    }
}
