package se.jobsesprit.entities;

import java.time.LocalDate;
import javafx.scene.image.Image;

public class Covoiturage {
    private int id;
    private int id_user_etudiant_id;
    private String heureDepart;
    private String lieuDepart;
    private String lieuArrivee;
    private String prix;
    private int nombrePlacesDisponibles;
    private String image;
    private String username; 

    public Covoiturage() {
    }

    public Covoiturage(int id, int id_user_etudiant_id, String heureDepart, String lieuDepart, String lieuArrivee, String prix, int nombrePlacesDisponibles, String image, String username) {
        this.id = id;
        this.id_user_etudiant_id = id_user_etudiant_id;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.prix = prix;
        this.nombrePlacesDisponibles = nombrePlacesDisponibles;
        this.image = image;
        this.username = username;
    }

    public Covoiturage(int id_user_etudiant_id, String heureDepart, String lieuDepart, String lieuArrivee, String prix, int nombrePlacesDisponibles, String image, String username) {
        this.id_user_etudiant_id = id_user_etudiant_id;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.prix = prix;
        this.nombrePlacesDisponibles = nombrePlacesDisponibles;
        this.image = image;
        this.username = username;
    }

    public Covoiturage(String heureDepart, String lieuDepart, String lieuArrivee, String prix, int nombrePlacesDisponibles, String username, int id_user_etudiant_id) {
        this.id_user_etudiant_id = id_user_etudiant_id;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.prix = prix;
        this.nombrePlacesDisponibles = nombrePlacesDisponibles;
        this.username = username;
    }



    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user_etudiant_id() {
        return id_user_etudiant_id;
    }

    public void setId_user_etudiant_id(int id_user_etudiant_id) {
        this.id_user_etudiant_id = id_user_etudiant_id;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getNombrePlacesDisponibles() {
        return nombrePlacesDisponibles;
    }

    public void setNombrePlacesDisponibles(int nombrePlacesDisponibles) {
        this.nombrePlacesDisponibles = nombrePlacesDisponibles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    
    
    
}
