/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.jobsesprit.entities.UserEtudiant;
import se.jobsesprit.services.UserEtudiantCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterEtudiantController implements Initializable {

    @FXML
    private Label fname;
    @FXML
    private Label flastname;
    @FXML
    private Label fnumber;
    @FXML
    private Label fbirthday;
    @FXML
    private Label fage;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField tfnumber;
    @FXML
    private DatePicker tfbirthday;
    @FXML
    private Label fpicture;
    @FXML
    private Button btnAdd;
    @FXML
    private Label fmail;
    @FXML
    private Label fpassword;
    @FXML
    private  TextField tfemail;
    @FXML
    private  TextField tfpassword;
    
    
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    @FXML
    private TextField pass_conf;
    @FXML
    private Label email_existe;
    
    UserEtudiantCRUD se=new UserEtudiantCRUD();
    @FXML
    private Button img1;
    @FXML
    private Label pass_conf1;
    @FXML
    private TextField tfage;
    @FXML
    private TextField tfusername;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    

    @FXML
    private void addEtudiant(ActionEvent event) {
         String nom= tfname.getText();
        String email=tfemail.getText();
        String prenom=tflastname.getText();
        String mdp=tfpassword.getText();
        String confpass=pass_conf.getText();
        System.out.println(confpass);
        String phone =tfnumber.getText();
        String username=tfusername.getText();
        String age= tfage.getText();
        boolean isValid = validateFields(email, phone, mdp,confpass);
        UserEtudiant etudiant=null; 
        
        if (isValid) {
                if (showConfirmationDialog("Confirmer", "Enregistrer votre compte", "Voulez-vous enregistrer votre nouveau compte ?")) {
                    etudiant= new UserEtudiant(Integer.parseInt(phone),email,  mdp, nom, prenom, username,"",Integer.parseInt(age),0);
                    
               
            }
                se.ajouterEntities(etudiant);
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


    @FXML
    private void selectImageButton(ActionEvent event) {
        
    }
}
