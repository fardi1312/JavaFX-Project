/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import se.jobsesprit.entities.CalendarActivity;
import se.jobsesprit.entities.Offre;
import se.jobsesprit.services.CalendarCrud;
import static se.jobsesprit.services.CalendarCrud.getCalendarActivitiesMonth;
import static se.jobsesprit.services.CalendarCrud.organizeEventsByYearMonthAndDay;
import se.jobsesprit.services.Mail;
        //List<CalendarActivity> events = C.getAllCalendarActivities();

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class CalenderController implements Initializable {
  
List<CalendarActivity> events =  CalendarCrud.getAllCalendarActivities();
Map<Integer, Map<Integer, Map<Integer, List<CalendarActivity>>>> eventsByYearAndMonth = organizeEventsByYearMonthAndDay(events);
   


private ZonedDateTime selectedDate;
    ZonedDateTime dateFocus;
    ZonedDateTime today;
private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    @FXML
    private FlowPane calendar;
    @FXML
    private Text year;
    @FXML
    private Text month;
  //List<String> credentials = afficherDialogueConnexionGmail();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
 


        setupCalendarClickEvent(formattedDate -> {
            showAddEventDialog(formattedDate);
       
    });
    }
  private Optional<Pair<String, String>> afficherDialogueConnexionGmail() {
    Dialog<Pair<String, String>> dialog = new Dialog<>();
    dialog.setTitle("Se connecter avec Gmail");

    ButtonType seConnecterButtonType = new ButtonType("Se connecter", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(seConnecterButtonType, ButtonType.CANCEL);

    // Créez des champs de texte pour l'email et le mot de passe
    TextField emailField = new TextField();
    emailField.setPromptText("Email");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Mot de passe");

    // Créez une disposition de grille pour les éléments du dialogue
    GridPane grid = new GridPane();
    grid.add(new Label("Email:"), 0, 0);
    grid.add(emailField, 1, 0);
    grid.add(new Label("Mot de passe:"), 0, 1);
    grid.add(passwordField, 1, 1);

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == seConnecterButtonType) {
            return new Pair<>(emailField.getText(), passwordField.getText());
        }
        return null;
    });

    return dialog.showAndWait();
}
private void showAddEventDialog(String formattedDate) {
    Optional<Pair<String, String>> credentialsResult = afficherDialogueConnexionGmail();

    if (credentialsResult.isPresent()) {
        Pair<String, String> credentials = credentialsResult.get();
        String email = credentials.getKey();
        String motDePasse = credentials.getValue();

        // Créer le TextField pour le nom du destinataire
        TextField nomTextField = new TextField();
        nomTextField.setPromptText("Nom");
        TextField destinataireTextField = new TextField();
        destinataireTextField.setPromptText("Destinataire");

        // Créer le ComboBox pour sélectionner l'heure
        ComboBox<String> heureComboBox = new ComboBox<>();
        heureComboBox.getItems().addAll("10-11", "11-12", "12-13");
        heureComboBox.setPromptText("Sélectionner l'heure");

        // Créer un conteneur GridPane pour les éléments du dialogue
        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Nom:"), 0, 0);
        gridPane.add(nomTextField, 1, 0);
        gridPane.add(new Label("Destinataire:"), 0, 1);
        gridPane.add(destinataireTextField, 1, 1);
        gridPane.add(new Label("Sélectionner l'heure:"), 0, 2);
        gridPane.add(heureComboBox, 1, 2);

        // Créer le dialogue personnalisé
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Ajouter un événement");
        dialog.getDialogPane().setContent(gridPane);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Attendre la réponse de l'utilisateur
        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                // L'utilisateur a cliqué sur le bouton OK
                String nomDestinataire = destinataireTextField.getText();
                String selectedHeure = heureComboBox.getValue();

                try {
                    // Utiliser l'email, le mot de passe, l'heure et le nom du destinataire comme nécessaire, par exemple :
                    Mail.envoyer(email, motDePasse, nomDestinataire);
                    
                    // Créer un nouvel objet newEvent avec les valeurs appropriées
                    CalendarActivity newEvent = new CalendarActivity(formattedDate, nomDestinataire, email, selectedHeure);
                   
                    // Ajouter newEvent à la base de données
                    CalendarCrud calendarCrud = new CalendarCrud();
                    calendarCrud.ajouterEntities(newEvent);
                    
                    // Actualiser l'affichage du calendrier
                    refreshCalendar();
                } catch (MessagingException ex) {
                    Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                // L'utilisateur a cliqué sur le bouton Annuler ou fermé le dialogue
                System.out.println("Opération annulée.");
            }
        });
    } else {
        System.out.println("Opération annulée ou champs vides.");
    }
}




   private Void setupCalendarClickEvent(Consumer<String> dateConsumer) {
    calendar.setOnMouseClicked(mouseEvent -> {
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();
        int colIndex = (int) (mouseEvent.getX() / (calendar.getWidth() / 7));
        int rowIndex = (int) (mouseEvent.getY() / (calendar.getHeight() / 6));
        int calculatedDate = (rowIndex * 7 + colIndex + 1) - dateOffset;

        int monthMaxDate = dateFocus.getMonth().length(dateFocus.toLocalDate().isLeapYear());

        if (calculatedDate >= 1 && calculatedDate <= monthMaxDate) {
            selectedDate = dateFocus.withDayOfMonth(calculatedDate);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
            String formattedDate = selectedDate.format(formatter);

            System.out.println("Selected Date: " + formattedDate);

            // Pass the formatted date to the consumer
            dateConsumer.accept(formattedDate);
        }
    });
    return null;
}

     @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
               drawCalendar();

    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
             drawCalendar();

    }

 private void showViewAllEventsDialog(StackPane stackPane, List<CalendarActivity> calendarActivities) {
    // Create a dialog for viewing all events
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("All Events");

    // Set the button type (Close)
    ButtonType closeButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(closeButton);

    // Create a ListView to display events
    ListView<String> eventsListView = new ListView<>();
    for (CalendarActivity activity : calendarActivities) {
        eventsListView.getItems().add(activity.getClientName() + ", " + activity.getDate());
    }

    // Set the content of the dialog pane
    dialog.getDialogPane().setContent(eventsListView);

    // Set dialog size (optional)
    dialog.setWidth(600);
    dialog.setHeight(500);

    // Show the dialog and wait for the user's response
    dialog.showAndWait();
}
 private Map<Integer, List<CalendarActivity>> createCalendarMap(Map<Integer, Map<Integer, List<CalendarActivity>>> eventsByYearAndMonth) {
    Map<Integer, List<CalendarActivity>> calendarActivityMap = new HashMap<>();

    // Iterate over the eventsByYearAndMonth map and reorganize the activities
    for (Map<Integer, List<CalendarActivity>> eventsByMonth : eventsByYearAndMonth.values()) {
        for (List<CalendarActivity> activities : eventsByMonth.values()) {
            for (CalendarActivity activity : activities) {
                // Trim the date string to remove leading and trailing spaces
                String trimmedDate = activity.getDate().trim();
                // Parse the trimmed date string to get the day of the month
                int dayOfMonth = LocalDate.parse(trimmedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).getDayOfMonth();

                // Add the activity to the corresponding day of the month
                calendarActivityMap.computeIfAbsent(dayOfMonth, k -> new ArrayList<>()).add(activity);
            }
        }
    }

    return calendarActivityMap;
}

