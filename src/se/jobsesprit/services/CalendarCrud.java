/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.jobsesprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.jobsesprit.entities.CalendarActivity;
import se.jobsesprit.interfaces.InterfaceCRUD;
import se.jobsesprit.utils.MyConnection;

/**
 *
 * @author Dell
 */
public class CalendarCrud  implements InterfaceCRUD<CalendarActivity>{

    @Override
    public void ajouterEntities(CalendarActivity t) {
             Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "INSERT INTO CalendarActivity (date, clientName, mail,heure) VALUES (?, ?, ?,?)";
            PreparedStatement pst = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, t.getDate());
            pst.setString(2, t.getClientName());
            pst.setString(3, t.getMail());
          pst.setString(4, t.getHeure());


            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                t.setId(generatedKeys.getInt(1));
            }

            System.out.println("CalendarActivity ajoutée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout de CalendarActivity : " + ex.getMessage());
        }
    }

    @Override
    public List<CalendarActivity> listeDesEntities() {
        return null;

    }

    @Override
      public void modifierEntities(CalendarActivity calendarActivity) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "UPDATE CalendarActivity SET date = ?, clientName = ?, mail = ?,heure = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setString(1, calendarActivity.getDate());
            pst.setString(2, calendarActivity.getClientName());
            pst.setString(3, calendarActivity.getMail());
            pst.setString(4, calendarActivity.getHeure());
            pst.setInt(5, calendarActivity.getId());

            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("CalendarActivity modifiée avec succès !");
            } else {
                System.out.println("Aucune modification effectuée pour l'ID : " + calendarActivity.getId());
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de CalendarActivity : " + ex.getMessage());
        }
    }

    @Override
    public void supprimerEntities(CalendarActivity calendarActivity) {
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String requete = "DELETE FROM CalendarActivity WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.setInt(1, calendarActivity.getId());

            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("CalendarActivity supprimée avec succès !");
            } else {
                System.out.println("Aucune CalendarActivity trouvée avec l'ID : " + calendarActivity.getId());
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de CalendarActivity : " + ex.getMessage());
        }
    }
    public static ObservableList<CalendarActivity> getAllCalendarActivities() {
        ObservableList<CalendarActivity> calendarActivitiesList = FXCollections.observableArrayList();
        Connection connection = MyConnection.getInstance().getCnx();
        try {
            String query = "SELECT * FROM CalendarActivity";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                CalendarActivity calendarActivity = new CalendarActivity();
                calendarActivity.setId(resultSet.getInt("id"));
                calendarActivity.setDate(resultSet.getString("date"));
                calendarActivity.setClientName(resultSet.getString("clientName"));
                calendarActivity.setMail(resultSet.getString("mail"));
                calendarActivity.setHeure(resultSet.getString("heure"));
                calendarActivitiesList.add(calendarActivity);
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching Calendar Activities: " + ex.getMessage());
        }
        return calendarActivitiesList;
    }
    
    public static Map<Integer, Map<Integer, List<CalendarActivity>>> getCalendarActivitiesMonth(List<CalendarActivity> events) {
        Map<Integer, Map<Integer, List<CalendarActivity>>> eventsByYearAndMonth = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (CalendarActivity event : events) {
            try {
                // Trim the date string to remove leading and trailing spaces
                String trimmedDate = event.getDate().trim();
                // Parse the trimmed date string to get year and month
                LocalDate eventDate = LocalDate.parse(trimmedDate, formatter);
                int year = eventDate.getYear();
                int month = eventDate.getMonthValue();

                // Create the structure if it doesn't exist
                eventsByYearAndMonth.computeIfAbsent(year, k -> new HashMap<>());
                eventsByYearAndMonth.get(year).computeIfAbsent(month, k -> new ArrayList<>());

                // Add the event to the corresponding year and month
                eventsByYearAndMonth.get(year).get(month).add(event);
            } catch (Exception e) {
                System.out.println("Error processing event: " + event.getDate());
                e.printStackTrace();
                // Handle the exception or continue processing other records
            }
        }

        return eventsByYearAndMonth;
    }
    public static Map<Integer, Map<Integer, Map<Integer, List<CalendarActivity>>>> organizeEventsByYearMonthAndDay(List<CalendarActivity> events) {
    Map<Integer, Map<Integer, Map<Integer, List<CalendarActivity>>>> eventsByYearMonthAndDay = new HashMap<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    for (CalendarActivity event : events) {
        try {
            String trimmedDate = event.getDate().trim();
            LocalDate eventDate = LocalDate.parse(trimmedDate, formatter);
            int year = eventDate.getYear();
            int month = eventDate.getMonthValue();
            int dayOfMonth = eventDate.getDayOfMonth();

            // Create the structure if it doesn't exist
            eventsByYearMonthAndDay.computeIfAbsent(year, k -> new HashMap<>());
            eventsByYearMonthAndDay.get(year).computeIfAbsent(month, k -> new HashMap<>());
            eventsByYearMonthAndDay.get(year).get(month).computeIfAbsent(dayOfMonth, k -> new ArrayList<>());

            // Add the event to the corresponding year, month, and day
            eventsByYearMonthAndDay.get(year).get(month).get(dayOfMonth).add(event);
        } catch (Exception e) {
            System.out.println("Error processing event: " + event.getDate());
            e.printStackTrace();
            // Handle the exception or continue processing other records
        }
    }

    return eventsByYearMonthAndDay;
}

}

     
    
    
    
    
    
    
    
    

