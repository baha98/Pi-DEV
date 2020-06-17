/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Velo;
import com.mycompany.myapp.services.ServiceMail;
import com.mycompany.myapp.services.ServiceVelo;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
Resources theme;
    public HomeForm(Resources theme) {
        this.theme=theme;
        current=this;
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnListvelos = new Button("List velos");
        Button btnListacc = new Button("List accessoires");
        Button btnlistFavoris = new Button("list favoris");
        Button sendMail = new Button("send mail");
        
        btnListvelos.addActionListener(e-> new ListVelosForm(current,theme).show());
        add(btnListvelos);
        btnListacc.addActionListener(e-> new ListAccessoiresForm(current, theme).show());
        add(btnListacc);
        btnlistFavoris.addActionListener(e -> new FavorisProduitForm(current,theme).show());
        add(btnlistFavoris);
        sendMail.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceMail.getInstance().sendMail(5, "zoulaikha.ouali@esprit.tn");
            }});
        add(sendMail);
        
    }
    
    
}
