package com.betvictor.action_monitor.services.jms;

import com.betvictor.action_monitor.AbstractTest;
import com.betvictor.action_monitor.configs.MockedTableChangeMessageConsumerConfig;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Import({MockedTableChangeMessageConsumerConfig.class})
public class TableChangeMessageProducerTest extends AbstractTest {

    @Autowired
    private TableChangeMessageProducer messageProducer;

    @Autowired
    private TableChangeMessageConsumer messageConsumer;

    @Before
    public void setup() {
        Assert.assertNotNull(messageProducer);
        Assert.assertNotNull(messageConsumer);
    }

    @Test
    public void testSendMessage() {
        messageProducer.sendMessage(new TableChangeMessage("xx123", System.nanoTime(), "testTable", TableChangeMessageProducer.DB_ACTIONS.INSERT));
        messageProducer.sendMessage(new TableChangeMessage("xx123", System.nanoTime(), "testTable", TableChangeMessageProducer.DB_ACTIONS.UPDATE));
        messageProducer.sendMessage(new TableChangeMessage("xx123", System.nanoTime(), "testTable", TableChangeMessageProducer.DB_ACTIONS.DELETE));

        verify(messageConsumer, times(3)).receiveQueue(Mockito.anyString());
    }
}
