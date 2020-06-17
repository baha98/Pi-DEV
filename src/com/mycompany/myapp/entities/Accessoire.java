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
public class Accessoire extends Produit{

    public Accessoire(int IdProduit, int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        super(IdProduit, Qte, Libelle, Marque, Couleur, Image, Description, Prix);
    }

    public Accessoire(Categorie categorie ,int Qte, String Libelle, String Marque, String Couleur, String Image, String Description, float Prix) {
        super(Qte, Libelle, Marque, Couleur, Image, Description, Prix);
        this.categorie = categorie;
    }

    public Accessoire() {
    }
    
    
}
