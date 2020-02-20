/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;
import java.util.Objects;
import javafx.scene.image.ImageView;

/**
 *
 * @author itsme
 */
public class AffichageLocation {
    private ImageView image;
    private int id_location ;
    private int id_user ;
    private String nom_user;
    private String prenom_user;
    private int id_velo ;
    private String nom_velo;
    private String descrption;
    private Date date_debut ;
    private Date date_fin ;
    private double prix;

    public AffichageLocation(ImageView image, int id_location, int id_user, String nom_user, String prenom_user, int id_velo, String nom_velo, String description, Date date_debut, Date date_fin, double prix) {
        this.image = image;
        this.id_location = id_location;
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.id_velo = id_velo;
        this.nom_velo = nom_velo;
        this.descrption = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    public AffichageLocation(ImageView image, String  nom_user, String prenom_user, String nom_velo, String Desc_velo, Date date_debut, Date date_fin, double prix) {
        this.image = image;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.nom_velo = nom_velo;
        this.descrption =Desc_velo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.image);
        hash = 47 * hash + this.id_location;
        hash = 47 * hash + this.id_user;
        hash = 47 * hash + Objects.hashCode(this.nom_user);
        hash = 47 * hash + Objects.hashCode(this.prenom_user);
        hash = 47 * hash + this.id_velo;
        hash = 47 * hash + Objects.hashCode(this.nom_velo);
        hash = 47 * hash + Objects.hashCode(this.descrption);
        hash = 47 * hash + Objects.hashCode(this.date_debut);
        hash = 47 * hash + Objects.hashCode(this.date_fin);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.prix) ^ (Double.doubleToLongBits(this.prix) >>> 32));
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
        final AffichageLocation other = (AffichageLocation) obj;
        if (this.id_location != other.id_location) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_velo != other.id_velo) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom_user, other.nom_user)) {
            return false;
        }
        if (!Objects.equals(this.prenom_user, other.prenom_user)) {
            return false;
        }
        if (!Objects.equals(this.nom_velo, other.nom_velo)) {
            return false;
        }
        if (!Objects.equals(this.descrption, other.descrption)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user = nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user = prenom_user;
    }

    public int getId_velo() {
        return id_velo;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
    }

    public String getNom_velo() {
        return nom_velo;
    }

    public void setNom_velo(String nom_velo) {
        this.nom_velo = nom_velo;
    }

    public String getDescription() {
        return descrption;
    }

    public void setDescription(String description) {
        this.descrption = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    
    
}
