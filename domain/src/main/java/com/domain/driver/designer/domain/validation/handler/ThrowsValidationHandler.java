package com.domain.driver.designer.domain.validation.handler;

import com.domain.driver.designer.domain.exceptions.DomainException;
import com.domain.driver.designer.domain.validation.Errors;
import com.domain.driver.designer.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final Errors anErrors) {
        throw DomainException.with(anErrors);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public <T> T validate(final Validation<T> anValidation) {
        try {
            return anValidation.validate();
        } catch (Exception e) {
            throw DomainException.with(new Errors(e.getMessage()));
        }
    }

    @Override
    public List<Errors> getErrors() {
        return List.of();
    }

}
