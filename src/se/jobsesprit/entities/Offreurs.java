package se.jobsesprit.entities;

public class Offreurs {

    private int id;
    private String matricule;
    private String nom;
    private String prenom;
    private String dateInscription;
    private byte[] image;
 

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

    // Constructeur avec le nouvel attribut matricule
    public Offreurs(int id, String matricule, String nom, String prenom, String dateInscription, byte[] image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = dateInscription;
        this.image = image;
        this.matricule = matricule;
    }

    // Getter et Setter pour l'attribut matricule
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Offreurs{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateInscription=" + dateInscription + ", image=" + image + ", matricule=" + matricule + '}';
    }
}
