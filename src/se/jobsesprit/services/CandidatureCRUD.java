package se.jobsesprit.services;

import se.jobsesprit.entities.Candidature;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.jobsesprit.entities.UserEntreprise;

public class CandidatureCRUD implements InterfaceCRUD<Candidature> {

    @Override
    public void ajouterEntities(Candidature candidature) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Candidature (username, cv, idEtudiant, idOffre) VALUES (?, ?,?,?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, candidature.getUsername());
            pst.setString(2, candidature.getCv());
            pst.setInt(3, candidature.getIdEtudiant());
            pst.setInt(4, candidature.getIdOffre());

            

            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                candidature.setId(generatedKeys.getInt(1));
            }
            
            System.out.println("Candidature ajoutée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Candidature> listeDesEntities() {
        List<Candidature> candidatures = new ArrayList<>();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, username, cv, idEtudiant, idOffre FROM Candidature";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Candidature candidature = new Candidature();
                candidature.setId(rs.getInt("id"));
                candidature.setIdEtudiant(rs.getInt("idEtudiant"));
                candidature.setIdOffre(rs.getInt("idOffre"));
                candidature.setUsername(rs.getString("username"));
                candidature.setCv(rs.getString("Cv"));

                candidatures.add(candidature);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return candidatures;
    }

    @Override
    public void modifierEntities(Candidature candidature) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Candidature SET username = ?, cv = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, candidature.getUsername());
            pst.setString(2, candidature.getCv());
            pst.setInt(3, candidature.getId());

            pst.executeUpdate();
            
            System.out.println("Candidature modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Candidature candidature) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Candidature WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, candidature.getId());

            pst.executeUpdate();
            
            System.out.println("Candidature supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
          public  static ObservableList<Candidature> getCandidatures() {
        ObservableList<Candidature> listaa = FXCollections.observableArrayList();
  
    try {
  
       String rq = "SELECT o.titre, u.nomEntreprise, c.cv , c.username"
                   +" FROM Candidature c"
                   +" JOIN Offre o ON c.idOffre = o.id"
                    +" JOIN UserEntreprise u ON o.entrepriseId = u.id"
                ;


        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery( rq);
        while (rs.next()) {
                    Candidature candi = new Candidature();
                    candi.setCv(rs.getString("Cv"));
                    candi.setUsername(rs.getString("Username"));
                    String nomEntreprise = rs.getString("nomEntreprise").trim();
                    
                       candi.setNomEntreprise(nomEntreprise);
                    String titre =rs.getString("titre").trim();
                    candi.setTitre(titre);
       

            listaa.add(candi);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return listaa;
 
    }
 
    
    
}
