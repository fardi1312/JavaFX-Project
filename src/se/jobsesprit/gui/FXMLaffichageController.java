package se.jobsesprit.gui;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import se.jobsesprit.entities.Covoiturage;
import static se.jobsesprit.gui.InscriptionController.getLoggedID;
import se.jobsesprit.services.CovoiturageCRUD;
import se.jobsesprit.utils.MyConnection;

public class FXMLaffichageController implements Initializable {

    @FXML
    private TextField MFlieuArrivee;
    @FXML
    private TextField MFlieuDepart;
    @FXML
    private Spinner<Integer> MFnombrePlacesDisponibles;
    @FXML
    private DatePicker MFheureDepart;
    @FXML
    private Button MFselectImageBT;
    @FXML
    private TextField MFprix;
    @FXML
    private Button MFsaveCovoiturageBT;
    @FXML
    private ImageView MFimageView;

    private File selectedImageFile;
    @FXML
    private Button MFupdateCovoiturageBT;
    @FXML
    private Button MFdeleteCovoiturageBT;

    @FXML
    private TableView<Covoiturage> MFtableView;
    @FXML
    private TableColumn<Covoiturage, String> colDateDepart;
    @FXML
    private TableColumn<Covoiturage, String> colLieuDepart;
    @FXML
    private TableColumn<Covoiturage, String> colLieuArrivee;
    @FXML
    private TableColumn<Covoiturage, Integer> colPrix;
    @FXML
    private TableColumn<Covoiturage, String> colNbrPdispo;
    @FXML
    private TableColumn<Covoiturage, String> colUsername;
    @FXML
    private Button MFreloadCovoiturageBT;
    @FXML
    
    
    private TextField MFusername;
    
    
        int id_useretudiantCNX = InscriptionController.getLoggedID();

    
@Override
public void initialize(URL url, ResourceBundle rb) {
    setMFusername();
    MFusername.setEditable(false);
    // Configuration for the spinner
    SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
    MFnombrePlacesDisponibles.setValueFactory(valueFactory);
    valueFactory.setValue(1);

    // Configure the table columns
    colDateDepart.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
    colLieuDepart.setCellValueFactory(new PropertyValueFactory<>("lieuDepart"));
    colLieuArrivee.setCellValueFactory(new PropertyValueFactory<>("lieuArrivee"));
    colPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
    colNbrPdispo.setCellValueFactory(new PropertyValueFactory<>("nombrePlacesDisponible"));
    colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

    loadCovoiturages();
}

private void loadCovoiturages() {
    String username = InscriptionController.getLoggedInUsername();
            int id_useretudiantCNX = InscriptionController.getLoggedID();

    CovoiturageCRUD covoiturageCRUD = new CovoiturageCRUD();
    ObservableList<Covoiturage> covoiturages = FXCollections.observableArrayList(covoiturageCRUD.listeDesEntities1());
    
    // Créez une liste pour stocker les covoiturages filtrés
    ObservableList<Covoiturage> filteredCovoiturages = FXCollections.observableArrayList();

    for (Covoiturage covoiturage : covoiturages) {
        if (covoiturage.getUsername().equals(username)) {
            filteredCovoiturages.add(covoiturage);
        }
    }

    MFtableView.setItems(filteredCovoiturages);
}


