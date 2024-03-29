package com.domain.driver.designer.infrastructure.category.presenters;

import com.domain.driver.designer.application.category.retrieve.get.CategoryOutput;
import com.domain.driver.designer.application.category.retrieve.list.ListCategoryOutput;
import com.domain.driver.designer.infrastructure.category.models.ListCategoryResponse;
import com.domain.driver.designer.infrastructure.category.models.CategoryResponse;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output) {
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static ListCategoryResponse present(final ListCategoryOutput output) {
        return new ListCategoryResponse(
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
