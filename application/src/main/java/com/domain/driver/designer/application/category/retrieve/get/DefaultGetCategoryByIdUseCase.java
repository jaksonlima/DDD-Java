package com.domain.driver.designer.application.category.retrieve.get;

import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.exceptions.DomainException;
import com.domain.driver.designer.domain.validation.Errors;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anId) {
        final var id = CategoryID.from(anId);

        return this.categoryGateway.findById(id)
                .map(CategoryOutput::from)
                .orElseThrow(notFound((id)));
    }

    private static Supplier<DomainException> notFound(final CategoryID id) {
        return () -> DomainException.with(new Errors("Category with ID %s was not found".formatted(id.getValue())));
    }

}
