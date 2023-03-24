package com.domain.driver.designer.domain.exceptions;

import com.domain.driver.designer.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }

}
