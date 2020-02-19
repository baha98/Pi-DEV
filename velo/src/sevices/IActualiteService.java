/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevices;

import entities.Actualite;
import entities.Categorie;

import java.util.List;

/**
 *
 * @author ONS
 */
public interface IActualiteService {
    public void  ajouterActualite(Actualite a);
    public void modifierActualite(Actualite a);
    public void supprimerActualite(int id_actualite);
    public List<Actualite> afficherActualite();
    public Actualite chercherActualite(int id_actualite);
    public boolean rechercherparNom(String nom);
    public void compter(Actualite a);
    public void compterjaime(Actualite a);
    public void compterjaimepas(Actualite a);
    public List<Actualite> afficherActualiteParCategorie(Categorie categorie);

}
