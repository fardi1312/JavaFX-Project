package se.jobsesprit.services;

import se.jobsesprit.entities.Portfolio;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PortfolioCRUD implements InterfaceCRUD<Portfolio> {
         private Connection connection; // Assume you have a valid database connection

    public PortfolioCRUD() {
          connection = MyConnection.getInstance().getCnx();
    }
    

    @Override
    public void ajouterEntities(Portfolio portfolio) {
         String requete = "INSERT INTO Portfolio (titre, description, image, secteur, dateP) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pst = connection.prepareStatement(requete)) {
                pst.setString(1, portfolio.getTitre());
                pst.setString(2, portfolio.getDescription());
                pst.setString(3, portfolio.getImage());
                pst.setString(4, portfolio.getSecteur());
                pst.setString(5, portfolio.getDateP());
                
                 pst.executeUpdate();
            System.out.println("Portfolio ajoutée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du portfolio : " + e.getMessage());
        }
    }


    private boolean PortfolioDouble(Portfolio newPortfolio) {
    Connection connection = MyConnection.getInstance().getCnx();
    String query = "SELECT COUNT(*) FROM Portfolio WHERE titre = ? OR description = ? OR image = ? OR secteur = ?";
    
    try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, newPortfolio.getTitre());
            pst.setString(2, newPortfolio.getDescription());
            pst.setString(3, newPortfolio.getImage());
            pst.setString(4, newPortfolio.getSecteur());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0; // Un enregistrement avec des informations similaires existe déjà
                }
            }
        } catch (SQLException ex) {
            System.out.println("Double Portfolio!");
        }

        return false; 
}
    
    
    
    @Override
    public List<Portfolio> listeDesEntities() {
        List<Portfolio> portfolios = new ArrayList<>();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "SELECT id, titre, description, image, secteur, dateP FROM Portfolio";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Portfolio portfolio = new Portfolio();
                portfolio.setId(rs.getInt("id"));
                portfolio.setTitre(rs.getString("titre"));
                portfolio.setDescription(rs.getString("description"));
                portfolio.setImage(rs.getString("image"));
                portfolio.setSecteur(rs.getString("secteur"));
                portfolio.setDateP(rs.getString("date"));

                portfolios.add(portfolio);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return portfolios;
    }

    @Override
    public void modifierEntities(Portfolio portfolio) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE Portfolio SET titre = ?, description = ?, image = ?, secteur = ?, dateP = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, portfolio.getTitre());
            pst.setString(2, portfolio.getDescription());
            pst.setString(3, portfolio.getImage());
            pst.setString(4, portfolio.getSecteur());
            pst.setString(5,portfolio.getDateP());
            pst.setInt(6, portfolio.getId());

            pst.executeUpdate();

            System.out.println("Portfolio modifié avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(Portfolio portfolio) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM Portfolio WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, portfolio.getId());

            pst.executeUpdate();

            System.out.println("Portfolio supprimé avec succès !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
        public static ObservableList<Portfolio> getAllPortfolios() {
        ObservableList<Portfolio> portfolios = FXCollections.observableArrayList();
        String rq = "SELECT * FROM portfolio";

        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement statement = connection.prepareStatement(rq)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Portfolio port = new Portfolio();
                port.setId(resultSet.getInt("id"));
                port.setTitre(resultSet.getString("titre"));
                port.setDescription(resultSet.getString("description"));
                port.setSecteur(resultSet.getString("Secteur"));
                port.setDateP(resultSet.getString("dateP"));
                port.setImage(resultSet.getString("image"));
                portfolios.add(port);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des portfolios : " + e.getMessage());
        }

        return portfolios;
    }
        
        
         public  static ObservableList<Portfolio> getPortfolios(int idport) {
        ObservableList<Portfolio> portfolios = FXCollections.observableArrayList();

        String rq = "SELECT * FROM Portfolio WHERE id = ?";
        try (Connection connection = MyConnection.getInstance().getCnx();
             PreparedStatement statement = connection.prepareStatement(rq)) {
            statement.setInt(1, idport);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Portfolio portfolio = new Portfolio();
                    portfolio.setId(resultSet.getInt("id"));
                    portfolio.setTitre(resultSet.getString("titre"));
                    portfolio.setDescription(resultSet.getString("description"));
                    portfolio.setSecteur(resultSet.getString("secteur"));
                    portfolio.setDateP(resultSet.getString("date"));
                    portfolio.setImage(resultSet.getString("images"));


                    portfolios.add(portfolio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des offres : " + e.getMessage());
        }

        return portfolios;
    }
 

}
