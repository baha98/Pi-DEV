/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Categorie;
import com.mycompany.myapp.entities.Velo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zoula
 */
public class ServiceCategorie {
     public ArrayList<Categorie> tasks;
    
    public static ServiceCategorie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategorie() {
         req = new ConnectionRequest();
    }

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public boolean addCategorie(Categorie c) {
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/categories/new";
//     Statics.BASE_URL + "/categories/" + c.getNomCategorie() + "/" + c.getTypeCateg();
        req.setUrl(url);
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

    public ArrayList<Categorie> parseCategories(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
//                Categorie t = new Categorie(); 
//                Map<String,Object> categ = (Map<String,Object>) obj.get("categorie");
                Categorie categorie = new Categorie();
                float idCateg = Float.parseFloat(obj.get("id").toString());
                categorie.setId((int)idCateg);
                categorie.setNomCategorie(((obj.get("NomCategorie").toString())));
                categorie.setTypeCateg(((obj.get("TypeCateg").toString())));
                tasks.add(categorie);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Categorie> getAllCategories(){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/categories/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    public ArrayList<Categorie> getVelosCategories(){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/categories/velos";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
     public ArrayList<Categorie> getAccessoiresCategories(){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/categories/accessoires";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
}
