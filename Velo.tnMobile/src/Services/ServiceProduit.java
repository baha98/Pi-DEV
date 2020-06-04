/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Utilis.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceProduit {
    
    public ArrayList<Produit> produits;
    public static ServiceProduit instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
      private ServiceProduit() {
         req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
    
    
    
    
    public ArrayList<Produit> parseProduits(String jsonText){
        try {
            produits=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("IdProduit").toString());
                p.setId_produit((int)id);
                p.setPrix(Float.parseFloat(obj.get("Prix").toString()));
                p.setMarque(obj.get("Marque").toString());
                p.setQte((int)Float.parseFloat(obj.get("Qte").toString()));
                p.setLibelle(obj.get("Libelle").toString());
                p.setCouleur(obj.get("Couleur").toString());
                p.setImg(obj.get("Image").toString());
                p.setDescription(obj.get("Description").toString());
                produits.add(p);
            }
            
            
        } catch (IOException ex) {
            
        }
        return produits;
    }
    
    public ArrayList<Produit> getAllTasks(){
        String url = Statics.BASE_URL+"/Produit/afficherProduitMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return produits;
    }
}
