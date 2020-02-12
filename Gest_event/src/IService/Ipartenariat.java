/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entite.partenariat;
import Entite.partnership;
import java.util.List;

/**
 *
 * @author Baha Dammak
 */
public interface Ipartenariat {
            public void  ajouterpartenariat(partenariat p);
    public void modifierpartenariat(partenariat p);
    public void supprimerpartenariat(int id_partenariat);
    public List<partenariat> afficherpartenariat();
   public List<partnership> listepartenaire(int id);
    
}
