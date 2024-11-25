package se.jobsesprit.entities;

import java.util.List;

public class Entreprise {
    private int id;
    private String image;
    private String bio;
    private String description;
    private String secteur;
    private String specialisation;
    private int nombreEmployes;


    public Entreprise() {}
        
    public Entreprise(String image, String bio, String description, String secteur, String specialisation, int nombreEmployes) {
        this.image = image;
        this.bio = bio;
        this.description = description;
        this.secteur = secteur;
        this.specialisation = specialisation;
        this.nombreEmployes = nombreEmployes;
    }
      
    public Entreprise(int id, String image, String bio, String description, String secteur, String specialisation, int nombreEmployes) {
        this.id = id;
        this.image = image;
        this.bio = bio;
        this.description = description;
        this.secteur = secteur;
        this.specialisation = specialisation;
        this.nombreEmployes = nombreEmployes;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getBio() {
        return bio;
    }

    

   
    

    public String getDescription() {
        return description;
    }

    public String getSecteur() {
        return secteur;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public void setNombreEmployes(int nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }
    

    @Override
    public String toString() {
        return "Entreprise{" + "id=" + id + ", image=" + image + ", bio=" + bio + ", description=" + description + ", secteur=" + secteur + ", specialisation=" + specialisation + ", nombreEmployes=" + nombreEmployes + '}';
    }
    
    
}

