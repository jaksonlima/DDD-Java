package com.domain.driver.designer.application.category.update;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryGateway;
import com.domain.driver.designer.domain.category.CategoryID;
import com.domain.driver.designer.domain.exceptions.DomainException;
import com.domain.driver.designer.domain.validation.Errors;
import com.domain.driver.designer.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var id = CategoryID.from(aCommand.id());

        final var aCategory = this.categoryGateway.findById(id)
                .orElseThrow(notFound(id));

        final var notification = Notification.create();

        aCategory.update(aCommand.name(),
                        aCommand.description(),
                        aCommand.isActive())
                .validate(notification);

        return notification.hasError() ? API.Left(notification) : getUpdate(aCategory);
    }

    private Either<Notification, UpdateCategoryOutput> getUpdate(Category aCategory) {
        return API.Try(() -> categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::from);
    }

    private static Supplier<DomainException> notFound(final CategoryID id) {
        return () -> DomainException.with(new Errors("Category with ID %s was not found".formatted(id.getValue())));
    }

}
