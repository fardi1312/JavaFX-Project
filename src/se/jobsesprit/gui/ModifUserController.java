/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import se.jobsesprit.entities.UserEtudiant;
import se.jobsesprit.services.UserEtudiantCRUD;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifUserController implements Initializable {

    @FXML
    private Button btimage;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tflocation;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfphone;
    @FXML
    private TextField password;
    @FXML
    private TextField conf_pass;
    @FXML
    private Button btvalider;
    @FXML
    private ImageView imageview;
    
    
     Boolean test = false;
     File file;
     UserEtudiantCRUD ue = new UserEtudiantCRUD();
     String img;
    @FXML
    private TextField tflastname;
    @FXML
    private TextField username;
    
    
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
    }    
    
    
    
    
    @FXML
    private void importerimage(ActionEvent event) {
    }

    @FXML
    private void valider(ActionEvent event) throws FileNotFoundException, IOException {
        
        UserEtudiant u = new UserEtudiant();
        if (password.getText().equals("") || tfname.getText().equals("") || tflastname.getText().equals("") || tfemail.getText().equals("") || tfphone.getText().equals("") || conf_pass.getText().equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        } else if (tfemail.getText().matches("(.*)@(.*)") == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        } else if (tfphone.getText().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid numero");
            alert.show();

        } else {
            u.setFirstName(tfname.getText());
            u.setLastName(tflastname.getText());
            u.setEmail(tfemail.getText());
            u.setPhone(Integer.valueOf(tfphone.getText()));
            u.setAge(0);
            u.setRate(0);
            u.setUsername(username.getText());
            u.setMotDePasse(password.getText());
            u.setRole("ETUDIANT");
            u.setId(0);
            if (test == true) {
                FileInputStream fl = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                img = file.getName();
                fl.read(data);
                fl.close();
                System.out.println("new" + img);
            }
            System.out.println("personne Modifier avec succes");
            System.out.println(u);
            u.setImage(img);
            ue.modifierEntities(u);
            
            
        }
    }
    
    
    private File AjouterImage(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        test = true;
        return file;

    }
    
}
