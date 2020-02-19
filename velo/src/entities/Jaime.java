/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hp
 */
public class Jaime {
    private int id_user;
    private int id_actualite;
    private int valeur_jaime;

    public Jaime() {
    }

    public Jaime(int id_user, int id_actualite, int valeur_jaime) {
        this.id_user = id_user;
        this.id_actualite = id_actualite;
        this.valeur_jaime = valeur_jaime;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_actualite() {
        return id_actualite;
    }

    public void setId_actualite(int id_actualite) {
        this.id_actualite = id_actualite;
    }

   
    public int getValeur_jaime() {
        return valeur_jaime;
    }

    public void setValeur_jaime(int valeur_jaime) {
        this.valeur_jaime = valeur_jaime;
    }
    
}
