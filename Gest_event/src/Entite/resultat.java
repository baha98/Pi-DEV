/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Time;

/**
 *
 * @author Baha Dammak
 */
public class resultat {
    private String nom;
    private String prenom;
    private Time record ;
    private int ranking;

    public resultat() {
    }

    public resultat(String nom, String prenom, Time record, int ranking) {
        this.nom = nom;
        this.prenom = prenom;
        this.record = record;
        this.ranking = ranking;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Time getRecord() {
        return record;
    }

    public int getRanking() {
        return ranking;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setRecord(Time record) {
        this.record = record;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "resultat{" + "nom=" + nom + ", prenom=" + prenom + ", record=" + record + ", ranking=" + ranking + "} \n";
    }
    
}
