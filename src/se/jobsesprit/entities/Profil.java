/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;

public class Profil {
    private int id;
    private String image;
    private String username;
    private String bio;
    private String description;
    private String formation;
    private String licences;
    private String competence;

    public Profil() {}
    
    public Profil(String image, String username, String bio, String description, String formation, String licences, String competence) {
        this.image = image;
        this.username = username;
        this.bio = bio;
        this.description = description;
        this.formation = formation;
        this.licences = licences;
        this.competence = competence;
    }

    public Profil(int id, String image, String username, String bio, String description, String formation, String licences, String competence) {
        this.id = id;
        this.image = image;
        this.username = username;
        this.bio = bio;
        this.description = description;
        this.formation = formation;
        this.licences = licences;
        this.competence = competence;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getLicences() {
        return licences;
    }

    public void setLicences(String licences) {
        this.licences = licences;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    @Override
    public String toString() {
        return "Profil{" + "id=" + id + ", image=" + image + ", username=" + username + ", bio=" + bio + ", description=" + description + ", formation=" + formation + ", licences=" + licences + ", competence=" + competence + '}';
    }
    
    
}
