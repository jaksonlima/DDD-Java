package com.domain.driver.designer.application.video.create;

import com.domain.driver.designer.application.UseCase;

public abstract sealed class CreateVideoUseCase
        extends UseCase<CreateVideoCommand, CreateVideoOutput>
        permits DefaultCreateVideoUseCase {
}
