package com.domain.driver.designer.application.category.retrieve.list;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryID;

import java.time.Instant;

public record ListCategoryOutput(
        CategoryID id,
        String name,
        String description,
        boolean isActive,
        Instant createdAt,
        Instant deletedAt
) {


    public static ListCategoryOutput from(final Category aCategory) {
        return new ListCategoryOutput(
                aCategory.getId(),
                aCategory.getName(),
                aCategory.getDescription(),
                aCategory.isActive(),
                aCategory.getCreatedAt(),
                aCategory.getDeletedAt()
        );
    }

}
