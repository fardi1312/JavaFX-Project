/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;

public class UserEntreprise {
    private int id;
    private int phone;
    private String role = "ENTREPRISE";
    private String email;
    private String motDePasse;
    private String nomEntreprise;
    private String localisation;
    private String image;

    public UserEntreprise() {
       
    }

    public UserEntreprise(int phone, String email, String motDePasse, String nomEntreprise, String localisation, String image) {
        this.phone = phone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nomEntreprise = nomEntreprise;
        this.localisation = localisation;
        this.image = image;
    }
    
    

    public UserEntreprise(int id, int phone, String email, String motDePasse, String nomEntreprise, String localisation, String image) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nomEntreprise = nomEntreprise;
        this.localisation = localisation;
        this.image = image;
        this.role = "ENTREPRISE"; // Vous pouvez définir le rôle par défaut ici ou laisser le constructeur sans ce paramètre
    }

    // Getters et Setters pour les champs
    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UserEntreprise{" + "id=" + id + ", phone=" + phone + ", role=" + role + ", email=" + email + ", motDePasse=" + motDePasse + ", nomEntreprise=" + nomEntreprise + ", localisation=" + localisation + ", image=" + image + '}';
    }
    
    
}
