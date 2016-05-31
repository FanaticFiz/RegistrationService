package com.simpleteam.jms;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message listener.
 */
@Component
public class SimpleMessageListener implements MessageListener {
    /**
     * Get logger.
     */
    private final Logger log = Logger.getLogger(SimpleMessageListener.class);

    @Override
    public void onMessage(final Message message) {
        log.info("Listener catch message: " + message);
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            log.error("Failed send message", e);
        }
    }

}
