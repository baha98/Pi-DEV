/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Objects;

/**
 *
 * @author itsme
 */
public class AffichageEntretien {
    private int id_entretien;
    private int id_user ;
    private String nom_user;
    private String prenom_user;
    private int id_velo ;
    private String nom_velo;
    private String etat;

    public AffichageEntretien(int id_entretien, int id_user, String nom_user, String prenom_user, int id_velo, String nom_velo, String etat) {
        this.id_entretien = id_entretien;
        this.id_user = id_user;
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.id_velo = id_velo;
        this.nom_velo = nom_velo;
        this.etat = etat;
    }

    public AffichageEntretien(String nom_user, String prenom_user, String nom_velo, String etat) {
        this.nom_user = nom_user;
        this.prenom_user = prenom_user;
        this.nom_velo = nom_velo;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "AffichageEntretien{" + "id_entretien=" + id_entretien + ", id_user=" + id_user + ", nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", id_velo=" + id_velo + ", nom_velo=" + nom_velo + ", etat=" + etat + '}';
    }

    public int getId_entretien() {
        return id_entretien;
    }

    public void setId_entretien(int id_entretien) {
        this.id_entretien = id_entretien;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.id_entretien;
        hash = 53 * hash + this.id_user;
        hash = 53 * hash + Objects.hashCode(this.nom_user);
        hash = 53 * hash + Objects.hashCode(this.prenom_user);
        hash = 53 * hash + this.id_velo;
        hash = 53 * hash + Objects.hashCode(this.nom_velo);
        hash = 53 * hash + Objects.hashCode(this.etat);
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
        final AffichageEntretien other = (AffichageEntretien) obj;
        if (this.id_entretien != other.id_entretien) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_velo != other.id_velo) {
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
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

    
    
}
