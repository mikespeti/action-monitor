package com.betvictor.action_monitor.services.jms;

import com.betvictor.action_monitor.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.concurrent.Future;


public class TableChangeMessageConsumerTest extends AbstractTest {

    @Value("${server.port}")
    private int tomcatPort;

    @Autowired
    private TableChangeMessageConsumer messageConsumer;

    @Before
    public void setup() {
        Assert.assertNotNull(messageConsumer);
    }

    @Test
    public void testStartAndBuild() throws Exception {
        TextWebSocketHandler webSocketHandler = Mockito.mock(TextWebSocketHandler.class);
        WebSocketClient client = new StandardWebSocketClient();
        Future connectionFuture = client.doHandshake(webSocketHandler, "ws://localhost:" + tomcatPort);

        messageConsumer.receiveQueue("testMessage1");
        messageConsumer.receiveQueue("testMessage1");
        messageConsumer.receiveQueue("testMessage1");
        messageConsumer.receiveQueue("testMessage1");
        verify(webSocketHandler, times(4));
    }

}
