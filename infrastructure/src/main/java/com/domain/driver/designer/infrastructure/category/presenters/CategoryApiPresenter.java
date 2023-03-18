package com.domain.driver.designer.infrastructure.category.presenters;

import com.domain.driver.designer.application.category.retrieve.get.CategoryOutput;
import com.domain.driver.designer.infrastructure.category.models.CategoryApiOutput;

public interface CategoryApiPresenter {

    static CategoryApiOutput present(final CategoryOutput output) {
        return new CategoryApiOutput(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

}
