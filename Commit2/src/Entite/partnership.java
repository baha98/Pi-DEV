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
public class partnership {
    private String nom_partenaire ;
    private String spec_partenaire ;
    private int contribution ;

    public partnership(String nom_partenaire, String spec_partenaire, int contribution) {
        this.nom_partenaire = nom_partenaire;
        this.spec_partenaire = spec_partenaire;
        this.contribution = contribution;
    }

    public String getNom_partenaire() {
        return nom_partenaire;
    }

    public String getSpec_partenaire() {
        return spec_partenaire;
    }

    public int getContribution() {
        return contribution;
    }

    public partnership() {
    }

    public void setNom_partenaire(String nom_partenaire) {
        this.nom_partenaire = nom_partenaire;
    }

    public void setSpec_partenaire(String spec_partenaire) {
        this.spec_partenaire = spec_partenaire;
    }

    public void setContribution(int contribution) {
        this.contribution = contribution;
    }

    @Override
    public String toString() {
        return "partnership{" + "nom_partenaire=" + nom_partenaire + ", specialite_partenaire=" + spec_partenaire + ", contribution=" + contribution + "\n.4"
                + ".";
    }
    
    
}
