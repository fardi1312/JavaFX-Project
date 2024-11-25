package se.jobsesprit.gui;

import java.io.IOException;
import se.jobsesprit.services.CovoiturageCRUD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import se.jobsesprit.entities.Covoiturage;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import se.jobsesprit.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import se.jobsesprit.entities.confirmCovoiturage;
import se.jobsesprit.services.confirmCovoiturageCRUD;

public class ListCovoiturageController implements Initializable {

    @FXML
    private AnchorPane MF_menu_form;
    @FXML
    private ScrollPane MF_menu_scrollPane;
    @FXML
    private GridPane MF_menu_gridPane;
    @FXML
    private Label MF_Label_username_pub;
    @FXML
    private Label MF_Label_heureDepart_pub;
    @FXML
    private Label MF_Label_lieuDepart_pub;
    @FXML
    private Label MF_Label_lieuArrivee_pub;
    @FXML
    private Label MF_Label_prix_pub;
    @FXML
    private Label MF_Label_nbrPlacesreserve_pub;
    @FXML
    private Label MF_Label_nbrPlaces_pub;
    @FXML
    private Button MF_confirm_BT;
    @FXML
    private Label MF_Label_prixtotal_pub;
    
  
    private Covoiturage covoiturageData;
    private ObservableList<Covoiturage> cardListData = FXCollections.observableArrayList();
    @FXML
    private Label MF_label_username;
    
    
    
    
    private String firstNameEtud = "";
    private String lastNameEtud = "";
    private int phoneEtud = 0;
    private String emailEtud = "";

