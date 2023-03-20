package com.domain.driver.designer.application.category.update;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryID;

public record UpdateCategoryOutput(
        String id
) {

    public static UpdateCategoryOutput from(final String anId) {
        return new UpdateCategoryOutput(anId);
    }

    public static UpdateCategoryOutput from(final Category aCategory) {
        return new UpdateCategoryOutput(aCategory.getId().getValue());
    }

}
