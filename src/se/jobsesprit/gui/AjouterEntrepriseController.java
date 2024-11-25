/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static jdk.nashorn.internal.runtime.UnwarrantedOptimismException.isValid;
import se.jobsesprit.entities.Portfolio;
import se.jobsesprit.entities.UserEntreprise;
import se.jobsesprit.services.PortfolioCRUD;
import se.jobsesprit.services.UserEntrepriseCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterEntrepriseController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private TextField tfphone;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tflocation;
    @FXML
    private Button tfadd;
    @FXML
    private Label email_existe;
    @FXML
    private Label conf_pass;
    
    
    
    UserEntrepriseCRUD se=new UserEntrepriseCRUD();
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @FXML
    private TextField tfconf;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void AddCompany(ActionEvent event) {
        String nom= tfname.getText();
        String email=tfemail.getText();
        String location=tflocation.getText();
        String mdp=tfpassword.getText();
        String confpass=tfconf.getText();
        System.out.println(confpass);
        String phone =tfphone.getText();
        boolean isValid = validateFields(email, phone, mdp,confpass);
        UserEntreprise entreprise=null;
            if (isValid) {
                if (showConfirmationDialog("Confirmer", "Enregistrer votre Entreprise", "Voulez-vous enregistrer cette Entreprise ?")) {
                    entreprise= new UserEntreprise(Integer.parseInt(phone), email, mdp, confpass, location, null);
                    
               
            }
                se.ajouterEntities(entreprise);
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                tfemail.getScene().setRoot(root);
        } catch (IOException e) {
        }
           
                
        }
        
          
        
        
        
        
       
 
        
        }
        
    
    
    private boolean validateFields(String email, String phone, String pass, String confpass) {
    boolean isValid = true;

    if (email.isEmpty()) {
        showAlert("Ce champ est obligatoire!", "Email est obligatoire", "Veuillez saisir votre Email.");
        isValid = false;
    } else {
        if(!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
                        isValid = false;
                        showAlert("Ce champ est invalide!", "Email est invalide", "Veuillez saisir votre Email correctement.");
                        
        }
        else try {
            if(se.findByMail(email).getEmail()!=null){
                System.err.println(se.findByMail(email).toString());
                showAlert(" ","Cette addresse existe deja", "Veuillez saisir votre Email.");
                isValid = false;
            }
        } catch (SQLException ex) {
        }System.out.println("erreur");
            
        }
    if (pass.isEmpty()) {
        showAlert("Ce champ est obligatoire!", "le champ est vide", "Veuillez saisir votre mot de passe.");
        isValid = false;
    } else if ( confpass.isEmpty() ) {
        showAlert("Ce champ est obligatoire!", "Le champ est vide", "Veuillez saisir votre mot de passe une autre fois pour le confirmer");
        isValid = false;
    } else if(!Objects.equals(pass, confpass)){
        showAlert("Invalide", "Mot de passe n'est pas conforme", "");
        isValid = false;
    }
    if (phone.length()!=8){
        showAlert("Ce champ est obligatoire!", "Le numéro est invalide", "votre numéro doit se composer de 8 chiffres");
        isValid = false;
    }

    return isValid;
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
        
    
    
        
         

    
}
