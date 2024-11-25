package se.jobsesprit.services;

import se.jobsesprit.services.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import se.jobsesprit.entities.Profil;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;

public class ProfilCRUD implements InterfaceCRUD<Profil> {

    @Override
    public void ajouterEntities(Profil profil) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Profil (image, username, bio, description, formation, licences, competence) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, profil.getImage());
            pst.setString(2, profil.getUsername());
            pst.setString(3, profil.getBio());
            pst.setString(4, profil.getDescription());
            pst.setString(5, profil.getFormation());
            pst.setString(6, profil.getLicences());
            pst.setString(7, profil.getCompetence());

            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                profil.setId(generatedKeys.getInt(1));
            }
            
            System.out.println("Profil ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Profil> listeDesEntities() {
        List<Profil> profils = new ArrayList<>();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, image, username, bio, description, formation, licences, competence FROM Profil";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Profil profil = new Profil();
                profil.setId(rs.getInt("id"));
                profil.setImage(rs.getString("image"));
                profil.setUsername(rs.getString("username"));
                profil.setBio(rs.getString("bio"));
                profil.setDescription(rs.getString("description"));
                profil.setFormation(rs.getString("formation"));
                profil.setLicences(rs.getString("licences"));
                profil.setCompetence(rs.getString("competence"));

                profils.add(profil);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return profils;
    }

    @Override
    public void modifierEntities(Profil profil) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Profil SET image = ?, username = ?, bio = ?, description = ?, formation = ?, licences = ?, competence = ? WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, profil.getImage());
            pst.setString(2, profil.getUsername());
            pst.setString(3, profil.getBio());
            pst.setString(4, profil.getDescription());
            pst.setString(5, profil.getFormation());
            pst.setString(6, profil.getLicences());
            pst.setString(7, profil.getCompetence());
            pst.setInt(8, profil.getId());

            pst.executeUpdate();
            
            System.out.println("Profil modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Profil profil) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Profil WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, profil.getId());

            pst.executeUpdate();
            
            System.out.println("Profil supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
