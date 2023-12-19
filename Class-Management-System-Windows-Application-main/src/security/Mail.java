package security;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    
    public static void sendMail(String recipientEmailAddress, String emailSubject, String emailMessage){
        String senderEmailAddress = "classmanagements@gmail.com";
        String senderName = "Class Management Support";
        String senderEmailPassword = "SLqD6h.rJ!U-R3c";
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(senderEmailAddress, senderEmailPassword);
            }
        });
        
        try {
            Message message =  new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmailAddress,senderName));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmailAddress));
            message.setSubject(emailSubject);
            message.setContent(emailMessage, "text/html");
            
            Transport.send(message);
        } catch (Exception ex){
            System.out.println("Invalid Email");
        }
        
    }
}
