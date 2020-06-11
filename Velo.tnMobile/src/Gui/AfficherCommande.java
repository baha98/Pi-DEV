/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Commande;
import Entities.Produit;
import Services.ServiceCommande;
import Services.ServiceProduit;
import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ASUS
 */
public class AfficherCommande extends Form {
    
    Resources theme=UIManager.initFirstTheme("/theme");
    Form current;

    
    public AfficherCommande() {
        
        
        current = this;
        setTitle("Mes Commandes ");
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));   
        
         setLayout(BoxLayout.y());
        Container ctn1= new Container(BoxLayout.y());
        Style labelicon = UIManager.getInstance().getComponentStyle("Label");
        labelicon.setFgColor(ColorUtil.rgb(255, 165, 0));
        Label Stat =  new Label("Mes Stats de mes commandes lors l'année",FontImage.createMaterial(FontImage.MATERIAL_INSERT_CHART, labelicon));
        Stat.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        ctn1.add(Stat);
        
        ComboBox cb = new ComboBox();
        for(int i=2010;i<=2030;i++)
        {    
        cb.addItem(String.valueOf(i));
        }
        
        cb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Stat(Integer.parseInt(cb.getSelectedItem().toString()),current).show();
                
            }
        });
        ctn1.add(cb);

        
        this.add(ctn1);
    
         for(Commande c :ServiceCommande.getInstance().getAllTasks())
        { 
            
           add(SetCommande(c));
           
           
        }

         
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
         
    }
    
    
    private  Container SetCommande(Commande c)
    {
        current = this;
        Container ctn1= new Container(BoxLayout.y());
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        Style labelicon = UIManager.getInstance().getComponentStyle("Label");
        Style buttonicon = UIManager.getInstance().getComponentStyle("Button");
        Style buttonicon2 = UIManager.getInstance().getComponentStyle("Button");
        buttonicon.setFgColor(ColorUtil.rgb(154, 205, 54));
        buttonicon2.setFgColor(ColorUtil.rgb(220, 20 ,60));
        labelicon.setFgColor(ColorUtil.rgb(255, 165, 0));
       
    
        
        Label lbDate = new Label(" : "+dateFormat.format(c.getDate_commande()),FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, labelicon));
        lbDate.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));

        Label Total =  new Label("Prix Total : "+Float.toString(c.getPrix_total()),FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, labelicon));
        Total.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        Label lbEtat = new Label();
        
       
       
        
        
        
        
        
        Button btn = new Button();
        ctn1.addAll(lbDate,Total,lbEtat,btn);
         if(c.getEtat_commande()==0)
        {
        lbEtat.setText("");
        Container ctn2= new Container(BoxLayout.y());
        Button Payer= new Button("Non Payé",FontImage.createMaterial(FontImage.MATERIAL_MONEY_OFF, buttonicon2));
        Payer.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        ctn2.add(Payer);
        ctn1.add(ctn2);
        
        }
        else 
        {
            lbEtat.setText("");
            Container ctn2= new Container(BoxLayout.y());
            Button Payer= new Button("Payé",FontImage.createMaterial(FontImage.MATERIAL_MONETIZATION_ON, buttonicon));
                    Payer.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));

            ctn2.add(Payer);
            ctn1.add(ctn2);
        }
        
        btn.addActionListener((e)-> {
        
        new DetailCommande(c,current).show();
        
        });

        ctn1.setLeadComponent(btn);
        return ctn1;
       
    }
    
}
