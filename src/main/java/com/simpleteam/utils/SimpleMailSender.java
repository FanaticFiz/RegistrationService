package com.simpleteam.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Simple email sender.
 */
@Component
public class SimpleMailSender {
    /**
     * Get logger.
     */
    private final Logger log = Logger.getLogger(SimpleMailSender.class);

    /**
     * Use parameter.
     */
    @Value("${adminEmail}")
    private String adminEmail;

    /**
     * Use parameter.
     */
    @Value("${adminPassword}")
    private String adminPass;

    /**
     * @param recipient Example: mail@gmail.com
     * @param subject   mail subject
     * @param text      mail text
     */
    public void send(final String recipient, final String subject, final String text) {
        try {
            Message message = new MimeMessage(getSession());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            log.info("Success sending email.");
        } catch (MessagingException e) {
            log.info("Failed send email.", e);
        }
    }

    /**
     * Get session.
     *
     * @return session
     */
    private Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(adminEmail, adminPass);
                    }
                });
    }

}
