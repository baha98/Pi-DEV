/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author zoula
 */
public class Produit {
    protected int IdProduit,Qte;
    protected String Libelle,Marque,Couleur,Image,Description;
    protected float Prix;
    protected Categorie categorie;

    public Produit(int IdProduit, int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        this.IdProduit = IdProduit;
        this.Qte = Qte;
        this.Libelle = Libelle;
        this.Marque = Marque;
        this.Couleur = Couleur;
        this.Image = Image;
        this.Description = Description;
        this.Prix = Prix;
    }

    public Produit(int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        this.Qte = Qte;
        this.Libelle = Libelle;
        this.Marque = Marque;
        this.Couleur = Couleur;
        this.Image = Image;
        this.Description = Description;
        this.Prix = Prix;
    }

    public Produit() {
    }

    public int getIdProduit() {
        return IdProduit;
    }

    public int getQte() {
        return Qte;
    }

    public String getLibelle() {
        return Libelle;
    }

    public String getMarque() {
        return Marque;
    }

    public String getCouleur() {
        return Couleur;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }

    public float getPrix() {
        return Prix;
    }

    public void setIdProduit(int IdProduit) {
        this.IdProduit = IdProduit;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "IdProduit=" + IdProduit + ", Qte=" + Qte + ", Libelle=" + Libelle + ", Marque=" + Marque + ", Couleur=" + Couleur + ", Image=" + Image + ", Description=" + Description + ", Prix=" + Prix + ", categorie=" + categorie + '}';
    }

    
    
    
}
