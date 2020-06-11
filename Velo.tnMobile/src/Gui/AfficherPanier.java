/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;



import Entities.Commande;
import Entities.Panier;
import Services.ServiceCommande;
import com.codename1.charts.util.ColorUtil;

import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ASUS
 */
public class AfficherPanier extends Form {
    
    
     Database db;
     Form current;
     Resources theme=UIManager.initFirstTheme("/theme");
    public AfficherPanier(Form previous)
    {
        setTitle("Panier");
        setLayout(BoxLayout.y());
        current = this;
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));  
         Style labelicon = UIManager.getInstance().getComponentStyle("Label");
        labelicon.setFgColor(ColorUtil.rgb(255, 165, 0));
        
        float total=0;
        
          for(Panier p :getAllProducts())
        {
            total=total+p.getPrix()*p.getQuantite();
            add(SetPanier(p,previous));
            
        }
          Label lbTotal =new Label("TOTAL = "+Float.toString(total),FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, labelicon));      
          lbTotal.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));

          add(lbTotal);
          String prixTotal=Float.toString(total);
          
          Button Commander = new Button("Valider Panier");
          Style buttonchange = UIManager.getInstance().getComponentStyle("Button");  
          buttonchange.setFgColor(ColorUtil.rgb(255, 255, 255));
          
          Commander.getAllStyles().setFgColor(ColorUtil.rgb(255, 165, 0));
         Commander.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, buttonchange,10));
          
        
            Toolbar tb = this.getToolbar();
         Image icon = theme.getImage("icon.png"); 
    Container topBar = BorderLayout.east(new Label(icon));
    topBar.add(BorderLayout.SOUTH, new Label("Velo.tn", "SidemenuTagline")); 
    topBar.setUIID("SideCommand");
    tb.addComponentToSideMenu(topBar);

    tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, (e) -> 
    {
        new AfficherProduits().show();
    }
   ); 
    tb.addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_SHOPPING_CART, e -> {new AfficherCommande().show();});
    tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {});
    tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {});

         
         getToolbar().addMaterialCommandToOverflowMenu("Voir Panier",
                FontImage.MATERIAL_SHOPPING_CART,(e)->{
                
                new AfficherPanier(current).show();
                });
         

        getToolbar().addMaterialCommandToLeftBar(" ", FontImage.MATERIAL_ARROW_BACK, (e)->{
            previous.showBack();
        });   
          getToolbar().addMaterialCommandToRightBar(" ", FontImage.MATERIAL_REMOVE_SHOPPING_CART, (e)->{
                             try {
                                 
                          Media m = MediaManager.createMedia("audio/trash.mp3", false);
                          m.play();
                          db.execute("DELETE FROM panier");
                          db.close();
                          
                          
                         } catch (IOException ex) {
                 
                                }
                            new AfficherPanier(previous).show();
            
        });  
        
        Commander.addActionListener((e)->
                {
                
                Commande c = new Commande(Float.parseFloat(prixTotal));
                if( ServiceCommande.getInstance().AjouterCommande(c,getAllProducts()))
                    if(Dialog.show("Confirmation","Commande Confirmé","OK","Close"))
                    {        Media m;
                    try {
                        m = MediaManager.createMedia("audio/done.mp3", false);
                        m.play();
                    } catch (IOException ex) {
                        
                    }
                         
                        int idcommande=ServiceCommande.getInstance().getLastId();
                       
                        new AjoutPaiement(idcommande).show();
                        
                    }
                else
                    {
                    Dialog.show("Alert","Echéc","OK","Close");}         
            }
        );
        
        
        add(Commander);
 
        
    }
    
    private  Container SetPanier(Panier p,Form previous)
    {
        
         Container ctn1= new Container(BoxLayout.x());

        
        Label lblNom = new Label(p.getNom());
        lblNom.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        Label lbQuantite=new Label(String.valueOf(p.getQuantite())+"x"+String.valueOf(p.getPrix()));
        lbQuantite.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));

        
        
        
        ImageViewer imgProduit = new ImageViewer(theme.getImage(p.getNomImg()));
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("casquevelo.jpg"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                p.getNomImg()
                , "http://127.0.0.1/Velo/web/Produit/"+p.getNomImg());        
        imgProduit.setImage(uRLImage);
        
        Style buttonicon = UIManager.getInstance().getComponentStyle("Button");  
        buttonicon.setFgColor(ColorUtil.rgb(255, 165, 0));
        Button supp = new Button(FontImage.createMaterial(FontImage.MATERIAL_DELETE_FOREVER, buttonicon));
      
        ctn1.addAll(imgProduit,lblNom,lbQuantite,supp);
        
      
        supp.addActionListener((e)->   
        {
             try {
                   Media m = MediaManager.createMedia("audio/delete.mp3", false);
                          m.play();
                 db.execute("DELETE FROM panier"
                         + " WHERE id =" + p.getId()
                 );
                 db.close();
               
                 
                 
             } catch (IOException ex) {
                 
             }
               new AfficherPanier(previous).show();
        }
   
        );
        
        
       
        return ctn1;
       
    }
    
    
     public List<Panier> getAllProducts()
    {  List<Panier> panier=new ArrayList();
        try {
             
          db=Database.openOrCreate("Velo");
                String req="create table if not exists panier("
                 +"id integer primary key ,"
                 +"nom TEXT,"
                 +"quantite Integer)";
                
                 req="select * from panier"; 
                 Cursor c=db.executeQuery(req);

            
            while(c.next())
            {
                Row r=c.getRow();
                
                Panier p= new Panier();
                p.setId(r.getInteger(0));
                p.setNom(r.getString(1));
                p.setQuantite(r.getInteger(2));
                p.setNomImg(r.getString(3));
                p.setPrix(r.getFloat(4));
                panier.add(p);
                
            }
                
        
        } catch (IOException ex) {
            System.out.println("okxxx");
        }
        
        
        return panier;
    } 
    
}
