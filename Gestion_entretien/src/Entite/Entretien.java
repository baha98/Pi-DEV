/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.util.Objects;

/**
 *
 * @author khalil slama
 */
public class Entretien {
    private int id_entretien ;
    private int id_cycliste;
    private int id_user;
    private int id_velo;
    private String etat;

    public Entretien() {
    }

    public Entretien(int id_entretien, String etat) {
        this.id_entretien = id_entretien;
        this.etat = etat;
    }

    public Entretien(int id_cycliste, int id_user, int id_velo, String etat) {
        this.id_cycliste = id_cycliste;
        this.id_user = id_user;
        this.id_velo = id_velo;
        this.etat = etat;
    }
    
    public Entretien(int id_entretien, int id_cycliste, int id_user, int id_velo, String etat) {
        this.id_entretien = id_entretien;
        this.id_cycliste = id_cycliste;
        this.id_user = id_user;
        this.id_velo = id_velo;
        this.etat = etat;
    }

    public int getId_entretien() {
        return id_entretien;
    }

    public void setId_entretien(int id_entretien) {
        this.id_entretien = id_entretien;
    }

    public int getId_cycliste() {
        return id_cycliste;
    }

    public void setId_cycliste(int id_cycliste) {
        this.id_cycliste = id_cycliste;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id_entretien;
        hash = 79 * hash + this.id_cycliste;
        hash = 79 * hash + this.id_user;
        hash = 79 * hash + this.id_velo;
        hash = 79 * hash + Objects.hashCode(this.etat);
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
        final Entretien other = (Entretien) obj;
        if (this.id_entretien != other.id_entretien) {
            return false;
        }
        if (this.id_cycliste != other.id_cycliste) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_velo != other.id_velo) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }
   

    

    
    
}
