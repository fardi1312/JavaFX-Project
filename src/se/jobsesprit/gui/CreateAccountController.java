/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CreateAccountController implements Initializable {

    @FXML
    private Label femail;
    @FXML
    private Label fpassword;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private Label frole;
    @FXML
    private RadioButton rentreprise;
    @FXML
    private RadioButton retudiant;
    @FXML
    private Button btncontinue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void validerButtonClicked() {
    
        String selectedRole = retudiant.isSelected() ? "Etudiant" : "Entreprise";
        System.out.println(selectedRole);
        

        // Chargez la deuxième interface en fonction du rôle sélectionné.
        if (selectedRole.equals("Etudiant")) {
            loadAjouterEtudiant();
        } else if (selectedRole.equals("Entreprise")) {
            loadAjouterEntreprise();
        }
        
    }


    private void loadAjouterEtudiant() {
        try {
            System.out.println("se.jobsesprit.gui.CreateAccountController.loadAjouterEtudiant()");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterEtudiant.fxml"));
            Parent root = loader.load();
            
            retudiant.getScene().setRoot(root);
            
        } catch (IOException e) {
        }
    }
    
    private void loadAjouterEntreprise() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterEntreprise.fxml"));
            Parent root = loader.load();
            retudiant.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }
    
    
    

}
    
