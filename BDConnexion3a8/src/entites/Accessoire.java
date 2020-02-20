/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author zoula
 */
public class Accessoire {
    private int Id_Accessoire;
    private String Libelle;
    private double Prix ;
    private int Qte;
    private String Couleur;
    private String Marque;
    private String Description;
    private String Image;
    private int Id_Membre;

    public Accessoire(int Id_Accessoire, String Libelle, double Prix, int Qte, String Couleur, String Marque, String Description, String Image, int Id_Membre) {
        this.Id_Accessoire = Id_Accessoire;
        this.Libelle = Libelle;
        this.Prix = Prix;
        this.Qte = Qte;
        this.Couleur = Couleur;
        this.Marque = Marque;
        this.Description = Description;
        this.Image = Image;
        this.Id_Membre = Id_Membre;
    }

    public Accessoire(String Libelle, double Prix, int Qte, String Couleur, String Marque, String Description, String Image, int Id_Membre) {
        this.Libelle = Libelle;
        this.Prix = Prix;
        this.Qte = Qte;
        this.Couleur = Couleur;
        this.Marque = Marque;
        this.Description = Description;
        this.Image = Image;
        this.Id_Membre = Id_Membre;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.Id_Accessoire;
        hash = 13 * hash + Objects.hashCode(this.Libelle);
        hash = 13 * hash + Objects.hashCode(this.Prix);
        hash = 13 * hash + Objects.hashCode(this.Qte);
        hash = 13 * hash + Objects.hashCode(this.Couleur);
        hash = 13 * hash + Objects.hashCode(this.Marque);
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
        final Accessoire other = (Accessoire) obj;
        if (this.Id_Accessoire != other.Id_Accessoire) {
            return false;
        }
        if (!Objects.equals(this.Libelle, other.Libelle)) {
            return false;
        }
        if (!Objects.equals(this.Prix, other.Prix)) {
            return false;
        }
        if (!Objects.equals(this.Qte, other.Qte)) {
            return false;
        }
        if (!Objects.equals(this.Couleur, other.Couleur)) {
            return false;
        }
        if (!Objects.equals(this.Marque, other.Marque)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Accessoire{" + "Id_Accesoire=" + Id_Accessoire + ", Libelle=" + Libelle + ", Prix=" + Prix + ", Qte=" + Qte + ", Couleur=" + Couleur + ", Marque=" + Marque + '}';
    }

    public int getId_Accessoire() {
        return Id_Accessoire;
    }

    public String getLibelle() {
        return Libelle;
    }

    public double getPrix() {
        return Prix;
    }

    public int getQte() {
        return Qte;
    }

    public String getCouleur() {
        return Couleur;
    }

    public String getMarque() {
        return Marque;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public int getId_Membre() {
        return Id_Membre;
    }

    public void setId_Accesoire(int Id_Accesoire) {
        this.Id_Accessoire = Id_Accesoire;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public void setId_Accessoire(int Id_Accessoire) {
        this.Id_Accessoire = Id_Accessoire;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setId_Membre(int Id_Membre) {
        this.Id_Membre = Id_Membre;
    }

    
    
}
