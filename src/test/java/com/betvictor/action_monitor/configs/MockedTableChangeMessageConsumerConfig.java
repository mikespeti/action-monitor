package com.betvictor.action_monitor.configs;

import com.betvictor.action_monitor.services.jms.TableChangeMessageConsumer;
import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockedTableChangeMessageConsumerConfig {

    @Primary
    @Bean(name = "mockMessageConsumer")
    public TableChangeMessageConsumer mockMessageProducer() {
        return Mockito.mock(TableChangeMessageConsumer.class);
    }
}
