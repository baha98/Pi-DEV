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
 * @author bhk
 */
public class ServiceVelo {

    public ArrayList<Velo> tasks;
    
    public static ServiceVelo instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceVelo() {
         req = new ConnectionRequest();
    }

    public static ServiceVelo getInstance() {
        if (instance == null) {
            instance = new ServiceVelo();
        }
        return instance;
    }

    public boolean addVelo(Velo v) {
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/velos/new";
        req.setUrl(url);
        req.setPost(true);
        req.addArgument("Libelle", v.getLibelle());
        req.addArgument("Categorie", v.getCategorie().getId()+"");
        req.addArgument("Qte", v.getQte()+"");
        req.addArgument("Image", v.getImage());
        req.addArgument("Prix", v.getPrix()+"");
        req.addArgument("Couleur", v.getCouleur());
        req.addArgument("Description", v.getDescription());
        req.addArgument("Marque", v.getMarque());
        req.addArgument("Etat", v.getEtat()+"");

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

    public ArrayList<Velo> parseVelos(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Velo t = new Velo();
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
               if ( categ != null) {
                   float idCateg = Float.parseFloat(categ.get("id").toString());
                categorie.setId((int)idCateg);
                categorie.setNomCategorie(((categ.get("nomCategorie").toString())));
                categorie.setTypeCateg(((categ.get("typeCateg").toString())));
                t.setCategorie(categorie);
               }
                
                t.setImage(((obj.get("Image").toString())));
                t.setEtat(((obj.get("Etat").toString())));
                float qte = Float.parseFloat(obj.get("Qte").toString());
                t.setQte((int)qte);
              
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Velo> getAllVelos(){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/velos/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseVelos(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    public ArrayList<Velo> getVelosByMarque(String marque){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/velos/find/"+marque;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseVelos(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
    public ArrayList<Velo> getVelosByCateg(String categ){
        String url = "http://localhost/espritclub/web/app_dev.php/Produit/velos/findbycateg/"+categ;
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseVelos(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
}
