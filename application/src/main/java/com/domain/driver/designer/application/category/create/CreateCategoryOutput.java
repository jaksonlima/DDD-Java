package com.domain.driver.designer.application.category.create;

import com.domain.driver.designer.domain.category.Category;
import com.domain.driver.designer.domain.category.CategoryID;

public record CreateCategoryOutput(
        String id
) {

    public static CreateCategoryOutput from(final String anId) {
        return new CreateCategoryOutput(anId);
    }

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId().getValue());
    }

}
