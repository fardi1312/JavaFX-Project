package se.jobsesprit.services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;

public class Mail {

  public static void envoyer(String username, String password, String destinataire) throws javax.mail.MessagingException, AddressException {
    System.out.println("Entrain d'envoyer un email d'entretien !! ");

    // Etape 1 : Création de la session
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    // Session de messagerie avec l'authentification de l'utilisateur
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    javax.mail.Message message = prepareMessage(session, username, destinataire);
    try {
        javax.mail.Transport.send(message);
        System.out.println("Message envoyé à " + destinataire + " !!");
    } catch (javax.mail.MessagingException ex) {
        Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    private static javax.mail.Message prepareMessage(Session session, String username, String destinataire) throws MessagingException {
        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject("Entretien");
            message.setText("Merci de venir à notre entreprise pour l'entretien à propos de l'offre de stage que vous avez postulée.");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            throw new MessagingException("Erreur lors de la préparation du message.", ex);
        }
    }
}
