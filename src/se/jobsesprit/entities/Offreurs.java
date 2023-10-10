package se.jobsesprit.entities;

public class Offreurs {

    private int id;
    private String nom;
    private String prenom;
    private String dateInscription; // Change the type to String
    private byte[] image; // Ajout de l'attribut image de type String

    public Offreurs() {
    }

    public Offreurs(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Offreurs(int id, String nom, String prenom, String dateInscription) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = dateInscription;
    }

    public Offreurs(int id, String nom, String prenom, String dateInscription, byte[] image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = dateInscription;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public byte[] getImage() { // Getter pour l'attribut image
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public void setImage(byte[] image) { // Setter pour l'attribut image
        this.image = image;
    }

    @Override
    public String toString() {
        return "Offreurs{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateInscription=" + dateInscription + ", image=" + image + '}';
    }
}
