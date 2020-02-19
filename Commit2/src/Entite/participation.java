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
public class participation {
    private int id_participant ;
    private int iduser;
   
    private int id_event;
   
    private Time record ;
    private int ranking ;

    public participation() {
    }



    public participation(int id_participant, int iduser, int id_event, Time record, int ranking) {
        this.id_participant = id_participant;
        this.iduser = iduser;
     
        this.id_event = id_event;
       
        this.record = record;
        this.ranking = ranking;
    }

    public participation(int iduser, int id_event, Time record, int ranking) {
        this.iduser = iduser;
     
        this.id_event = id_event;
      
        this.record = record;
        this.ranking = ranking;
    }



    public participation(int id_participant, Time record, int ranking) {
        this.id_participant = id_participant;
        this.record = record;
        this.ranking = ranking;
    }

    public int getIdpart() {
        return id_participant;
    }

    public int getIduser() {
        return iduser;
    }



    public int getIdevent() {
        return id_event;
    }





    public Time getRecord() {
        return record;
    }

    public int getRank() {
        return ranking;
    }

    public void setIdpart(int id_participant) {
        this.id_participant = id_participant;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }



    public void setIdevent(int id_event) {
        this.id_event = id_event;
    }



    public void setRecord(Time record) {
        this.record = record;
    }

    public void setRank(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id_participant;
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
        final participation other = (participation) obj;
        if (this.id_participant != other.id_participant) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "participation{" + "iduser=" + iduser + ", id_event=" + id_event + ", record=" + record + ", ranking=" + ranking + '}';
    }

    
}
