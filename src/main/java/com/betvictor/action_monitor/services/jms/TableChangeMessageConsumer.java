package com.betvictor.action_monitor.services.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class TableChangeMessageConsumer {
    static final Logger logger = LoggerFactory.getLogger(TableChangeMessageConsumer.class);

    // Name of the queue for entity change messages
    public static final String QUEUE_NAME = "entity.change.queue";

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * Listener method to an ActiveMQ queue, which will contain the table changes (insert/update/delete)
     * @param text - the message what will be send out tot he websocket channel
     */
    @JmsListener(destination = QUEUE_NAME)
    public void receiveQueue(String text) {
        // Pushes the formatted string to the websocket channel for the connected clients
        template.convertAndSend("/active-monitor/updates", text);
    }

}