private Map<Integer, List<CalendarActivity>> createDayToActivitiesMap(List<CalendarActivity> activities) {
    Map<Integer, List<CalendarActivity>> dayToActivitiesMap = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    for (CalendarActivity activity : activities) {
        try {
            String trimmedDate = activity.getDate().trim();
            LocalDate eventDate = LocalDate.parse(trimmedDate, formatter);
            int dayOfMonth = eventDate.getDayOfMonth();

            // Add the activity to the corresponding day of the month
            dayToActivitiesMap.computeIfAbsent(dayOfMonth, k -> new ArrayList<>()).add(activity);
        } catch (Exception e) {
            System.out.println("Error processing activity: " + activity.getDate());
            e.printStackTrace();
            // Handle the exception or continue processing other records
        }
    }

    return dayToActivitiesMap;
}



private void createCalendarActivity(List<CalendarActivity> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
    VBox calendarActivityBox = new VBox();
    for (CalendarActivity activity : calendarActivities) {
        Text text = new Text(activity.getClientName() + ", " + activity.getDate() + ',' + activity.getHeure() + ',' + activity.getMail());
        calendarActivityBox.getChildren().add(text);
        text.setOnMouseClicked((MouseEvent mouseEvent) -> {
            // When text is clicked, show the edit event dialog
            showEditEventDialog(stackPane, activity);
        });
    }
    calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
    calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
    calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
    calendarActivityBox.setStyle("-fx-background-color: GRAY");
    stackPane.getChildren().add(calendarActivityBox);
}
 private void showEditEventDialog(StackPane stackPane, CalendarActivity calendarActivity) {
    // Create a custom dialog for editing username, service number, and heure
    Dialog<CalendarActivity> dialog = new Dialog<>();
    dialog.setTitle("Edit Event");

    ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
   ButtonType deleteButton = new ButtonType("Delete", ButtonBar.ButtonData.APPLY);
    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(saveButton, deleteButton, cancelButton);

    // Create form fields and set their initial values to the existing event's details
    TextField clientNameField = new TextField(calendarActivity.getClientName());
    clientNameField.setPromptText("Client's Name");

    TextField serviceNumberField = new TextField(String.valueOf(calendarActivity.getMail()));
    serviceNumberField.setPromptText("Service Number");

    ComboBox<String> heureComboBox = new ComboBox<>();
    heureComboBox.getItems().addAll("10-11", "11-12", "12-13");
    heureComboBox.setPromptText(calendarActivity.getHeure());

    VBox content = new VBox();
    content.getChildren().addAll(
        new Label("Edit Client's Name:"),
        clientNameField,
        new Label("Edit  Candidature Mail:"),
        serviceNumberField,
        new Label("Edit Heure:"),
        heureComboBox
    );

    dialog.getDialogPane().setContent(content);

    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == saveButton) {
            // Get the updated details from the form fields
            String updatedClientName = clientNameField.getText();
            String updatedServiceNumber = serviceNumberField.getText();
            String selectedHeure = heureComboBox.getValue();

            // Update the existing event with the new details
            calendarActivity.setClientName(updatedClientName);
            calendarActivity.setMail(updatedServiceNumber);
            calendarActivity.setHeure(selectedHeure);

            // Call the updateEntities method to update the event in the database
            CalendarCrud calendarCrud = new CalendarCrud();
            calendarCrud.modifierEntities(calendarActivity);
refreshCalendar();
            return calendarActivity;
        }else if (dialogButton == deleteButton) {
    // Afficher une boîte de dialogue de confirmation
    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationDialog.setTitle("Confirmation de suppression");
    confirmationDialog.setHeaderText(null);
    confirmationDialog.setContentText("Voulez-vous vraiment supprimer cet événement?");

    // Affichez la boîte de dialogue de confirmation et attendez la réponse de l'utilisateur
    Optional<ButtonType> result = confirmationDialog.showAndWait();

    // Si l'utilisateur confirme la suppression, supprimez l'événement
    if (result.isPresent() && result.get() == ButtonType.OK) {
        CalendarCrud calendarCrud = new CalendarCrud();
        calendarCrud.supprimerEntities(calendarActivity);
        refreshCalendar();

        // Optional: Vous pouvez gérer une logique supplémentaire après la suppression si nécessaire
        System.out.println("Event deleted: " + calendarActivity.getClientName() + ", " + calendarActivity.getMail()+ ", " + calendarActivity.getHeure() + ", " + calendarActivity.getDate());
    }
} else if (dialogButton == cancelButton) {
            // Cancel button is clicked
            // Handle cancel action (if needed)
        }
        return null;
    });
    Optional<CalendarActivity> result = dialog.showAndWait();

    // Handle the result if needed
    result.ifPresent(updatedEvent -> {
        // Add your logic to handle the updated event if necessary
        System.out.println("Event updated: " + updatedEvent.getClientName() + ", " + updatedEvent.getMail()+ ", " + updatedEvent.getHeure() + ", " + updatedEvent.getDate());
    });
}
 private void refreshCalendar() {
    // Clear the existing content of the calendar
    calendar.getChildren().clear();
    
    // Redraw the calendar with the updated data
    drawCalendar();
}

 
    private void drawCalendar() {
    year.setText(String.valueOf(dateFocus.getYear()));
    month.setText(String.valueOf(dateFocus.getMonth()));

    double calendarWidth = calendar.getPrefWidth();
    double calendarHeight = calendar.getPrefHeight();
    double strokeWidth = 1;
    double spacingH = calendar.getHgap();
    double spacingV = calendar.getVgap();

    int monthMaxDate = dateFocus.getMonth().maxLength();
    // Check for leap year
    if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
        monthMaxDate = 28;
    }
    int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

   Map<Integer, List<CalendarActivity>> dayToActivitiesMap = createDayToActivitiesMap(events);

    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
            StackPane stackPane = new StackPane();
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(strokeWidth);
            double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
            double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
            rectangle.setWidth(rectangleWidth);
            rectangle.setHeight(rectangleHeight);
            stackPane.getChildren().add(rectangle);

            int calculatedDate = (j + 1) + (7 * i);
            if (calculatedDate > dateOffset) {
                int currentDate = calculatedDate - dateOffset;
                if (currentDate <= monthMaxDate) {
                    Text dateText = new Text(String.valueOf(currentDate));
                    double textTranslationY = -(rectangleHeight / 2) * 0.75;
                    dateText.setTranslateY(textTranslationY);
                    stackPane.getChildren().add(dateText);

                    // Get activities for the current day from the map
                    List<CalendarActivity> activitiesForDay = dayToActivitiesMap.getOrDefault(currentDate, Collections.emptyList());

                    createCalendarActivity(activitiesForDay, rectangleHeight, rectangleWidth, stackPane);

                   
                }
            }
            calendar.getChildren().add(stackPane);
        }
    }
}

    @FXML
    private void OB_backoffre(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("zza.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();}

  
 
}
    
    


 


