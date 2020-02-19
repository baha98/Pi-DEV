/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;


/**
 *
 * @author ONS
 */
public class Actualite {
    private int id_actualite;
    private String titre;
    private String description;
    private String image;
    private String date_actualite;
    private int nbre_vues ;
    private int nbre_jaime;
    private Categorie categorie;

    public Actualite() {
    }

    public Actualite(String titre, String description, String image, Categorie categorie) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.categorie = categorie;
    }


    public Actualite(int id_actualite, String titre, String description, String image, String date_actualite, int nbre_vues, int nbre_jaime, Categorie categorie) {
        this.id_actualite = id_actualite;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.date_actualite = date_actualite;
        this.nbre_vues = nbre_vues;
        this.nbre_jaime = nbre_jaime;
        this.categorie = categorie;
    }

    public Actualite(int id_actualite, String titre, String description, String image, String date_actualite, Categorie categorie) {
        this.id_actualite = id_actualite;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.date_actualite = date_actualite;
        this.categorie = categorie;
    }
    

    @Override
    public String toString() {
        return "actualite{" + "id_actualite=" + id_actualite + ", titre=" + titre + ", description=" + description + ", image=" + image + ", date_actualite=" + date_actualite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_actualite;
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
        final Actualite other = (Actualite) obj;
        if (this.id_actualite != other.id_actualite) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return true;
    }

    public int getId_actualite() {
        return id_actualite;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getDate_actualite() {
        return date_actualite;
    }

    public void setId_actualite(int id_actualite) {
        this.id_actualite = id_actualite;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate_actualite(String date_actualite) {
        this.date_actualite = date_actualite;
    }

    public int getNbre_vues() {
        return nbre_vues;
    }

    public void setNbre_vues(int nbre_vues) {
        this.nbre_vues = nbre_vues;
    }

    public int getNbre_jaime() {
        return nbre_jaime;
    }

    public void setNbre_jaime(int nbre_jaime) {
        this.nbre_jaime = nbre_jaime;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    
}
