/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entite.Velo;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author itsme
 */
public interface IVelo {
    public void  ajouterVelo(Velo v);
    public void modifierVelo(Velo v);
    public void supprimerVelo(int id_velo);
    public ArrayList<Velo> afficherVelo();
}
