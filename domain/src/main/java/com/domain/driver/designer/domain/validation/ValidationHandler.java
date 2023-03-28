package com.domain.driver.designer.domain.validation;

import java.util.List;
import java.util.Optional;

public interface  ValidationHandler {

    ValidationHandler append(Errors anErrors);

    ValidationHandler append(ValidationHandler anHandler);

    <T> T validate(Validation<T> anValidation);

    List<Errors> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Optional<Errors> firstError() {
        return hasError() ? Optional.of(getErrors().get(0)) : Optional.empty();
    }

    interface Validation<T> {
        T validate();
    }

}