    private String firstNameConducteur = "";
    private String lastNameConducteur = "";
    private int phoneConducteur = 0;
    private String emailConducteur = "";
    @FXML
    private Button MF_maps_BT;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
            setMFusername();
            menuCardListData();
    }

    public void menuCardListData() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;
        MF_menu_gridPane.getRowConstraints().clear();
        MF_menu_gridPane.getColumnConstraints().clear();
        for (int q = 0; q < cardListData.size(); q++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("CardCovioturage.fxml"));
                AnchorPane pane = load.load();
                CardCovioturageController cardCovoiturage = load.getController();
                cardCovoiturage.setDaata(cardListData.get(q));
                cardCovoiturage.setListCovoiturageController(this);
                if (column == 1) 
                {
                    column = 0;
                    row += 1;
                }
                MF_menu_gridPane.add(pane, column++, row);
            } catch (Exception ex) {
                Logger.getLogger(ListCovoiturageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ObservableList<Covoiturage> menuGetData() {
        ObservableList<Covoiturage> myList = FXCollections.observableArrayList();

        Connection connection = MyConnection.getInstance().getCnx();
        String requete = "SELECT id, heureDepart, lieuDepart, lieuArrivee, image, prix, nombrePlacesDisponible, username FROM Covoiturage where nombrePlacesDisponible != 0 ";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Covoiturage covoiturage = new Covoiturage();
                covoiturage.setId(rs.getInt("id"));
                covoiturage.setHeureDepart(rs.getString("heureDepart"));
                covoiturage.setLieuDepart(rs.getString("lieuDepart"));
                covoiturage.setLieuArrivee(rs.getString("lieuArrivee"));
                covoiturage.setImage(rs.getString("image"));
                covoiturage.setPrix(rs.getString("prix"));
                covoiturage.setNombrePlacesDisponibles(rs.getInt("nombrePlacesDisponible"));
                covoiturage.setUsername(rs.getString("username"));
                myList.add(covoiturage);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

public void updateDataInListCovoiturageController(String username, String heureDepart, String lieuDepart, String lieuArrivee, String prix, String nbrPlacesReserve, String nbrPlaces) {
    MF_Label_username_pub.setText(username);
    MF_Label_heureDepart_pub.setText(heureDepart);
    MF_Label_lieuDepart_pub.setText(lieuDepart);
    MF_Label_lieuArrivee_pub.setText(lieuArrivee);
    MF_Label_prix_pub.setText(prix);
    MF_Label_nbrPlacesreserve_pub.setText(nbrPlacesReserve);
    MF_Label_nbrPlaces_pub.setText(nbrPlaces);
    try {
        double price = Double.parseDouble(prix.replaceAll("[^0-9.]", "").trim());
        int reservedSeats = Integer.parseInt(nbrPlacesReserve);
        double totalPrice = price * reservedSeats;
        MF_Label_prixtotal_pub.setText(String.valueOf(totalPrice));
    } catch (NumberFormatException e) {
        MF_Label_prixtotal_pub.setText("N/A");
    }
}


 @FXML
    private void MF_confirm_BT(ActionEvent event) {
        try {
            // Récupérez les informations nécessaires depuis les labels
            String usernameEtud = MF_label_username.getText();
            String usernameConducteur = MF_Label_username_pub.getText();
            String heureDepart = MF_Label_heureDepart_pub.getText();
            String lieuDepart = MF_Label_lieuDepart_pub.getText();
            String lieuArrivee = MF_Label_lieuArrivee_pub.getText();
            String prix = MF_Label_prix_pub.getText();
            String nbrPlacesReserve = MF_Label_nbrPlacesreserve_pub.getText();
            String nbrPlaces = MF_Label_nbrPlaces_pub.getText();

            int nombrePlacesDispo = Integer.parseInt(nbrPlaces);
            int nombrePlacesReserve = Integer.parseInt(nbrPlacesReserve);
            int nombrePlacesDisponibles = nombrePlacesDispo - nombrePlacesReserve;

            // Mise à jour des labels pour afficher les valeurs mises à jour
            MF_Label_nbrPlaces_pub.setText(String.valueOf(nombrePlacesDisponibles));

            // Mise à jour de la base de données pour refléter les places restantes
            if (covoiturageData != null) {
                int covoiturageId = covoiturageData.getId();
                if (CovoiturageCRUD.updateNombrePlacesDisponibles(covoiturageId, nombrePlacesDisponibles)) {
                    System.out.println("Successfully updated the available seats.");
                } else {
                    System.err.println("Failed to update available seats.");
                }
            } else {
                // Si covoiturageData est null, mettez à jour la base de données directement
                String updateQuery = "UPDATE Covoiturage SET nombrePlacesDisponible = ? WHERE username = ? AND heureDepart = ? AND lieuDepart = ? AND lieuArrivee = ? AND prix = ?";
                Connection connection = MyConnection.getInstance().getCnx();
                PreparedStatement statement = connection.prepareStatement(updateQuery);
                statement.setInt(1, nombrePlacesDisponibles);
                statement.setString(2, usernameConducteur);
                statement.setString(3, heureDepart);
                statement.setString(4, lieuDepart);
                statement.setString(5, lieuArrivee);
                statement.setString(6, prix);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Successfully updated the available seats.");
                } else {
                    System.err.println("Failed to update available seats.");
                }
            }
            fetchUserDataFromTable(usernameEtud, usernameConducteur);
            insertDataIntoConfirmCovoiturage();
        } catch (NumberFormatException | SQLException e) {
            System.err.println("An error occurred while updating the available seats: " + e.getMessage());
        }
        MF_Label_nbrPlacesreserve_pub.setText(String.valueOf(0));
         reloadInterface();
    }

 
    private void reloadInterface() {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCovoiturage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) MF_confirm_BT.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
////////////////////////////Login_MASSOUD///////////////////////////////////////
///////////////////////////////////////////////////////////////////
    private void setMFusername() {
        // Récupérer le nom d'utilisateur depuis le contrôleur LoginController
        String username = InscriptionController.getLoggedInUsername();
        MF_label_username.setText(username);
    }
////////////////////////////Login_MASSOUD///////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

    
    

    
    

    
    
private void fetchUserDataFromTable(String usernameEtud, String usernameConducteur) {
    String queryEtud = "SELECT first_Name, last_Name, phone, email FROM userEtudiant WHERE username = ?";
    
    Connection connection = MyConnection.getInstance().getCnx();

    try {
        // Récupérer les données de l'utilisateur étudiant
        PreparedStatement stmtEtud = connection.prepareStatement(queryEtud);
        stmtEtud.setString(1, usernameEtud);
        ResultSet rsEtud = stmtEtud.executeQuery();

        if (rsEtud.next()) {
            firstNameEtud = rsEtud.getString("first_Name");
            lastNameEtud = rsEtud.getString("last_Name");
            phoneEtud = rsEtud.getInt("phone");
            emailEtud = rsEtud.getString("email");
        }

        // Réinitialiser le ResultSet pour le conducteur
        rsEtud.close();
        stmtEtud.clearParameters();
        stmtEtud.setString(1, usernameConducteur);
        rsEtud = stmtEtud.executeQuery();

        if (rsEtud.next()) {
            firstNameConducteur = rsEtud.getString("first_Name");
            lastNameConducteur = rsEtud.getString("last_Name");
            phoneConducteur = rsEtud.getInt("phone");
            emailConducteur = rsEtud.getString("email");
        }
        rsEtud.close(); // Fermer le ResultSet
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


private void insertDataIntoConfirmCovoiturage() {
    if (MF_Label_nbrPlacesreserve_pub != null) {
        confirmCovoiturage confirmCovoiturage = new confirmCovoiturage();
        confirmCovoiturage.setUsernameEtud(MF_label_username.getText());
        confirmCovoiturage.setUsernameConducteur(MF_Label_username_pub.getText());
        confirmCovoiturage.setFirstNameEtud(firstNameEtud);
        confirmCovoiturage.setLastNameEtud(lastNameEtud);
        confirmCovoiturage.setFirstNameConducteur(firstNameConducteur);
        confirmCovoiturage.setLastNameConducteur(lastNameConducteur);
        confirmCovoiturage.setPhoneEtud(phoneEtud);
        confirmCovoiturage.setPhoneConducteur(phoneConducteur);
        confirmCovoiturage.setEmailEtud(emailEtud);
        confirmCovoiturage.setEmailConducteur(emailConducteur);
        confirmCovoiturage.setHeureDepart(MF_Label_heureDepart_pub.getText());
        confirmCovoiturage.setLieuDepart(MF_Label_lieuDepart_pub.getText());
        confirmCovoiturage.setLieuArrivee(MF_Label_lieuArrivee_pub.getText());
        confirmCovoiturage.setPrixTotalePlacesReserve(MF_Label_prixtotal_pub.getText());
        confirmCovoiturage.setNombrePlacesReserve(Integer.parseInt(MF_Label_nbrPlacesreserve_pub.getText()));
        confirmCovoiturageCRUD confirmCovoiturageCRUD = new confirmCovoiturageCRUD();
        confirmCovoiturageCRUD.ajouterEntities(confirmCovoiturage);
    } else {
        System.err.println("Covoiturage data is missing. ooooooooo ");
    }
}

@FXML
private void MF_maps_BT(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MapsView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Carte Massoud");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    
    
    
}
