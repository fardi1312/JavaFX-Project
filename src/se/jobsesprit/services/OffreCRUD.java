package se.jobsesprit.services;

import se.jobsesprit.entities.Offre;
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

public class OffreCRUD implements InterfaceCRUD<Offre> {

    
    
    
    @Override
    public void ajouterEntities(Offre offre) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO Offre2 (titre, description, typeStage, secteurs, fonction, dateInscription, images, entrepriseId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, offre.getTitre());
            pst.setString(2, offre.getDescription());
            pst.setString(3, offre.getTypeStage());
            pst.setString(4, offre.getSecteurs());
            pst.setString(5, offre.getFonction());
            pst.setString(6, offre.getDateInscription());
            pst.setString(7, offre.getImages());
            pst.setInt(8, offre.getEntrepriseId());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                offre.setId(generatedKeys.getInt(1));
            }
            System.out.println("Offre ajoutée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Offre> listeDesEntities1() {
        ObservableList<Offre> myList = FXCollections.observableArrayList();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, titre, description, typeStage, secteurs, fonction, dateInscription, images, entrepriseId FROM Offre2";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Offre offre = new Offre();
                offre.setId(rs.getInt("id"));
                offre.setTitre(rs.getString("titre"));
                offre.setDescription(rs.getString("description"));
                offre.setTypeStage(rs.getString("typeStage"));
                offre.setSecteurs(rs.getString("secteurs"));
                offre.setFonction(rs.getString("fonction"));
                offre.setDateInscription(rs.getString("dateInscription"));
                offre.setImages(rs.getString("images"));
                offre.setEntrepriseId(rs.getInt("entrepriseId"));
                myList.add(offre);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void modifierEntities(Offre offre) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Offre2 SET titre = ?, description = ?, typeStage = ?, secteurs = ?, fonction = ?, dateInscription = ?, images = ?, entrepriseId = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, offre.getTitre());
            pst.setString(2, offre.getDescription());
            pst.setString(3, offre.getTypeStage());
            pst.setString(4, offre.getSecteurs());
            pst.setString(5, offre.getFonction());
            pst.setString(6, offre.getDateInscription());
            pst.setString(7, offre.getImages());
            pst.setInt(8, offre.getEntrepriseId());
            pst.setInt(9, offre.getId());
            pst.executeUpdate();

            System.out.println("Offre modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Offre offre) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Offre2 WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, offre.getId());
            pst.executeUpdate();
            System.out.println("Offre supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Offre> listeDesEntities() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
public static ObservableList<Offre> getOffresByEntrepriseConnectee(int idEntreprise) {
     ObservableList<Offre> myList = FXCollections.observableArrayList();
        Connection connection = MyConnection.getInstance().getCnx();
    String sql = "SELECT * FROM Offre2 WHERE entrepriseId = ?";

    try {
      
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEntreprise);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Offre offre = new Offre();
            offre.setId(resultSet.getInt("id"));
            offre.setTitre(resultSet.getString("titre"));
            offre.setDescription(resultSet.getString("description"));
            offre.setTypeStage(resultSet.getString("typeStage"));
            offre.setSecteurs(resultSet.getString("secteurs"));
            offre.setFonction(resultSet.getString("fonction"));
            offre.setDateInscription(resultSet.getString("dateInscription"));
            offre.setImages(resultSet.getString("images"));
            offre.setEntrepriseId(resultSet.getInt("entrepriseId"));

            myList.add(offre);
        }

        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des offres : " + e.getMessage());
    }

    return myList;
}
     public  static ObservableList<Offre> listedesoffreuser() {
    
     
     ObservableList<Offre> myList = FXCollections.observableArrayList();
     
    try {
  String requete = "SELECT o.id, o.titre, o.description, o.typeStage, o.secteurs, o.fonction, o.dateInscription, o.images, o.entrepriseId , u.nomEntreprise " +
                 "FROM Offre2 o " +
                 "JOIN UserEntreprise u ON o.entrepriseId = u.id";



        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        while (rs.next()) {
            Offre p = new Offre();
            p.setId(rs.getInt("id"));
            p.setTitre(rs.getString("titre"));
            p.setDescription(rs.getString("description"));
            p.setTypeStage(rs.getString("typeStage"));
            p.setSecteurs(rs.getString("secteurs"));
            p.setFonction(rs.getString("fonction"));
            p.setDateInscription(rs.getString("dateInscription"));
            p.setImages(rs.getString("images"));


String nomEntreprise = rs.getString("nomEntreprise").trim();


    p.setNomEntreprise(nomEntreprise);
p.setEntrepriseId(rs.getInt("entrepriseId"));
       

            myList.add(p);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return myList;

 }
     public static  int getLikesCount(int offreId) {
    int likesCount = 0;
    String query = "SELECT COUNT(*) FROM Likes WHERE offreId = ? AND type = 'like'";
    
    try (Connection connection = MyConnection.getInstance().getCnx();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, offreId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            likesCount = resultSet.getInt(1);
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération du nombre de likes : " + e.getMessage());
    }
    
    return likesCount;
}
  
    public boolean etudiantAdejaLike(int offreId, int  username) {
       
        return false; 
    }
   

    public void ajouterLike(int offreId, int username) {
    if (!etudiantAdejaLike(offreId, username)) {
        String insertLikeQuery = "INSERT INTO Likes (offreId, username, type) VALUES (?, ?, 'like')";
        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement statement = connection.prepareStatement(insertLikeQuery)) {
            statement.setInt(1, offreId);
            statement.setInt(2, username);
            statement.executeUpdate();
            System.out.println("Like ajouté avec succès pour l'offre avec l'ID : " + offreId);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du dislike : " + e.getMessage());
        }
    } else {
        System.out.println("L'étudiant a déjà ajouté un dislike pour cette offre.");
    }}






}
   
    


