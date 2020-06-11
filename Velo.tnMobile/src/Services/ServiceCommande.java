/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.Paiement;
import Entities.Panier;
import Entities.Produit;
import Gui.AfficherPanier;
import Utilis.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




/**
 *
 * @author ASUS
 */
public class ServiceCommande {
    
     public ArrayList<Commande> commandes;
     public int id=0;
    
    public static ServiceCommande instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        private ServiceCommande() {
         req = new ConnectionRequest();
    }
    
        
    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }
    
      public boolean AjouterCommande(Commande c,List<Panier> panier) {
        
        String url = Statics.BASE_URL + "/Commande/AjouterCommandeMobile?total="+c.getPrix_total()+"&userid=2";
        int nbrproduit=0;
        for(Panier p:panier)
        {   
            nbrproduit++;
            url+="&idproduit"+nbrproduit+"="+p.getId()+"&quantite"+nbrproduit+"="+p.getQuantite();
            
        }
        url=url+"&nbproduit="+nbrproduit;
        System.out.println(url);
        System.out.println(nbrproduit);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this); 
               
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      

    public ArrayList<Commande> parseCommande(String jsonText){
        try {
            commandes=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
          
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();
                float id = Float.parseFloat(obj.get("IdCommande").toString());
                c.setId_commande((int)id);
                c.setPrix_total(Float.parseFloat(obj.get("PrixTotal").toString()));
                c.setEtat_commande((int)Float.parseFloat(obj.get("EtatCommande").toString()));
                
                    java.util.LinkedHashMap datefin=(java.util.LinkedHashMap)obj.get("DateCommande");
                     Double date_f=(Double)datefin.get("timestamp");
                     
                     Date date_fin=new Date((date_f.longValue())*1000);
                 c.setDate_commande(date_fin);
                    
                    
                    
                  
                
                c.setId_user(2);
                commandes.add(c);
                
              
            }
          
            
            
        } catch (IOException ex) {
            
        }
        return commandes;
    }
    
    public ArrayList<Commande> getAllTasks(){
        String url = Statics.BASE_URL+"/Commande/AfficherCommandeMobile?id=2";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommande(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    } 
    
    
     public ArrayList<Commande> parseCommandeStat(String jsonText){
        try {
            commandes=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
          
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Commande c = new Commande();
                float id = Float.parseFloat(obj.get("IdCommande").toString());
                c.setId_commande((int)id);
                c.setPrix_total(Float.parseFloat(obj.get("PrixTotal").toString()));
                c.setEtat_commande((int)Float.parseFloat(obj.get("EtatCommande").toString()));
                
                    java.util.LinkedHashMap datefin=(java.util.LinkedHashMap)obj.get("DateCommande");
                     Double date_f=(Double)datefin.get("timestamp");
                     
                     Date date_fin=new Date((date_f.longValue())*1000);
                 c.setDate_commande(date_fin);
                c.setId_user(2);
                commandes.add(c);
                
              
            }
          
            
            
        } catch (IOException ex) {
            
        }
        return commandes;
    }
    
    public ArrayList<Commande> getCommandeMonth(String date1,String date2,int iduser){
        String url = Statics.BASE_URL+"/Commande/StatMobile?date1="+date1+"&date2="+date2+"&id=2";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandeStat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return commandes;
    } 
    
    
    public int parseLastCommande(String jsonText) throws IOException{
        
            int idmax=0;
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            
            for(Map<String,Object> obj : list){
                
                
                float id = Float.parseFloat(obj.get("IdCommande").toString());
                if(idmax<(int)id)
                {
                   idmax=(int)id;
                }
            }     
        
        return idmax;
    }
    
    public int getLastId(){
        String url = Statics.BASE_URL+"/Commande/NewestDate?id=2";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    id = parseLastCommande(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                } catch (IOException ex) {
                    
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return id;
    } 
    
    
    
    
}
