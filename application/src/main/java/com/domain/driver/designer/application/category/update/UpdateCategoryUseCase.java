package com.domain.driver.designer.application.category.update;

import com.domain.driver.designer.application.UseCase;
import com.domain.driver.designer.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends
        UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
