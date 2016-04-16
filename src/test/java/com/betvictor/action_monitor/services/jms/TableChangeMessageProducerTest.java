package com.betvictor.action_monitor.services.jms;

import com.betvictor.action_monitor.AbstractTest;
import com.betvictor.action_monitor.configs.MockedTableChangeMessageConsumerConfig;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        messageProducer.sendMessage(ImmutableMap.<String, Object>builder()
                .put("timestamp", System.nanoTime())
                .put("action", TableChangeMessageProducer.DB_ACTIONS.INSERT)
                .put("id", "xx123")
                .put("table", "testTable")
                .build()
        );
        messageProducer.sendMessage(ImmutableMap.<String, Object>builder()
                .put("timestamp", System.nanoTime())
                .put("action", TableChangeMessageProducer.DB_ACTIONS.UPDATE)
                .put("id", "xx123")
                .put("table", "testTable")
                .build()
        );
        messageProducer.sendMessage(ImmutableMap.<String, Object>builder()
                .put("timestamp", System.nanoTime())
                .put("action", TableChangeMessageProducer.DB_ACTIONS.DELETE)
                .put("id", "xx123")
                .put("table", "testTable")
                .build()
        );

        verify(messageConsumer, times(3));
    }
}
