/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import se.jobsesprit.entities.Offre;
import se.jobsesprit.services.OffreCRUD;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class OffreEtudiantController implements Initializable {
 
int i = 0;
    @FXML
    private GridPane OB_gridetu;
ObservableList<Offre> myList2 = FXCollections.observableArrayList();
 

@Override
public void initialize(URL url, ResourceBundle rb) {
   myList2.addAll(OffreCRUD.listedesoffreuser());
    int colum = 0;
    int rows = 0;

    for (int i = 0; i < myList2.size(); i++) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Offre.fxml"));
            AnchorPane anchorpane = fxmlLoader.load();
            OffreController itemController = fxmlLoader.getController();
            Offre offre = myList2.get(i);
            itemController.setData(offre);

            if (colum == 3) {
                colum = 0;
                rows++;
            }

            OB_gridetu.add(anchorpane, colum, rows);
                        //grididhth
            OB_gridetu.setMinWidth(Region.USE_COMPUTED_SIZE);
            OB_gridetu.setPrefWidth(Region.USE_COMPUTED_SIZE);
            OB_gridetu.setMaxWidth(Region.USE_PREF_SIZE);

            //gridhheigh
            OB_gridetu.setMinHeight(Region.USE_COMPUTED_SIZE);
            OB_gridetu.setPrefHeight(Region.USE_COMPUTED_SIZE);
            OB_gridetu.setMaxHeight(Region.USE_PREF_SIZE);


            
            GridPane.setMargin(anchorpane, new Insets(10));
            colum++;
        } catch (IOException ex) {
            Logger.getLogger(OffreEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


    @FXML
    private void switchToSceneInsc(ActionEvent event) {
    }


  
  
    
}
