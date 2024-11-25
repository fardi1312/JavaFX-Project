/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;

import com.sun.xml.internal.bind.v2.model.core.ID;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class Offre {
    private  int id;
    private String titre;
    private String description;
    private String typeStage; 
    private String secteurs;
    private String fonction;
    private String dateInscription;
    private String images;
    private int entrepriseId;
    
      private String nomEntreprise;
      private int like;
            private int dislike;

    // Getter methods for other properties (id, titre, description, etc.)...

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

 
    public Offre() {
    }

    public Offre(String titre, String description, String dateInscription, int entrepriseId) {
        this.titre = titre;
        this.description = description;
        this.dateInscription = dateInscription;
        this.entrepriseId = entrepriseId;
    }
            

    public Offre(int id, String titre, String description, String typeStage, String secteurs, String fonction, String dateInscription, String images, int entrepriseId) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.typeStage = typeStage;
        this.secteurs = secteurs;
        this.fonction = fonction;
        this.dateInscription = dateInscription;
        this.images = images;
        this.entrepriseId = entrepriseId;
    }

    public Offre(String titre, String description, String typeStage, String secteurs, String fonction, String dateInscription, String images, int entrepriseId) {
        this.titre = titre;
        this.description = description;
        this.typeStage = typeStage;
        this.secteurs = secteurs;
        this.fonction = fonction;
        this.dateInscription = dateInscription;
        this.images = images;
        this.entrepriseId = entrepriseId;
    }

    public Offre(String titre, String description, String typeStage, String secteurs, String fonction, String dateInscription, int entrepriseId) {
        this.titre = titre;
        this.description = description;
        this.typeStage = typeStage;
        this.secteurs = secteurs;
        this.fonction = fonction;
        this.dateInscription = dateInscription;
        this.entrepriseId = entrepriseId;
    }

    public Offre(String titre, String description, String dateInscription) {
        this.titre = titre;
        this.description = description;
        this.dateInscription = dateInscription;
    }

    public int getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(int entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }
    

    
    

    public int getId() {
        return id;
    }

    public Offre(String titre, String description) {
        this.titre = titre;
        this.description = description;
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

     private static final List<String> BAD_WORDS = Arrays.asList("fuck", "fucking", "sex","slut","cock","coook","slut","pute","kiss");

     private String repeatCharacter(char character, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(character);
        }
        return sb.toString();
    }

    public String getDescription() {
        String[] words = description.split("\\s+"); // Split description into words
        for (int i = 0; i < words.length; i++) {
            if (BAD_WORDS.contains(words[i].toLowerCase())) {
                // Replace bad word with asterisks of the same length
                words[i] = repeatCharacter('*', words[i].length());
            }
        }
        return String.join(" ", words); // Join words back into a sentence
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeStage() {
        return typeStage;
    }

    public void setTypeStage(String typeStage) {
        this.typeStage = typeStage;
    }

    public String getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(String secteurs) {
        this.secteurs = secteurs;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }
    

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", typeStage=" + typeStage + ", secteurs=" + secteurs + ", fonction=" + fonction + ", images=" + images + '}';
    }
public static TypeStage getTypeStageFromString(String typeString) {
        switch (typeString.toLowerCase()) {
            case "formation_humaine_et_s":
                return TypeStage.FORMATION_HUMAINE_ET_S;
            case "immersion_en_entreprise":
                return TypeStage.IMMERSION_EN_ENTREPRISE;
            case "stage_ingenieur":
                return TypeStage.STAGE_INGENIEUR;
            default:
                throw new IllegalArgumentException("Invalid TypeStage string: " + typeString);
        }
}

    public void setImage(String imageString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTypeStage(TypeStage typ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


public enum TypeStage {
    FORMATION_HUMAINE_ET_S,
    IMMERSION_EN_ENTREPRISE,
    STAGE_INGENIEUR
}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.titre);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.typeStage);
        hash = 67 * hash + Objects.hashCode(this.secteurs);
        hash = 67 * hash + Objects.hashCode(this.fonction);
        hash = 67 * hash + Objects.hashCode(this.dateInscription);
        hash = 67 * hash + Objects.hashCode(this.images);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.secteurs, other.secteurs)) {
            return false;
        }
        if (!Objects.equals(this.fonction, other.fonction)) {
            return false;
        }
        if (!Objects.equals(this.dateInscription, other.dateInscription)) {
            return false;
        }
        if (!Objects.equals(this.images, other.images)) {
            return false;
        }
        if (this.typeStage != other.typeStage) {
            return false;
        }
        return true;
    }




}