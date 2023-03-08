package com.domain.driver.designer.application.category.create;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryCategory;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryCategory) {
        this.categoryCategory = Objects.requireNonNull(categoryCategory);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final var aCategory = Category.newCategory(
                aCommand.aName(),
                aCommand.aDescription(),
                aCommand.isActive()
        );

        aCategory.validate(new ThrowsValidationHandler());

        return CreateCategoryOutput.from(categoryCategory.create(aCategory));
    }

}
