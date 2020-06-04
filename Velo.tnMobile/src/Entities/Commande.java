/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Commande {
    private int id_commande;
    private Date  date_commande;
    private float prix_total;
    private int etat_commande;
    private int id_user;

    public Commande(Date date_commande, float prix_total, int etat_commande, int id_user) {
        this.date_commande = date_commande;
        this.prix_total = prix_total;
        this.etat_commande = etat_commande;
        this.id_user = id_user;
    }

    public Commande(float prix_total) {
        this.prix_total = prix_total;
    }

    public Commande() {
    }

    
    
    public Commande(float prix_total, int id_user) {
        this.prix_total = prix_total;
        this.id_user = id_user;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public float getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(float prix_total) {
        this.prix_total = prix_total;
    }

    public int getEtat_commande() {
        return etat_commande;
    }

    public void setEtat_commande(int etat_commande) {
        this.etat_commande = etat_commande;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
  
   
}
