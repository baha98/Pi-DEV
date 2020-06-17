/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.services.ServiceCategorie;
import com.mycompany.myapp.services.ServiceVelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author bhk
 */
public class ListVelosForm extends Form{
    
    Resources theme;
    Form f = this;
    Integer page = 0;
    ArrayList<Velo> velos = ServiceVelo.getInstance().getAllVelos();


    public ListVelosForm(Form previous, Resources theme) {
        setTitle("Velos");
        this.theme=theme;
        Form element= new Form( BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
        getToolbar().addCommandToOverflowMenu("Favoris",null,e->new FavorisProduitForm(previous,theme).show());
        setLayout(BoxLayout.y());
        element.getToolbar().addMaterialCommandToRightBar(""
                , FontImage.MATERIAL_ARROW_FORWARD
                ,e -> affichePage(element) );
        element.getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previousPage(element) );
        affichePage(element);
        TextField txtmarque = new TextField();
        txtmarque.setHint("marque");
        this.add(txtmarque);
        Button btnFind = new Button("Chercher");
        this.add(btnFind);
        int i=0;
        Container categories = new Container( BoxLayout.x());
        ArrayList<Categorie> velobycateg = ServiceCategorie.getInstance().getVelosCategories();
        int size = velobycateg.size() % 3 ;
        int cmp = 0;
        for(Iterator<Categorie> iter = velobycateg.iterator();iter.hasNext();) {
             Categorie categ = iter.next();
             if (i==3) {
                i=0;
                this.add(categories);
                categories = new Container( BoxLayout.x());
            }
             if ((velobycateg.size()-1 == cmp ) && (size!=0)) {
                 this.add(categories);
             }
            
           Button b = new Button(categ.getNomCategorie());
           b.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                 element.removeAll();
                 velos = ServiceVelo.getInstance().getVelosByCateg(categ.getNomCategorie());
//                    for (Velo v:) {
//                    element.add(createElement(v));      
//                    }
page = 0;
affichePage(element);
                    refresh();
            }});
            categories.add(b);
            i++;
            cmp++;
        
        
        }
       
        this.add(element);
        btnFind.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                if (txtmarque.getText() == null || "".equals(txtmarque.getText()) ) {
                     element.removeAll();
                     velos = ServiceVelo.getInstance().getAllVelos();
                     page = 0;
                     affichePage(element);
//                    for (Velo v: ) {
//                         element.add(createElement(v));
//                    }
                    element.repaint();
                    element.refreshTheme();
                    refresh();
                } else {
                    element.removeAll();
                    velos = ServiceVelo.getInstance().getVelosByMarque(txtmarque.getText());
//                    for (Velo v:) {
//                    element.add(createElement(v));      
//                    }
                    page = 0;
                    affichePage(element);
                    refresh();
                }
            }});
        
  }
    
    private void refresh() {
        this.repaint();
        this.refreshTheme();
    }
    
    
    private Container createElement(Velo p){        
        
        Container element= new Container(BoxLayout.x());
        
        ImageViewer img;
        try {
            img = new ImageViewer(Image.createImage("file:///C:/wamp64/www/espritclub/web/image/"+p.getImage()).scaled(500, 300));
        } catch (IOException ex) {
            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("logo.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                p.getImage()
                , ""+p.getImage());        
        img.setImage(uRLImage);
        }

            element.add(img);
        

        
        
        
        Label lbNom= new Label(p.getLibelle());
        Label lbPrix= new Label(p.getPrix()+"DT");

        Button b = new Button();
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailsVeloForm(theme, f, p).show();
            }
        });
        element.add(lbNom);
        element.add(lbPrix);
        element.setLeadComponent(b);
        return element;
    }
        private void affichePage(Form element) {
            if (page < ((velos.size() + 3) / 4)){
                page++;
                element.setTitle("page "+page);
                element.removeAll();
                element.refreshTheme();
                refresh();
                for (int i= (page-1)*4; i<(((page-1)*4)+4) && (velos.size()>i); i++) {
                    element.add(createElement(velos.get(i)));
                }
                
                element.refreshTheme();
                refresh();
            } else {
            }
    }
        
         private void previousPage(Form element) {
             if (page>1) {
                    element.removeAll();
                    element.refreshTheme();
                    refresh();
                     page--;
                    for (int i= (page-1)*4; i<(((page-1)*4)+4) && (velos.size()-1>i); i++) {
                    element.add(createElement(velos.get(i)));
        } 
                   
                    element.setTitle("page "+page);
                element.refreshTheme();
                    refresh();
             }
    }
    
}
