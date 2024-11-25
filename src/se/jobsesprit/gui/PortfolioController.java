/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import se.jobsesprit.entities.Portfolio;
import se.jobsesprit.services.PortfolioCRUD;
import se.jobsesprit.services.QrCode;


/**
 * FXML Controller class
 *
 * @author ACER
 */
public class PortfolioController implements Initializable {

    @FXML
    private ImageView addpng;
    @FXML
    private ImageView modifypng;
    @FXML
    private ImageView deletepng;
    @FXML
    private ComboBox <String> comb;
    
    private ImageView imageView;
    private File selectedImageFile;
    @FXML
    private Button Mp;
    @FXML
    private TextField tftitre;
    @FXML
    private Button img1;

    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfdescription;
    
    
    @FXML
    private TableView<Portfolio> tab_portfolio;
    @FXML
    private TableColumn<Portfolio, String> col_titre;
    @FXML
    private TableColumn<Portfolio, String> col_secteur;
    @FXML
    private TableColumn<Portfolio, String> col_desc;
    @FXML
    private TableColumn<Portfolio, String> col_date;
    @FXML
    private TableColumn<Portfolio, String> col_image;
    @FXML
    private ImageView ImgSelect;
    @FXML
    private Button Qr;
    public Portfolio portfolio;
    @FXML
    private ImageView imgQr;
    public File SelectedQr;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         ObservableList<String> list = FXCollections.observableArrayList("Business","Santé","Art","Science","Divertiseement","Education","Sport");
        comb.setItems(list);
        
        

        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_secteur.setCellValueFactory(new PropertyValueFactory<>("secteur"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateP"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        loadPortfolio();
        
    }    
    
    
        private void loadPortfolio() {
        PortfolioCRUD portc = new PortfolioCRUD();
ObservableList<Portfolio> portfolios = FXCollections.observableArrayList(PortfolioCRUD.getAllPortfolios());
        tab_portfolio.setItems(portfolios);
    }

    @FXML
    public void selectImageButton(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog((Window) img1.getScene().getWindow());
      
        




        if (selectedImageFile != null) {
            
            Image image = new Image(selectedImageFile.toURI().toString());
                        ImgSelect.setImage(image);
        
           
        }
    }



  

    @FXML
    public void savePortfolio(ActionEvent event) {
        String titre =tftitre.getText();
        String description =tfdescription.getText();
       String secteur  = (String )comb.getSelectionModel().getSelectedItem();
        LocalDate localDate =tfdate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateP = localDate.format(formatter);
        
        
        
        
        boolean isValid = validateFields(titre, description, localDate);

            if (isValid) {
                if (showConfirmationDialog("Confirmer", "Enregistrer votre portfolio", "Voulez-vous enregistrer ce portfolio ?")) {
                    Portfolio p = new Portfolio(titre, description, dateP, secteur, dateP);
              if (selectedImageFile != null) {
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

                
                p.setImage(imageString);
                bo.close();
                
              } catch (IOException e) {
                System.err.println("error");
            }
        }
                      PortfolioCRUD pcd = new PortfolioCRUD();
                      pcd.ajouterEntities(p);
                      tab_portfolio.refresh();
                      loadPortfolio();
                      clearFields();
                      
               
               
            }
        }
           
        }
    
    
        private void clearFields() {
        tftitre.clear();
        tfdescription.clear();
        tfdate.setValue(null);
      
   
    }
    
        public boolean showConfirmationDialog(String title, String headerText, String contentText) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(headerText);
        confirmationDialog.setContentText(contentText);
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    
         public boolean validateFields(String titre, String description, LocalDate dateP) {
    boolean isValid = true;

    if (titre.isEmpty()) {
        showAlert("Ce champ est obligatoire!", "le Titre est obligatoire", "Veuillez saisir le titre.");
        isValid = false;
    } else if (description.isEmpty()) {
        showAlert("Ce champs est obligatoire!", "la Description est obligatoire", "Veuillez saisir la description.");
        isValid = false;
    } else if (dateP == null ) {
        showAlert("La date est invalide!", "La date est trop rècente", "Veuillez choisir une date valide!");
        isValid = false;
    } 

    return isValid;
}
         
        private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
        


    @FXML
    private void rowClicked(MouseEvent event) {
                Portfolio selectedPort = tab_portfolio.getSelectionModel().getSelectedItem();
                portfolio = selectedPort;
        if (selectedPort != null) {
            tftitre.setText(selectedPort.getTitre());
            tfdescription.setText(selectedPort.getDescription());


            String dateString = selectedPort.getDateP();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localdate = LocalDate.parse(dateString, formatter);
            tfdate.setValue(localdate);

            String secteur = selectedPort.getSecteur();
            comb.setValue(secteur);


        }
    }
            @FXML
    private void ModifierPortfolio(ActionEvent event) {
                           Portfolio selectedPort = tab_portfolio.getSelectionModel().getSelectedItem();
          
   if (selectedPort != null) {
             String titre =tftitre.getText();
          String description =tfdescription.getText();
          LocalDate date= tfdate.getValue();
            boolean isValid = validateFields(titre, description, date);
 if (isValid) {if (showConfirmationDialog("Confirmation", "Modifier ce portfolio!", "Voulez-vous modifier ce portfolio ?")) {
                    // Valider l'image

         selectedPort.setTitre(tftitre.getText());
        selectedPort.setDescription(tftitre.getText());
        
        
        LocalDate localDate = tfdate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = localDate.format(formatter);
        selectedPort.setDateP(dateString);

        String secteursAsString = comb.getSelectionModel().getSelectedItem();
        selectedPort.setSecteur(secteursAsString);

        
     
  
 if (selectedImageFile != null) {
            try {
                FileInputStream fis = new FileInputStream(selectedImageFile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = bos.toByteArray();

                // Convertir les bytes en une chaîne encodée en Base64
                String imageString = new String(Base64.getEncoder().encode(imageBytes), StandardCharsets.UTF_8);

                
                selectedPort.setImage(imageString);

               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       

        // Mettez à jour le portfolio dans la base de données
        PortfolioCRUD portc = new PortfolioCRUD();
        portc.modifierEntities(selectedPort);

        // Rafraîchissez le TableView pour refléter les modifications
        loadPortfolio();
        clearFields();
}
                
            }
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un portfolio à mettre à jour.", "");
        }
    }

    @FXML
    private void SuppPortfolio(ActionEvent event) {

        Portfolio selectedport = tab_portfolio.getSelectionModel().getSelectedItem();

        if (selectedport != null) {
            if (showConfirmationDialog("Confirmation", "Supprimer ce portfolio!", "Voulez-vous supprimer ce portfolio ?")) {
            PortfolioCRUD offreCRUD = new PortfolioCRUD();
            offreCRUD.supprimerEntities(selectedport);
            tab_portfolio.getItems().remove(selectedport);
            tab_portfolio.refresh();
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un portfolio à supprimer!", "");
        }
    }
    }

    private void GoToOffres(ActionEvent event) {
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


    @FXML
    private void CreateQrCode(ActionEvent event) {
           
        portfolio.setImage(null);
        String data=portfolio.toString();
        System.out.println(data);
        QrCode qc = new QrCode();
        try {
            Image qrCodeImage = qc.Qrcode(data);
            portfolio.setQrCodeImage(data);
           imgQr.setImage(qrCodeImage);
        } catch (Exception ex) {
            System.out.println("erreur qr");
        }
       
    }
}
    
    
    
    

    
    
    
    
    
    



    


    

