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
public class ListeProduit {
   private int id_produit;
   private double prix;
   private int quantite;

    public ListeProduit() {
    }

    public ListeProduit(int id_produit, double prix, int quantite) {
        this.id_produit = id_produit;
        this.prix = prix;
        this.quantite = quantite;
    }

    public int getId_produit() {
        return id_produit;
    }

    public double getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id_produit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListeProduit other = (ListeProduit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ListeProduit{" + "id_produit=" + id_produit + ", prix=" + prix + ", quantite=" + quantite + '}';
    }
   
    
    
}
