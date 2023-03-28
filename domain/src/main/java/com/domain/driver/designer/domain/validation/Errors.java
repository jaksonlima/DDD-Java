package com.domain.driver.designer.domain.validation;

public record Errors(String message) {

    public static Errors with(final String aMessage) {
        return new Errors(aMessage);
    }

}
