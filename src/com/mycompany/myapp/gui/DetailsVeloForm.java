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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.services.ServiceMail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhk
 */
public class DetailsVeloForm extends Form {
    Resources theme;
    Form previous;
    public DetailsVeloForm(Resources theme, Form previous,Velo p ) {
        this.previous = previous;
        this.theme=theme;
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
        
        ImageViewer img;
        try {
            img = new ImageViewer(Image.createImage("file:///C:/wamp64/www/espritclub/web/image/"+p.getImage()).scaled(1100, 600));
        } catch (IOException ex) {
            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage
                .createFromImage(theme.getImage("logo.png"), true);
        URLImage uRLImage = URLImage.createToStorage(placeholder,
                p.getImage()
                , ""+p.getImage());        
        img.setImage(uRLImage);
        }

        this.add(img);
        Container c1 = new Container(BoxLayout.x());
        Label liblb = new Label("Libelle : ");
        Label lbNom = new Label(p.getLibelle());
         c1.add(liblb);
         c1.add(lbNom);
        this.add(c1);
        Container c2 = new Container(BoxLayout.x());
        Label prix = new Label("prix : ");
        Label lbPrix = new Label(p.getPrix()+" DT");
        c2.add(prix);
         c2.add(lbPrix);
        this.add(c2);
        Container c3 = new Container(BoxLayout.x());
        Label marque = new Label("Marque : ");
        Label lblMarque = new Label(p.getMarque());
        c3.add(marque);
        c3.add(lblMarque);
        this.add(c3);
        Container c4 = new Container(BoxLayout.x());
        Label couleur = new Label("Couleur : ");
        Label lblCoul = new Label(p.getCouleur());
        c4.add(couleur);
        c4.add(lblCoul);
        this.add(c4);
        Container c5 = new Container(BoxLayout.x());
        Label desc = new Label("Description : ");
        Label lbDescr = new Label(p.getDescription());
        c5.add(desc);
        c5.add(lbDescr);
        this.add(c5);   
        Button btnacheter = new Button("Acheter");
                this.add(btnacheter);
                btnacheter.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceMail.getInstance().sendMail(p.getIdProduit(), "zoulaikha.ouali@esprit.tn");
            }});
        Button btnLouer = new Button("Louer");
        this.add(btnLouer);
        Button addToFavori = new Button("Ajouter au favoris");
        addToFavori.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FavorisProduitForm fpf = new FavorisProduitForm();
//                List l = fpf.getAllProducts().stream().filter(e -> e.getIdProduit() == p.getIdProduit()).collect(Collectors.toList());
               List<Produit> l = fpf.getAllProducts();
               boolean exist = false;
               for(Produit ve : l) {
                   if (ve.getIdProduit() == p.getIdProduit()) {
                       exist = true;
                       break;
                   }
               }
                if (exist) {
                     Dialog.show("Error", "ce produit est déja dans favoris", "OK", "Cancel");
                } else
                fpf.addProduct(p);
            }
        });
        this.add(addToFavori);
    }
    
    
    
}
