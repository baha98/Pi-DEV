/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.util.List;
import Entite.event;
import java.sql.*;
import java.util.ArrayList;


/**
 *
 * @author Baha Dammak
 */
public interface IEvents {
        public void  ajouterevent(event p);
    public void modifierevent(event p);
    public void supprimerevent(int id_event);
    public ResultSet afficherevent();
     public ArrayList<event> afficherevent1();
    public event findById(Integer id) ;
     public List<event> findByNom(String nom) ;
    
}
