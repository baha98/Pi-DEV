/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Objects;
import javafx.scene.image.ImageView;

/**
 *
 * @author itsme
 */
public class Velo {
    int id_velo;
    String Libelle;
    String Marque;	
    Double Prix;
    int Qte;	
    String Couleur;	
    ImageView Image;
    String Descrption;
    String Etat;	
    int Id_Membre;

    public Velo(int id_velo, String Libelle, String Marque, Double Prix, int Qte, String Couleur, ImageView Image, String Descrption, String Etat, int Id_Membre) {
        this.id_velo = id_velo;
        this.Libelle = Libelle;
        this.Marque = Marque;
        this.Prix = Prix;
        this.Qte = Qte;
        this.Couleur = Couleur;
        this.Image = Image;
        this.Descrption = Descrption;
        this.Etat = Etat;
        this.Id_Membre = Id_Membre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_velo;
        hash = 97 * hash + Objects.hashCode(this.Libelle);
        hash = 97 * hash + Objects.hashCode(this.Marque);
        hash = 97 * hash + Objects.hashCode(this.Prix);
        hash = 97 * hash + this.Qte;
        hash = 97 * hash + Objects.hashCode(this.Couleur);
        hash = 97 * hash + Objects.hashCode(this.Image);
        hash = 97 * hash + Objects.hashCode(this.Descrption);
        hash = 97 * hash + Objects.hashCode(this.Etat);
        hash = 97 * hash + this.Id_Membre;
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
        final Velo other = (Velo) obj;
        if (this.id_velo != other.id_velo) {
            return false;
        }
        if (this.Qte != other.Qte) {
            return false;
        }
        if (this.Id_Membre != other.Id_Membre) {
            return false;
        }
        if (!Objects.equals(this.Libelle, other.Libelle)) {
            return false;
        }
        if (!Objects.equals(this.Marque, other.Marque)) {
            return false;
        }
        if (!Objects.equals(this.Couleur, other.Couleur)) {
            return false;
        }
        if (!Objects.equals(this.Descrption, other.Descrption)) {
            return false;
        }
        if (!Objects.equals(this.Etat, other.Etat)) {
            return false;
        }
        if (!Objects.equals(this.Prix, other.Prix)) {
            return false;
        }
        if (!Objects.equals(this.Image, other.Image)) {
            return false;
        }
        return true;
    }
    
    public int getId_velo() {
        return id_velo;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String getMarque() {
        return Marque;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public Double getPrix() {
        return Prix;
    }

    public void setPrix(Double Prix) {
        this.Prix = Prix;
    }

    public int getQte() {
        return Qte;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }

    public ImageView getImage() {
        return Image;
    }

    public void setImage(ImageView Image) {
        this.Image = Image;
    }

    public String getDescrption() {
        return Descrption;
    }

    public void setDescrption(String Descrption) {
        this.Descrption = Descrption;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public int getId_Membre() {
        return Id_Membre;
    }

    public void setId_Membre(int Id_Membre) {
        this.Id_Membre = Id_Membre;
    }

   
}
