/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;

import java.util.Date;

public class Portfolio {
    private int id;
    private String titre;
    private String description;
    private String image;
    private String secteur;
    private String dateP;
    private String QrCodeImage;
    

    public Portfolio() {
    }
    
    public Portfolio(int id, String titre, String description, String image, String secteur, String dateP) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.secteur = secteur;
        this.dateP = dateP;
        
    }


        public Portfolio(String titre, String description, String image, String secteur, String dateP) {    
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.secteur = secteur;
        this.dateP = dateP;
        
    }

        public Portfolio(String titre, String description, String secteur, String dateP) {    
        this.titre = titre;
        this.description = description;
        this.secteur = secteur;
        this.dateP = dateP;
      
    }

    public Portfolio(String titre, String description, String dateP) {
        this.titre = titre;
        this.description = description;
        this.dateP = dateP;
   
    }
    public Portfolio(String QrCodeImage){
        this.QrCodeImage= QrCodeImage;
    }

    public String getQrCodeImage() {
        return QrCodeImage;
    }

    public void setQrCodeImage(String QrCodeImage) {
        this.QrCodeImage = QrCodeImage;
    }

 
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getDateP() {
        return dateP;
    }

    public void setDateP(String dateP) {
        this.dateP = dateP;
    }
    



    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", secteur='" + secteur + '\'' +
                ", date=" + dateP +
                
                '}';
    }
}

