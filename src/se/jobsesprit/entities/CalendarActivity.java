/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.entities;
import java.time.ZonedDateTime;

/**
 *
 * @author Dell
 */
public class CalendarActivity {
    private int id ;
 private String date;
    private String clientName;
    private String mail;
    private String heure;

     public CalendarActivity(){}

   

    public CalendarActivity(String date, String clientName, String mail, String heure) {
        this.date = date;
        this.clientName = clientName;
        this.mail = mail;
        this.heure = heure;
    }
     

    public CalendarActivity(String clientName, String mail, String heure) {
        this.clientName = clientName;
        this.mail = mail;
        this.heure = heure;
    }
     
 

    

    public CalendarActivity(int id, String date, String clientName, String serviceNo) {
        this.id = id;
        this.date = date;
        this.clientName = clientName;
        this.mail = mail;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

  

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + clientName + '\'' +
                ", serviceNo=" + mail +
                '}';
    }
}
   
