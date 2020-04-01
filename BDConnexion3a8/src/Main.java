import entites.Accessoire;
import entites.Velo;
import javafx.application.Application;
import javafx.stage.Stage;
import service.VeloService;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import service.AccessoireService;





public class Main extends Application   {
  
    
//    public static void main(String[] args) {
//        VeloService V=new VeloService();
//        Velo v1=new Velo("velo1","marque",200.0,5,"rouge","imae","description","à vendre",3);
//        V.ajouterVelo(v1);
        /*Velo v1=new Velo("velo1","marque", 200,4,"rouge" , "ddcd" , "dsdfcf","locat" ,1); 
        V.ajouterVelo(v1);
        
       AccessoireService service = new AccessoireService();
     
       System.out.println(service.afficherAccessoire());
       Accessoire A2=new Accessoire(2,"frein",50, 200,"blanc","dec" ,"hshshs","dghdhd",1);
     service.ajouterAccessoire(A2);
      Accessoire A3=new Accessoire(3,"frein",345, 200,"blanc","dec" ,"hshshs","dghdhd",1);
     service.ajouterAccessoire(A3);
       service.ajouterAccessoire(A3);
       service.modifierAccessoire(A2);
       service.modifierAccessoire(A3);
       System.out.println(service.afficherAccessoire());
       service.supprimerAccessoire(4);
       System.out.println(service.afficherAccessoire());
       System.out.println( service.rechercheParMarque("dec"));
       service.triParPrix();
       System.out.println( service.triParPrix());
       service.supprimerAccessoire(3);
       
*/
   

//    @Override
//    public void start(Stage stage) throws Exception {
//      Parent root= FXMLLoader.load(getClass().getResource("/gui/VeloFXML.fxml"));
//      Scene scene = new Scene(root);
//      stage.setScene(scene);
//      stage.show();
//    }
    public static void main (String [] args) {
         AccessoireService service = new AccessoireService();
     
       System.out.println(service.afficherAccessoire());
       Accessoire A2=new Accessoire(2,"frein",50, 200,"blanc","dec" ,"hshshs","dghdhd",3);
     service.ajouterAccessoire(A2);
       launch(args);          
    }

    @Override
    public void start(Stage stage) throws Exception {
     Parent root= FXMLLoader.load(getClass().getResource("/gui/AcceuilFXML.fxml"));
        //Parent root= FXMLLoader.load(getClass().getResource("/gui/AjouterVeloAccess.fxml"));

      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    }
    
