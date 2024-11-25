package se.jobsesprit.services;

import se.jobsesprit.entities.Covoiturage;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CovoiturageCRUD implements InterfaceCRUD<Covoiturage> {

    @Override
    public void ajouterEntities(Covoiturage covoiturage) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Covoiturage (id_user_etudiant_id, heureDepart, lieuDepart, lieuArrivee, prix, nombrePlacesDisponible, image, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, covoiturage.getId_user_etudiant_id());
            pst.setString(2, covoiturage.getHeureDepart());
            pst.setString(3, covoiturage.getLieuDepart());
            pst.setString(4, covoiturage.getLieuArrivee());
            pst.setString(5, covoiturage.getPrix());
            pst.setInt(6, covoiturage.getNombrePlacesDisponibles());
            pst.setString(7, covoiturage.getImage());
            pst.setString(8, covoiturage.getUsername());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                covoiturage.setId(generatedKeys.getInt(1));
            }
            System.out.println("Covoiturage ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Covoiturage> listeDesEntities1() {
        ObservableList<Covoiturage> myList = FXCollections.observableArrayList();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, id_user_etudiant_id, heureDepart, lieuDepart, lieuArrivee, prix, nombrePlacesDisponible, image, username FROM Covoiturage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setId(rs.getInt("id"));
                covoiturage.setId_user_etudiant_id(rs.getInt("id_user_etudiant_id"));
                covoiturage.setHeureDepart(rs.getString("heureDepart"));
                covoiturage.setLieuDepart(rs.getString("lieuDepart"));
                covoiturage.setLieuArrivee(rs.getString("lieuArrivee"));
                covoiturage.setPrix(rs.getString("prix"));
                covoiturage.setNombrePlacesDisponibles(rs.getInt("nombrePlacesDisponible"));
                covoiturage.setImage(rs.getString("image"));
                covoiturage.setUsername(rs.getString("username"));
                myList.add(covoiturage);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifierEntities(Covoiturage covoiturage) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Covoiturage SET id_user_etudiant_id = ?, heureDepart = ?, lieuDepart = ?, lieuArrivee = ?, prix = ?, nombrePlacesDisponible = ?, image = ?, username = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, covoiturage.getId_user_etudiant_id());
            pst.setString(2, covoiturage.getHeureDepart());
            pst.setString(3, covoiturage.getLieuDepart());
            pst.setString(4, covoiturage.getLieuArrivee());
            pst.setString(5, covoiturage.getPrix());
            pst.setInt(6, covoiturage.getNombrePlacesDisponibles());
            pst.setString(7, covoiturage.getImage());
            pst.setString(8, covoiturage.getUsername());
            pst.setInt(9, covoiturage.getId());
            pst.executeUpdate();

            System.out.println("Covoiturage modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Covoiturage covoiturage) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Covoiturage WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, covoiturage.getId());
            pst.executeUpdate();
            System.out.println("Covoiturage supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Covoiturage> listeDesEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static boolean updateNombrePlacesDisponibles(int covoiturageId, int newNombrePlacesDisponibles) {
        String updateQuery = "UPDATE Covoiturage SET nombrePlacesDisponible = ? WHERE id = ?";
        
        try {
            Connection connection = MyConnection.getInstance().getCnx();
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setInt(1, newNombrePlacesDisponibles);
            statement.setInt(2, covoiturageId);
           
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Nombre de places disponibles updated successfully.");
                return true;
            } else {
                System.err.println("Failed to update nombrePlacesDisponibles.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error updating nombrePlacesDisponible: " + e.getMessage());
            return false;
        }
    }

    public ObservableList<Covoiturage> getEntitiesWithFilter(String filterColumn, String filterValue) {
        ObservableList<Covoiturage> covoiturages = FXCollections.observableArrayList();
        Connection connection = MyConnection.getInstance().getCnx();

        try {
            String query = "SELECT * FROM Covoiturage WHERE " + filterColumn + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, filterValue);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Covoiturage covoiturage = new Covoiturage(
                    resultSet.getInt("id"),
                    resultSet.getInt("id_user_etudiant_id"),
                    resultSet.getString("heureDepart"),
                    resultSet.getString("lieuDepart"),
                    resultSet.getString("lieuArrivee"),
                    resultSet.getString("prix"),
                    resultSet.getInt("nombrePlacesDisponible"),
                    resultSet.getString("image"),
                    resultSet.getString("username")
                );
                covoiturages.add(covoiturage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return covoiturages;
    }
}
