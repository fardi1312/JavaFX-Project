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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class menuEntrepriseController implements Initializable {

    @FXML
    private Button MF_Offre_BT;
    @FXML
    private Button MT_Evnt_BT;
    @FXML
    private Button MF_Entretien_BT;
    @FXML
    private Button MF_Candidature_BT;
    @FXML
    private Button MF_Logout_BT;
    @FXML
    private AnchorPane MF_AnchorView_BT;
    @FXML
    private Button profileEntreprise;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void MF_Offre_BT(ActionEvent event) {
     try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreEntreprise.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }}

    @FXML
    private void MT_evnt_BT(ActionEvent event) {
    }

    @FXML
    private void MF_Entretien_BT(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }}

    @FXML
    private void MF_Candidature_BT(ActionEvent event) {
                try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MesCandidature.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }

    @FXML
    private void MF_Logout_BT(ActionEvent event) throws IOException {
        
          
              Parent root = FXMLLoader.load(getClass().getResource("FXMLaffichage.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void btnprofile(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profilEntreprise.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }
        
    }
    
}
