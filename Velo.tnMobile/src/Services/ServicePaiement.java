/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Paiement;
import Entities.Panier;
import Gui.AfficherPanier;
import Utilis.Statics;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ServicePaiement {
     public ArrayList<Paiement> paiements;
     Database db;
    
    public static ServicePaiement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
        private ServicePaiement() {
         req = new ConnectionRequest();
    }
    
        
    public static ServicePaiement getInstance() {
        if (instance == null) {
            instance = new ServicePaiement();
        }
        return instance;
    }
    
      public boolean AjouterPaiement(Paiement p,int idcommande) {
      
        String url = Statics.BASE_URL + "/Commande/AjouterMobile?type_paiement="+p.getType_Paiement()+"&num_carte="+p.getNum_Carte()+"&date_exp="+p.getDate_Expiration()+"&code_sec="+p.getCode_Sec()+"&pays="+p.getPays()+"&idcommande="+idcommande+"&membre=2";
        
        int nbrproduit=0;
        for(Panier panier:getAllProducts())
        {   
            nbrproduit++;
            url+="&idproduit"+nbrproduit+"="+panier.getId()+"&quantite"+nbrproduit+"="+panier.getQuantite();
            
        }
        url=url+"&nbproduit="+nbrproduit;
        
        
        
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
      
      
      public List<Panier> getAllProducts()
    {  List<Panier> panier=new ArrayList();
        try {
             
          db=Database.openOrCreate("Velo");
                String req="create table if not exists panier("
                 +"id integer primary key ,"
                 +"nom TEXT,"
                 +"quantite Integer)";
                
                 req="select * from panier"; 
                 Cursor c=db.executeQuery(req);

            
            while(c.next())
            {
                Row r=c.getRow();
                
                Panier p= new Panier();
                p.setId(r.getInteger(0));
                p.setNom(r.getString(1));
                p.setQuantite(r.getInteger(2));
                p.setNomImg(r.getString(3));
                p.setPrix(r.getFloat(4));
                panier.add(p);
                
            }
                
        
        } catch (IOException ex) {
            System.out.println("okxxx");
        }
        
        
        return panier;
    } 
    
}
