/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ONS
 */
public class Commentaire {
  private int id_Commentaire;
    private int id_actualite;
    private int id_user;
    private String contenu_Commentaire;
    private String temps_Commentaire;

    public Commentaire() {
    }

    public Commentaire(int id_Commentaire, int id_actualite, int id_user, String contenu_Commentaire, String temps_Commentaire) {
        this.id_Commentaire = id_Commentaire;
        this.id_actualite = id_actualite;
        this.id_user = id_user;
        this.contenu_Commentaire = contenu_Commentaire;
        this.temps_Commentaire = temps_Commentaire;
    }

    public Commentaire(int id_Commentaire, int id_actualite, String contenu_Commentaire) {
        this.id_Commentaire = id_Commentaire;
        this.id_actualite = id_actualite;
        this.contenu_Commentaire = contenu_Commentaire;
    }

    public int getId_Commentaire() {
        return id_Commentaire;
    }

    public void setId_Commentaire(int id_Commentaire) {
        this.id_Commentaire = id_Commentaire;
    }

    public int getId_actualite() {
        return id_actualite;
    }

    public void setId_actualite(int id_actualite) {
        this.id_actualite = id_actualite;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContenu_Commentaire() {
        return contenu_Commentaire;
    }

    public void setContenu_Commentaire(String contenu_Commentaire) {
        this.contenu_Commentaire = contenu_Commentaire;
    }

    public String getTemps_Commentaire() {
        return temps_Commentaire;
    }

    public void setTemps_Commentaire(String temps_Commentaire) {
        this.temps_Commentaire = temps_Commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id_Commentaire=" + id_Commentaire + ", id_actualite=" + id_actualite + ", id_user=" + id_user + ", contenu_Commentaire=" + contenu_Commentaire + ", temps_Commentaire=" + temps_Commentaire + '}';
    }
    

}
