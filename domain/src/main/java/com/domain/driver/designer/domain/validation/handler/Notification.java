package com.domain.driver.designer.domain.validation.handler;

import com.domain.driver.designer.domain.exceptions.DomainException;
import com.domain.driver.designer.domain.validation.Errors;
import com.domain.driver.designer.domain.validation.ValidationHandler;

import java.util.ArrayList;
import java.util.List;

public class Notification implements ValidationHandler {

    private final List<Errors> erros;

    private Notification(final List<Errors> erros) {
        this.erros = erros;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Errors anError) {
        return create().append(anError);
    }

    public static Notification create(final Throwable aThrowable) {
        return create().append(new Errors(aThrowable.getMessage()));
    }

    @Override
    public Notification append(final Errors anErrors) {
        this.erros.add(anErrors);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.erros.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> anValidation) {
        try {
            return anValidation.validate();
        } catch (final DomainException domainException) {
            this.erros.addAll(domainException.getErrors());
        } catch (final Throwable throwable) {
            this.erros.add(new Errors(throwable.getMessage()));
        }
        return null;
    }

    @Override
    public List<Errors> getErrors() {
        return this.erros;
    }

}
