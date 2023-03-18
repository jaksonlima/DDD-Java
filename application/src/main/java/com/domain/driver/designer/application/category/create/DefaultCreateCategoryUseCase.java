package com.domain.driver.designer.application.category.create;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryCategory;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryCategory) {
        this.categoryCategory = Objects.requireNonNull(categoryCategory);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aCategory = Category.newCategory(
                aCommand.name(),
                aCommand.description(),
                aCommand.isActive()
        );

        final var notification = Notification.create();

        aCategory.validate(notification);

        return notification.hasError() ? API.Left(notification) : create(aCategory);
    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
        return API.Try(() -> categoryCategory.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }

}
