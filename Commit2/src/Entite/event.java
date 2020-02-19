/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

import java.sql.Date;

/**
 *
 * @author Baha Dammak
 */
public class event {
    private int id_event ;

    public event() {
      
    }

    @Override
    public String toString() {
        return "evenement {" + "nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", dateevent=" + dateevent + ", type=" + type + "}\n";
    }
    private String nom ;
    private String lieu ;
    private String description ;
    private Date dateevent ;
    private String type ;


    public event(int id_event, String nom, String lieu, String description, Date dateevent, String type) {
        this.id_event = id_event;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateevent = dateevent;
        this.type = type;
    }

    public event(String nom, String lieu, String description, Date dateevent, String type) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.dateevent = dateevent;
        this.type = type;
    }

    public Date getDateevent() {
        return dateevent;
    }



    public int getId_event() {
        return id_event;
    }

    public String getNom() {
        return nom;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDescription() {
        return description;
    }



    public String getType() {
        return type;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    
    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_event;
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
        final event other = (event) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        return true;
    }
    
    
    
}
