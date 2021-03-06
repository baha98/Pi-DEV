/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Commentaire;
import entities.Jaime;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sevices.ActualiteService;
import sevices.CommentaireService;
import sevices.JaimeService;

/**
 *
 * @author ONS
 */
public class DetailsController implements Initializable{
      @FXML
    private ScrollPane x;
    @FXML
    private VBox msget;
    @FXML
    private JFXButton aff;
    @FXML
    private Button id_ajout_Comment;

    @FXML
    private TextArea text_comment;
    @FXML
    private TextArea text_comment1;
    @FXML
    private JFXButton id_comment_modif;
    @FXML
    private JFXButton id_annuler;
    @FXML
    private JFXTextField mytitre;

    @FXML
    private JFXTextField mydesc;

    @FXML
    private JFXTextField id_nbre_jaime;
    @FXML
    private JFXButton id_jaime;
    @FXML
    private JFXButton id_jaimepas;

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane id_anch;
    @FXML
    private AnchorPane id_send;
    @FXML
    private AnchorPane id_detail_sujet;
    private HBox wrap;
    private VBox wrap1;
    VBox content2 = new VBox();
    Label textField[] = new Label[150000];

    Button button[] = new Button[150000];
    Button button1[] = new Button[150000];

    CommentaireService commentaireService = new CommentaireService();
    ActualiteService actualiteservice = new ActualiteService();
    JaimeService jaimeService = new JaimeService();
    private static Commentaire commentaire_à_modifier = new Commentaire();

    public static Commentaire getCommentaire_à_modifier() {
        return commentaire_à_modifier;
    }

    public static void setCommentaire_à_modifier(Commentaire commentaire_à_modifier) {
        DetailsController.commentaire_à_modifier = commentaire_à_modifier;
    }

    int i = 0;
    int j = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

