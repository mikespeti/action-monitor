package com.betvictor.action_monitor.services.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.betvictor.action_monitor.services.jms.TableChangeMessageConsumer.QUEUE_NAME;

@Component
public class TableChangeMessageProducer {
    static final Logger logger = LoggerFactory.getLogger(TableChangeMessageProducer.class);

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

    public TableChangeMessageProducer() {
        jsonConverter = new ObjectMapper();
    }

    @Autowired
    private JmsTemplate jmsTemplate;


    private ObjectMapper jsonConverter;

    /**
     * Generic method to add message to the ActiveMQ queue
     */
    public void sendMessage(TableChangeMessage changeMessage) {

        try {
            jmsTemplate.convertAndSend(QUEUE_NAME, jsonConverter.writeValueAsString(changeMessage));
        } catch (Exception e) {
            logger.error("Can't send message to queue=[" + QUEUE_NAME + "], because jmsTempalte was not autowired");
        }

    }
}
