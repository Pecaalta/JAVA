/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import entities.Cliente;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Lucas
 */
public class Correo {
    
    
     public static void verificacionCorreoCliente(Cliente c){
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.smtp.host", "smtp.gmail.com");
            prop.setProperty("mail.smtp.starttls.enable", "true");
            prop.setProperty("mail.smtp.port", "587");
            prop.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(prop);
            
            String correoRemitente = "culturarteg4@gmail.com";
            String passwordRemitente = "equipog4";
            String correoReceptor = c.getEmail();
            String asunto = "[Monte] Confirmar direccion de correo ";
            String mensaje = "Bienvenido a Monte! \n\n"
                    + "Necesitamos que verifiques tu direccion de correo, para ello simplemente debes hacer click en el siguiente link: \n\n"
                    + "http://localhost:8080/monte-web/api/hola/confirmarCorreo?validador=" + c.getEmailVerificado() + "&email=" + c.getEmail();
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoRemitente));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor));
            message.setSubject(asunto);
            message.setText(mensaje);
            
            Transport t = session.getTransport("smtp");
            t.connect(correoRemitente, passwordRemitente);
            t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            
            
        } catch (Exception e) {
        } 
    }
    
}
