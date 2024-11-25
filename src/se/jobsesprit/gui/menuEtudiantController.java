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
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class menuEtudiantController implements Initializable {

    @FXML
    private Button MF_OffreEdu_BT;
    @FXML
    private Button MF_CoivEdu_BT;
    @FXML
    private Button MF_Logout_BT;
    @FXML
    private AnchorPane MF_AnchorView_BT;
    private Label MF_coivCrud_BT;
    private Label MF_coivConfirm_BT;
    @FXML
    private Button MF_covoiturageCrud_BT;
    @FXML
    private Button MF_covoiturageConfirm_BT;
    @FXML
    private Label MF_profil_BT;
    @FXML
    private Button MT_Portfolio_BT;
    @FXML
    private Button profiletudiant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
 
private void MF_OffreEdu_BT(ActionEvent event) {
  try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreEtudiant.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }}


    private void MT_evntEdu_BT(MouseEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreEtudiant.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }



    
    @FXML
    private void MF_Logout_BT(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    }

    
    
    
    

    private void MF_EntretienEdu_BT(ActionEvent event) {      
            try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLaffichage.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }    
        
        
        
    }

    
    
    
    
    
    
    
    
    
    
    
    

    
//    public void reloadInterface() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("menuEtudiant.fxml"));
//            Parent root = loader.load();
//            
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) .getScene().getWindow(); // Remplacez `yourNode` par un nœud de votre scène actuelle
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//             Gérer l'exception, par exemple afficher un message d'erreur
//        }
//    }


    @FXML
    private void MF_covoiturageConfirm_BT(ActionEvent event) {           
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Liste_de_Reservation.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
        @FXML
    private void MF_CoivEdu_BT(ActionEvent event) {
        MF_covoiturageCrud_BT.setVisible(true);
        MF_covoiturageConfirm_BT.setVisible(true);  
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListCovoiturage.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } 
        catch (IOException e) {
        e.printStackTrace();
    }
        
        
    }

    @FXML
    private void MF_covoiturageCrud_BT(ActionEvent event) {
        
                    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLaffichage.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }
        
        
    }

    @FXML
    private void MT_Portfolio_BT(ActionEvent event) {
                           try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Portfolio.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }    
    }

    @FXML
    private void btnprofile(ActionEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profil.fxml"));
        Node offreNode = loader.load();
        MF_AnchorView_BT.getChildren().setAll(offreNode);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    
    
}
