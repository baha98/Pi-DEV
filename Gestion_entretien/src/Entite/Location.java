/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 *
 * @author itsme
 */
public class Location {
    private int id_location ;
    private int id_user ;
    private int id_velo ;
    private Date date_debut ;
    private Date date_fin ;
    private  double prix;
 

    public Location() {
    }

    public Location(int id_user, int id_velo, java.sql.Date date_debut, java.sql.Date date_fin, double prix) {
        this.id_user = id_user;
        this.id_velo = id_velo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = (double)prix/30;
    }
    
    public Location(int id_location, int id_user, int id_velo, Date date_debut, Date date_fin, double prix) {
        this.id_location = id_location;
        this.id_user = id_user;
        this.id_velo = id_velo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = (double)prix/30;
    }
    public Location(int id_location,Date date_debut, Date date_fin) {
        this.id_location = id_location;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = (double)prix/30;
    }
    public Location(Date date_debut, Date date_fin, double prix) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = (double)prix/30;
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

    public int getId_velo() {
        return id_velo;
    }

    public void setId_velo(int id_velo) {
        this.id_velo = id_velo;
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
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id_location;
        hash = 13 * hash + this.id_user;
        hash = 13 * hash + this.id_velo;
        hash = 13 * hash + Objects.hashCode(this.date_debut);
        hash = 13 * hash + Objects.hashCode(this.date_fin);
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.prix) ^ (Double.doubleToLongBits(this.prix) >>> 32));
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
        final Location other = (Location) obj;
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
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }    
    
    
}
