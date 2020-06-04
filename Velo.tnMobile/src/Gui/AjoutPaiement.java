/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Paiement;
import Services.ServicePaiement;
import com.codename1.charts.util.ColorUtil;
import com.codename1.db.Database;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.Map;


/**
 *
 * @author ASUS
 */
public class AjoutPaiement extends Form   {
    Resources theme=UIManager.initFirstTheme("/theme");
    Form current;
    Database db;
    
    public AjoutPaiement(int idcommande)
    {
        current = this;
        this.getAllStyles().setBgImage(theme.getImage("banner-1.jpg"));   
        setTitle("Paiement");
        setLayout(BoxLayout.y());
        Style labelicon = UIManager.getInstance().getComponentStyle("Label");
        Style buttonicon = UIManager.getInstance().getComponentStyle("Button");  
        Style buttonchange = UIManager.getInstance().getComponentStyle("Button");  
        buttonicon.setFgColor(ColorUtil.rgb(154, 205, 54));

        buttonchange.setFgColor(ColorUtil.rgb(255, 255, 255));
        labelicon.setFgColor(ColorUtil.rgb(255, 165, 0));
        
        
        Container c = new Container(BoxLayout.x());
        CheckBox Mastercard = new CheckBox("Master Card");
        Mastercard.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));

        CheckBox Edinar = new CheckBox("Edinar ");
        Edinar.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        
        CheckBox Visa  = new CheckBox("Visa");
        Visa.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
        c.addAll(Mastercard,Edinar,Visa);
        this.add(c);
        
        
        
         TextField NumCarte = new TextField("", "Code de Sécurité", 23, TextField.NUMERIC);
         Picker DateExpiration= new Picker();
         DateExpiration.setType(Display.PICKER_TYPE_DATE);
         TextField CodeSec = new TextField("", "Code Sécurité ", 4, TextField.PASSWORD);
         

         ComboBox<String> Pays = new ComboBox();
         Pays.addItem("Tunisia");
         Pays.addItem("UK");
         Pays.addItem("Algeria");
         Pays.addItem("USA");
                
         
          Button btn=new Button("Payer",FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATM, buttonicon,8));
          //btn.getAllStyles().setBgImage(theme.getImage("payer.png"));
          btn.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
         btn.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATM, buttonchange,10));
          //btn.setRolloverIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATM, buttonchange,11));
          //btn.setRolloverPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_LOCAL_ATM, buttonchange,10));

                  

         btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String str="";
                if(Mastercard.isSelected())
                        {
                         str +=Mastercard.getText();
                          }
                    if(Edinar.isSelected())
                     {
                         str +=Edinar.getText();
                        }
                    if(Visa.isSelected())
                     {
                         str +=Visa.getText();
                        }
                
                if ((NumCarte.getText().length()==0)||(CodeSec.getText().length()==0) || str.length()==0 )
                    Dialog.show("Alert","Please fill all the fields","OK","Close");
                else
                {
                    try {
                        Paiement p = new Paiement(str,Integer.parseInt(NumCarte.getText()),DateExpiration.getDate(),Integer.parseInt(CodeSec.getText()),Pays.getSelectedItem(),1);
                        if( ServicePaiement.getInstance().AjouterPaiement(p,idcommande))
                        {
                            
                                Media m;
                            try {
                                m = MediaManager.createMedia("audio/done.mp3", false);
                                m.play();
                            } catch (IOException ex) {
                                
                            }
                              
                            Dialog.show("Confirmation","Paiement Effectué","OK","Close");
                            //vider Panier
                         /*    try {
                          db.execute("DELETE FROM panier");
                          db.close();
                         } catch (IOException ex) {
                 
                                }*/
                        
                       //SMS API MOBILE 
                       String accountSID = "AC482699b3a5fadada07b4fb6098226a72";
                       String authToken = "d2c6e9c9b24c4e2205314c46ad882dea";
                       String fromPhone = "+18329908993";
                       Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                         queryParam("To", "+21620566666").
                            queryParam("From", fromPhone).
                            queryParam("Body", "").basicAuth(accountSID, authToken).getAsJsonMap();
                         
                            
                        
                        
                        }
                            
                        else
                            Dialog.show("Alert","Echéc","OK","Close");
                    } catch (NumberFormatException e) {
                        Dialog.show("Alerte","Code sécurité et Num carte doit être des chiffre","OK","Close");
                    }
                    
                }
                
               
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
         
        
        this.addAll(NumCarte,CodeSec,DateExpiration,Pays,btn);
        
        
        
        
    }
    
    
}