    @FXML
    private void MFreloadCovoiturageBT(ActionEvent event) {
        loadCovoiturages();
    }
    
    
@FXML
private void MFsaveCovoiturageBT(ActionEvent event) {
    String lieuArrivee = MFlieuArrivee.getText();
    String lieuDepart = MFlieuDepart.getText();
    LocalDate heureDepart = MFheureDepart.getValue();
    String prix = MFprix.getText();
    int nombrePlacesDisponibles = MFnombrePlacesDisponibles.getValue();
    String username = MFusername.getText();
//////////////////////////////////////////////////////
    int id_user_etudiant_id = getLoggedID();
/////////////////////////////////////////////////
    boolean isValid = validateFields(lieuArrivee, lieuDepart, heureDepart, prix, nombrePlacesDisponibles, username);

    if (isValid) {
        if (isImageSelected()) {
            if (showConfirmationDialog("Confirmation", "Enregistrer le covoiturage", "Voulez-vous enregistrer ce covoiturage ?")) {
                String heureDepartStr = heureDepart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Covoiturage covoiturage = new Covoiturage(heureDepartStr, lieuDepart, lieuArrivee, prix, nombrePlacesDisponibles, username, id_user_etudiant_id);

                try {
                    Connection connection = MyConnection.getInstance().getCnx();
                    String insertQuery = "INSERT INTO Covoiturage (heureDepart, lieuDepart, lieuArrivee, prix, nombrePlacesDisponible, username, image, id_user_etudiant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, covoiturage.getHeureDepart());
                    preparedStatement.setString(2, covoiturage.getLieuDepart());
                    preparedStatement.setString(3, covoiturage.getLieuArrivee());
                    preparedStatement.setString(4, covoiturage.getPrix());
                    preparedStatement.setInt(5, covoiturage.getNombrePlacesDisponibles());
                    preparedStatement.setString(6, covoiturage.getUsername());

                    // Convert and set the image path
                    String imagePath = selectedImageFile.toPath().toString();
                    preparedStatement.setString(7, imagePath);
                        displayImage(imagePath);


                    // Set id_useretudiant at position 8
                    preparedStatement.setInt(8, covoiturage.getId_user_etudiant_id());

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                clearFields();
                loadCovoiturages();
            }
        } else {
            showAlert("Image manquante", "Veuillez sélectionner une image pour le covoiturage.", "");
        }
    }
}

private void displayImage(String imagePath) {
    if (imagePath != null && !imagePath.isEmpty()) {
        Image image = new Image("file:" + imagePath);
        MFimageView.setImage(image);
    }
}


@FXML
private void MFselectImageBT(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png", "*.gif"));
    selectedImageFile = fileChooser.showOpenDialog((Window) MFselectImageBT.getScene().getWindow());
    if (selectedImageFile != null) {
        Image image = new Image(selectedImageFile.toURI().toString());
        MFimageView.setImage(image);
    }
}


@FXML
private void MFupdateCovoiturageBT(ActionEvent event) {
    Covoiturage selectedCovoiturage = MFtableView.getSelectionModel().getSelectedItem();

    if (selectedCovoiturage != null) {
        String lieuArrivee = MFlieuArrivee.getText();
        String lieuDepart = MFlieuDepart.getText();
        LocalDate heureDepart = MFheureDepart.getValue();
        String prix = MFprix.getText();
        int nombrePlacesDisponibles = MFnombrePlacesDisponibles.getValue();
        String username = MFusername.getText();

        boolean isValid = validateFields(lieuArrivee, lieuDepart, heureDepart, prix, nombrePlacesDisponibles, username);

        if (isValid) {
            if (showConfirmationDialog("Confirmation", "Mettre à jour le covoiturage", "Voulez-vous mettre à jour ce covoiturage ?")) {
                String heureDepartStr = heureDepart.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                selectedCovoiturage.setLieuArrivee(lieuArrivee);
                selectedCovoiturage.setLieuDepart(lieuDepart);
                selectedCovoiturage.setHeureDepart(heureDepartStr);
                selectedCovoiturage.setPrix(prix);
                selectedCovoiturage.setNombrePlacesDisponibles(nombrePlacesDisponibles);
                selectedCovoiturage.setUsername(username);

                // Check if a new image is selected
                if (isImageSelected()) {
                    // Convert and set the image path
                    String imagePath = selectedImageFile.toPath().toString();
                    selectedCovoiturage.setImage(imagePath);
                }

                // Update the covoiturage
                CovoiturageCRUD covoiturageCRUD = new CovoiturageCRUD();
                covoiturageCRUD.modifierEntities(selectedCovoiturage);

                clearFields();
                loadCovoiturages();
            }
        }
    } else {
        showAlert("Aucune sélection", "Veuillez sélectionner un covoiturage à mettre à jour.", "");
    }
}


    @FXML
    private void MFdeleteCovoiturageBT(ActionEvent event) {
        Covoiturage selectedCovoiturage = MFtableView.getSelectionModel().getSelectedItem();

        if (selectedCovoiturage != null) {
            if (showConfirmationDialog("Confirmation", "Supprimer le covoiturage", "Voulez-vous supprimer ce covoiturage ?")) {
                CovoiturageCRUD covoiturageCRUD = new CovoiturageCRUD();
                covoiturageCRUD.supprimerEntities(selectedCovoiturage);
                MFtableView.getItems().remove(selectedCovoiturage);
            }
        }
    }

  @FXML
private void rowClicked(javafx.scene.input.MouseEvent event) {
    Covoiturage selectedCovoiturage = MFtableView.getSelectionModel().getSelectedItem();
    MFlieuArrivee.setText(selectedCovoiturage.getLieuArrivee());
    MFlieuDepart.setText(selectedCovoiturage.getLieuDepart());
    MFheureDepart.setValue(LocalDate.parse(selectedCovoiturage.getHeureDepart(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    MFprix.setText(selectedCovoiturage.getPrix());
    MFnombrePlacesDisponibles.getValueFactory().setValue(selectedCovoiturage.getNombrePlacesDisponibles());
    MFusername.setText(selectedCovoiturage.getUsername());

    String imagePath = selectedCovoiturage.getImage();
    if (imagePath != null && !imagePath.isEmpty()) {
        try {
            // Create an Image object from the file path
            Image image = new Image(new FileInputStream(imagePath));
            MFimageView.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while loading the image.", "");
        }
    } else {
        MFimageView.setImage(null);
    }
}


    private boolean validateFields(String lieuArrivee, String lieuDepart, LocalDate heureDepart, String prix, int nombrePlacesDisponibles, String username) {
        boolean isValid = true;

       if (lieuArrivee.isEmpty()) {
            showAlert("Champ requis", "Lieu d'arrivée est requis", "Veuillez saisir le lieu d'arrivée.");
            isValid = false;
        } else if (lieuDepart.isEmpty()) {
            showAlert("Champ requis", "Lieu de départ est requis", "Veuillez saisir le lieu de départ.");
            isValid = false;
        } else if (nombrePlacesDisponibles < 1 || nombrePlacesDisponibles > 10) {
            showAlert("Nombre de places invalide", "Le nombre de places doit être compris entre 1 et 10.", "");
            isValid = false;
        } else if (heureDepart == null) {
            showAlert("Champ requis", "Heure de départ est requise", "Veuillez sélectionner l'heure de départ.");
            isValid = false;
        } else if (username.isEmpty()) {
            showAlert("Champ requis", "Username est requis", "Veuillez saisir un nom d'utilisateur.");
            isValid = false;
        } else {
            LocalDate currentDate = LocalDate.now();
            LocalDate maxDate = currentDate.plusDays(7);
            if (heureDepart.isBefore(currentDate) || heureDepart.isAfter(maxDate)) {
                showAlert("Heure de départ invalide", "L'heure de départ doit être comprise entre aujourd'hui et une semaine à partir de la date actuelle.", "");
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean isImageSelected() {
        return selectedImageFile != null;
    }

    private void showAlert(String title, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void clearFields() {
        MFlieuArrivee.clear();
        MFlieuDepart.clear();
        MFheureDepart.setValue(null);
        MFprix.clear();
        MFnombrePlacesDisponibles.getValueFactory().setValue(1);
        MFimageView.setImage(null);
        MFusername.clear();
    }

    private boolean showConfirmationDialog(String title, String headerText, String contentText) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(headerText);
        confirmationDialog.setContentText(contentText);
        Optional<ButtonType> result = confirmationDialog.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
    
    
    
////////////////////////////Login_MASSOUD///////////////////////////////////////
///////////////////////////////////////////////////////////////////
    private void setMFusername() {
        // Récupérer le nom d'utilisateur depuis le contrôleur LoginController
        String username = InscriptionController.getLoggedInUsername();
        MFusername.setText(username);
    }     
////////////////////////////Login_MASSOUD///////////////////////////////////////
///////////////////////////////////////////////////////////////////
}
