/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entite.participation;
import Entite.event;
import Entite.resultat;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Baha Dammak
 */
public interface Iparticipation {
    public void  ajouterparticipation(participation p);
    public void modifierparticipation(participation p);
    public void supprimerparticipation(int id);
    public ResultSet afficherparticipation();
    public ResultSet listeevent(int id);
     public ArrayList<resultat> listeparticipant(int id);
     public void FacturePdf(int id) throws SQLException,FileNotFoundException,DocumentException,IOException ;
     public void supprimer2participation(int id,int id2);
    
}
