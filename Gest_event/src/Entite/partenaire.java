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
public class partenaire {
    private int id_partenaire ;
    private String nom_partenaire;
    private String spec_partenaire;
    private String desc_partenaire;

    public partenaire(int id_partenaire, String nom_partenaire, String spec_partenaire, String desc_partenaire) {
        this.id_partenaire = id_partenaire;
        this.nom_partenaire = nom_partenaire;
        this.spec_partenaire = spec_partenaire;
        this.desc_partenaire = desc_partenaire;
    }

    public partenaire(String nom_partenaire, String spec_partenaire, String desc_partenaire) {
        this.nom_partenaire = nom_partenaire;
        this.spec_partenaire = spec_partenaire;
        this.desc_partenaire = desc_partenaire;
    }

    public partenaire() {
    }

    public int getId_partenaire() {
        return id_partenaire;
    }

    public String getNom_partenaire() {
        return nom_partenaire;
    }

    public String getSpec_partenaire() {
        return spec_partenaire;
    }

    public String getDesc_partenaire() {
        return desc_partenaire;
    }

    public void setId_partenaire(int id_partenaire) {
        this.id_partenaire = id_partenaire;
    }

    public void setNom_partenaire(String nom_partenaire) {
        this.nom_partenaire = nom_partenaire;
    }

    public void setSpec_partenaire(String spec_partenaire) {
        this.spec_partenaire = spec_partenaire;
    }

    public void setDesc_partenaire(String desc_partenaire) {
        this.desc_partenaire = desc_partenaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_partenaire;
        return hash;
    }

    @Override
    public String toString() {
        return "partenaire{" + "nom_partenaire=" + nom_partenaire + ", specialite_partenaire=" + spec_partenaire + ", description_partenaire=" + desc_partenaire + '}';
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
        final partenaire other = (partenaire) obj;
        if (this.id_partenaire != other.id_partenaire) {
            return false;
        }
        return true;
    }
    
    
}
