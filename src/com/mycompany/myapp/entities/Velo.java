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
public class Velo extends Produit{
    private String Etat;

    public Velo(String Etat, int IdProduit, int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        super(IdProduit, Qte, Libelle, Marque, Couleur, Image, Description, Prix);
        this.Etat = Etat;
    }

    public Velo(String Etat, int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        super(Qte, Libelle, Marque, Couleur, Image, Description, Prix);
        this.Etat = Etat;
    }
    
    public Velo(Categorie categorie, String Etat, int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        super(Qte, Libelle, Marque, Couleur, Image, Description, Prix);
        this.Etat = Etat;
        this.categorie = categorie;
    }

    public Velo(String Etat) {
        this.Etat = Etat;
    }

    public Velo() {
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    @Override
    public String toString() {
        return super.toString()+"Velo{" + "Etat=" + Etat + '}';
    }

   
}
