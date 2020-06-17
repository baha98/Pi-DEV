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
public class Categorie {
    private int id;
    private String NomCategorie,TypeCateg;

    public Categorie() {
    }
    
    public Categorie(int id) {
        this.id = id;
    }

    public Categorie(int id, String NomCategorie, String TypeCateg) {
        this.id = id;
        this.NomCategorie = NomCategorie;
        this.TypeCateg = TypeCateg;
    }

    public Categorie(String NomCategorie, String TypeCateg) {
        this.NomCategorie = NomCategorie;
        this.TypeCateg = TypeCateg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return NomCategorie;
    }

    public void setNomCategorie(String NomCategorie) {
        this.NomCategorie = NomCategorie;
    }

    public String getTypeCateg() {
        return TypeCateg;
    }

    public void setTypeCateg(String TypeCateg) {
        this.TypeCateg = TypeCateg;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", NomCategorie=" + NomCategorie + ", TypeCateg=" + TypeCateg + '}';
    }
    
}
