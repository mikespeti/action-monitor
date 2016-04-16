package com.betvictor.action_monitor;

import com.betvictor.action_monitor.configs.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;


@EnableAutoConfiguration
@Import({com.betvictor.action_monitor.controllers.RestController.class, WebSocketConfig.class})
@ComponentScan(basePackages = {"com/betvictor/action_monitor/domain", "com/betvictor/action_monitor/services", "com/betvictor/action_monitor/configs", "com/betvictor/action_monitor/database"})
@EnableJpaRepositories(basePackages = {"com/betvictor/action_monitor/repositories"})
@EntityScan(basePackages = {"com/betvictor/action_monitor/domain"})
@EnableJms
public class ActiveMonitorApplication {

    /*@RequestMapping("/")
    String home() {
        return "Hello World!";
    }*/

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ActiveMonitorApplication.class, args);
    }

}