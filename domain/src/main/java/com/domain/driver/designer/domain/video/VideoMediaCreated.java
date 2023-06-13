package com.domain.driver.designer.domain.video;

import com.domain.driver.designer.domain.events.DomainEvent;
import com.domain.driver.designer.domain.utils.InstantUtils;

import java.time.Instant;

public record VideoMediaCreated(
        String resourceId,
        String filePath,
        Instant occurredOn
) implements DomainEvent {

    public VideoMediaCreated(final String resourceId, final String filePath) {
        this(resourceId, filePath, InstantUtils.now());
    }
}