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
    private int id_participant ;
    private String nom;
    private String prenom;
    private Time record ;
    private int ranking;

    public resultat() {
    }

    public resultat(int id_participant, String nom, String prenom, Time record, int ranking) {
        this.id_participant = id_participant;
        this.nom = nom;
        this.prenom = prenom;
        this.record = record;
        this.ranking = ranking;
    }

    public resultat(String nom, String prenom, Time record, int ranking) {
        this.nom = nom;
        this.prenom = prenom;
        this.record = record;
        this.ranking = ranking;
    }

    public int getId_participant() {
        return id_participant;
    }

    public void setId_participant(int id_participant) {
        this.id_participant = id_participant;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id_participant;
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
        final resultat other = (resultat) obj;
        if (this.id_participant != other.id_participant) {
            return false;
        }
        return true;
    }
    
}
