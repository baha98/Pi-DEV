/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import Entite.Location;
import Entite.Entretien;
import Service.LocationsService;
import Service.EntretienService;
import com.sun.javafx.application.LauncherImpl;
import controllers.LoginFXMLController;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.*; 
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author itsme
 */
public class main extends Application{
    private static final long COUNT_LIMIT = 3;
    private static Stage myStage = null;
   @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/gui/LoginFXML.fxml"));

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.initStyle(StageStyle.UNDECORATED);
        myStage = stage;
        stage.show();
        javafx.geometry.Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
       
        
    }
    
     public void init() throws Exception {       
        
        for (int i = 1; i <= COUNT_LIMIT; i++) {
            double progress =(double) i/10;
            System.out.println("progress: " +  progress);            
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
            Thread.sleep(2000);
        }
        
    }
    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {    
        LauncherImpl.launchApplication(main.class, MyPreloader.class, args);
    }
    
}
