/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.jobsesprit.entities.Candidature;
import se.jobsesprit.entities.Offre;
import se.jobsesprit.entities.Portfolio;
import se.jobsesprit.entities.UserEntreprise;
import se.jobsesprit.services.CandidatureCRUD;
import se.jobsesprit.services.PortfolioCRUD;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class MesCandidatureController implements Initializable {

    @FXML
    private Button MF_Logout_BT;
    @FXML
    private AnchorPane MF_AnchorView_BT;
    @FXML
    private TableView<Candidature> table_cand;
    @FXML
    private TableColumn<Offre, String> off_titre;
    @FXML
    private TableColumn<UserEntreprise, String> entrep_nom;
    @FXML
    private TableColumn<Candidature, String> etud_cv;
    
    private int iddd=1;
    @FXML
    private Button MF_Offre_BT;
    @FXML
    private Button MT_Evnt_BT;
    @FXML
    private Button MF_Entretien_BT;
    @FXML
    private Button MF_Candidature_BT;
    @FXML
    private TableColumn<Offre, String> col_username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                off_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        entrep_nom.setCellValueFactory(new PropertyValueFactory<>("nomEntreprise"));
        etud_cv.setCellValueFactory(new PropertyValueFactory<>("cv"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));

        loadInCCandidature();
        
        
      
    } 
    
            private void loadInCCandidature() {
        CandidatureCRUD c = new CandidatureCRUD();
ObservableList<Candidature> cond = FXCollections.observableArrayList(CandidatureCRUD.getCandidatures());
        table_cand.setItems(cond);
    }
            @FXML
            private void MF_Logout_BT(ActionEvent event) {
            }

    @FXML
    private void MF_Offre_BT(ActionEvent event) {
    }

    @FXML
    private void MT_evnt_BT(ActionEvent event) {
    }

    @FXML
    private void MF_Entretien_BT(ActionEvent event) {
    }

    @FXML
    private void MF_Candidature_BT(ActionEvent event) {
    }

    
}
