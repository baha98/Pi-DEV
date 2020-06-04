/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


/**
 *
 * @author ASUS
 */
public class Paiement {
    private int Id_Paiement;
    private String Type_Paiement;
    private int Num_Carte;
    private Date Date_Expiration;
    private int Code_Sec;
    private String Pays;
    private int Id_Membre;


    public Paiement() {
    }

    public Paiement(int Id_Paiement, int Num_Carte, Date Date_Expiration, int Code_Sec, String Pays, int Id_Membre) {
        this.Id_Paiement = Id_Paiement;
        this.Num_Carte = Num_Carte;
        this.Date_Expiration = Date_Expiration;
        this.Code_Sec = Code_Sec;
        this.Pays = Pays;
        this.Id_Membre = Id_Membre;
    }

    public Paiement(String Type_Paiement, int Num_Carte, Date Date_Expiration, int Code_Sec, String Pays, int Id_Membre) {
        this.Type_Paiement = Type_Paiement;
        this.Num_Carte = Num_Carte;
        this.Date_Expiration = Date_Expiration;
        this.Code_Sec = Code_Sec;
        this.Pays = Pays;
        this.Id_Membre = Id_Membre;
    }
    

    public int getId_Paiement() {
        return Id_Paiement;
    }

    public void setId_Paiement(int Id_Paiement) {
        this.Id_Paiement = Id_Paiement;
    }

    public String getType_Paiement() {
        return Type_Paiement;
    }

    public void setType_Paiement(String Type_Paiement) {
        this.Type_Paiement = Type_Paiement;
    }

    public int getNum_Carte() {
        return Num_Carte;
    }

    public void setNum_Carte(int Num_Carte) {
        this.Num_Carte = Num_Carte;
    }

    public Date getDate_Expiration() {
        return Date_Expiration;
    }

    public void setDate_Expiration(Date Date_Expiration) {
        this.Date_Expiration = Date_Expiration;
    }

    public int getCode_Sec() {
        return Code_Sec;
    }

    public void setCode_Sec(int Code_Sec) {
        this.Code_Sec = Code_Sec;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String Pays) {
        this.Pays = Pays;
    }

    public int getId_Membre() {
        return Id_Membre;
    }

    public void setId_Membre(int Id_Membre) {
        this.Id_Membre = Id_Membre;
    }
    
    
    
   
    
    
    
    
    
}
