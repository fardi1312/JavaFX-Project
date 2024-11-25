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
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
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
import se.jobsesprit.entities.Entreprise;
import se.jobsesprit.entities.Offre;
import se.jobsesprit.services.OffreCRUD;
import se.jobsesprit.services.UserEntrepriseCRUD;

public class OffreEntrepriseController implements Initializable {

    private ImageView quit;
    private Label menu;
    private Label menuback;
    private AnchorPane slider;

    @FXML
    private ImageView OB_imagesselcted;
    @FXML
    private TextField OB_titrefield;
    @FXML
    private TextField OB_function;
    @FXML
    private TextField OB_descfield;
    @FXML
    private DatePicker OB_datefield;
    @FXML
    private Button OB_offrefield;
    @FXML
    private ComboBox<String> combo1;
    @FXML
    private ComboBox<String> combo2;
    private File selectedImageFile;
    @FXML
    private TableView<Offre> OBtableView;
    @FXML
    private TableColumn<Offre, String> titre;
    @FXML
    private TableColumn<Offre, String> desc;
    @FXML
    private TableColumn<Offre, String> type;
    @FXML
    private TableColumn<Offre, String> date;
    @FXML
    private TableColumn<Offre, String> fonction;
    @FXML
    private TableColumn<Offre, String> secteur;
    @FXML
    private TableColumn<Offre, String> image;
    
