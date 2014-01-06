/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deadormi.util;

import com.deadormi.entity.User;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author francesco
 */
public class Mailer {

    public void sendEmail(User u, String subject, String text) {
        final String email_to = u.getEmail();
        final String username = "deadormi@gmail.com";
        final String password = "diomerda";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Nuovo messaggio
            Message message = new MimeMessage(session);
            // Mittente
            message.setFrom(new InternetAddress("deadormi@gmail.com"));
            // Destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_to));
            // Inserisce l'oggetto del messaggio
            message.setSubject(subject);
            // Inserisci il testo del messaggio
            message.setText(text);
            // Invia il messaggio
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
