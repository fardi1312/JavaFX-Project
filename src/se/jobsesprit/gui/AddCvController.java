/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import se.jobsesprit.entities.Candidature;
import se.jobsesprit.services.CandidatureCRUD;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AddCvController implements Initializable {

    @FXML
    private ImageView imageCv;
        private File selectedImageFile;
    @FXML
    private TextField tfuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutCv(ActionEvent event) {


        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog((Window) imageCv.getScene().getWindow());




        if (selectedImageFile != null) {
            
            Image image = new Image(selectedImageFile.toURI().toString());
                        imageCv.setImage(image);
           
        }
    }

    @FXML
    private void ajoutCandidature(ActionEvent event) {
          String username =tfuser.getText();
          
        

    
                     if (selectedImageFile != null) {
                         
                                 Candidature c = new Candidature(username, username, 1, 1);
            try {
                FileInputStream fis = new FileInputStream(selectedImageFile);
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    bo.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = bo.toByteArray();

              
                String imageString = new String(Base64.getEncoder().encode(imageBytes), StandardCharsets.UTF_8);

                
                c.setCv(imageString);
                
              } catch (IOException e) {
                System.err.println("error");
            }
                              CandidatureCRUD ccd = new CandidatureCRUD();
                      ccd.ajouterEntities(c);
        
    }}

    @FXML
    private void retour(MouseEvent event) {
                try {
            Parent root = FXMLLoader.load(getClass().getResource("Candidature.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PortfolioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
        
    
    

