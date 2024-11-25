/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;




public class confirmCovoiturage {
    
    
    private int id;
    private String usernameEtud;
    private String usernameConducteur;
    private String firstNameEtud;
    private String lastNameEtud;
    private String firstNameConducteur;
    private String lastNameConducteur;
    private int phoneEtud;
    private int phoneConducteur;
    private String emailEtud;
    private String emailConducteur;
    private String heureDepart;
    private String lieuDepart;
    private String lieuArrivee;
    private String prixTotalePlacesReserve;
    private int nombrePlacesReserve;

    public confirmCovoiturage() {
        // Constructeur par défaut
    }
    
    
    
    // Constructeur avec l'ID
    public confirmCovoiturage(int id, String usernameEtud, String usernameConducteur, String firstNameEtud,
                              String lastNameEtud, String firstNameConducteur, String lastNameConducteur,
                              int phoneEtud, int phoneConducteur, String emailEtud, String emailConducteur,
                              String heureDepart, String lieuDepart, String lieuArrivee, String prixTotalePlacesReserve,
                              int nombrePlacesReserve) 
    {
        this.id = id;
        this.usernameEtud = usernameEtud;
        this.usernameConducteur = usernameConducteur;
        this.firstNameEtud = firstNameEtud;
        this.lastNameEtud = lastNameEtud;
        this.firstNameConducteur = firstNameConducteur;
        this.lastNameConducteur = lastNameConducteur;
        this.phoneEtud = phoneEtud;
        this.phoneConducteur = phoneConducteur;
        this.emailEtud = emailEtud;
        this.emailConducteur = emailConducteur;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.prixTotalePlacesReserve = prixTotalePlacesReserve;
        this.nombrePlacesReserve = nombrePlacesReserve;
    }    
    
    // Constructeur sans le champ 'id'
    public confirmCovoiturage(String usernameEtud, String usernameConducteur, String firstNameEtud, String lastNameEtud,
                              String firstNameConducteur, String lastNameConducteur, int phoneEtud, int phoneConducteur,
                              String emailEtud, String emailConducteur, String heureDepart, String lieuDepart, String lieuArrivee,
                              String prixTotalePlacesReserve, int nombrePlacesReserve) 
    {
        this.usernameEtud = usernameEtud;
        this.usernameConducteur = usernameConducteur;
        this.firstNameEtud = firstNameEtud;
        this.lastNameEtud = lastNameEtud;
        this.firstNameConducteur = firstNameConducteur;
        this.lastNameConducteur = lastNameConducteur;
        this.phoneEtud = phoneEtud;
        this.phoneConducteur = phoneConducteur;
        this.emailEtud = emailEtud;
        this.emailConducteur = emailConducteur;
        this.heureDepart = heureDepart;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.prixTotalePlacesReserve = prixTotalePlacesReserve;
        this.nombrePlacesReserve = nombrePlacesReserve;
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsernameEtud() {
        return usernameEtud;
    }

    public void setUsernameEtud(String usernameEtud) {
        this.usernameEtud = usernameEtud;
    }

    public String getUsernameConducteur() {
        return usernameConducteur;
    }

    public void setUsernameConducteur(String usernameConducteur) {
        this.usernameConducteur = usernameConducteur;
    }

    public String getFirstNameEtud() {
        return firstNameEtud;
    }

    public void setFirstNameEtud(String firstNameEtud) {
        this.firstNameEtud = firstNameEtud;
    }

    public String getLastNameEtud() {
        return lastNameEtud;
    }

    public void setLastNameEtud(String lastNameEtud) {
        this.lastNameEtud = lastNameEtud;
    }

    public String getFirstNameConducteur() {
        return firstNameConducteur;
    }

    public void setFirstNameConducteur(String firstNameConducteur) {
        this.firstNameConducteur = firstNameConducteur;
    }

    public String getLastNameConducteur() {
        return lastNameConducteur;
    }

    public void setLastNameConducteur(String lastNameConducteur) {
        this.lastNameConducteur = lastNameConducteur;
    }

    public int getPhoneEtud() {
        return phoneEtud;
    }

    public void setPhoneEtud(int phoneEtud) {
        this.phoneEtud = phoneEtud;
    }

    public int getPhoneConducteur() {
        return phoneConducteur;
    }

    public void setPhoneConducteur(int phoneConducteur) {
        this.phoneConducteur = phoneConducteur;
    }

    public String getEmailEtud() {
        return emailEtud;
    }

    public void setEmailEtud(String emailEtud) {
        this.emailEtud = emailEtud;
    }

    public String getEmailConducteur() {
        return emailConducteur;
    }

    public void setEmailConducteur(String emailConducteur) {
        this.emailConducteur = emailConducteur;
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

    public String getPrixTotalePlacesReserve() {
        return prixTotalePlacesReserve;
    }

    public void setPrixTotalePlacesReserve(String prixTotalePlacesReserve) {
        this.prixTotalePlacesReserve = prixTotalePlacesReserve;
    }

    public int getNombrePlacesReserve() {
        return nombrePlacesReserve;
    }

    public void setNombrePlacesReserve(int nombrePlacesReserve) {
        this.nombrePlacesReserve = nombrePlacesReserve;
    }
    
    
    
    
    
}
