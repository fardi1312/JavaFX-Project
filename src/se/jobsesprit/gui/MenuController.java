/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView quit;
    @FXML
    private Label menu;
    @FXML
    private Label menuback;
    @FXML
    private AnchorPane slider;
    @FXML
    private Button profil;
    @FXML
    private Button portfolio;
    @FXML
    private Button offres;
    @FXML
    private Button cov;
    @FXML 
    private Button btnprofil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
        // TODO
      

    @FXML
    private void btnprofil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void btnportfolio(ActionEvent event) {
    }

    @FXML
    private void btnoffres(ActionEvent event) {
    }

    @FXML
    private void btncov(ActionEvent event) {
    }
    
}
