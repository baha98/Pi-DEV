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
import com.mycompany.myapp.entities.Accessoire;
import com.mycompany.myapp.entities.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author zoula
 */
public class ServiceAccessoire {
       public ArrayList<Accessoire> tasks;
    
    public static ServiceAccessoire instance =null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceAccessoire() {
         req = new ConnectionRequest();
    }

    public static ServiceAccessoire getInstance() {
        if (instance == null) {
            instance = new ServiceAccessoire();
        }
        return instance;
    }

    public boolean addAccessoire(Accessoire a) {
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/accessoires/new";
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("Libelle", a.getLibelle());
        req.addArgument("Categorie", a.getCategorie().getId()+"");
        req.addArgument("Qte", a.getQte()+"");
        req.addArgument("Image", a.getImage());
        req.addArgument("Prix", a.getPrix()+"");
        req.addArgument("Couleur", a.getCouleur());
        req.addArgument("Description", a.getDescription());
        req.addArgument("Marque", a.getMarque());
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

    public ArrayList<Accessoire> parseAccessoires(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Accessoire t = new Accessoire();
                float id = Float.parseFloat(obj.get("IdProduit").toString());
                t.setIdProduit((int)id);
                t.setLibelle(((obj.get("Libelle").toString())));
                float Prix = Float.parseFloat(obj.get("Prix").toString());
                t.setPrix(Prix);
                t.setMarque(((obj.get("Marque").toString())));
                t.setCouleur(((obj.get("Couleur").toString())));
                t.setDescription(((obj.get("Description").toString())));
                Map<String,Object> categ = (Map<String,Object>) obj.get("categorie");
                Categorie categorie = new Categorie();
                float idCateg = Float.parseFloat(categ.get("id").toString());
                categorie.setId((int)idCateg);
                categorie.setNomCategorie(((categ.get("nomCategorie").toString())));
                categorie.setTypeCateg(((categ.get("typeCateg").toString())));
                t.setCategorie(categorie);
                t.setImage(((obj.get("Image").toString())));
                float qte = Float.parseFloat(obj.get("Qte").toString());
                t.setQte((int)qte);
              
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Accessoire> getAllAccessoires(){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/accessoires/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseAccessoires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
     public ArrayList<Accessoire> getAccessoiresByMarque(String marque){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/accessoires/find/"+marque;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseAccessoires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    public ArrayList<Accessoire> getAccessoiressByCateg(String categ){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/accessoires/findbycateg/"+categ;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseAccessoires(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
}