         try {
            mytitre.setText(Afficher_Actualites_MembreController.actualite_a_ouvrir.getTitre());
            mydesc.setText(Afficher_Actualites_MembreController.actualite_a_ouvrir.getDescription());
         
              //   id_nbre_jaime.setText(String.valueOf(AffichageForumController.getSujet_à_ouvrir().getNbre_jaime()));
              id_nbre_jaime.setText(String.valueOf(actualiteservice.FindNombreJaimeById_actualite(Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite())));
         
            mydesc.setEditable(false);
            mytitre.setEditable(false);
          
            id_nbre_jaime.setEditable(false);

            text_comment.setPromptText("écrire votre commentaire ici ...");
           
            if (jaimeService.FindValeurJaimeByIdUserAndIdActualite(1, Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite()) ==0)
            {
                 id_jaime.setVisible(true);
                 id_jaimepas.setVisible(false);
            }
            else if (jaimeService.FindValeurJaimeByIdUserAndIdActualite(1, Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite()) ==1) {
                id_jaime.setVisible(true);
                 id_jaimepas.setVisible(false);
            }else {
                id_jaime.setVisible(false);
                 id_jaimepas.setVisible(true);
            }
            
            afficher_liste_commentaires();
        } catch (SQLException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void afficher_liste_commentaires() throws SQLException {
        clearContent(content2);

        List<Commentaire> ff = commentaireService.readAll(Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite());
        ff.forEach(e -> {
            //  String thrd = e.getContent();
            //thread = Integer.parseInt(thrd);

            textField[i] = new Label();
            button[j] = new Button();
            button1[j] = new Button();
            textField[i].setText(" " + e.getContenu_Commentaire() + " \n" + e.getTemps_Commentaire() + "\n");
            textField[i].setTranslateX(10);
            textField[i].setAlignment(Pos.TOP_LEFT);
            wrap1 = new VBox();
            //  wrap1.setPrefWidth(id_hbox.getPrefWidth() - 80);
            wrap1.setAlignment(Pos.TOP_LEFT);
            wrap1.getChildren().add(textField[i]);
            if (e.getId_user() == 1) {
                textField[i].setStyle("-fx-text-fill: red;");

                textField[i].setTranslateX(70);
                button[j].setTranslateX(600);
                button[j].setTranslateY(-30);
                button[j].setText("modifier");
                button[j].setStyle("-fx-text-fill: green;");
                button[j].setAlignment(Pos.TOP_LEFT);
                button[j].setOnMouseClicked(UpdateEventHandler(e));
                button1[j].setTranslateX(680);
                button1[j].setTranslateY(-55);
                button1[j].setText("supprimer");
                button1[j].setStyle("-fx-text-fill: green;");
                button1[j].setAlignment(Pos.TOP_LEFT);
                button1[j].setOnMouseClicked(DeleteEventHandler(e));

                wrap1.getChildren().add(button[j]);
                wrap1.getChildren().add(button1[j]);

                j = j + 1;

            }

            // textField[i].setTranslateX(40);
            wrap = new HBox();
            wrap.setPrefWidth(msget.getPrefWidth() - 80);
            wrap.setAlignment(Pos.TOP_LEFT);

//textField[i].getStyleClass().add("recumsg");
            wrap.getChildren().add(wrap1);
            wrap.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            content2.getChildren().add(wrap);
            content2.setPrefHeight(content2.getPrefHeight() + 88 + textField[i].getPrefHeight() + 88);
            i = i + 1;

            x.setContent(content2);
            x.setVvalue(1.0d);
        });

    }

    @FXML
    void ajoutComment(ActionEvent event) throws SQLException {
        Commentaire commentaire = new Commentaire(Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite(), 1, text_comment.getText());
        commentaireService.ajouterCommentaire(commentaire);
        text_comment.clear();
        afficher_liste_commentaires();

    }

    private void clearContent(Pane container) {
        container.getChildren().clear();
    }

    private EventHandler<MouseEvent> DeleteEventHandler(Commentaire commentaire) {
        return event -> {

            commentaireService.supprimerCommentaire(commentaire);

            try {
                afficher_liste_commentaires();
            } catch (SQLException ex) {
                Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Notifications notificationBuilder = Notifications.create()
                    .title("Commentaire Supprimé")
                    .text("Vous avez supprimé votre commentaire")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.showError();
        };
    }

    private EventHandler<MouseEvent> UpdateEventHandler(Commentaire commentaire) {
        return event -> {

            commentaire_à_modifier = commentaire;
            text_comment1.setText(commentaire.getContenu_Commentaire());
            id_anch.setVisible(false);
            id_send.setVisible(true);

        };

    }

    @FXML
    void modifier_commentaire(ActionEvent event) throws SQLException {

        commentaire_à_modifier.setContenu_Commentaire(text_comment1.getText());
        commentaireService.modifierCommentaire(commentaire_à_modifier);
        id_send.setVisible(false);
        id_anch.setVisible(true);

        afficher_liste_commentaires();

    }
 @FXML
    void annuler_modifier_commentaire(ActionEvent event) {
id_send.setVisible(false);
        id_anch.setVisible(true);
    }
    @FXML
    void jaime(ActionEvent event) throws SQLException {
 actualiteservice.compterjaime(Afficher_Actualites_MembreController.actualite_a_ouvrir);
        
        if (jaimeService.FindValeurJaimeByIdUserAndIdActualite(1, Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite()) == 0
               ){
                jaimeService.ajouterJaime(new Jaime(1, Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite(), 2));
   
        }
        else if(jaimeService.FindValeurJaimeByIdUserAndIdActualite(1, Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite()) ==1) {
               jaimeService.incrementerjaime(1,  Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite());
                }
        id_nbre_jaime.setText(String.valueOf(actualiteservice.FindNombreJaimeById_actualite(Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite())));

        System.out.println(Afficher_Actualites_MembreController.actualite_a_ouvrir.getNbre_jaime());
        id_jaime.setVisible(false);
        id_jaimepas.setVisible(true);
    }

    @FXML
    void jaimepas(ActionEvent event) throws SQLException {
  jaimeService.decrementerjaime(1,  Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite());

        actualiteservice.compterjaimepas(Afficher_Actualites_MembreController.actualite_a_ouvrir);
        id_nbre_jaime.setText(String.valueOf(actualiteservice.FindNombreJaimeById_actualite(Afficher_Actualites_MembreController.actualite_a_ouvrir.getId_actualite())));

        System.out.println(Afficher_Actualites_MembreController.actualite_a_ouvrir.getNbre_jaime());
        id_jaimepas.setVisible(false);
        id_jaime.setVisible(true);
    }


    
}
