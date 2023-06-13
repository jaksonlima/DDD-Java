package com.domain.driver.designer.infrastructure.configuration;

import com.domain.driver.designer.infrastructure.configuration.amqp.QueueProperties;
import com.domain.driver.designer.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.domain.driver.designer.infrastructure.service.EventService;
import com.domain.driver.designer.infrastructure.service.impl.RabbitEventService;
import com.domain.driver.designer.infrastructure.service.local.InMemoryEventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EventConfig {

    @Bean
    @VideoCreatedQueue
    @Profile({"development"})
    EventService localVideoCreatedEventService() {
        return new InMemoryEventService();
    }

    @Bean
    @VideoCreatedQueue
    @ConditionalOnMissingBean
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}