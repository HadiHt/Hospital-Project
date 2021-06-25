package Control;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {

    public void sendMail(String s, String b) {
        
    String host = "smtp.gmail.com";
    String from =  "h.hospitaltester@gmail.com";
    String pass =  "Hadi16611881";
    String to   =  b;
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", "true"); 
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
     
    
    try 
    {
        
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));

        InternetAddress[] toAddress = new InternetAddress[to.length()];
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        

        String content = s;
        
        message.setSubject("H.Hospital Work Update");
        message.setText(content);
        
        Transport transport = session.getTransport("smtp");
        transport.connect(host, from, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    } 
    catch (MessagingException mx) 
    {
        mx.printStackTrace();
    }

}}
