/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Velo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author zoula
 */
public interface iVeloService {
      public void  ajouterVelo(Velo V);
    public void modifierVelo(Velo V);
    public void supprimerVelo(Velo V);
    public List<Velo> afficherVelo();
    public  List<Velo> triParPrix();
    public  List<Velo> rechercheParMarque(String marque);
    public boolean UpdateStock(int id,int quantite) throws SQLException;;
}
