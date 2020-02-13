/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entities.Commande;
import Entities.ListeProduit;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface ICommandeService {
    public void ajouterCommande(Commande c) throws SQLException;
    public void ajouterCommande1(Commande c) throws SQLException;
    public boolean SupprimerCommande(Commande c) throws SQLException;
    public boolean ModifierCommande(Commande c) throws SQLException;
    public List<Commande> AfficherCommande() throws SQLException;
    public List<Commande> TrierCommande() throws SQLException;
    public List<Commande> RechercheCommandeParIdMembre(int Id_Membre) throws SQLException;
    public List<Commande> RechercheCommandeParNomMembre(String Nom_Membre) throws SQLException;
    public double CalculerTotalCommande(List<ListeProduit> L);
    public void FacturePdf(int id_membre) throws SQLException,FileNotFoundException,DocumentException,IOException;
    
    
    
}
