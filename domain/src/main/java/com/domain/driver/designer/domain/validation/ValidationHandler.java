package com.domain.driver.designer.domain.validation;

import java.util.List;

public interface ValidationHandler {

    ValidationHandler append(Errors anErrors);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation anValidation);

    List<Errors> getErrors();

    default boolean hasError() {
        return getErrors() != null && getErrors().isEmpty();
    }

    interface Validation {
        void validate();
    }

}
