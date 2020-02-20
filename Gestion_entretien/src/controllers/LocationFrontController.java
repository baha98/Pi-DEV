/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entite.Velo;
import Service.VeloService;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author itsme
 */
public class LocationFrontController implements Initializable {

    private ImageView Image;
    @FXML
    private Label Title;
    @FXML
    private TextArea Description;
    @FXML
    private Button louer;
    @FXML
    private GridPane GridContainer;
    @FXML
    private GridPane ProductContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VeloService v= new VeloService();
            ArrayList<Velo> Velos = v.afficherVelo();
            Description.setEditable(false);
            GridPane GP = new GridPane();
            
            for(int i=0;i<3;i++)
            {
                Title.setText(Velos.get(i).getLibelle());
                Description.setText(Velos.get(i).getDescrption());
                ProductContainer.add(Velos.get(i).getImage(), 0, 1);
                for(int j=0;j<3;j++)
                {
                    GridContainer.add(ProductContainer, i,j);
                }
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LocationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
}
