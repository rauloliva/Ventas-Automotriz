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
import com.automotriz.Constantes.Constants;
import com.automotriz.VO.MailVO;

public class Mail implements Runnable {

    private String destinatario;
    private String asunto;
    private String mensaje;
    private String fileName;
    private DataHandler dataHandler;

    /**
     * Initializes the object Mail with data provided by user
     *
     * @param destinatario where the email will be sent to
     * @param asunto The reason why the email is sending to destinatario
     * @param mensaje The message
     */
    public Mail(MailVO mail) {
        this.destinatario = mail.getDestinatario();
        this.asunto = mail.getAsunto();
        this.mensaje = mail.getMensaje();
    }

    /**
     * Prepares the files that will be sent along side with the email
     *
     * @param fileName The name of the file
     * @param filePath The full file's path
     */
    public void attachFiles(String fileName, String filePath) {
        this.fileName = fileName;
        this.dataHandler = new DataHandler(new FileDataSource(filePath));
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
        Session session = Session.getDefaultInstance(getSMTPProps());
        session.setDebug(false);
        BodyPart texto = new MimeBodyPart();
        try {
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
            message.setFrom(new InternetAddress(Constants.CORREO_PRINCIPAL));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setContent(multiParte);
            Transport t = session.getTransport("smtp");
            t.connect(Constants.CORREO_PRINCIPAL, Constants.PWD_PRINCIPAL);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            Logger.log("Mail to " + destinatario + " has been sent");
            success();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            Logger.error(e.getStackTrace());
            failure();
        }
    }

    /**
     * Gets the SMTP properties from application.properties
     * @return SMTP properties
     */
    private Properties getSMTPProps() {
        Logger.log("Reading SMTP Properties");
        Properties props = new Properties();
        props.put("mail.smtp.auth", ReadProperties.props.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", ReadProperties.props.getProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", ReadProperties.props.getProperty("mail.smtp.host"));
        props.put("mail.smtp.ssl.trust", ReadProperties.props.getProperty("mail.smtp.ssl.trust"));
        props.put("mail.smtp.port", ReadProperties.props.getProperty("mail.smtp.port"));
        return props;
    }

    /**
     * Show a message of success with the email that were sent
     */
    private void success() {
        Logger.log("The email was sent to '" + destinatario + "' successfuly");
        JOptionPane.showMessageDialog(null,
                ReadProperties.props.getProperty("mail.msg.success").replace("*", destinatario),
                ReadProperties.props.getProperty("mail.msg.success.title"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void failure() {
        Logger.log("The email was not sent to '" + destinatario + "', Check your internet connection");
        JOptionPane.showMessageDialog(null,
                ReadProperties.props.getProperty("mail.msg.failure"),
                ReadProperties.props.getProperty("mail.msg.failure.title"),
                JOptionPane.ERROR_MESSAGE);
    }
}
