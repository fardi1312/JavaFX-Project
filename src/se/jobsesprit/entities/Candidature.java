/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;

import java.io.File;

public class Candidature {
    private int id;
    private String username;
    private String cv;
    private int idEtudiant;
    private int idOffre;
    private int entrepriseId;
      private String nomEntreprise;
      private String titre;

 

    public Candidature() {}

    public Candidature(String username, String cv, int idEtudiant,int idOffre) {
        this.username = username;
        this.cv = cv;
         this.idEtudiant = idEtudiant;
        this.idOffre = idOffre;
    }

    public int getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(int entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Candidature(int entrepriseId, String nomEntreprise, String titre) {
        this.entrepriseId = entrepriseId;
        this.nomEntreprise = nomEntreprise;
        this.titre = titre;
    }
        public Candidature(String username, String cv) {
        this.username = username;
        this.cv = cv;

    }

     
    public Candidature(int id, String username, String cv, int idEtudiant,int idOffre) {
        this.id = id;
        this.username = username;
        this.cv = cv;
        this.idEtudiant = idEtudiant;
        this.idOffre = idOffre;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Candidature{" + "id=" + id + ", username=" + username + ", cv=" + cv + ",idEtduiant=" + idEtudiant+ ",idOffre=" + idOffre+'}';
    }
    
    
}
