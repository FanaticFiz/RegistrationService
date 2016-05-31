package com.simpleteam.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * JMS Message sender.
 *
 * @author Fiz
 * @version 0.1
 */
@Component
public class MessageSender {
    /**
     * Get logger.
     */
    private final Logger log = Logger.getLogger(MessageSender.class);

    /**
     * Use JMS template.
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Send.
     *
     * @param message messsage
     */
    public void send(final String message) {
        log.info("Try send message: " + message);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(final Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
}
