package com.simpleteam.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * JMS Consumer.
 */
@Component
public class ConsumerJMS {
    /**
     * Get logger.
     */
    private final Logger log = org.apache.log4j.Logger.getLogger(ConsumerJMS.class);

    /**
     * Use simpleMailSender.
     */
    @Autowired
    private SimpleMailSender simpleMailSender;

    /**
     * Consume consumedMessage.
     *
     * @param consumedMessage Map - consumed consumedMessage
     */
    @JmsListener(destination = "email_queue")
    public void consumeMessage(final Map<String, String> consumedMessage) {
        log.info("Consume message...");

        Map<String, String> message = (Map<String, String>) consumedMessage;
        simpleMailSender.sendMail(message);

//        simpleMailSender.send(
//                message.get("email"),
//                "subject",
//                "Your password: " + message.get("password") + "<br>"
//                        + "Link: localhost:8080/confirm/" + message.get("uuid"));
    }
}
