/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.event;
import java.sql.*;
import Entite.participation;
import Entite.resultat;
import IService.Iparticipation;
import Technique.TrayIconDemo;
import Utilis.ConnexionDB;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Baha Dammak
 */
public class participationService implements Iparticipation{
    
            Connection  myConnex;
       Statement ste;
    
    public participationService() throws ClassNotFoundException {
          try {
              myConnex  = ConnexionDB.
                      getInstance()
                      .getConnection();
           
              ste = myConnex.createStatement();
          } catch (SQLException ex) {
              
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void ajouterparticipation(participation p) {
                    try {
              String req =
                      "INSERT INTO participation"
                      + "(id_membre,id_event,record,ranking) VALUES "
                      + "(?,?,?,?)";
              
                    PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, p.getIduser());
            
             ps.setInt(2, p.getIdevent());
      
             ps.setTime(3, p.getRecord());
             ps.setInt(4,p.getRank());
             
             
           
            
           
            ps.executeUpdate();
         
           
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void modifierparticipation(participation p) {
         try {
            String req = "update participation set record=?,ranking=? where id_participant =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setTime(1, p.getRecord());
            ps.setInt(2, p.getRank());
            ps.setInt(3, p.getIdpart());
        
            ps.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public void supprimerparticipation(int id) {
                  try {
            String req = "delete from participation where id_participant =?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
    }

    @Override
    public ResultSet afficherparticipation() {
         
    
        try {
              String req3 =
                      "select * from participation";
              ResultSet res =   ste.executeQuery(req3);
   
             return res ; 
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
          
           return null;
    }

    @Override
    public ResultSet listeevent(int id) {
        
         ArrayList<event> psr = new ArrayList<>();
                  try {
              String req3;
                      req3 = "select participation.id_participant,evenement.* from participation INNER JOIN evenement ON participation.id_event=evenement.id_event where participation.id_membre = ?";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                      ps.setInt(1, id);
                      ResultSet res =   ps.executeQuery();
              
            return res ;
              
             
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
            return null;
    }

    @Override
    public ArrayList<resultat> listeparticipant(int id) {
          ArrayList<resultat> psr = new ArrayList<>();
                  try {
              String req3;
                      req3 = "select participation.*,membre.* from participation INNER JOIN membre ON participation.id_membre=membre.id_membre where participation.id_event = ? ORDER BY participation.ranking";
                       PreparedStatement ps = myConnex.prepareStatement(req3);
                      ps.setInt(1, id);
                      ResultSet res =   ps.executeQuery();
              
              while (res.next()) {
                                 resultat p = new resultat();
                 p.setId_participant(res.getInt("id_participant"));
                p.setNom(res.getString("nom_membre"));
                p.setPrenom(res.getString("prenom_membre"));
                p.setRecord(res.getTime("record"));
                p.setRanking(res.getInt("ranking"));
               
                psr.add(p);
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
            return psr;
    }

            @Override
    public void FacturePdf(int id) throws SQLException,FileNotFoundException,DocumentException,IOException 
    {
        Document doc = new Document();
        
       
        ste=myConnex.createStatement();
        ResultSet rs=ste.executeQuery("select participation.*,membre.* from participation INNER JOIN membre ON participation.id_membre=membre.id_membre where participation.id_event='"+id+"'ORDER BY ranking");
        PdfWriter.getInstance(doc, new FileOutputStream("d:/pdf/Resultat.pdf"));
        
        doc.open();
        doc.add(new Paragraph("   "));
        doc.add(new Paragraph("  Resultat de la Competition  "));
        doc.add(new Paragraph("   "));
        
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        PdfPCell cell;
        
        cell = new PdfPCell(new Phrase("nom_membre",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("prenom_membre",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase("record",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        
        cell = new PdfPCell(new Phrase("ranking",FontFactory.getFont("Comic Sans MS",12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        
        

        
        
        
        
        
        
        
     while (rs.next()) {                
            
               String nom_membre=rs.getString("nom_membre");
               String prenom_membre=rs.getString("prenom_membre");
               Time record=rs.getTime("record");
                  int ranking=rs.getInt("ranking");
               
            
               
                 
               
               //Conversion to String
              /*
               String nom  = nom.toString();
               String prenom  = prenom.toString();
*/
               DateFormat df = new SimpleDateFormat("hh:mm:ss");
               String rec = df.format(record);
                String rank = Integer.toString(ranking);
         
               
               
               
               
               cell = new PdfPCell(new Phrase(nom_membre,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(prenom_membre,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
               
               cell = new PdfPCell(new Phrase(rec,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
        
        
               cell = new PdfPCell(new Phrase(rank,FontFactory.getFont("Comic Sans MS",12)));
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.GRAY);
               table.addCell(cell);
        
        
   
        
        
               
              
        
                        }
            doc.add(table);
            doc.close();
            Desktop.getDesktop().open(new File ("d:/pdf/Resultat.pdf"));
            }

    @Override
    public void supprimer2participation(int id, int id2) {
                          try {
            String req = "delete from participation where id_event =? AND id_membre=?";
            PreparedStatement ps = myConnex.prepareStatement(req);
            ps.setInt(1, id);
            ps.setInt(2, id2);
            ps.executeUpdate();
           
          } catch (SQLException ex) {
              Logger.getLogger(participationService.class.getName()).log(Level.SEVERE, null, ex);
          }
     
    }
    
}
