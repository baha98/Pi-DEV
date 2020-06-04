/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Produit;
import Services.ServiceProduit;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class AfficherProduits extends Form {
     Resources theme=UIManager.initFirstTheme("/theme");
     Form current;

    
    public AfficherProduits() {
        
        
        current = this;
        setTitle("Les Produits");
        
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));        
       
                
           
        

        
    
        setLayout(BoxLayout.y());
         for(Produit p :ServiceProduit.getInstance().getAllTasks())
        { 
            add(SetProduit(p));
           
        }

         
       
            Toolbar tb = this.getToolbar();
         Image icon = theme.getImage("webfrontlogo.png").fill(450, 400); 
         
    Container topBar = BorderLayout.center(new Label());
    topBar.add(BorderLayout.SOUTH, new Label("  ")); 
    topBar.add(BorderLayout.SOUTH, new Label("  ")); 
    topBar.add(BorderLayout.CENTER, new Label(icon)); 
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
         
         
    }
    
    
    private  Container SetProduit(Produit p)
    {
        current = this;
        Container ctn1= new Container(BoxLayout.x());

        
        Label lblNom = new Label(p.getLibelle());
        lblNom.getAllStyles().setFgColor(0xFFFAF0);

        
        
        
        ImageViewer imgProduit = new ImageViewer(theme.getImage(p.getImg()));
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("casquevelo.jpg"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                p.getImg()
                , "http://127.0.0.1/Velo/web/Produit/"+p.getImg());        
        imgProduit.setImage(uRLImage);
        
        
        
        Button btn = new Button();
        ctn1.addAll(imgProduit,lblNom,btn);
        
        btn.addActionListener((e)-> {
        
        new AfficherDetailProduit(p,current).show();
        
        });

        ctn1.setLeadComponent(btn);
        return ctn1;
       
    }
    
}
