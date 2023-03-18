package com.domain.driver.designer.domain.exceptions;

import com.domain.driver.designer.domain.AggregateRoot;
import com.domain.driver.designer.domain.Identifier;
import com.domain.driver.designer.domain.validation.Errors;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String aMessage, final List<Errors> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(final Class<? extends AggregateRoot<?>> anAggregateRoot,
                                  final Identifier aId) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregateRoot.getSimpleName(),
                aId.getValue()
        );

        return new NotFoundException(anError, Collections.emptyList());
    }

}
