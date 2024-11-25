package se.jobsesprit.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import se.jobsesprit.entities.confirmCovoiturage;
import se.jobsesprit.services.confirmCovoiturageCRUD;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.glxn.qrgen.QRCode;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;





public class Liste_de_ReservationController implements Initializable {
    
    @FXML
    private TableView<confirmCovoiturage> MF_res_tableView;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_firstName;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_lastName;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_Date;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_lieuDepart;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_lieuArrivee;
    @FXML
    private TableColumn<confirmCovoiturage, Integer> MF_coll_phone;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_email;
    @FXML
    private TableColumn<confirmCovoiturage, Integer> MF_coll_nbrPlacesReserve;
    @FXML
    private TableColumn<confirmCovoiturage, String> MF_coll_prictotal;
    @FXML
    private Label MF_Label_username_pub;
    @FXML
    private Label MF_Label_firstName_pub;
    @FXML
    private Label MF_Label_lastname_pub;
    @FXML
    private Label MF_Label_dateDepart_pub;
    @FXML
    private Label MF_Label_lieuDepart_pub;
    @FXML
    private Label MF_Label_lieuarivee_pub;
    @FXML
    private Label MF_Label_phone_pub;
    @FXML
    private Label MF_Label_email_pub;
    @FXML
    private Label MF_label_nbrPReserve_pub;
    @FXML
    private Label MF_Label_prixtotal_pub;
    @FXML
    private Button MF_imprimer_BT;
    @FXML
    private Button MF_confirmation_BT;
    
