package com.domain.driver.designer.application.category.create;

import com.domain.driver.designer.application.UseCase;
import com.domain.driver.designer.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {
}
