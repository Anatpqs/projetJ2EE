package Controller;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailTest {
    final String username = "j2ee.mail.confirm@gmail.com"; // Remplacez par votre adresse e-mail
    final String password = "vsca zhln bqvm atvt"; // Remplacez par votre mot de passe

    public void sendConfirmationEmail(String recipientEmail, String confirmationLink) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com"); // Changez selon votre fournisseur de messagerie
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Confirmation de votre compte");
            message.setText("Bienvenue sur notre site! Pour confirmer votre compte, veuillez cliquer sur le lien suivant:\n\n" + confirmationLink);

            Transport.send(message);

            System.out.println("Email envoyé avec succès!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
