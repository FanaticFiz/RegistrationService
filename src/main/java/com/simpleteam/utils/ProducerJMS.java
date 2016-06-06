package com.simpleteam.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Map;

/**
 * Sender message to broker.
 */
@Component
public class ProducerJMS {
    /**
     * Get logger.
     */
    private final Logger log = org.apache.log4j.Logger.getLogger(ProducerJMS.class);

    /**
     * JmsTemplate.
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Queue.
     */
    @Autowired
    private Queue queue;

    /**
     * Send message to queue.
     * @param message send to broker
     */
    public void send(final Map<String, String> message) {
        log.debug("Try send message to JMS broker.");
        jmsTemplate.convertAndSend(queue, message);
    }

}
