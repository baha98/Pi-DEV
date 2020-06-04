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
public class Panier {
    private int id;
    private String nom;
    private int quantite;
    private String NomImg;
    private float prix;

    public Panier() {
    }

    public String getNomImg() {
        return NomImg;
    }

    public void setNomImg(String NomImg) {
        this.NomImg = NomImg;
    }

    public Panier(int id, String nom, int quantite, String NomImg, float prix) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.NomImg = NomImg;
        this.prix = prix;
    }

    public Panier(String nom, int quantite, String NomImg, float prix) {
        this.nom = nom;
        this.quantite = quantite;
        this.NomImg = NomImg;
        this.prix = prix;
    }

    
    
    
    
    public Panier(int id, String nom, int quantite, String NomImg) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.NomImg = NomImg;
    }

    public Panier(int id, String nom, int quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
    
    
    
}
