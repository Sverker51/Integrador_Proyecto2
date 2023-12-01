/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author Miguel
 */
public class EnviarMail {    
    public void enviarCorreo(String destinatario, String asunto, String contenido){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.user", "mi@gmail.com");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        // Credenciales de autenticación
        final String username = "miguegarci4@gmail.com";
        final String password = "xsdljnanjdziavwd";        
        // Crear una sesión de correo
        Session session = Session.getDefaultInstance(properties);
        
        try {            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));            
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));// Configurar el destinatario
            message.setSubject(asunto);
            message.setContent(contenido, "text/html");
            
            //Transport.send(message);
            Transport transport = session.getTransport("smtp");
            transport.connect(username,password);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            System.out.println("Correo electrónico enviado con éxito.");
            JOptionPane.showMessageDialog(null, "Correo electrónico enviado con éxito.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Su correo no pudo ser enviado!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
