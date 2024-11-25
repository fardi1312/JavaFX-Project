/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import se.jobsesprit.entities.UserEntreprise;
import se.jobsesprit.entities.UserEtudiant;
import se.jobsesprit.services.UserEntrepriseCRUD;
import se.jobsesprit.services.UserEtudiantCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InscriptionController implements Initializable {

    @FXML
    private Label fusername;
    @FXML
    private Label fpassword;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button btcreate;
    @FXML
    private Button btnlogin;
    @FXML
    private Line fligne;
    @FXML
    private TextField tfmail;

    UserEtudiantCRUD se=new UserEtudiantCRUD();
    UserEntrepriseCRUD sa=new UserEntrepriseCRUD();
        public static UserEntreprise entreprise=null;
        public static UserEtudiant etudiant=null;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void create(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("createAccount.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                tfmail.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }
     
    
    

 @FXML
    private void login(ActionEvent event) {
        String mail = tfmail.getText();
        String mdp = tfpassword.getText();
        UserEtudiant user = null;
        UserEntreprise user1 = null;
        
        try {
            user = se.authenticate(mail, mdp);
            user1 = sa.authenticate(mail, mdp);
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'authentification : " + ex.getMessage());
        }
        
        if (user1 != null && "ENTREPRISE".equals(user1.getRole())) {
            this.entreprise=user1;
               try {
            // Fermez la scène actuelle
            Stage currentStage = (Stage) tfmail.getScene().getWindow();
            currentStage.close();
        loggedInId = user1.getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("menuEntreprise.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
                System.out.println("Erreur lors du chargement de menuEntreprise.fxml : " + e.getMessage());
            }
        }

        else if (user != null && "ETUDIANT".equals(user.getRole())) {
            this.etudiant=user;
              try {
            // Fermez la scène actuelle
            Stage currentStage = (Stage) tfmail.getScene().getWindow();
            currentStage.close();
            loggedInUsername = user.getUsername();
            loggedInId = user.getId();


            FXMLLoader loader = new FXMLLoader(getClass().getResource("menuEtudiant.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
                System.out.println("Erreur lors du chargement de menuEtudiant.fxml : " + e.getMessage());
            }
        }
        
        else {
            System.out.println("Authentification échouée. Veuillez vérifier vos informations d'identification.");

            // Obtenez la scène actuelle à partir d'un des éléments de votre interface graphique
            Scene currentScene = tfmail.getScene();

            // Chargez à nouveau la même page (inscription.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
            Parent root;
            try {
                root = loader.load();
                currentScene.setRoot(root);
            } catch (IOException e) {
                System.out.println("Erreur lors du chargement de inscription.fxml : " + e.getMessage());
            }
        }
    }
    
     
    
    
    //////////////////////MASSOUD///////////////////////
    
    private static String loggedInUsername; // Variable pour stocker le nom d'utilisateur connecté
    public static String getLoggedInUsername() {
        return loggedInUsername;
    }
   /////////////////////////////////////////////
    
    
    
    
    
    

    private static int loggedInId; // Variable pour stocker l'ID d'utilisateur connecté
        public static int getLoggedID() {
            return loggedInId;
        }

    
    
}