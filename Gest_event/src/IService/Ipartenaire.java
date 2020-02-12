/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entite.partenaire;
import java.util.List;

/**
 *
 * @author Baha Dammak
 */
public interface Ipartenaire {
            public void  ajouterpartenaire(partenaire p);
    public void modifierpartenaire(partenaire p);
    public void supprimerpartenaire(int id_partenaire);
    public List<partenaire> afficherpartenaire();
}
