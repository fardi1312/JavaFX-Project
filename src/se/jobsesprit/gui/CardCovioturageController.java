package se.jobsesprit.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Spinner;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import se.jobsesprit.entities.Covoiturage;

public class CardCovioturageController implements Initializable {

    @FXML
    private AnchorPane card_from;
    @FXML
    private Label MF_Label_username;
    @FXML
    private Label MF_Label_heureDepart;
    @FXML
    private Label MF_Label_lieuDepart;
    @FXML
    private Label MF_Label_lieuArrivee;
    @FXML
    private Label MF_Label_prix;
    @FXML
    private Label MF_Label_nbrPlaces;
    @FXML
    private ImageView MF_imageView;
    @FXML
    private Spinner<Integer> MF_nbr_places_reserve;
    @FXML
    private Button MF_interested_BT;

    private Covoiturage covoiturageData;
    private ListCovoiturageController listCovoiturageController;

    public void setDaata(Covoiturage covoiturageData) {
        this.covoiturageData = covoiturageData;

        MF_Label_username.setText(covoiturageData.getUsername());
        MF_Label_heureDepart.setText(covoiturageData.getHeureDepart());
        MF_Label_lieuDepart.setText(covoiturageData.getLieuDepart());
        MF_Label_lieuArrivee.setText(covoiturageData.getLieuArrivee());
        MF_Label_prix.setText(covoiturageData.getPrix());
        MF_Label_nbrPlaces.setText(String.valueOf(covoiturageData.getNombrePlacesDisponibles()));

        // Display image from file path
        if (covoiturageData.getImage() != null && !covoiturageData.getImage().isEmpty()) {
            Image image = new Image("file:" + covoiturageData.getImage());
            MF_imageView.setImage(image);
        } else {
            // Display default image or handle the absence of a file path
            // ...
        }

        int placesDisponibles = Integer.parseInt(MF_Label_nbrPlaces.getText());

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, placesDisponibles, 1);
        MF_nbr_places_reserve.setValueFactory(valueFactory);
    }

    public void setListCovoiturageController(ListCovoiturageController controller) {
        this.listCovoiturageController = controller;
    }

    public ListCovoiturageController getListCovoiturageController() {
        return listCovoiturageController;
    }

    public void updateSeats(int newAvailableSeats) {
        MF_Label_nbrPlaces.setText(String.valueOf(newAvailableSeats));
        covoiturageData.setNombrePlacesDisponibles(newAvailableSeats);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    @FXML
    private void MF_interested_BT(ActionEvent event) {
        int placesReserve = MF_nbr_places_reserve.getValue();

        try {
            int placesDisponibles = Integer.parseInt(MF_Label_nbrPlaces.getText());
            int newAvailableSeats = placesDisponibles - placesReserve;

            if (newAvailableSeats >= 0) {
                // Mettre à jour le nombre de places disponibles
                if (listCovoiturageController != null) {
                    listCovoiturageController.updateDataInListCovoiturageController(
                            covoiturageData.getUsername(),
                            covoiturageData.getHeureDepart(),
                            covoiturageData.getLieuDepart(),
                            covoiturageData.getLieuArrivee(),
                            covoiturageData.getPrix(),
                            String.valueOf(placesReserve),
                            String.valueOf(placesDisponibles)
                    );
                }
            } else {
                System.err.println("Pas suffisamment de places disponibles pour la réservation.");
            }
        } catch (NumberFormatException e) {
            System.err.println("La valeur de MF_Label_nbrPlaces n'est pas un entier valide.");
        }
    }
}
