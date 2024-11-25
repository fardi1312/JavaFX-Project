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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.jobsesprit.entities.UserEtudiant;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ProfilController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Button retour;
    @FXML
    private Button modifier;
    @FXML
    private Label bio;
    @FXML
    private Label competences;
    @FXML
    private Label formation;
    @FXML
    private Label tfnom;
    @FXML
    private Label tfprenom;
    @FXML
    private Label tfmail;
    @FXML
    private Label tfphone;
    @FXML
    private Label tfage;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfnom.setText(InscriptionController.etudiant.getFirstName());
        
        tfmail.setText(InscriptionController.etudiant.getEmail());
        tfprenom.setText(InscriptionController.etudiant.getLastName());
        tfage.setText(Integer.toString(InscriptionController.etudiant.getAge()));
        tfphone.setText(Integer.toString(InscriptionController.etudiant.getPhone()));
        
    }    

    @FXML
    private void btretour(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                tfmail.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }

    @FXML
    private void btmodifier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifUser.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                tfmail.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }
    
}
