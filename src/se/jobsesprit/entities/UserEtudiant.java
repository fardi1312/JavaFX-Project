/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;

public class UserEtudiant {

    private int id;
    private int phone;
    private String role = "ETUDIANT";
    private String email;
    private String motDePasse;
    private String firstName;
    private String lastName;
    private String username;
    private String image;
    private int age;
    private double rate;

    public UserEtudiant() {
    }

    public UserEtudiant(int phone, String email, String motDePasse, String firstName, String lastName, String username, String image, int age, double rate) {
        this.phone = phone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.image = image;
        this.age = age;
        this.rate = rate;
    }

    public UserEtudiant(int id, int phone, String email, String motDePasse, String firstName, String lastName, String username, String image, int age, double rate) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.motDePasse = motDePasse;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.image = image;
        this.age = age;
        this.rate = rate;
    }


    
    
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "UserEtudiant{" + "id=" + id + ", phone=" + phone + ", role=" + role + ", email=" + email + ", motDePasse=" + motDePasse + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", image=" + image + ", age=" + age + ", rate=" + rate + '}';
    }
    
    
    
    
}
