/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entites.Accessoire;
import java.util.List;

/**
 *
 * @author zoula
 */
public interface iAccessoireService {
     public boolean ajouterAccessoire(Accessoire A);
    public boolean modifierAccessoire(Accessoire A);
    public  boolean supprimerAccessoire(Accessoire A);
    public  List<Accessoire> afficherAccessoire();
    public  List<Accessoire> triParPrix();
    public  List<Accessoire> rechercheParMarque(String marque);
    
}
