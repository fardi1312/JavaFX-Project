/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.services;

import se.jobsesprit.entities.confirmCovoiturage;
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

public class confirmCovoiturageCRUD implements InterfaceCRUD<confirmCovoiturage> {

    @Override
    public void ajouterEntities(confirmCovoiturage confirmCovoiturage) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Confirm_Covoiturage (username_Etud, username_Conducteur, first_Name_Etud, last_Name_Etud, first_Name_Conducteur, last_Name_Conducteur, phone_Etud, phone_Conducteur, email_Etud, email_Conducteur, heure_Depart, lieu_Depart, lieu_Arrivee, prix_totale_places_reserve, nombre_Places_Reserve) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, confirmCovoiturage.getUsernameEtud());
            pst.setString(2, confirmCovoiturage.getUsernameConducteur());
            pst.setString(3, confirmCovoiturage.getFirstNameEtud());
            pst.setString(4, confirmCovoiturage.getLastNameEtud());
            pst.setString(5, confirmCovoiturage.getFirstNameConducteur());
            pst.setString(6, confirmCovoiturage.getLastNameConducteur());
            pst.setInt(7, confirmCovoiturage.getPhoneEtud());
            pst.setInt(8, confirmCovoiturage.getPhoneConducteur());
            pst.setString(9, confirmCovoiturage.getEmailEtud());
            pst.setString(10, confirmCovoiturage.getEmailConducteur());
            pst.setString(11, confirmCovoiturage.getHeureDepart());
            pst.setString(12, confirmCovoiturage.getLieuDepart());
            pst.setString(13, confirmCovoiturage.getLieuArrivee());
            pst.setString(14, confirmCovoiturage.getPrixTotalePlacesReserve());
            pst.setInt(15, confirmCovoiturage.getNombrePlacesReserve());

            pst.executeUpdate();
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                confirmCovoiturage.setId(generatedKeys.getInt(1));
            }
            System.out.println("ConfirmCovoiturage ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<confirmCovoiturage> listeDesEntities1() {
        ObservableList<confirmCovoiturage> myList = FXCollections.observableArrayList();

        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, username_Etud, username_Conducteur, first_Name_Etud, last_Name_Etud, first_Name_Conducteur, last_Name_Conducteur, phone_Etud, phone_Conducteur, email_Etud, email_Conducteur, heure_Depart, lieu_Depart, lieu_Arrivee, prix_totale_places_reserve, nombre_Places_Reserve FROM Confirm_Covoiturage";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                confirmCovoiturage confirmCovoiturage = new confirmCovoiturage();
                confirmCovoiturage.setId(rs.getInt("id"));
                confirmCovoiturage.setUsernameEtud(rs.getString("username_Etud"));
                confirmCovoiturage.setUsernameConducteur(rs.getString("username_Conducteur"));
                confirmCovoiturage.setFirstNameEtud(rs.getString("first_Name_Etud"));
                confirmCovoiturage.setLastNameEtud(rs.getString("last_Name_Etud"));
                confirmCovoiturage.setFirstNameConducteur(rs.getString("first_Name_Conducteur"));
                confirmCovoiturage.setLastNameConducteur(rs.getString("last_Name_Conducteur"));
                confirmCovoiturage.setPhoneEtud(rs.getInt("phone_Etud"));
                confirmCovoiturage.setPhoneConducteur(rs.getInt("phone_Conducteur"));
                confirmCovoiturage.setEmailEtud(rs.getString("email_Etud"));
                confirmCovoiturage.setEmailConducteur(rs.getString("email_Conducteur"));
                confirmCovoiturage.setHeureDepart(rs.getString("heure_Depart"));
                confirmCovoiturage.setLieuDepart(rs.getString("lieu_Depart"));
                confirmCovoiturage.setLieuArrivee(rs.getString("lieu_Arrivee"));
                confirmCovoiturage.setPrixTotalePlacesReserve(rs.getString("prix_Totale_Places_Reserve"));
                confirmCovoiturage.setNombrePlacesReserve(rs.getInt("nombre_Places_Reserve"));
                myList.add(confirmCovoiturage);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifierEntities(confirmCovoiturage confirmCovoiturage) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Confirm_Covoiturage SET username_Etud = ?, username_Conducteur = ?, first_Name_Etud = ?, last_Name_Etud = ?, first_Name_Conducteur = ?, last_Name_Conducteur = ?, phone_Etud = ?, phone_Conducteur = ?, email_Etud = ?, email_Conducteur = ?, heure_Depart = ?, lieu_Depart = ?, lieu_Arrivee = ?, prix_Totale_Places_Reserve = ?, nombre_Places_Reserve = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, confirmCovoiturage.getUsernameEtud());
            pst.setString(2, confirmCovoiturage.getUsernameConducteur());
            pst.setString(3, confirmCovoiturage.getFirstNameEtud());
            pst.setString(4, confirmCovoiturage.getLastNameEtud());
            pst.setString(5, confirmCovoiturage.getFirstNameConducteur());
            pst.setString(6, confirmCovoiturage.getLastNameConducteur());
            pst.setInt(7, confirmCovoiturage.getPhoneEtud());
            pst.setInt(8, confirmCovoiturage.getPhoneConducteur());
            pst.setString(9, confirmCovoiturage.getEmailEtud());
            pst.setString(10, confirmCovoiturage.getEmailConducteur());
            pst.setString(11, confirmCovoiturage.getHeureDepart());
            pst.setString(12, confirmCovoiturage.getLieuDepart());
            pst.setString(13, confirmCovoiturage.getLieuArrivee());
            pst.setString(14, confirmCovoiturage.getPrixTotalePlacesReserve());
            pst.setInt(15, confirmCovoiturage.getNombrePlacesReserve());
            pst.setInt(16, confirmCovoiturage.getId());

            pst.executeUpdate();

            System.out.println("ConfirmCovoiturage modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(confirmCovoiturage confirmCovoiturage) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Confirm_Covoiturage WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, confirmCovoiturage.getId());
            pst.executeUpdate();
            System.out.println("ConfirmCovoiturage supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<confirmCovoiturage> listeDesEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private String fetchUserDataFromTable(String tableName, String columnName, String username) {
    String userData = null;
    Connection connection = MyConnection.getInstance().getCnx();

    try {
        String query = "SELECT " + columnName + " FROM " + tableName + " WHERE username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            userData = resultSet.getString(columnName);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userData;
}

    
    
}
