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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ProfilEntrepriseController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private ImageView image;
    @FXML
    private Button retour;
    @FXML
    private Label tfnom;
    @FXML
    private Label phone;
    @FXML
    private Label mail;
    @FXML
    private Label adresse;
    @FXML
    private Label bio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfnom.setText(InscriptionController.entreprise.getNomEntreprise());
        
        mail.setText(InscriptionController.entreprise.getEmail());
        
        adresse.setText(InscriptionController.entreprise.getLocalisation());
        phone.setText(Integer.toString(InscriptionController.entreprise.getPhone()));
    }    

    @FXML
    private void btmodifier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifEntreprise_1.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                mail.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }

    @FXML
    private void btretour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                mail.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }
    
}
