/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Commande;
import Entities.LignesCommande;
import Entities.Panier;
import Entities.Produit;
import Services.ServiceLignesCommande;
import Services.ServiceProduit;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author ASUS
 */
public class DetailCommande extends Form {
    
    
     Resources theme=UIManager.initFirstTheme("/theme");
     Form current;

    
    public DetailCommande(Commande c,Form previous) {
        
        
        current = this;
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));   
        setTitle("Detail Du Commande "+c.getId_commande());
        setLayout(BoxLayout.y());
        current = this;
        float total=0;
        Style labelicon = UIManager.getInstance().getComponentStyle("Label");
        Style buttonicon = UIManager.getInstance().getComponentStyle("Button");  
        Style buttonchange = UIManager.getInstance().getComponentStyle("Button");  
        buttonchange.setFgColor(ColorUtil.rgb(255, 255, 255));
        buttonicon.setFgColor(ColorUtil.rgb(154, 205, 54));
        labelicon.setFgColor(ColorUtil.rgb(255, 165, 0));
        

          for(LignesCommande lg :ServiceLignesCommande.getInstance().getAllTasks(c.getId_commande()))
        {
            total=total+lg.getPirx()*lg.getQte();
            
            add(SetDetail(lg));
            
        }
          Label lbTotal =new Label("TOTAL = "+Float.toString(total),FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, labelicon));
          lbTotal.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));

          add(lbTotal);
          String prixTotal=Float.toString(total);
          Button Payer = new Button("Payer",FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATM, buttonicon,8));
                    Payer.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
          Payer.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATM, buttonchange,10));


         if(c.getEtat_commande()==0)
         {
          add(Payer);
          Payer.addActionListener((e)->
          {
              new AjoutPaiement(c.getId_commande()).show();
          }
          );
          }
         
          
          
          

         
        
         
         Toolbar tb = this.getToolbar();
         Image icon = theme.getImage("icon.png"); 
         Container topBar = BorderLayout.east(new Label(icon));
         topBar.add(BorderLayout.SOUTH, new Label("Velo.tn", "SidemenuTagline")); 
         topBar.setUIID("SideCommand");
         tb.addComponentToSideMenu(topBar);

         tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {}); 
        tb.addMaterialCommandToSideMenu("Mes Commandes", FontImage.MATERIAL_SHOPPING_CART, (e) -> 
    {
    new AfficherCommande().show();
    }
        );
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
    
    
    private  Container SetDetail(LignesCommande lg)
    {
        current = this;
        Container ctn1= new Container(BoxLayout.x());
        Style buttonicon = UIManager.getInstance().getComponentStyle("Button");            
        
        Label lblNom = new Label(lg.getLibelle());
        lblNom.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        Label lbQuantite=new Label(String.valueOf(lg.getQte())+"x"+String.valueOf(lg.getPirx()));
        lbQuantite.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        
        
        
        ImageViewer imgProduit = new ImageViewer(theme.getImage(lg.getImg()));
        EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("casquevelo.jpg"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                lg.getImg()
                , "http://127.0.0.1/Velo/web/Produit/"+lg.getImg());        
        imgProduit.setImage(uRLImage);
        
        
        
        Button btn = new Button();
        ctn1.addAll(imgProduit,lblNom,lbQuantite,btn);
        
 
        return ctn1;
       
    }
    
}
