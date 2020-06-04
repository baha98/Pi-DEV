/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ASUS
 */
public class LignesCommande {
    
    private int id_commande;
    private int id_produit;
    private String libelle;
    private float pirx;
    private int qte;
    private String img;

    public LignesCommande() {
    }

    
    public LignesCommande(int id_commande, int id_produit, int qte) {
        this.id_commande = id_commande;
        this.id_produit = id_produit;
        this.qte = qte;
    }

    
    
    
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPirx() {
        return pirx;
    }

    public void setPirx(float pirx) {
        this.pirx = pirx;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
    
    
    
    
    
}
