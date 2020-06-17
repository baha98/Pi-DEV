/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Velo;
import java.util.ArrayList;

/**
 *
 * @author zoula
 */
public class ServiceMail {
    
    public ArrayList<Velo> tasks;
    
    public static ServiceMail instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceMail() {
         req = new ConnectionRequest();
    }

    public static ServiceMail getInstance() {
        if (instance == null) {
            instance = new ServiceMail();
        }
        return instance;
    }

     public boolean sendMail(int id, String mail){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/mailto/"+id+"/"+mail;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
