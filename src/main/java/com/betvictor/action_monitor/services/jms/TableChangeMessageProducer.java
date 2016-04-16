package com.betvictor.action_monitor.services.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TableChangeMessageProducer {

    public enum DB_ACTIONS {
        INSERT {
            @Override
            public String toString() {
                return "was inserted into";
            }
        },
        UPDATE {
            @Override
            public String toString() {
                return "was updated in";
            }
        },
        DELETE {
            @Override
            public String toString() {
                return "was deleted from";
            }
        }
    }

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * Generic method to add message to the ActiveMQ queue
     * @param nvMap - contains properties from the status message
     */
    public void sendMessage(Map<String, Object> nvMap) {
        jmsTemplate.convertAndSend(TableChangeMessageConsumer.QUEUE_NAME, "timestamp=" + nvMap.get("timestamp") + " :: a row with ID=" + nvMap.get("id") + " " + nvMap.get("action") + " " + nvMap.get("table"));
    }


}