   int identreprise=InscriptionController.getLoggedID();
    ObservableList<Offre> offreList = FXCollections.observableArrayList();
    Entreprise entreprisecnnecter = new Entreprise();
    @FXML
    private Button OB_savepersonn;
    @FXML
    private Button updateoffre;
    @FXML
    private Button OB_delete;


  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("FORMATION_HUMAINE_ET_S", "IMMERSION_EN_ENTREPRISE", "STAGE_INGENIEUR");
        ObservableList<String> list1 = FXCollections.observableArrayList("Tech", "agroalimentaire", "industrie", "secteur bancaire", "immobilier", "autre");
        combo1.setItems(list);
        combo2.setItems(list1);

        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        type.setCellValueFactory(new PropertyValueFactory<>("typeStage"));
        secteur.setCellValueFactory(new PropertyValueFactory<>("secteurs"));
        fonction.setCellValueFactory(new PropertyValueFactory<>("fonction"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateInscription"));
        image.setCellValueFactory(new PropertyValueFactory<>("images"));
      

        loadoffre();
    }

 
   private void loadoffre() {
        OffreCRUD offreCRUD = new OffreCRUD();
       
ObservableList<Offre> offres=FXCollections.observableArrayList(offreCRUD.listeDesEntities1());
OBtableView.setItems(offres);
    }
   
  

   
   
   
   
   
    @FXML
    private void OB_reload(ActionEvent event) {
        loadoffre();
    }

    private boolean showConfirmationDialog(String title, String headerText, String contentText) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(headerText);
        confirmationDialog.setContentText(contentText);
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
   private void updateoffre(ActionEvent event) {
          Offre selectedPersonne = OBtableView.getSelectionModel().getSelectedItem();
          
   if (selectedPersonne != null) {
             String tit =OB_titrefield.getText();
          String des =OB_descfield.getText();
          LocalDate date= OB_datefield.getValue();
          
   boolean isValid = validateFields(tit, des, date);
 if (isValid) {if (showConfirmationDialog("Confirmation", "Mettre à jour l'offre", "Voulez-vous mettre à jour cet offre ?")) {
                    // Valider l'image
                    if (selectedImageFile == null) {
                        showAlert("Erreur", "Image manquante", "Veuillez sélectionner une image.");
                    } else {
         selectedPersonne.setTitre(OB_titrefield.getText());
        selectedPersonne.setDescription(OB_descfield.getText());
        
        
        LocalDate localDate = OB_datefield.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateString = localDate.format(formatter);
        selectedPersonne.setDateInscription(dateString);
String secteursAsString = combo2.getSelectionModel().getSelectedItem().toString();
        selectedPersonne.setSecteurs(secteursAsString);
         String selectedType = combo1.getSelectionModel().getSelectedItem().toString();
        selectedPersonne.setTypeStage(selectedType);
        
     
  
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

                
                selectedPersonne.setImages(imageString);

               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       

        // Mettez à jour la personne dans la base de données
        OffreCRUD personneCRUD = new OffreCRUD();
        personneCRUD.modifierEntities(selectedPersonne);

        // Rafraîchissez le TreeView pour refléter les modifications
        OBtableView.refresh();
        clearFields();

   
        
             }
                }
            }
        } else {
            showAlert("Aucune sélection", "Veuillez sélectionner un covoiturage à mettre à jour.", "");
        }
    }


    @FXML
    private void OB_select(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog((Window) OB_offrefield.getScene().getWindow());

        if (selectedImageFile != null) {
            // Chargez l'image dans l'ImageView
            Image image = new Image(selectedImageFile.toURI().toString());
            OB_imagesselcted.setImage(image);
        }
    }

     

  /*  @FXML
    private void OB_savepersonn(ActionEvent event) {
       String titre = OB_titrefield.getText();
        String description = OB_descfield.getText();
      
        
       String selectedType = (String )combo1.getSelectionModel().getSelectedItem();
        String secteurs  = (String )combo2.getSelectionModel().getSelectedItem();
        
        
        String function = OB_function.getText();
        LocalDate localDate =OB_datefield.getValue();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String dateString = localDate.format(formatter);


        boolean isValid = validateFields(titre,description,localDate);
 if (isValid) {
            if (showConfirmationDialog("Confirmation", "Enregistrer l'offre", "Voulez-vous enregistrer cet offre ?")) {
             Offre off=new Offre(titre, description, dateString,1);
         
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

              
                String imageString = new String(Base64.getEncoder().encode(imageBytes), StandardCharsets.UTF_8);

                
                off.setImages(imageString);

                
              } catch (IOException e) {
                e.printStackTrace();
            }
        }
                      OffreCRUD offreCRUD = new OffreCRUD();
                offreCRUD.ajouterEntities(off);
               

                
               
            }
        }
    }*/
     private boolean validateFields(String titre, String descreption, LocalDate date) {
    boolean isValid = true;

    if (titre.isEmpty()) {
        showAlert("Champ requis", "Titre est requis", "Veuillez saisir le titre.");
        isValid = false;
    } else if (descreption.isEmpty()) {
        showAlert("Champ requis", "Description est requis", "Veuillez saisir la description.");
        isValid = false;
    } else if (date == null || date.isBefore(LocalDate.now())) {
        showAlert("Date invalide", "Date doit être après aujourd'hui", "Veuillez choisir une date future.");
        isValid = false;
    } 

    return isValid;
}

    @FXML
    private void OB_delete(ActionEvent event) {
        Offre selectedOffre = OBtableView.getSelectionModel().getSelectedItem();

        if (selectedOffre != null) {
            OffreCRUD offreCRUD = new OffreCRUD();
            offreCRUD.supprimerEntities(selectedOffre);
            OBtableView.getItems().remove(selectedOffre);
            OB_reload(event);
        } else {
            System.out.println("No Offre selected.");
        }
    }

    @FXML
    private void rowClicked(MouseEvent event) {
        Offre selectedPersonne = OBtableView.getSelectionModel().getSelectedItem();
        if (selectedPersonne != null) {
            OB_titrefield.setText(selectedPersonne.getTitre());
            OB_descfield.setText(selectedPersonne.getDescription());
            OB_function.setText(selectedPersonne.getFonction());

            String dateString = selectedPersonne.getDateInscription(); // Supposez que getDate() renvoie la date en tant que chaîne
 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
            OB_datefield.setValue(date);
          String secteursAsString = selectedPersonne.getSecteurs();
     combo2.setValue(secteursAsString);
 

            String TypeAsString = selectedPersonne.getTypeStage();
            combo1.setValue(TypeAsString);

            String imageString = selectedPersonne.getImages();
            if (imageString != null && !imageString.isEmpty()) {
                byte[] imageBytes = Base64.getDecoder().decode(imageString);
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                OB_imagesselcted.setImage(image);
            } else {
                OB_imagesselcted.setImage(null);
            }
        }
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void clearFields() {
        OB_descfield.clear();
        OB_titrefield.clear();
        OB_datefield.setValue(null);
        OB_imagesselcted.setImage(null);
        OB_function.clear();
    }

//    private void gotoentretien(ActionEvent event) throws IOException {
//        
//          Parent root = FXMLLoader.load(getClass().getResource("entretien.fxml"));
//    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//    Scene scene = new Scene(root);
//    stage.setScene(scene);
//    stage.show();
//    }

@FXML
private void OB_savepersonn(ActionEvent event) {
    String titre = OB_titrefield.getText();
    String desc = OB_descfield.getText();
    LocalDate date = OB_datefield.getValue();

    // Validation des champs
    if (!validateFields(titre, desc, date)) {
        return;
    }

    String imageString = null;// Initialize the imageString variable outside the try block

    if (selectedImageFile != null) {
        try (FileInputStream fis = new FileInputStream(selectedImageFile);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            // Convert image to Base64 string
            imageString = Base64.getEncoder().encodeToString(bos.toByteArray());

        } catch (IOException e) {
            // Handle the IOException appropriately, e.g., show an error message
            e.printStackTrace();
            // You might want to return or display an error message here
            return;
        }
    }

    String datee = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

    Offre off = new Offre(titre, desc, datee, identreprise);

    if (imageString != null) {
        off.setImage(imageString);
    }

    OffreCRUD covoiturageCRUD = new OffreCRUD();
    covoiturageCRUD.ajouterEntities(off);
loadoffre();
//reloadZzaScene();
//    clearFields();
   
}



}

