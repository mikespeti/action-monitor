package com.betvictor.action_monitor.configs;

import com.betvictor.action_monitor.services.jms.TableChangeMessageProducer;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockedTableChangeMessageProducerConfig {

    @Primary
    @Bean(name = "mockMessageProducer")
    public TableChangeMessageProducer mockMessageProducer() {
        return Mockito.mock(TableChangeMessageProducer.class);
    }
}
