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
public class Produit {
    private int id_produit;
    private String libelle;
    private float prix;
    private String marque;
    private int qte;
    private String couleur;
    private String img;
    private String description;
    private int id_membre;
    private String type;
    private String Categorie;

    public Produit() {
    }

    public Produit(int id_produit, String libelle, float prix, String marque, int qte, String couleur, String img, String description, int id_membre, String type, String Categorie) {
        this.id_produit = id_produit;
        this.libelle = libelle;
        this.prix = prix;
        this.marque = marque;
        this.qte = qte;
        this.couleur = couleur;
        this.img = img;
        this.description = description;
        this.id_membre = id_membre;
        this.type = type;
        this.Categorie = Categorie;
    }

    public Produit(String libelle, float prix, String marque, int qte, String couleur, String img, String description, int id_membre, String type, String Categorie) {
        this.libelle = libelle;
        this.prix = prix;
        this.marque = marque;
        this.qte = qte;
        this.couleur = couleur;
        this.img = img;
        this.description = description;
        this.id_membre = id_membre;
        this.type = type;
        this.Categorie = Categorie;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String Categorie) {
        this.Categorie = Categorie;
    }
    
    
    
    
    
    
    
}
