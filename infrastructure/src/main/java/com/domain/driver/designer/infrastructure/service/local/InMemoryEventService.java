package com.domain.driver.designer.infrastructure.service.local;

import com.domain.driver.designer.infrastructure.configuration.json.Json;
import com.domain.driver.designer.infrastructure.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InMemoryEventService implements EventService {

    private static final Logger LOG = LoggerFactory.getLogger(InMemoryEventService.class);

    @Override
    public void send(Object event) {
        LOG.info("Event was observed: {}", Json.writeValueAsString(event));
    }
}