package com.simpleteam.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Map;
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
     * Use TemplateEngine.
     */
    @Autowired
    private TemplateEngine templateEngine;

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

    /**
     * Sending HTML email.
     *
     * @param recipientData  Map with data.
     * @throws MessagingException
     */
    public void sendMail(final Map<String, String> recipientData) {
        log.debug("Try create message");

        // Prepare the evaluation context
        final Context ctx = new Context(Locale.ENGLISH);
        ctx.setVariable("email", recipientData.get("email"));
        ctx.setVariable("password", recipientData.get("password"));
        ctx.setVariable("uuid", recipientData.get("uuid"));

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = new MimeMessage(getSession());
        final MimeMessageHelper message;
        try {
            log.debug("Try send email");
            message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setSubject("Example HTML email with inline image");
            message.setTo(recipientData.get("email"));

            // Create the HTML body using Thymeleaf
            final String htmlContent = templateEngine.process("mail/regMail", ctx);
            message.setText(htmlContent, true); // true = isHtml

            // Send mail
            Transport.send(message.getMimeMessage());
            log.info("HTML email sending successfully");
        } catch (MessagingException e) {
            log.error("Failed send message", e);
        }
    }
}
