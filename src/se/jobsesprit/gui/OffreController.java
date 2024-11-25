/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import static java.awt.SystemColor.text;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import se.jobsesprit.entities.Entreprise;
import se.jobsesprit.entities.Offre;
import se.jobsesprit.services.OffreCRUD;
import static se.jobsesprit.services.OffreCRUD.getLikesCount;
import se.jobsesprit.services.Translator;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class OffreController implements Initializable {

    private Label OB_titreoffreetu;
    @FXML
    private Label OB_typeoffreetu;
    @FXML
    private Label OB_dateoffreetu;
    @FXML
    private Label OB_foncoffreetu;
    @FXML
    private Label OB_secoffreetu;
    @FXML
    private Label OB_desceoffreetu;
    private  Offre offre ;
    @FXML
    private ImageView OB_image;
    @FXML
    private Label OB_entrepriseoffre;
    @FXML
    private Label OB_like;
    @FXML
    private Label OB_dislike;
    @FXML
    private Label OB_titreoffreetu1;
    @FXML
    private Button OB_buttonlike;
    private Button OB_buttonldisike;
    @FXML
    private Label OB_offreTraduire;
    @FXML
    private Button btncandi;
    

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      OB_offreTraduire.setOnMouseClicked(new EventHandler<MouseEvent>() {
          
            @Override
            public void handle(MouseEvent event) {
    String description = offre.getDescription();  // Obtenez la description de votre offre (remplacez ceci par votre logique)

     
        String fromLang = "en";  // Langue source (anglais)
        String toLang = "fr";    // Langue cible (français)

        try {
             String translatedText = Translator.translate(fromLang, toLang, description);
                  OB_offreTraduire.setText(translatedText);
 System.out.println("Texte traduit : " + translatedText);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
            }
        });
   
    }  

public void setData(Offre offre) {
    this.offre = offre;
    if (offre != null) {
        OB_titreoffreetu1.setText(offre.getTitre());
        OB_dateoffreetu.setText(offre.getDateInscription());
        OB_foncoffreetu.setText(offre.getFonction());
        OB_secoffreetu.setText(offre.getSecteurs());
        OB_desceoffreetu.setText(offre.getDescription());
        OB_typeoffreetu.setText(offre.getTypeStage());
OB_entrepriseoffre.setText(offre.getNomEntreprise());
       
        int likesCount = OffreCRUD.getLikesCount(offre.getId());

       OB_like.setText("" + likesCount);
    


String imageString = offre.getImages();
        if (imageString != null && !imageString.isEmpty()) {
            try {
                byte[] imageBytes = Base64.getDecoder().decode(imageString);
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                OB_image.setImage(image);
            } catch (Exception e) {
                System.out.println("Error loading image: " + e.getMessage());
                OB_image.setImage(null); // Set default image or handle the error as needed
            }
        } else {
            OB_image.setImage(null); 
        }
    } 
}
/*rivate void updateLikesCount(int offreId) {
    int likesCount = getLikesCount(offreId);
    OB_like.setText("Likes: " + likesCount);
}*/

    @FXML
    private void OB_makelike(ActionEvent event) {
   int offreId =offre.getId(); //  ceci par la logique pour obtenir l'identifiant de l'offre depuis votre interface utilisateur
OffreCRUD offreManager = new OffreCRUD();
offreManager.ajouterLike(offreId, 1);
 OB_buttonlike.setDisable(true);
     setData(offre);

    }

 
 

    
    
    private void onVoirTraductionClicked(MouseEvent event) {
       String description = "offre.getDescription()";  // Obtenez la description de votre offre (remplacez ceci par votre logique)

     
        String fromLang = "en";  // Langue source (anglais)
        String toLang = "fr";    // Langue cible (français)

        try {
             String translatedText = Translator.translate(fromLang, toLang, description);
                  OB_offreTraduire.setText(translatedText);
 System.out.println("Texte traduit : " + translatedText);
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
           public boolean showConfirmationDialog(String title, String headerText, String contentText) {
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
    private void GoToCv(ActionEvent event) {
                                 
          
  
       if (showConfirmationDialog("Confirmation", "Choisir cette offre!", "Voulez-vous choisir cette offre ?")) {
   
                try {
            Parent root = FXMLLoader.load(getClass().getResource("addCv.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PortfolioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }else {
            showAlert("Aucune sélection", "Veuillez sélectionner une offre!", "");
        }

    }
}
