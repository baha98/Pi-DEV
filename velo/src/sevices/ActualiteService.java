/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sevices;

import entities.Actualite;
import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilis.ConnexionDB;

/**
 *
 * @author ONS
 */
public class ActualiteService implements IActualiteService {

    Connection myConnex;
    Statement ste;

    public ActualiteService() {
        try {
            myConnex = ConnexionDB.
                    getInstance()
                    .getConnection();
            ste = myConnex.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterActualite(Actualite a) {
        try {
            String req
                    = "INSERT INTO actualite"
                    + "(titre ,description,image,date_actualite ,nbre_vues,nbre_jaime ,categorie) VALUES "
                    + "(?,?,?,?,?,?,?)";

            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, a.getTitre());
            ps.setString(2, a.getDescription());
            ps.setString(3, a.getImage());
            java.util.Date date1 = new java.util.Date();
            String Date_actualite = new SimpleDateFormat("yyyy-MM-dd").format(date1);

            ps.setString(4, Date_actualite);
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.setString(7, a.getCategorie().toString());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierActualite(Actualite a) {
        try {
            String req = "update actualite set titre=?,description=?,image=?,categorie=? where id_actualite =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setString(1, a.getTitre());
            ps.setString(2, a.getDescription());
            ps.setString(3, a.getImage());
            ps.setString(4, a.getCategorie().toString());
            ps.setInt(5, a.getId_actualite());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimerActualite(int id_actualite) {
        try {
            String req = "delete from actualite where id_actualite =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id_actualite);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Actualite> afficherActualite() {
        List<Actualite> list = new ArrayList<>();
        try {
            String req3
                    = "select id_actualite,titre ,description,image,date_actualite  ,categorie from actualite";
            ResultSet res = ste.executeQuery(req3);

            while (res.next()) {

                Actualite a = new Actualite(res.getInt("id_actualite"), res.getString("titre"), res.getString("description"),
                        res.getString("image"), res.getString("date_actualite"), Categorie.valueOf(res.getString("categorie")));

                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        return list;
    }

    @Override
    public Actualite chercherActualite(int id_actualite) {
        List<Actualite> List = new ArrayList<>();
        try {
            String req3
                    = "select * from actualite where id_actualite='" + id_actualite + "'";
            ResultSet res = ste.executeQuery(req3);

            while (res.next()) {
                System.out.println("l'actualité est "
                        + res.getString("titre") + " "
                        + res.getString("description") + " "
                        + res.getString("image") + " "
                        + res.getString("date_actualite"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    @Override
    public boolean rechercherparNom(String nom) {
        boolean test = false;
        String req = "SELECT * from actualite where titre='" + nom + "'";

        try {

            ResultSet stp = ste.executeQuery(req);
            stp.last();
            if (stp.getRow() != 0) {
                test = true;
                System.out.println(test + "sujet trouver");
            } else {
                test = false;
                System.out.println(test + "sujet non trouver");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
    }

    @Override
    public void compter(Actualite a) {
        try {
            PreparedStatement ste = myConnex.prepareStatement("update actualite set nbre_vues=nbre_vues+1 WHERE id_actualite=" + a.getId_actualite());

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
     public void compterjaime(Actualite a) {
        try {
            PreparedStatement ste = myConnex.prepareStatement("update actualite set nbre_jaime=nbre_jaime+1 WHERE id_actualite=" + a.getId_actualite());
            /*pt.setInt(1,b.getNbrVue());
            pt.setInt(2,b.getId());*/

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      @Override
     public void compterjaimepas(Actualite a) {
        try {
            PreparedStatement ste = myConnex.prepareStatement("update actualite set nbre_jaime=nbre_jaime-1 WHERE id_actualite=" + a.getId_actualite());
            /*pt.setInt(1,b.getNbrVue());
            pt.setInt(2,b.getId());*/

            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     @Override
    public List<Actualite> afficherActualiteParCategorie(Categorie categorie) {
        List<Actualite> list = new ArrayList<>();
        try {
            String req3
                    = "select id_actualite,titre,description,image,date_actualite,categorie from actualite WHERE categorie='"+ categorie.name() + "'";
            ResultSet res = ste.executeQuery(req3);

            while (res.next()) {

                Actualite a = new Actualite(res.getInt("id_actualite"), res.getString("titre"), res.getString("description"),
                        res.getString("image"), res.getString("date_actualite"), Categorie.valueOf(res.getString("categorie")));

                list.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list);
        return list;
    }
    
     public List<Actualite> TrierParNbreVue() throws SQLException {
        List<Actualite> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from actualite ORDER BY nbre_vues DESC");
        Actualite com = null;
        while (res.next()) {
            com = new Actualite(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getInt(6),res.getInt(7), Categorie.valueOf(res.getString(8)));
            list.add(com);
        }
        return list;
    }
     
     public List<Actualite> TrierParDateCreation() throws SQLException {
        List<Actualite> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from actualite ORDER BY date_actualite DESC");
        Actualite com = null;
        while (res.next()) {
            com = new Actualite(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5),res.getInt(6),res.getInt(7),Categorie.valueOf(res.getString(8)));
            list.add(com);
        }
        return list;
    }
     
     public int FindNombreJaimeById_actualite(int id_actualite) throws SQLException{
    String req = "SELECT * from actualite where id_actualite='" + id_actualite + "'";
    ResultSet res = ste.executeQuery(req);
        
          int nbre = 0;
        while (res.next()) {
          
           nbre = res.getInt(7);
        }
        return nbre;
}
}
