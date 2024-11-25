/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import se.jobsesprit.entities.Offre;
import se.jobsesprit.services.OffreCRUD;
/**
 * FXML Controller class
 *
 * @author ACER
 */
public class CandidatureController implements Initializable {

    @FXML
    private ImageView quit;
    @FXML
    private ImageView addpng;
    @FXML
    private Button Mp;
    @FXML
    private ImageView modifypng;
    @FXML
    private ImageView deletepng;
    @FXML
    private Label menu;
    @FXML
    private Label menuback;
    @FXML
    private AnchorPane slider;
    @FXML
    private TableView<Offre> tab_offre;
    @FXML
    private TableColumn<Offre, String> tab_titre;
    @FXML
    private TableColumn<Offre, String> tab_desc;
    @FXML
    private TableColumn<Offre, String> tab_typeStage;
    @FXML
    private TableColumn<Offre, String> tab_secteur;
    @FXML
    private TableColumn<Offre, String> tab_fonction;
    @FXML
    private TableColumn<Offre, String> tab_date;
    @FXML
    private TableColumn<Offre, String> tab_image;
int identreprise=1;
    ObservableList<Offre> offreList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               quit.setOnMouseClicked(event -> {
            System.exit(0);
         
        });
        slider.setTranslateX(-176);
        menu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(false);
                menuback.setVisible(true);
            });
        });

        menuback.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
               slide.setToX(-176);

            slide.play();
            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                menu.setVisible(true);
                menuback.setVisible(false);
            });
        });
                tab_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
       tab_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_typeStage.setCellValueFactory(new PropertyValueFactory<>("typeStage"));
        tab_secteur.setCellValueFactory(new PropertyValueFactory<>("secteurs"));
        tab_fonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
        tab_date.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        tab_image.setCellValueFactory(new PropertyValueFactory<>("images"));
      

        loadoffre();
    }    
    
    
    private void loadoffre() {
        OffreCRUD offreCRUD = new OffreCRUD();
ObservableList<Offre> offres = FXCollections.observableArrayList(OffreCRUD.getOffresByEntrepriseConnectee(identreprise));
        tab_offre.setItems(offres);
    }

    @FXML
    private void GoToPortfolio(ActionEvent event) {
               try {
            Parent root = FXMLLoader.load(getClass().getResource("Portfolio.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PortfolioController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void GoToAddCv(ActionEvent event) {
                    Offre selectedOffre = tab_offre.getSelectionModel().getSelectedItem();
          
   if (selectedOffre != null) {
       if (showConfirmationDialog("Confirmation", "Choisir cette offre!", "Voulez-vous choisir cette offre ?")) {
   }
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

 

    
}}