    private String  MailC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureTable();
        setMFusername();
        MF_res_tableView.setOnMouseClicked(this::handleTableViewDoubleClick);       
}
    private void handleTableViewDoubleClick(MouseEvent event) {
    if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
        confirmCovoiturage selectedReservation = MF_res_tableView.getSelectionModel().getSelectedItem();
        if (selectedReservation != null) {
            MF_Label_firstName_pub.setText(selectedReservation.getFirstNameEtud());
            MF_Label_lastname_pub.setText(selectedReservation.getLastNameEtud());
            MF_Label_dateDepart_pub.setText(selectedReservation.getHeureDepart());
            MF_Label_lieuDepart_pub.setText(selectedReservation.getLieuDepart());
            MF_Label_lieuarivee_pub.setText(selectedReservation.getLieuArrivee());
            MF_Label_phone_pub.setText(String.valueOf(selectedReservation.getPhoneEtud()));
            MF_Label_email_pub.setText(selectedReservation.getEmailEtud());
            MF_label_nbrPReserve_pub.setText(String.valueOf(selectedReservation.getNombrePlacesReserve()));
            MF_Label_prixtotal_pub.setText(selectedReservation.getPrixTotalePlacesReserve());
        }
    }
}
    
    
    
  
private void configureTable() {
    MF_coll_firstName.setCellValueFactory(new PropertyValueFactory<>("first_Name_Etud"));
    MF_coll_lastName.setCellValueFactory(new PropertyValueFactory<>("last_Name_Etud"));
    MF_coll_Date.setCellValueFactory(new PropertyValueFactory<>("heure_Depart"));
    MF_coll_lieuDepart.setCellValueFactory(new PropertyValueFactory<>("lieu_Depart"));
    MF_coll_lieuArrivee.setCellValueFactory(new PropertyValueFactory<>("lieu_Arrivee"));
    MF_coll_phone.setCellValueFactory(new PropertyValueFactory<>("phone_Etud"));
    MF_coll_email.setCellValueFactory(new PropertyValueFactory<>("email_Etud"));
    MF_coll_nbrPlacesReserve.setCellValueFactory(new PropertyValueFactory<>("nombre_Places_Reserve"));
    MF_coll_prictotal.setCellValueFactory(new PropertyValueFactory<>("prix_Totale_Places_Reserve"));
    try {
        // Load data from the database using your listeDesEntities1 method
        confirmCovoiturageCRUD crud = new confirmCovoiturageCRUD();
        ObservableList<confirmCovoiturage> reservations = crud.listeDesEntities1();
        // Set the items of your TableView
        MF_res_tableView.setItems(reservations);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void setMFusername() {
        String username = InscriptionController.getLoggedInUsername();
        MF_Label_username_pub.setText(username);
    }
   
    
    
 @FXML
    private void MF_imprimer_BT(ActionEvent event) throws WriterException {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setLineWidth(1f);

            // Couleur de la ligne
            contentStream.setStrokingColor(0.3f, 0.3f, 0.3f); // Gris foncé
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);

            float margin = 50;
            float yPosition = page.getMediaBox().getHeight() - margin;

            // Code pour ajouter les informations (à personnaliser)
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText("Nom du passager : " + MF_Label_firstName_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date de départ : " + MF_Label_dateDepart_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Lieu de départ : " + MF_Label_lieuDepart_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Lieu d'arrivée : " + MF_Label_lieuarivee_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Numéro de téléphone : " + MF_Label_phone_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Adresse e-mail : " + MF_Label_email_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Nombre de places réservées : " + MF_label_nbrPReserve_pub.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Coût total du covoiturage : " + MF_Label_prixtotal_pub.getText());
            contentStream.endText();

            // Générer le code QR avec les mêmes informations
            String qrText = "Nom du passager : " + MF_Label_firstName_pub.getText() + "\n"
                    + "Date de départ : " + MF_Label_dateDepart_pub.getText() + "\n"
                    + "Lieu de départ : " + MF_Label_lieuDepart_pub.getText() + "\n"
                    + "Lieu d'arrivée : " + MF_Label_lieuarivee_pub.getText() + "\n"
                    + "Numéro de téléphone : " + MF_Label_phone_pub.getText() + "\n"
                    + "Adresse e-mail : " + MF_Label_email_pub.getText() + "\n"
                    + "Nombre de places réservées : " + MF_label_nbrPReserve_pub.getText() + "\n"
                    + "Coût total du covoiturage : " + MF_Label_prixtotal_pub.getText();


ByteArrayOutputStream baos = new ByteArrayOutputStream();
MatrixToImageWriter.writeToStream(new QRCodeWriter().encode(qrText, BarcodeFormat.QR_CODE, 200, 200), "PNG", baos);
            // Convertir le code QR binaire en BufferedImage
            byte[] qrBytes = baos.toByteArray();
            InputStream in = new ByteArrayInputStream(qrBytes);
            BufferedImage qrImage = ImageIO.read(in);

            // Convertir BufferedImage en PDImageXObject
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, qrImage);

            // Ajouter le code QR au PDF
            contentStream.drawImage(pdImage, page.getMediaBox().getWidth() - margin - 200, yPosition - 170, 200, 200);

            // Ligne horizontale
            contentStream.moveTo(margin, yPosition - 420);
            contentStream.lineTo(page.getMediaBox().getWidth() - margin, yPosition - 420);
            contentStream.stroke();

            contentStream.close();

            String desktopPath = System.getProperty("user.home") + "/Desktop/";
            String pdfFilePath = Paths.get(desktopPath, "billet_covoiturage.pdf").toString();
            document.save(pdfFilePath);
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



@FXML
private void MF_confirmation_BT(ActionEvent event) {
    String recipientEmail = "masoud.ozel.5@gmail.com"; // Obtenir l'adresse e-mail à partir de MF_Label_email_pub

    // Créez une boîte de dialogue JavaFX pour saisir l'adresse e-mail
    TextInputDialog emailDialog = new TextInputDialog();
    emailDialog.setTitle("Connexion au compte e-mail");
    emailDialog.setHeaderText("Veuillez vous connecter à votre compte e-mail.");
    emailDialog.setContentText("Adresse e-mail:");

    Optional<String> emailResult = emailDialog.showAndWait();

    emailResult.ifPresent(username -> {
        // Créez une autre boîte de dialogue pour saisir le mot de passe
        TextInputDialog passwordDialog = new TextInputDialog();
        passwordDialog.setTitle("Connexion au compte e-mail");
        passwordDialog.setHeaderText("Veuillez vous connecter à votre compte e-mail.");
        passwordDialog.setContentText("Mot de passe:");

        Optional<String> passwordResult = passwordDialog.showAndWait();

        passwordResult.ifPresent(password -> {
            // Envoyer l'e-mail à recipientEmail en utilisant ces informations d'authentification

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

    // Session de messagerie avec l'authentification de l'utilisateur
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
            return new javax.mail.PasswordAuthentication(username, password);
                    
            
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject("Acceptation de votre demande de covoiturage");
                ////////////////////////////////////////////////////////////////////
                String nomMembre = MF_Label_firstName_pub.getText();
String nomFamilleMembre = MF_Label_lastname_pub.getText();
String dateDepart = MF_Label_dateDepart_pub.getText();
String lieuDepart = MF_Label_lieuDepart_pub.getText();
String lieuArrivee = MF_Label_lieuarivee_pub.getText();
String telephoneMembre = MF_Label_phone_pub.getText();
String emailMembre = MF_Label_email_pub.getText();
String nombrePlacesReservees = MF_label_nbrPReserve_pub.getText();
String coutTotalCovoiturage = MF_Label_prixtotal_pub.getText();

String messageText = "Nous sommes heureux de vous informer que votre demande de covoiturage a été acceptée.\n\n"
    + "Détails de la réservation :\n"
    + "Nom et prénom du membre : " + MF_Label_firstName_pub.getText() + " " + MF_Label_lastname_pub.getText() + "\n"
    + "Numéro de téléphone du membre : " + MF_Label_phone_pub.getText() + "\n"
    + "Date de départ : " + MF_Label_dateDepart_pub.getText() + "\n"
    + "Lieu de départ : " + MF_Label_lieuDepart_pub.getText() + "\n"
    + "Lieu d'arrivée : " + MF_Label_lieuarivee_pub.getText() + "\n"
    + "Nombre de places réservées : " + MF_label_nbrPReserve_pub.getText() + "\n"
    + "Coût total du covoiturage : " + MF_Label_prixtotal_pub.getText() + "\n\n"
    + "Nous avons hâte de vous retrouver pour ce covoiturage. Bon voyage!";
            message.setText(messageText);
            Transport.send(message);
///////////////////////////////////////////////////////////////////////////////////
                showAlert("E-mail envoyé", "L'e-mail a été envoyé avec succès.");
            } catch (MessagingException ex) {
                ex.printStackTrace();
                showAlert("Erreur", "Une erreur s'est produite lors de l'envoi de l'e-mail.");
            }
        });
    });
}

private void showAlert(String title, String content) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
}




}