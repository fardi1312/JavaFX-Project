package se.jobsesprit.gui;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.jobsesprit.utils.MyConnection;

public class testLogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger la vue FXML (le fichier FXML doit être dans le même répertoire que votre classe testLogin)
            Parent root = FXMLLoader.load(getClass().getResource("FXMLaffichage.fxml"));

            // Configurer la scène
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            // Configurer le titre de la fenêtre
            primaryStage.setTitle("Application de Connexion");

            // Afficher la fenêtre
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Établir la connexion à la base de données
        MyConnection.getInstance();

        launch(args);
    }
}
