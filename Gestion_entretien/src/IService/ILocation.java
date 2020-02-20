/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entite.AffichageLocation;
import java.util.List;
import Entite.Location;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author itsme
 */
public interface ILocation {
        public void  ajouterlocation(Location p);
    public void modifierlocation(Location p);
    public void supprimerlocation(int id_location);
    public ArrayList<AffichageLocation> afficherlocation();
    public int recherche(AffichageLocation Al);
    
}
