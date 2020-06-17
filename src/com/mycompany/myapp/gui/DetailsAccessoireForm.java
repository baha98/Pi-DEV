/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Accessoire;
import java.io.IOException;

/**
 *
 * @author zoula
 */
public class DetailsAccessoireForm extends Form {
     Resources theme;
    Form previous;
    public DetailsAccessoireForm(Resources theme, Form previous,Accessoire a ) {
        this.previous = previous;
        this.theme=theme;
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
        
        ImageViewer img;
        try {
            img = new ImageViewer(Image.createImage("file:///C:/wamp64/www/espritclub/web/image/"+a.getImage()).scaled(1100, 600));
        } catch (IOException ex) {
            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("logo.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                a.getImage()
                , ""+a.getImage());        
        img.setImage(uRLImage);
        }
        this.add(img);
       Container c1 = new Container(BoxLayout.x());
        Label liblb = new Label("Libelle : ");
        Label lbNom = new Label(a.getLibelle());
         c1.add(liblb);
         c1.add(lbNom);
        this.add(c1);
         Container c2 = new Container(BoxLayout.x());
        Label prix = new Label("prix : ");
        Label lbPrix = new Label(a.getPrix()+" DT");
        c2.add(prix);
         c2.add(lbPrix);
        this.add(c2);
        Container c3 = new Container(BoxLayout.x());
        Label marque = new Label("Marque : ");
        Label lblMarque = new Label(a.getMarque());
        c3.add(marque);
        c3.add(lblMarque);
        this.add(c3);
        Container c4 = new Container(BoxLayout.x());
        Label couleur = new Label("Couleur : ");
        Label lblCoul = new Label(a.getCouleur());
        c4.add(couleur);
        c4.add(lblCoul);
        this.add(c4);
        Container c5 = new Container(BoxLayout.x());
        Label desc = new Label("Description : ");
        Label lbDescr = new Label(a.getDescription());
        c5.add(desc);
        c5.add(lbDescr);
        this.add(c5); 
Button btnacheter = new Button("Acheter");
                this.add(btnacheter);
                Button addToFavori = new Button("Ajouter au favoris");
        addToFavori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FavorisProduitForm fpf = new FavorisProduitForm();
                fpf.addProduct(a);
                Dialog.show("Success", "Ajouter au favoris avec succes", "OK", "Cancel");

            }
        });
          this.add(addToFavori);
                
    }
}
