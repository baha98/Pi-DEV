/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
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
import com.mycompany.myapp.entities.Produit;
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.services.ServiceVelo;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author zoula
 */
public class FavorisProduitForm extends Form {
    Database db;
    
     Resources theme;
    Form f = this;
    Integer page = 0;
    Form previous;
    ArrayList<Produit> products = getAllProducts();

    public FavorisProduitForm() {
        
    }
    
    public FavorisProduitForm(Form previous, Resources theme) {
        this.previous = previous;
        setTitle("List favoris");
        this.theme=theme;
        Form element= new Form( BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previous.showBack() );
        setLayout(BoxLayout.y());
//        for (Produit v: products) {
//            element.add(createElement(v));
//        }
        element.getToolbar().addMaterialCommandToRightBar(""
                , FontImage.MATERIAL_ARROW_FORWARD
                ,e -> affichePage(element) );
        element.getToolbar().addMaterialCommandToLeftBar(""
                , FontImage.MATERIAL_ARROW_BACK
                ,e -> previousPage(element) );
        affichePage(element);
        this.add(element);
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceVelo.getInstance().getAllVelos().toString());
//        add(sp);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    private void refresh() {
        this.repaint();
        this.refreshTheme();
    }
    
    
    private Container createElement(Produit p){        
        
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
                new DetailsProduitForm(theme, f, p, previous).show();
            }
        });
        element.add(lbNom);
        element.add(lbPrix);
        element.setLeadComponent(b);
        return element;
    }
    
    
    public ArrayList<Produit> getAllProducts(){
        ArrayList<Produit> products =  new ArrayList<>();
        
        
        try {
            db = Database.openOrCreate("db3A8.velotn");
            String req = "Create table if not exists Favoris("
                    + "id integer primary key,"
                    + "libelle TEXT,"
                     + "couleur TEXT,"
                     + "image TEXT,"
                     + "description TEXT,"
                     + "qte integer,"
                    + "marque TEXT,"
                     + "type TEXT,"
                      + "etat TEXT,"
                    + "prix float )";
            db.execute(req);

            req = "SELECT * from Favoris";

            Cursor c= db.executeQuery(req);
            
            while (c.next()){
                Row r = c.getRow();
                
                Produit p ;
                String type = r.getString(7);
                if ("Velo".equals(type)) {
                    p = new Velo();
                    ((Velo)p).setEtat(r.getString(8));
                } else {
                    p = new Accessoire();
                }
                p.setIdProduit(r.getInteger(0));
                p.setLibelle(r.getString(1));
                p.setCouleur(r.getString(2));
                p.setImage(r.getString(3));
                p.setDescription(r.getString(4));
                p.setQte(r.getInteger(5));                
                p.setMarque(r.getString(6));
                p.setPrix(r.getFloat(9));
                
                products.add(p);
            }
            c.close();
            db.close();

        } catch (IOException ex) {
            //System.err.println();
            ex.printStackTrace();
        }
        
        
        return products;
    }
    
    
    public void deleteProduct(Integer id) {        
        
        try {
            db = Database.openOrCreate("db3A8.velotn");
            String req = "Create table if not exists Favoris("
                    + "id integer primary key,"
                    + "libelle TEXT,"
                     + "couleur TEXT,"
                     + "image TEXT,"
                     + "description TEXT,"
                     + "qte integer,"
                    + "marque TEXT,"
                     + "type TEXT,"
                      + "etat TEXT,"
                    + "prix float )";
            db.execute(req);

            req = "DELETE from Favoris WHERE id="+id;

            db.execute(req);
            Dialog.show("Success", "supprimer avec succes", "OK", "Cancel");
    } catch (IOException ex) {
            //System.err.println();
            ex.printStackTrace();
        }
    }
    
     public void addProduct(Produit p) {

        try {
            db = Database.openOrCreate("db3A8.velotn");
           String req = "Create table if not exists Favoris("
                    + "id integer primary key,"
                    + "libelle TEXT,"
                     + "couleur TEXT,"
                     + "image TEXT,"
                     + "description TEXT,"
                     + "qte integer,"
                    + "marque TEXT,"
                     + "type TEXT,"
                      + "etat TEXT,"
                    + "prix float )";
            db.execute(req);
            String type;
            String Etat = null;
            if (p instanceof Velo) {
               type = "Velo" ;
               Etat = ((Velo) p).getEtat();
            } else {
                type = "Accessoire";
            }
            req = "INSERT into Favoris values ("
                    + p.getIdProduit()+","
                    + "'" + p.getLibelle() + "',"
                    + "'" + p.getCouleur() + "',"
                    + "'" + p.getImage() + "',"
                    + "'" + p.getDescription() + "',"
                    + "" + p.getQte() + ","
                    + "'" + p.getMarque() + "',"
                    + "'" + type + "',"
                    + "'" + Etat + "',"
                    + p.getPrix() + ")";

            db.execute(req);

            db.close();
            Dialog.show("Success", "Ajouter au favoris avec succes", "OK", "Cancel");


        } catch (IOException ex) {
            //System.err.println();
            Dialog.show("Error", "Erreur", "OK", "Cancel");
            ex.printStackTrace();
        }
    }
     
     public void dropTable() {

        try {
            db = Database.openOrCreate("db3A8.velotn");
           String req = "Drop table Favoris";
            db.execute(req);
        } catch (IOException ex) {
            //System.err.println();
            ex.printStackTrace();
        }
     }
     
     private void affichePage(Form element) {
            if (page < ((products.size() + 3) / 4)){
                page++;
                element.setTitle("page "+page);
                element.removeAll();
                element.refreshTheme();
                refresh();
                for (int i= (page-1)*4; i<(((page-1)*4)+4) && (products.size()-1>i); i++) {
                    element.add(createElement(products.get(i)));
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
                    for (int i= (page-1)*4; i<(((page-1)*4)+4) && (products.size()-1>i); i++) {
                    element.add(createElement(products.get(i)));
        } 
                   
                    element.setTitle("page "+page);
                element.refreshTheme();
                    refresh();
             }
    }

}
