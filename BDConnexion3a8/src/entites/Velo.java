/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author zoula
 */
public class Velo {
    private int Id_Velo;
    private String Libelle;
    private String Marque ;
    private double Prix;
    private int Qte;
    private String Couleur;
    private String Image;
    private String Description;
    private String Etat;
    private int Id_Membre;
    

    public Velo(int Id_Velo, String Libelle, String Marque, double Prix, int Qte, String Couleur, String Image, String Description, String Etat, int Id_Membre) {
        this.Id_Velo = Id_Velo;
        this.Libelle = Libelle;
        this.Marque = Marque;
        this.Prix = Prix;
        this.Qte = Qte;
        this.Couleur = Couleur;
        this.Image = Image;
        this.Description = Description;
        this.Etat = Etat;
        this.Id_Membre = Id_Membre;
    }

    public Velo(String Libelle, String Marque, double Prix, int Qte, String Couleur, String Image, String Description, String Etat, int Id_Membre) {
        this.Libelle = Libelle;
        this.Marque = Marque;
        this.Prix = Prix;
        this.Qte = Qte;
        this.Couleur = Couleur;
        this.Image = Image;
        this.Description = Description;
        this.Etat = Etat;
        this.Id_Membre = Id_Membre;
    }

    

   
    

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.Id_Velo;
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
        final Velo other = (Velo) obj;
        if (this.Id_Velo != other.Id_Velo) {
            return false;
        }
        return true;
    }

 
  

  
    @Override
    public String toString() {
        return "Velo{" + "Id_Velo=" + Id_Velo + ", Libelle=" + Libelle + ", Marque=" + Marque + ", Prix=" + Prix + ", Qte=" + Qte + ", Couleur=" + Couleur + '}';
    }

    public int getId_Velo() {
        return Id_Velo;
    }

    public String getLibelle() {
        return Libelle;
    }

    public String getMarque() {
        return Marque;
    }

    public double getPrix() {
        return Prix;
    }

    public int getQte() {
        return Qte;
    }

    public String getImage() {
        return Image;
    }

    public String getDescription() {
        return Description;
    }

    public String getEtat() {
        return Etat;
    }

    public int getId_Membre() {
        return Id_Membre;
    }

    
    public String getCouleur() {
        return Couleur;
    }

    public void setId_Velo(int Id_Velo) {
        this.Id_Velo = Id_Velo;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public void setMarque(String Marque) {
        this.Marque = Marque;
    }

    public void setPrix(float Prix) {
        this.Prix = Prix;
    }

    public void setQte(int Qte) {
        this.Qte = Qte;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public void setId_Membre(int Id_Membre) {
        this.Id_Membre = Id_Membre;
    }

  

    public void setCouleur(String Couleur) {
        this.Couleur = Couleur;
    }
    
    
}
