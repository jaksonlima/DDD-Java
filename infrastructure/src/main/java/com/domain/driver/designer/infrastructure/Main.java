package com.domain.driver.designer.infrastructure;

import com.domain.driver.designer.infrastructure.configuration.WebServerConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @RabbitListener(queues = "video.encoded.queue")
    void dynamicRabbitAmqp() {
        System.out.println("RUN....");
    }

}