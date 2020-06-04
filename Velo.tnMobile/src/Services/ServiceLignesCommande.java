/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.LignesCommande;
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
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceLignesCommande {
    
     public ArrayList<LignesCommande> ligneCommande;
    
    public static ServiceLignesCommande instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        private ServiceLignesCommande() {
         req = new ConnectionRequest();
    }
    
        
    public static ServiceLignesCommande getInstance() {
        if (instance == null) {
            instance = new ServiceLignesCommande();
        }
        return instance;
    }
    
    
    
    
     public ArrayList<LignesCommande> parseCommande(String jsonText){
        try {
            ligneCommande=new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
          
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                LignesCommande c = new LignesCommande();
                //float id = Float.parseFloat(obj.get("IdCommande").toString());
                
                java.util.LinkedHashMap commande=(java.util.LinkedHashMap)obj.get("IdCommande");
                
                float idcommande=(Float.parseFloat(commande.get("idCommande").toString()));
                c.setId_commande(((int)idcommande));
                float qte=(Float.parseFloat(obj.get("qte").toString()));
                java.util.LinkedHashMap produit=(java.util.LinkedHashMap)obj.get("IdProduit");
                
                float idproduit=(Float.parseFloat(produit.get("IdProduit").toString()));
                String libelle=produit.get("Libelle").toString();
                String IMAGE=produit.get("Image").toString();
                float prix=Float.valueOf(produit.get("Prix").toString());
                float Qte=(Float.parseFloat(produit.get("Qte").toString()));
                c.setId_produit((int)(idproduit));
                c.setLibelle((libelle));
                c.setPirx((prix));
                c.setImg(IMAGE);
                c.setQte((int)(qte));

                ligneCommande.add(c);
               
              
            }
          
            
            
        } catch (IOException ex) {
            
        }
        return ligneCommande;
    }
    
    public ArrayList<LignesCommande> getAllTasks(int id){
        String url = Statics.BASE_URL+"/Commande/DetailsMobile?idcommande="+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ligneCommande = parseCommande(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ligneCommande;
    } 
    
    
    
    
    
    
}
