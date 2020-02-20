/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entite.AffichageEntretien;
import Entite.Entretien;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author itsme
 */
public interface IEntretien {
    public void  ajouterEntretien(Entretien p);
    public void modifierEntretien(Entretien p);
    public void supprimerEntretien(int id);
    public ResultSet afficherEntretien();
    public int recherche(AffichageEntretien Ae);
    
}
