/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.jobsesprit.entities.UserEntreprise;
import se.jobsesprit.services.UserEntrepriseCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifEntrepriseController_1 implements Initializable {

    @FXML
    private Button btimage;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField localisation;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpass;
    @FXML
    private TextField confpass;
    @FXML
    private TextField tfphone;
    @FXML
    private Button btvalider;
    @FXML
    private ImageView imageview;
    @FXML
    private Button btvalider1;
    
    Boolean test = false;
     File file;
     UserEntrepriseCRUD ue = new UserEntrepriseCRUD();
     String img;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tfnom.setText(InscriptionController.entreprise.getNomEntreprise());
        tfemail.setText(InscriptionController.entreprise.getEmail());
        localisation.setText(InscriptionController.entreprise.getLocalisation());
        
        tfphone.setText(Integer.toString(InscriptionController.entreprise.getPhone()));
        
    }    

    @FXML
    private void importerimage(ActionEvent event) {
        
    }

    @FXML
    private void valider(ActionEvent event) {
        UserEntreprise u = new UserEntreprise();
        if (tfpass.getText().equals("") || tfnom.getText().equals("") || confpass.getText().equals("") || tfemail.getText().equals("") || tfphone.getText().equals("") || localisation.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        } else if (tfemail.getText().matches("(.*)@(.*)") == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        } else if (tfphone.getText().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid numero");
            alert.show();

        } else {
            System.out.println(InscriptionController.entreprise.toString());
            u.setId(InscriptionController.entreprise.getId());
            u.setNomEntreprise(tfnom.getText());
            u.setEmail(tfemail.getText());
            u.setPhone(Integer.valueOf(tfphone.getText()));
            u.setLocalisation(localisation.getText());
            u.setMotDePasse(tfpass.getText());
            u.setRole("ENTERPRISE");
            if (showConfirmationDialog("Confirmer", "Enregistrer votre compte", "Voulez-vous enregistrer votre  compte ?")) {
               ue.modifierEntities(u);System.out.println("personne Modifier avec succes");
            System.out.println(u);
            InscriptionController.entreprise=u;
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profilEntreprise.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                tfemail.getScene().setRoot(root);
        } catch (IOException e) {
        }
               
            }
            
        }
    }
    
    private boolean showConfirmationDialog(String title, String headerText, String contentText) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(headerText);
        confirmationDialog.setContentText(contentText);
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    
    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        if (showConfirmationDialog("Confirmation", "Supprimer votre compte!", "Voulez-vous supprimer votre compte ?")) {
            
        ue.supprimerEntities(InscriptionController.entreprise);
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                tfnom.getScene().setRoot(root);
        } catch (IOException e) {
        }
    }
    
}
