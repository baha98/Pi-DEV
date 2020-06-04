/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produit;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
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
import com.codename1.ui.Slider;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class AfficherDetailProduit extends Form{
    Resources theme=UIManager.initFirstTheme("/theme");
    Database db;
    Form current;
    
    
    public AfficherDetailProduit(Produit p,Form previous)
    {
        setTitle(p.getLibelle());
        setLayout(BoxLayout.y());
        current = this;
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));   
        
        
        
        
        ImageViewer imgProduit = new ImageViewer(theme.getImage(p.getImg()));
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("casquevelo.jpg"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                p.getImg()
                , "http://127.0.0.1/Velo/web/Produit/"+p.getImg());        
        imgProduit.setImage(uRLImage);
        
        add(imgProduit);
        

        
        Label lbNom = new Label(p.getLibelle());
        lbNom.getAllStyles().setFgColor(0xFFFAF0);
        add(lbNom);
        lbNom.setText(p.getLibelle()+"  "+p.getPrix()+" DT");
       
        
        Label lbQuantite=new Label("Qauntité = ");
        lbQuantite.getAllStyles().setFgColor(0xFFFAF0);

        add(lbQuantite);
        
        Slider sQuantite = new Slider();
        sQuantite.setEditable(true);
        sQuantite.setMaxValue(p.getQte());
        sQuantite.setMinValue(1);
        
        add(sQuantite);
        
        Style buttonicon = UIManager.getInstance().getComponentStyle("Button");  
        Style buttonchange = UIManager.getInstance().getComponentStyle("Button");  
        buttonchange.setFgColor(ColorUtil.rgb(255, 255, 255));
        buttonicon.setFgColor(ColorUtil.rgb(255, 165, 0));

        Button btn=new Button("Ajouter au Panier",FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, buttonicon,8));
        btn.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_ADD_SHOPPING_CART, buttonchange,10));

        add(btn);
        btn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        
        sQuantite.addActionListener((e)->{
        lbQuantite.setText("Quantité : "+String.valueOf(sQuantite.getProgress()));
        btn.setText("Ajouter au Panier  ("+String.valueOf(sQuantite.getProgress())+")");
        });      
        
       btn.addActionListener((e)->{
         try {
             Media m = MediaManager.createMedia("audio/done.mp3", false);
             m.play();
            db=Database.openOrCreate("Velo");
                String req="create table if not exists panier("
                 +"id integer primary key ,"
                 +"nom TEXT,"
                 +"quantite Integer,"
                 +"image TEXT)";

        db.execute(req); 
        
    
       
        req="insert into panier values('"+p.getId_produit()+"','"+p.getLibelle()+"','"+sQuantite.getProgress()+"','"+p.getImg()+"','"+p.getPrix()+"')";
        db.execute(req);
       
        db.close();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
                        if (ex.getMessage().contains("PRIMARY KEY must be unique")) {
                try {
                     Media m = MediaManager.createMedia("audio/done.mp3", false);
                     m.play();
                    db.execute("UPDATE panier SET"
                            + " quantite=quantite+" +sQuantite.getProgress()
                            + " WHERE id =" + p.getId_produit()
                    );
                    db.close();
                    
                    Dialog.show("Confirmation","MAJ quantité du  "+p.getLibelle()+"g","OK","Close");
                    
                } catch (IOException ex1) {
                    
                }
                        }
                        
                        
        }
          if(Dialog.show("Confirmation","Ajout du  "+p.getLibelle()+"Au Panier","OK","Close"))
       {
           new AfficherPanier(current).show();
       }
       else {
        //Display.getInstance().exitApplication();
        this.show();
       }
               
        });


       
        
        
        
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
         
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)->{
        
        previous.showBack();
        });
         
    }
         
        
        
        
    }
    

