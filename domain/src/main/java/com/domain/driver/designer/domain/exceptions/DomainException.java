package com.domain.driver.designer.domain.exceptions;

import com.domain.driver.designer.domain.validation.Errors;

import java.util.List;

public class DomainException extends NoStackTraceException {

    private final List<Errors> anErrors;

    protected DomainException(final String aMessage, final List<Errors> anErrors) {
        super(aMessage);
        this.anErrors = anErrors;
    }

    public static DomainException with(final List<Errors> anErros) {
        return new DomainException("", anErros);
    }

    public static DomainException with(final Errors anErros) {
        return new DomainException(anErros.message(), List.of(anErros));
    }

    public List<Errors> getErrors() {
        return this.anErrors;
    }

}
