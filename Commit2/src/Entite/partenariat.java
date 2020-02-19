/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Baha Dammak
 */
public class partenariat {
    
    private int id_partenariat;
    private int id_partenaire;
    private int id_event;
    private int conribution ;

    public partenariat(int id_partenariat, int id_partenaire, int id_event, int conribution) {
        this.id_partenariat = id_partenariat;
        this.id_partenaire = id_partenaire;
        this.id_event = id_event;
        this.conribution = conribution;
    }

    public partenariat(int id_partenaire, int id_event, int conribution) {
        this.id_partenaire = id_partenaire;
        this.id_event = id_event;
        this.conribution = conribution;
    }

    public partenariat(int id_partenariat, int conribution) {
        this.id_partenariat = id_partenariat;
        this.conribution = conribution;
    }

    public partenariat() {
    }

  

    public int getId_partenariat() {
        return id_partenariat;
    }

    public int getId_partenaire() {
        return id_partenaire;
    }

    public int getId_event() {
        return id_event;
    }

    public int getConribution() {
        return conribution;
    }

    public void setId_partenariat(int id_partenariat) {
        this.id_partenariat = id_partenariat;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setConribution(int conribution) {
        this.conribution = conribution;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id_partenariat;
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
        final partenariat other = (partenariat) obj;
        if (this.id_partenariat != other.id_partenariat) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "partenariat{" + "id_partenaire=" + id_partenaire + ", id_event=" + id_event + ", conribution=" + conribution + '}';
    }
    
    
    
}
