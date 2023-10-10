package se.jobsesprit.services;

import se.jobsesprit.entities.Offreurs; // Changement du nom de la classe importée
import se.jobsesprit.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import se.jobsesprit.interfaces.InterfaceOffreur;

public class OffreursCRUD implements InterfaceOffreur<Offreurs> {

    @Override
    public void ajouterOffreur(Offreurs t) { 
        try {
            String requete = "INSERT INTO personne(nom, prenom, date_inscription, image) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.setString(3, t.getDateInscription());

            // Assurez-vous que l'attribut image de la classe Offreurs est de type byte[]
            pst.setBytes(4, t.getImage());

            pst.executeUpdate();
            System.out.println("Offreur ajouté avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Offreurs> listeDesOffreurs() {
        List<Offreurs> myList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM personne";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Offreurs p = new Offreurs();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setDateInscription(rs.getString("date_inscription"));

                // Récupérez les données binaires de l'image depuis la colonne image
                byte[] imageBytes = rs.getBytes("image");
                p.setImage(imageBytes);

                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    public void modifierOffreur(Offreurs t) {
    try {
        String requete = "UPDATE personne SET nom = ?, prenom = ?, date_inscription = ?, image = ? WHERE id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, t.getNom());
        pst.setString(2, t.getPrenom());
        pst.setString(3, t.getDateInscription());
        pst.setBytes(4, t.getImage());
        pst.setInt(5, t.getId());
        pst.executeUpdate();
        System.out.println("Offreur modifié avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

 public Offreurs rechercherOffreur(int id) {
    Offreurs offreur = null;
    try {
        String requete = "SELECT * FROM personne WHERE id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            offreur = new Offreurs();
            offreur.setId(rs.getInt(1));
            offreur.setNom(rs.getString("nom"));
            offreur.setPrenom(rs.getString("prenom"));
            offreur.setDateInscription(rs.getString("date_inscription"));
            offreur.setImage(rs.getBytes("image"));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return offreur;
}

 
 public void supprimerOffreur(int id) {
    try {
        String requete = "DELETE FROM personne WHERE id = ?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        System.out.println("Offreur supprimé avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
} 
}

