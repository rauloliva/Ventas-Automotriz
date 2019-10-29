package com.automotriz.Datos;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import com.automotriz.Presentacion.ReadProperties;
import com.automotriz.logger.Logger;

public class Mail implements Runnable {

    private static final String correoPrincipal = ReadProperties.props.getProperty("main.mail");
    private static final String contraPrincipal = ReadProperties.props.getProperty("main.mail.pwd");

    private String[] destinatarios;
    private String asunto;
    private String mensaje;
    private String fileName;
    private String filePath;
    private DataHandler dataHandler;

    /**
     *
     * @param destinatarios
     * @param asunto
     * @param mensaje
     */
    public Mail(String destinatarios, String asunto, String mensaje) {
        this.destinatarios = destinatarios.split(",");
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    /**
     *
     * @param fileName
     * @param filePath
     */
    public void attachFiles(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
        dataHandler = new DataHandler(new FileDataSource(filePath));
    }

    /**
     * starts the thread for sending the mail
     */
    public void send() {
        this.start();
    }

    private void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);
        BodyPart texto = new MimeBodyPart();
        try {
            for (String address : destinatarios) {
                texto.setText(mensaje);
                MimeMultipart multiParte = new MimeMultipart();
                //if dataHandler is ready to attach a file
                if (dataHandler != null) {
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(dataHandler);
                    adjunto.setFileName(fileName);
                    multiParte.addBodyPart(adjunto);
                }

                multiParte.addBodyPart(texto);

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correoPrincipal));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
                message.setSubject(asunto);
                message.setContent(multiParte);
                Transport t = session.getTransport("smtp");
                t.connect(correoPrincipal, contraPrincipal);
                t.sendMessage(message, message.getAllRecipients());
                t.close();
                Logger.log("Mail to " + address + " has been sent");
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
        }
    }
}
